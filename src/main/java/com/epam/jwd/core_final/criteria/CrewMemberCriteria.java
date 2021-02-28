package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import lombok.Getter;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    @Getter
    private String name;
    @Getter
    private Role role;
    @Getter
    private Rank rank;
    @Getter
    private Boolean isReadyForNextMissions;


    //    public CrewMemberCriteria(String name, Role role, Rank rank, Boolean isReady) {
//        this.name = name;
//        this.role = role;
//        this.rank = rank;
//        this.isReadyForNextMissions = isReady;
//    }
    public static  class CrewMemberCriteriaBuilder extends CrewMember {
        private String name;
        private Role role;
        private Rank rank;
        private Boolean isReadyForNextMissions;

        public CrewMemberCriteriaBuilder(String name, Role role, Rank rank) {
            super(name, role, rank);
        }

        public CrewMemberCriteriaBuilder name(String name){
            this.name = name;
            return this;
        }

        public CrewMemberCriteriaBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public CrewMemberCriteriaBuilder rank(Rank rank) {
            this.rank = rank;
            return this;
        }

        public CrewMemberCriteriaBuilder isReadyForNextMissions(Boolean isReady) {
            this.isReadyForNextMissions = isReady;
            return this;
        }

        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(this);
        }
    }

    private CrewMemberCriteria(CrewMemberCriteriaBuilder builder) {
        this.name = builder.name;
        this.rank = builder.rank;
        this.role = builder.role;
        this.isReadyForNextMissions = builder.isReadyForNextMissions;
    }

    @Override
    void criteria(Object... objects) {
        for (Object criteria :
                objects) {
            switch (criteria.getClass().getSimpleName()) {
                case "Role": {
                    this.role = (Role) criteria;
                    break;
                }
                case "Rank": {
                    this.rank = (Rank) criteria;
                    break;
                }
                case "String": {
                    assert criteria instanceof String;
                    this.name = (String) criteria;
                    break;
                }
            }
        }
    }

}
