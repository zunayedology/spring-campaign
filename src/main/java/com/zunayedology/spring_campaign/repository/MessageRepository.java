package com.zunayedology.spring_campaign.repository;

import com.zunayedology.spring_campaign.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
