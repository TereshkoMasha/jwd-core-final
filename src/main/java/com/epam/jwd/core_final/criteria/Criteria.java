package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import lombok.Getter;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity>  {
    @Getter
    private final Long id;
    @Getter
    private final String name;

    public void makeCriteria(Object... objects) {
        criteria(objects);
    }

    public Criteria(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static class CriteriaBuilder<T extends BaseEntity> implements BaseEntity {

        private Long id;
        private String name;

        public CriteriaBuilder<T> setId(Long id) {
            this.id = id;
            return this;
        }

        public CriteriaBuilder<T> setName(String name) {
            this.name = name;
            return this;
        }

        public Criteria<T> createCriteria() {
            return new Criteria<T>(id, name) {
                @Override
                void criteria(Object... objects) {

                }
            };
        }

        @Override
        public Long getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    abstract void criteria(Object... objects);
}
