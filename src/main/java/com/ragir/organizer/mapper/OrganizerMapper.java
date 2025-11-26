package com.ragir.organizer.mapper;

import com.ragir.organizer.model.dto.OrganizerRequest;
import com.ragir.organizer.model.dto.OrganizerResponse;
import com.ragir.organizer.model.entity.Organizer;
import com.ragir.organizer.util.OrganizerCodeGenerator;

public class OrganizerMapper {
    public static Organizer toEntity(OrganizerRequest request){
        Organizer organizer = new Organizer();
        organizer.setOrganizerCode(OrganizerCodeGenerator.GenerateOrganizerCode());
        organizer.setName(request.getName());
        organizer.setEmail(request.getEmail());
        organizer.setPhone(request.getPhone());
        organizer.setBusinessType(request.getBusinessType());
        return organizer;
    }

    public static OrganizerResponse toOrganizerResponse(Organizer organizer){
        OrganizerResponse response = new OrganizerResponse();
        response.setName(organizer.getName());
        response.setEmail(organizer.getEmail());
        response.setPhone(organizer.getPhone());
        response.setBusinessType(organizer.getBusinessType());
        response.setStatus(organizer.getStatus().toString());
        response.setCreatedAt(organizer.getCreatedAt().toString());
        response.setUpdatedAt(organizer.getUpdatedAt().toString());
        return response;
    }
}
