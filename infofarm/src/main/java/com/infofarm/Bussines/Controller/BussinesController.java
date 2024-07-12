package com.infofarm.Bussines.Controller;

import com.infofarm.Bussines.Dto.Request.BussinesDTO;
import com.infofarm.Bussines.Implementation.BussinesServiceImplementation;
import com.infofarm.Bussines.Models.Bussines;
import com.infofarm.Exception.Errors.IdNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bussines")
public class BussinesController {

    @Autowired
    BussinesServiceImplementation bussinesService;

    @GetMapping("get-bussines/{bussinesId}")
    public ResponseEntity<?> getBussinesById(@PathVariable Long bussinesId) throws IdNotFoundException {
        return ResponseEntity.ok(bussinesService.getBussinesById(bussinesId));
    }

    @PostMapping("create-bussines")
    public ResponseEntity<?> createBussines(@Valid @RequestBody BussinesDTO bussines) throws IdNotFoundException {
        bussinesService.createBussines(bussines);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update-bussines/{idBussines}")
    public ResponseEntity<?> updateBussines(@Valid @RequestBody BussinesDTO bussines, @PathVariable Long idBussines) throws IdNotFoundException {
        bussinesService.updateBussines(bussines, idBussines);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete-bussines/{idBussines}")
    public ResponseEntity<?> deleteBussines(@PathVariable Long idBussines) throws IdNotFoundException {
        bussinesService.deleteBussines(idBussines);
        return ResponseEntity.ok().build();
    }

}
