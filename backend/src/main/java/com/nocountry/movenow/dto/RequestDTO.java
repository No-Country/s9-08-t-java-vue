package com.nocountry.movenow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    @NotBlank(message = "Password cannot be blank")
    String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email")
    String email;


}
