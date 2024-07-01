package com.zunayedology.spring_campaign.mapper;

import com.zunayedology.spring_campaign.dto.MessageDTO;
import com.zunayedology.spring_campaign.entity.Message;

public class MessageMapper {
    public static Message mapToMessage(MessageDTO messageDTO) {
        return new Message(
                messageDTO.getId(),
                messageDTO.getMessageBody(),
                messageDTO.isSent()
        );
    }
    public static MessageDTO mapToMessageDTO(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getMessageBody(),
                message.isSent()
        );
    }
}
