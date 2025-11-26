package com.ragir.organizer.service.impl;

import com.ragir.organizer.exception.DuplicateException;
import com.ragir.organizer.exception.ResourceNotFoundException;
import com.ragir.organizer.exception.InternalServiceException;
import com.ragir.organizer.mapper.OrganizerMapper;
import com.ragir.organizer.model.dto.OrganizerListItemDTO;
import com.ragir.organizer.model.dto.OrganizerRequest;
import com.ragir.organizer.model.dto.OrganizerResponse;
import com.ragir.organizer.model.entity.Organizer;
import com.ragir.organizer.repository.OrganizerRepository;
import com.ragir.organizer.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository orgRepository;

    @Override
    public OrganizerResponse CreateOrganizer(OrganizerRequest req) {
        if (orgRepository.existsByEmail(req.getEmail()))
            throw new DuplicateException("Email already exists");

        if (orgRepository.existsByPhone(req.getPhone()))
            throw new DuplicateException("Phone already exists");

        Integer lastId = orgRepository.count() == 0 ? null : orgRepository.findAll(PageRequest.of(0, 1, Sort.by("id").descending()))
                .getContent().getFirst().getId();

        try {
            Organizer organizer = OrganizerMapper.toEntity(req, lastId);
            Organizer saved = orgRepository.save(organizer);
            return OrganizerMapper.toOrganizerResponse(saved);
        } catch (RuntimeException ex) {
            throw new InternalServiceException("Service error while creating organizer");
        }
    }

    @Override
    public OrganizerResponse GetOrganizerById(Integer id) {
        Organizer organizer = orgRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer Not found by id : " + id));
        return OrganizerMapper.toOrganizerResponse(organizer);
    }

    @Override
    public Page<OrganizerListItemDTO> SearchPartially(
            String q, int page, int size, String[] sort
    ) {
        if (sort.length > 2){
            throw new InternalServiceException("sort must contain field and direction");
        }

        Sort.Direction direction = sort[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        Page<Organizer> result = orgRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContaining(q, q, q, pageable);

        return result.map(o -> new OrganizerListItemDTO(o.getId(), o.getName(), o.getEmail()));
    }
}
