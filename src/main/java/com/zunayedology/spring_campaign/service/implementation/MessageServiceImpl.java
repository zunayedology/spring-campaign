package com.zunayedology.spring_campaign.service.implementation;

import com.zunayedology.spring_campaign.dto.MessageDTO;
import com.zunayedology.spring_campaign.entity.Message;
import com.zunayedology.spring_campaign.mapper.MessageMapper;
import com.zunayedology.spring_campaign.repository.MessageRepository;
import com.zunayedology.spring_campaign.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(MessageMapper::mapToMessageDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDTO getMessageById(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        return MessageMapper.mapToMessageDTO(message);
    }


//    @Override
//    public MessageDTO generateMessage(MessageDTO messageDto) {
//        Message message = MessageMapper.mapToMessage(messageDto);
//        message = messageRepository.save(message);
//        return MessageMapper.mapToMessageDTO(message);
//    }

    @Override
    public MessageDTO updateMessage(Long id, MessageDTO messageDto) {
        Message existingMessage = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        if (existingMessage.isSent()) {
            throw new RuntimeException("Cannot update a sent message");
        }
        existingMessage.setMessageBody(messageDto.getMessageBody());
        Message updatedMessage = messageRepository.save(existingMessage);
        return MessageMapper.mapToMessageDTO(updatedMessage);
    }

    @Override
    public void deleteMessage(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        if (message.isSent()) {
            throw new RuntimeException("Cannot delete a sent message");
        }
        messageRepository.delete(message);
    }

}
