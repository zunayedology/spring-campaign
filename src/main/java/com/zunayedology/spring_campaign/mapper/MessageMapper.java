package com.zunayedology.spring_campaign.mapper;

import com.zunayedology.spring_campaign.dto.MessageDto;
import com.zunayedology.spring_campaign.entity.Message;

public class MessageMapper {
    public static Message mapToMessage(MessageDto messageDTO) {
        return new Message(
                messageDTO.getId(),
                messageDTO.getMessageBody(),
                messageDTO.isSent()
        );
    }

    public static MessageDto mapToMessageDTO(Message message) {
        return new MessageDto(
                message.getId(),
                message.getMessageBody(),
                message.isSent()
        );
    }
}
