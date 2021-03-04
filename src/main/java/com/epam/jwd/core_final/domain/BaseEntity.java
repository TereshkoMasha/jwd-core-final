package com.epam.jwd.core_final.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = CrewMember.class)
public interface BaseEntity {

    Long getId();

    String getName();

}
