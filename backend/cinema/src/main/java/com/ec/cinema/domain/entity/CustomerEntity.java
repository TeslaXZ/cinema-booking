package com.ec.cinema.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "Customer")
@Table(name = "Customers")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity extends BaseEntity{

    @NotNull
    @Size(max = 20)
    @Column(unique = true)
    private String documentNumber;
    @NotNull
    @Size(max = 30)
    private String name;
    @NotNull
    @Size(max = 30)
    private String lastname;
    @NotNull
    @Min(0)
    private short age;
    @Size(max = 20)
    private String phoneNumber;
    @Size(max = 100)
    private String email;

}
