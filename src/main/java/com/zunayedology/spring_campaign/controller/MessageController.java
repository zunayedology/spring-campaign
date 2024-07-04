package com.zunayedology.spring_campaign.controller;

import com.zunayedology.spring_campaign.dto.MessageDTO;
import com.zunayedology.spring_campaign.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
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
    public ResponseEntity<MessageDTO> generateMessage(@RequestParam String topic) {
        MessageDTO newMessage = messageService.generateMessage(topic);
        return ResponseEntity.ok(newMessage);
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
