package com.zunayedology.spring_campaign.service.implementation;

import com.zunayedology.spring_campaign.dto.MessageDto;
import com.zunayedology.spring_campaign.entity.Message;
import com.zunayedology.spring_campaign.mapper.MessageMapper;
import com.zunayedology.spring_campaign.repository.MessageRepository;
import com.zunayedology.spring_campaign.service.MessageService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatClient chatClient;

    public MessageServiceImpl(MessageRepository messageRepository
            , ChatClient.Builder chatClientBuilder) {
        this.messageRepository = messageRepository;
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public List<MessageDto> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(MessageMapper::mapToMessageDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDto getMessageById(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        return MessageMapper.mapToMessageDTO(message);
    }


    @Override
    public MessageDto generateMessage(String topic) {
        PromptTemplate promptTemplate = new PromptTemplate(
                "Write a wish message for a customer on the occasion of "
                        + topic + "from Bangladesh-based IT company RedDot Digital Limited"
        );
        Prompt prompt = promptTemplate.create();

        ChatClient.ChatClientRequest.CallPromptResponseSpec
                responseSpec = chatClient.prompt(prompt).call();
        List<Generation> responses = responseSpec.chatResponse().getResults();

        Message newMessage = new Message();
        newMessage.setMessageBody(responses.get(0).getOutput().getContent());
        newMessage = messageRepository.save(newMessage);
        return MessageMapper.mapToMessageDTO(newMessage);
    }

    @Override
    public MessageDto updateMessage(Long id, MessageDto messageDto) {
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
