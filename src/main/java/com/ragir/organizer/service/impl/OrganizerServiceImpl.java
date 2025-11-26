package com.ragir.organizer.service.impl;

import com.ragir.organizer.exception.ResourceNotFoundException;
import com.ragir.organizer.exception.InternalServiceException;
import com.ragir.organizer.mapper.OrganizerMapper;
import com.ragir.organizer.model.dto.OrganizerListItemDTO;
import com.ragir.organizer.model.dto.OrganizerRequest;
import com.ragir.organizer.model.dto.OrganizerResponse;
import com.ragir.organizer.model.entity.Organizer;
import com.ragir.organizer.repository.OrganizerRepository;
import com.ragir.organizer.service.OrganizerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OrganizerServiceImpl implements OrganizerService {
    private final OrganizerRepository organizerRepository;

    public OrganizerServiceImpl(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Override
    public OrganizerResponse CreateOrganizer(OrganizerRequest organizerDTO) {
        try {
            Organizer organizer = OrganizerMapper.toEntity(organizerDTO);
            organizerRepository.save(organizer);
            return OrganizerMapper.toOrganizerResponse(organizer);
        } catch (RuntimeException ex) {
            throw new InternalServiceException("Service error while creating organizer");
        }
    }

    @Override
    public OrganizerResponse GetOrganizerById(Integer id) {
        Organizer organizer = organizerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer Not found by id : " + id));
        return OrganizerMapper.toOrganizerResponse(organizer);
    }

    @Override
    public Page<OrganizerListItemDTO> SearchPartially(
            int page, int size, String[] sort
    ) {
        if (sort.length > 2){
            throw new InternalServiceException("sort must contain field and direction");
        }

        Sort.Direction direction = sort[0].equalsIgnoreCase("des")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, direction);
        String q = sort.length == 2 ? sort[1] : "";
        Page<Organizer> result = organizerRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContaining(q, q, q, pageable);

        return result.map(o -> new OrganizerListItemDTO(o.getId(), o.getName(), o.getEmail()));
    }
}
