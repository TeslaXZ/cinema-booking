package com.ec.cinema.helper.mappers;

import org.mapstruct.Mapper;

import com.ec.cinema.domain.dto.customer.CustomerDTO;
import com.ec.cinema.domain.entity.CustomerEntity;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    
    CustomerEntity toCustomer(CustomerDTO customerDto);
    CustomerDTO toCustomerDTO(CustomerEntity customer);
}
