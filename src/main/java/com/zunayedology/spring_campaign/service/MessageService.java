package com.zunayedology.spring_campaign.service;

import com.zunayedology.spring_campaign.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO> getAllMessages();
    MessageDTO getMessageById(Long id);
    MessageDTO generateMessage(String topic);
    MessageDTO updateMessage(Long id, MessageDTO messageDto);
    void deleteMessage(Long id);
}
