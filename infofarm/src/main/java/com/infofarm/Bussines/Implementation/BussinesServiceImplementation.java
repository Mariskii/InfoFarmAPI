package com.infofarm.Bussines.Implementation;

import com.infofarm.Bussines.Dto.Request.BussinesDTO;
import com.infofarm.Bussines.Models.Bussines;
import com.infofarm.Bussines.Repository.BussinesRepository;
import com.infofarm.Bussines.Service.BussinesService;
import com.infofarm.Exception.Errors.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BussinesServiceImplementation implements BussinesService {

    @Autowired
    BussinesRepository bussinesRepository;

    @Override
    public Bussines getBussinesById(Long id) throws IdNotFoundException {
        return bussinesRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Bussines with "+id+" doesnt exists"));
    }

    @Override
    public void createBussines(BussinesDTO bussines) {
        Bussines newBussines = Bussines.builder()
                .creationDate(new Date())
                .name(bussines.getName())
                .logoURL(bussines.getLogoURL())
                .build();

        bussinesRepository.save(newBussines);
    }

    @Override
    public void updateBussines(BussinesDTO bussines, Long bussinesId) throws IdNotFoundException {
        Bussines actualBussines = bussinesRepository.findById(bussinesId).orElseThrow(() -> new IdNotFoundException("Bussines with "+bussinesId+" doesnt exists"));

        actualBussines.setName(bussines.getName());
        actualBussines.setLogoURL(bussines.getLogoURL());
        bussinesRepository.save(actualBussines);
    }

    @Override
    public void deleteBussines(Long id) {
        bussinesRepository.deleteById(id);
    }
}
