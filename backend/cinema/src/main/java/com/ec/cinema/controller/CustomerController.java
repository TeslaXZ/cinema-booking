package com.ec.cinema.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ec.cinema.domain.dto.customer.CustomerDTO;
import com.ec.cinema.service.impl.CustomerServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Controlador clientes")
public class CustomerController {
    private final CustomerServiceImpl customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO CustomerDTO){
        CustomerDTO createdRoom = customerService.create(CustomerDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdRoom.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.findById(id));
    } 
    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody @Valid CustomerDTO CustomerDTO){
        return ResponseEntity.ok(customerService.update(CustomerDTO)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> deactiveCustomer(@PathVariable Long id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    


}
