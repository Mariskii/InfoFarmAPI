package com.infofarm.Bussines.Service;

import com.infofarm.Bussines.Dto.Request.BussinesDTO;
import com.infofarm.Bussines.Models.Bussines;
import com.infofarm.Exception.Errors.IdNotFoundException;

public interface BussinesService {

    Bussines getBussinesById(Long id) throws IdNotFoundException;

    void createBussines(BussinesDTO bussines);

    void updateBussines(BussinesDTO bussines, Long bussinesId) throws IdNotFoundException;

    void deleteBussines(Long id);
}
