package com.infofarm.Users.Implementation;

import com.infofarm.Images.Implementation.CloudinaryServiceImpl;
import com.infofarm.Users.Dto.Request.AuthCreateUserDTO;
import com.infofarm.Users.Dto.Request.AuthLoginRequest;
import com.infofarm.Users.Dto.Response.AuthResponse;
import com.infofarm.Users.Models.Roles;
import com.infofarm.Users.Models.UserEntity;
import com.infofarm.Users.Repository.RolesRepository;
import com.infofarm.Users.Repository.UserRepository;
import com.infofarm.Users.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final String FOLDER_NAME = "userPics";

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CloudinaryServiceImpl cloudinaryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar usuario en la base de datos
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username+" not found"));

        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        user.getRoles()
                .forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleEnum().name())));

        user.getRoles()
                .stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName())));

        //Devolver Usuario que entiende spring
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                grantedAuthorities);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authentication(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse("User logged succes", accessToken);
        return authResponse;
    }

    public Authentication authentication(String username, String password) {
        //Buscar el usuario en base de datos
        UserDetails userDetails = loadUserByUsername(username);

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        //Comprobar contraseña
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        //Si las credenciales son correctas
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserDTO authCreateUser, MultipartFile file){
        //Obtener datos del usuario
        String username = authCreateUser.username();
        String password = authCreateUser.password();
        List<String> roles = authCreateUser.roleRequest().roleListName();

        Set<Roles> rolesEntities = new HashSet<>(rolesRepository.findRolesByRoleEnumIn(roles));

        if(rolesEntities.isEmpty()) {
            throw new IllegalArgumentException("Roles doesnt exist");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(rolesEntities)
                .isEnabled(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .build();

        if(file != null) {
            String[] imageData = cloudinaryService.uploadFile(file,FOLDER_NAME);
            userEntity.setImageURL(imageData[0]);
            userEntity.setImage_public_id(imageData[1]);
        }

        UserEntity userCreated = userRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        userCreated.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleEnum().name())));

        userCreated.getRoles()
                .stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName())));

        Authentication auth = new UsernamePasswordAuthenticationToken(userCreated.getUsername(), userCreated.getPassword(), grantedAuthorities);

        String accessToken = jwtUtils.createToken(auth);

        return new AuthResponse("User logged succes", accessToken);
    }
}
