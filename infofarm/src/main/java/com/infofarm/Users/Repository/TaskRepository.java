package com.infofarm.Users.Repository;

import com.infofarm.Users.Models.Tasks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Tasks, Long> {
    List<Tasks> findByUser_Id(Long title);
}
