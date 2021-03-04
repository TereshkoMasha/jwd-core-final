package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CrewMemberDeserializer extends StdDeserializer<CrewMemberCriteria> {
    public CrewMemberDeserializer() {
        this(null);
    }

    protected CrewMemberDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CrewMemberCriteria deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        CrewMemberCriteria.CrewMemberCriteriaBuilder criteria = new CrewMemberCriteria.CrewMemberCriteriaBuilder();
        JsonNode node = p.getCodec().readTree(p);
        long id = Long.parseLong(node.get("id").asText());
        String name = node.get("name").asText();
        Role role = Role.valueOf(node.get("role").asText());
        Rank rank = Rank.valueOf(node.get("rank").asText());
        boolean isReady = node.get("isReadyForNextMissions").asBoolean();
        criteria.setIsReadyForNextMissions(isReady).setRole(role).setRank(rank).setId(id).setName(name);
        return criteria.build();
    }
}
