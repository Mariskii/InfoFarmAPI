package com.infofarm.Users.Models;

import com.infofarm.Bussines.Models.Bussines;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    @Column(name = "is_Enabled")
    private boolean isEnabled;
    @Column(name = "account_No_Expired")
    private boolean accountNonExpired;
    @Column(name = "account_No_Locked")
    private boolean accountNonLocked;
    @Column(name = "credentials_No_Expired")
    private boolean credentialsNonExpired;

    private String imageURL;

    private String image_public_id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();//El set no permite duplicados

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Bussines bussines;
}
