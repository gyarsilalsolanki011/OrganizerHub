package com.ragir.organizer.service;

import com.ragir.organizer.model.dto.OrganizerListItemDTO;
import com.ragir.organizer.model.dto.OrganizerRequest;
import com.ragir.organizer.model.dto.OrganizerResponse;
import org.springframework.data.domain.Page;

public interface OrganizerService {
    OrganizerResponse CreateOrganizer(OrganizerRequest organizerDTO);
    OrganizerResponse GetOrganizerById(Integer id);
    Page<OrganizerListItemDTO> SearchPartially(
            int page, int size, String[] sort
    );
}
