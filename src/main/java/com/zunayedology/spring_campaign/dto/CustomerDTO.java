package com.zunayedology.spring_campaign.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String customerName;
    private String phoneNumber;
}
