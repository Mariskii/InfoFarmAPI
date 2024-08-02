package com.infofarm.Users.Service;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Users.Dto.Request.Tasks.TaskRequesteDTO;
import com.infofarm.Users.Dto.Response.Tasks.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(TaskRequesteDTO newTask) throws IdNotFoundException;
    TaskResponseDTO updateTask(TaskRequesteDTO task, Long id) throws IdNotFoundException;
    void deleteTask(Long id);
    TaskResponseDTO getTaskById(Long id) throws IdNotFoundException;
    List<TaskResponseDTO> getUserTasks(Long id);
}
