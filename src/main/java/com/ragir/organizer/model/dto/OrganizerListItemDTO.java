package com.ragir.organizer.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizerListItemDTO {
    private Integer id;
    private String name;
    private String email;
}
