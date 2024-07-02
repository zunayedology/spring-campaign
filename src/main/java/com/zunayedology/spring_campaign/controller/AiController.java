package com.zunayedology.spring_campaign.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AiController {

    private final ChatClient chatClient;
    public AiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai")
    public String ai() {
        String text = "Write a wish message";
        PromptTemplate promptTemplate = new PromptTemplate(text);
        Prompt prompt = promptTemplate.create();
        ChatClient.ChatClientRequest.CallPromptResponseSpec responseSpec = chatClient.prompt(prompt).call();
        List<Generation> responses = responseSpec.chatResponse().getResults();
        return responses.get(0).getOutput().getContent();
    }
}
