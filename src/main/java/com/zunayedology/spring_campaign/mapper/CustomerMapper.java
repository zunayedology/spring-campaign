package com.zunayedology.spring_campaign.mapper;


import com.zunayedology.spring_campaign.dto.CustomerDto;
import com.zunayedology.spring_campaign.entity.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerDto customerDTO) {
        return new Customer(
                customerDTO.getId(),
                customerDTO.getCustomerName(),
                customerDTO.getPhoneNumber()
        );
    }

    public static CustomerDto mapToCustomerDTO(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getCustomerName(),
                customer.getPhoneNumber()
        );
    }
}
