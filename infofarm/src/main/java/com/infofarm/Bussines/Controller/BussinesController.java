package com.infofarm.Bussines.Controller;

import com.infofarm.Bussines.Dto.Request.BussinesDTO;
import com.infofarm.Bussines.Dto.Response.BussinesResponseDTO;
import com.infofarm.Bussines.Implementation.BussinesServiceImplementation;
import com.infofarm.Bussines.Models.Bussines;
import com.infofarm.Exception.Errors.IdNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/bussines")
public class BussinesController {

    @Autowired
    BussinesServiceImplementation bussinesService;

    @GetMapping("get-bussines/{bussinesId}")
    public ResponseEntity<BussinesResponseDTO> getBussinesById(@PathVariable Long bussinesId) throws IdNotFoundException {
        return ResponseEntity.ok(bussinesService.getBussinesById(bussinesId));
    }

    @PostMapping("create-bussines")
    public ResponseEntity<BussinesResponseDTO> createBussines(@RequestParam("bussinesName") String bussines, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(bussinesService.createBussines(bussines, file));
    }

    @PutMapping("update-bussines/{idBussines}")
    public ResponseEntity<BussinesResponseDTO> updateBussines(@RequestParam("bussinesName") String bussinesName,
                                                   @PathVariable Long idBussines,
                                                   @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IdNotFoundException, IOException {
        bussinesService.updateBussines(bussinesName, idBussines, file);
        return ResponseEntity.ok(bussinesService.updateBussines(bussinesName, idBussines, file));
    }

    @DeleteMapping("delete-bussines/{idBussines}")
    public ResponseEntity<?> deleteBussines(@PathVariable Long idBussines) throws IdNotFoundException {
        bussinesService.deleteBussines(idBussines);
        return ResponseEntity.ok().build();
    }

}
