package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.factory.impl.CrewMemberFactory;
import lombok.Getter;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    @Getter
    private final Role role;
    @Getter
    private final Rank rank;
    @Getter
    private final Boolean isReadyForNextMissions;

    public CrewMemberCriteria(String name, Long id, Role role, Rank rank, Boolean isReadyForNextMissions) {
        super(id, name);
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMissions = isReadyForNextMissions;
    }

    public static class CrewMemberCriteriaBuilder extends CriteriaBuilder<CrewMember> {
        private Role role;
        private Rank rank;
        private Boolean isReadyForNextMissions;

        public CrewMemberCriteriaBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public CrewMemberCriteriaBuilder setRank(Rank rank) {
            this.rank = rank;
            return this;
        }

        public CrewMemberCriteriaBuilder setIsReadyForNextMissions(Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(this.getName(), this.getId(), role, rank, isReadyForNextMissions);
        }

    }

    void crewCheck(CrewMemberCriteria criteria){
        if(NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .noneMatch(crewMember -> crewMember.getId().equals(criteria.getId())
                        && crewMember.getName().equals(criteria.getName())
                        && crewMember.getRole() == criteria.getRole()
                        && crewMember.getRank() == criteria.getRank()
                        && getIsReadyForNextMissions())){
            NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class).add(new CrewMemberFactory().create(criteria.getRole(),
                    criteria.getRank(),criteria.getName()));
        }
    }
    @Override
    void criteria(Object... objects) {

    }

}
