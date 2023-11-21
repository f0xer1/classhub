package com.classhub.api.service.impl;

import com.classhub.api.exeption.AdministratorNotFoundException;
import com.classhub.api.exeption.ChangeException;
import com.classhub.api.exeption.UserNotFoundException;
import com.classhub.api.model.users.Administrator;
import com.classhub.api.repository.AdministratorsRepository;
import com.classhub.api.repository.UserRepository;
import com.classhub.api.service.AdministratorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorsRepository administratorsRepository;
    private final UserRepository userRepository;

    @Override
    public void createAdmin(Long id) {
          administratorsRepository.save(new Administrator(id));
    }

    @Override
    public Optional<Administrator> getAdminById(Long id) {
        return administratorsRepository.findById(id);
    }

    @Override
    public Administrator getAdminByUsername(String username) {
        return userRepository.findByUsername(username)
                .flatMap(user -> administratorsRepository.findById(user.getId()))
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with username %s not found", username)));
    }

    @Override
    public Administrator editAdmin(Administrator administrator, String username) {

        try {
            Administrator existingAdministrator = getAdminByUsername(username);
            existingAdministrator.setPatronymic(administrator.getPatronymic());
            existingAdministrator.setLast_name(administrator.getLast_name());
            existingAdministrator.setFirst_name(administrator.getFirst_name());
            administratorsRepository.save(existingAdministrator);
            return existingAdministrator;
        } catch (EntityNotFoundException e) {
            throw new AdministratorNotFoundException("Administrator with username " + username + " not found");
        } catch (Exception e) {
            throw new ChangeException("Error editing administrator");
        }
    }

}
