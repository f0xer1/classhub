package com.classhub.api.service.impl;

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
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findBySubject(Long id) {
        return taskRepository.findByTeachingSubject(
                teachingSubjectService.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Teaching subject not found with id: " + id))
        );
    }

    @Override
    public Optional<Task> findById(Long taskId) {
        return taskRepository.findById(taskId);
    }


}
