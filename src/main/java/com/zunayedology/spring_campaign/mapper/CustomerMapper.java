package com.zunayedology.spring_campaign.mapper;


import com.zunayedology.spring_campaign.dto.CustomerDTO;
import com.zunayedology.spring_campaign.entity.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getId(),
                customerDTO.getCustomerName(),
                customerDTO.getPhoneNumber()
        );
    };
    public static CustomerDTO mapToCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getCustomerName(),
                customer.getPhoneNumber()
        );
    }
}
