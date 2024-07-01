package com.zunayedology.spring_campaign.repository;

import com.zunayedology.spring_campaign.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
