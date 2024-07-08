package com.zunayedology.spring_campaign.controller;

import com.zunayedology.spring_campaign.dto.MessageDto;
import com.zunayedology.spring_campaign.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageDto> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable Long id) {
        MessageDto messageDTO = messageService.getMessageById(id);
        return ResponseEntity.ok(messageDTO);
    }

    @PostMapping("/ai")
    public ResponseEntity<MessageDto> generateMessage(@RequestParam String topic) {
        MessageDto newMessage = messageService.generateMessage(topic);
        return ResponseEntity.ok(newMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updateMessage(@PathVariable Long id,
                                                    @RequestBody MessageDto messageDTO) {
        MessageDto updatedMessage = messageService.updateMessage(id, messageDTO);
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
