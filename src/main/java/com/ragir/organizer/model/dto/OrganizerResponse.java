package com.ragir.organizer.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerResponse {
    private Integer Id;
    private String name;
    private String email;
    private String phone;
    private String businessType;
    private String status;
    private String createdAt;
    private String updatedAt;
}
