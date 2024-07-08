package com.zunayedology.spring_campaign.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String customerName;
    private String phoneNumber;
}
