package com.zunayedology.spring_campaign.service.implementation;

import com.zunayedology.spring_campaign.dto.CustomerDTO;
import com.zunayedology.spring_campaign.entity.Customer;
import com.zunayedology.spring_campaign.mapper.CustomerMapper;
import com.zunayedology.spring_campaign.repository.CustomerRepository;
import com.zunayedology.spring_campaign.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::mapToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return CustomerMapper.mapToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO);
        customer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        existingCustomer.setCustomerName(customerDTO.getCustomerName());
        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return CustomerMapper.mapToCustomerDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
