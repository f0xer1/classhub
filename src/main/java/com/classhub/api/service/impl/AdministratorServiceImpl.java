package com.classhub.api.service.impl;

import com.classhub.api.exeption.ChangeException;
import com.classhub.api.exeption.UserNotFoundException;
import com.classhub.api.model.Administrator;
import com.classhub.api.model.dto.AdministratorDto;
import com.classhub.api.model.mapper.AdministratorMapper;
import com.classhub.api.repository.AdministratorsRepository;
import com.classhub.api.repository.UserRepository;
import com.classhub.api.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorsRepository administratorsRepository;
    private final AdministratorMapper administratorMapper;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<String> createAdmin(Long id) {
        try {
            administratorsRepository.save(new Administrator(id));
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @Override
    public Optional<Administrator> getAdminById(Long id) {
        return administratorsRepository.findById(id);
    }

    @Override
    public Administrator getAdminByUsername(String username) {
        Long id =userRepository.findByUsername(username).get().getId();
        if (!administratorsRepository.existsById(id)) {
            throw new UserNotFoundException(
                    "User with username %s not found".formatted(username));
        }
        return administratorsRepository.findById(id).get();
    }

    @Override
    public String editAdmin(AdministratorDto administratorDto) {
        try {
            Administrator existingAdministrator = getAdminByUsername(administratorDto.getUsername());
            Administrator updatedAdministrator = administratorMapper.updateAdministratorFromDTO(administratorDto,
                    existingAdministrator);
            administratorsRepository.save(updatedAdministrator);
            return "Success";
        } catch (UserNotFoundException e) {
            return "User not found: " + e.getMessage();
        } catch (ChangeException e) {
            return "Change error: " + e.getMessage();
        } catch (Exception e) {
            return "An error occurred while processing the request";
        }

    }
}
