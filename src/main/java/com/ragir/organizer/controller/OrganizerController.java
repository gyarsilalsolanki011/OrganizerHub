package com.ragir.organizer.controller;

import com.ragir.organizer.model.dto.OrganizerListItemDTO;
import com.ragir.organizer.model.dto.OrganizerRequest;
import com.ragir.organizer.model.dto.OrganizerResponse;
import com.ragir.organizer.service.OrganizerService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0,desc") String[] sort
    ) {
        return ResponseEntity.ok(organizerService.SearchPartially(page, size, sort));
    }
}
