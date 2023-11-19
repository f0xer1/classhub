package com.classhub.api.service;

import com.classhub.api.model.dto.TaskDto;
import com.classhub.api.model.subjects.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
   Task addTask(TaskDto taskDto);

   List<Task> findBySubject(Long id);

   Optional<Task> findById(Long taskId);
}
