package com.infofarm.Bussines.Service;

import com.infofarm.Bussines.Dto.Response.BussinesResponseDTO;
import com.infofarm.Bussines.Models.Bussines;
import com.infofarm.Exception.Errors.IdNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BussinesService {

    BussinesResponseDTO getBussinesById(Long id) throws IdNotFoundException;

    BussinesResponseDTO createBussines(String bussinesName, MultipartFile file) throws IOException;

    BussinesResponseDTO updateBussines(String bussinesName, Long bussinesId, MultipartFile file) throws IdNotFoundException, IOException;

    void deleteBussines(Long id);
}
