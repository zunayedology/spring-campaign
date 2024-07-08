package com.zunayedology.spring_campaign.service;

import com.zunayedology.spring_campaign.dto.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAllMessages();

    MessageDto getMessageById(Long id);

    MessageDto generateMessage(String topic);

    MessageDto updateMessage(Long id, MessageDto messageDto);

    void deleteMessage(Long id);
}
