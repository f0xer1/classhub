package com.classhub.api.model.user;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCreationDto {
    @NotBlank(message = "Specify username")
    @Pattern(regexp = "^\\w+$", message = "You can use a-z, 0-9 and underscores")
    @Size(min = 4, max = 32, message = "Enter at least 4 and less than 32 characters")
    private String username;


    @NotBlank(message = "Specify password")
    @Size(min = 6, max = 32, message = "Enter at least 6 and less than 32 characters")
    private String pwd;

    private String role;
}
