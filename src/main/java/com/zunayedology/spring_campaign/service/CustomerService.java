package com.zunayedology.spring_campaign.service;

import com.zunayedology.spring_campaign.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createCustomer(CustomerDto customerDTO);

    CustomerDto updateCustomer(Long id, CustomerDto customerDTO);

    void deleteCustomer(Long id);
}
