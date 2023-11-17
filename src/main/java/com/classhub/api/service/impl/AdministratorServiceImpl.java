package com.classhub.api.service.impl;

import com.classhub.api.exeption.ChangeException;
import com.classhub.api.exeption.UserNotFoundException;
import com.classhub.api.model.users.Administrator;
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
    public ResponseEntity<String>  editAdmin(AdministratorDto administratorDto) {
        try {
            Administrator existingAdministrator = getAdminByUsername(administratorDto.getUsername());
            Administrator updatedAdministrator = administratorMapper.updateAdministratorFromDTO(administratorDto,
                    existingAdministrator);
            administratorsRepository.save(updatedAdministrator);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ChangeException e) {
            return new ResponseEntity<>("Change error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
