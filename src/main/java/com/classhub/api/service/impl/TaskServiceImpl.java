package com.classhub.api.service.impl;

import com.classhub.api.model.dto.TaskDto;
import com.classhub.api.model.subjects.Task;
import com.classhub.api.repository.TaskRepository;
import com.classhub.api.service.TaskService;
import com.classhub.api.service.TeachingSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TeachingSubjectService teachingSubjectService;

    @Override
    public Task addTask(TaskDto taskDto) {
        return taskRepository.save(mapToTask(taskDto));
    }

    @Override
    public List<Task> findBySubject(Long id) {
        return  taskRepository.findByTeachingSubject(teachingSubjectService.findById(id).get());
    }

    @Override
    public Optional<Task> findById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public Task mapToTask(TaskDto taskDto) {
        Task task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setTitle(taskDto.getTitle());
        task.setTeachingSubject(teachingSubjectService.findById(taskDto.getTeaching_subject_id()).get());
        return task;
    }
}
