package com.zunayedology.spring_campaign.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Long id;
    private String messageBody;
    private boolean isSent;
}
