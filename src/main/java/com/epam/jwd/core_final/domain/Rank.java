package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@JsonDeserialize(as = Rank.class)
public enum Rank implements BaseEntity {
    TRAINEE(1L),
    SECOND_OFFICER(2L),
    FIRST_OFFICER(3L),
    CAPTAIN(4L);

    @Getter private final Long id;

    Rank(Long id) {
        this.id = id;
    }


    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {
        Rank[] values = values();
        if (this.id == 1L) return values[0].name();
        if (this.id == 2L) return values[1].name();
        if (this.id == 3L) return values[2].name();
        if (this.id == 4L) return values[3].name();

        return null;
    }

    /**
     * todo via java.lang.enum methods!
     *
     * @throws UnknownEntityException if such id does not exist
     */
    public static Rank resolveRankById(int id) throws UnknownEntityException {
        Rank[] values = values();
        switch (id){
            case 1: {
                return values[0];
            }
            case 2: {
                return values[1];
            }
            case 3: {
                return values[2];
            }
            case 4: {
                return values[3];
            }
            default:{
                throw new UnknownEntityException("Such id doesn't exist!");
            }
        }
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
