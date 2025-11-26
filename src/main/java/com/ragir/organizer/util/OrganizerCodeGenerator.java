package com.ragir.organizer.util;

import org.springframework.stereotype.Component;

@Component
public class OrganizerCodeGenerator {
    public static String GenerateNextCode(Integer lastId){
        int nextNum = (lastId == null ? 1 : lastId + 1);
        return "ORG" + String.format("%05d", nextNum);
    }
}
