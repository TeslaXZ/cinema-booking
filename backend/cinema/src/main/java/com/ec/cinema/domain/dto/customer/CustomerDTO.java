package com.ec.cinema.domain.dto.customer;

import com.ec.cinema.domain.dto.BaseDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerDTO extends BaseDTO {

    @NotBlank
    @Size(max = 20, min = 8)
    private String documentNumber;
    @NotEmpty
    @Size(max = 30, min = 1)
    private String name;
    @NotEmpty
    @Size(max = 30, min = 1)
    private String lastname;
    @NotNull
    @Min(value = 1)
    private Short age;
    @Size(max = 20, min = 0)
    private String phoneNumber;
    @Email
    private String email;

}
