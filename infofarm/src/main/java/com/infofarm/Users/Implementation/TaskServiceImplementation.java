package com.infofarm.Users.Implementation;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Users.Dto.Request.Tasks.TaskRequesteDTO;
import com.infofarm.Users.Dto.Response.Tasks.TaskResponseDTO;
import com.infofarm.Users.Models.Tasks;
import com.infofarm.Users.Models.UserEntity;
import com.infofarm.Users.Repository.TaskRepository;
import com.infofarm.Users.Repository.UserRepository;
import com.infofarm.Users.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImplementation(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TaskResponseDTO createTask(TaskRequesteDTO newTask) throws IdNotFoundException {

        UserEntity user = userRepository.findById(newTask.userId())
                .orElseThrow(() -> new IdNotFoundException("User not found"));

        Tasks task = Tasks.builder()
                .completed(newTask.completed())
                .description(newTask.description())
                .priority(newTask.priority())
                .title(newTask.name())
                .user(user)
                .build();

        task = taskRepository.save(task);

        return createTaskResponseDTO(task);
    }

    @Override
    public TaskResponseDTO updateTask(TaskRequesteDTO task, Long id) throws IdNotFoundException {

        Tasks actualTask = taskRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Task not found"));

        actualTask.setCompleted(task.completed());
        actualTask.setDescription(task.description());
        actualTask.setPriority(task.priority());
        actualTask.setTitle(task.name());
        actualTask.setLimit_date(task.limit_date());

        actualTask = taskRepository.save(actualTask);

        return createTaskResponseDTO(actualTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskResponseDTO getTaskById(Long id) throws IdNotFoundException{

        Tasks task = taskRepository.findById(id).orElseThrow(() ->
                new IdNotFoundException("Task with the id: "+id+" not found")
        );

        return createTaskResponseDTO(task);
    }

    @Override
    public List<TaskResponseDTO> getUserTasks(Long id) {
        return taskRepository.findByUser_Id(id).stream().map(this::createTaskResponseDTO).toList();
    }

    private TaskResponseDTO createTaskResponseDTO(Tasks task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .name(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .priority(task.getPriority())
                .limit_date(task.getLimit_date())
                .build();
    }
}
