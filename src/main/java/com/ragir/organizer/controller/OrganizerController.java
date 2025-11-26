package com.ragir.organizer.controller;

import com.ragir.organizer.model.dto.OrganizerListItemDTO;
import com.ragir.organizer.model.dto.OrganizerRequest;
import com.ragir.organizer.model.dto.OrganizerResponse;
import com.ragir.organizer.service.OrganizerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/organizers")
public class OrganizerController {
    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping
    public ResponseEntity<OrganizerResponse> CreateOrganizer(@RequestBody OrganizerRequest organizerDTO) {
        OrganizerResponse response = organizerService.CreateOrganizer(organizerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizerResponse> FindById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok(organizerService.GetOrganizerById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<OrganizerListItemDTO>> SearchPartially(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String[] sort
    ) {
        return ResponseEntity.ok(organizerService.SearchPartially(page, size, sort));
    }
}
