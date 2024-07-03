package com.zunayedology.spring_campaign.controller;

import com.zunayedology.spring_campaign.dto.MessageDTO;
import com.zunayedology.spring_campaign.entity.Message;
import com.zunayedology.spring_campaign.repository.MessageRepository;
import com.zunayedology.spring_campaign.service.MessageService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    private final ChatClient chatClient;
    private final MessageService messageService;

    public MessageController(MessageService messageService,
                             ChatClient.Builder chatClientBuilder) {
        this.messageService = messageService;
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public List<MessageDTO> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable Long id) {
        MessageDTO messageDTO  = messageService.getMessageById(id);
        return ResponseEntity.ok(messageDTO);
    }

    @PostMapping("/ai")
    public String generateMessage(@RequestParam String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("Write a poem about" + topic);
        Prompt prompt = promptTemplate.create();
        ChatClient.ChatClientRequest.CallPromptResponseSpec responseSpec = chatClient.prompt(prompt).call();
        List<Generation> responses = responseSpec.chatResponse().getResults();
        Message message = new Message();
        message.setMessageBody(responses.get(0).getOutput().getContent());
        messageRepository.save(message);

        return responses.get(0).getOutput().getContent();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> updateMessage(@PathVariable Long id,
                                                    @RequestBody MessageDTO messageDTO) {
        MessageDTO updatedMessage = messageService.updateMessage(id, messageDTO);
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
