package com.ec.cinema.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ec.cinema.domain.dto.customer.CustomerDTO;
import com.ec.cinema.domain.entity.CustomerEntity;
import com.ec.cinema.helper.mappers.CustomerMapper;
import com.ec.cinema.repository.CustomerRepository;
import com.ec.cinema.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream().map(customerMapper :: toCustomerDTO).toList();
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customer = customerRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No customer whith id: " + id));
        return customerMapper.toCustomerDTO(customer);
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        CustomerEntity customerCreated = customerRepository.save(customerMapper.toCustomer(customerDTO));
        return customerMapper.toCustomerDTO(customerCreated); 
    }

    @Override
    @Transactional
    public CustomerDTO update(CustomerDTO customerDTO) {
       CustomerEntity customer = customerMapper.toCustomer(findById(customerDTO.getId()));
       customer.setPhoneNumber(customerDTO.getPhoneNumber());
       customer.setEmail(customerDTO.getEmail());
       return customerMapper.toCustomerDTO(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CustomerEntity customer = customerMapper.toCustomer(findById(id));
        customer.setStatus(false);
    }

}
