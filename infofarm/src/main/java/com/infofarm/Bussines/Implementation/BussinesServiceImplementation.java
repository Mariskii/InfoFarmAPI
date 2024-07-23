package com.infofarm.Bussines.Implementation;

import com.infofarm.Bussines.Dto.Response.BussinesResponseDTO;
import com.infofarm.Bussines.Models.Bussines;
import com.infofarm.Bussines.Repository.BussinesRepository;
import com.infofarm.Bussines.Service.BussinesService;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Images.Implementation.CloudinaryServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class BussinesServiceImplementation implements BussinesService {

    @Autowired
    BussinesRepository bussinesRepository;

    @Autowired
    CloudinaryServiceImpl cloudinaryService;

    @Override
    public BussinesResponseDTO getBussinesById(Long id) throws IdNotFoundException {
        Bussines bussines = bussinesRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Bussines with "+id+" doesnt exists"));

        return BussinesResponseDTO.builder()
                .id(bussines.getId())
                .name(bussines.getName())
                .logoURL(bussines.getLogoURL())
                .build();
    }

    @Override
    @Transactional
    public BussinesResponseDTO createBussines(String bussinesName, MultipartFile file) throws IOException {

        Bussines newBussines = Bussines.builder()
                .creationDate(new Date())
                .name(bussinesName)
                .build();

        if(file != null) {
            String[] imageData = cloudinaryService.uploadFile(file,"bussines_logos");

            newBussines.setLogoURL(imageData[0]);
            newBussines.setImage_public_id(imageData[1]);
        }

        newBussines = bussinesRepository.save(newBussines);

        return BussinesResponseDTO.builder()
                .id(newBussines.getId())
                .name(newBussines.getName())
                .logoURL(newBussines.getLogoURL())
                .build();
    }

    @Override
    @Transactional
    public BussinesResponseDTO updateBussines(String bussinesName, Long bussinesId, MultipartFile file) throws IdNotFoundException {
        Bussines actualBussines = bussinesRepository.findById(bussinesId).orElseThrow(() -> new IdNotFoundException("Bussines with "+bussinesId+" doesnt exists"));

        actualBussines.setName(bussinesName);

        if(!file.isEmpty()) {
            cloudinaryService.deleteFile(actualBussines.getImage_public_id());

            String[] imageData = cloudinaryService.uploadFile(file,"bussines_logos");

            actualBussines.setLogoURL(imageData[0]);
            actualBussines.setImage_public_id(imageData[1]);
        }

        actualBussines.setName(bussinesName);

        bussinesRepository.save(actualBussines);

        return BussinesResponseDTO.builder()
                .id(actualBussines.getId())
                .name(actualBussines.getName())
                .logoURL(actualBussines.getLogoURL())
                .build();
    }

    @Override
    @Transactional
    public void deleteBussines(Long id) {
        cloudinaryService.deleteFile(bussinesRepository.findById(id).get().getImage_public_id());
        bussinesRepository.deleteById(id);
    }
}
