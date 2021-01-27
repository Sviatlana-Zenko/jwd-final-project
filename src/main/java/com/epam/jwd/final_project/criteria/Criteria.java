package com.epam.jwd.final_project.criteria;

import com.epam.jwd.final_project.domain.AppEntity;

public class Criteria<T extends AppEntity> {

    private Long id;

    public Criteria(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static CriteriaBuilder criteriaBuilder() {
        return new CriteriaBuilder();
    }

    public static class CriteriaBuilder {
        Long id;

        public CriteriaBuilder() {
            this.configure();
        }

        protected void configure() {}

        public CriteriaBuilder id(Long id) {
            this.id = id;
            return this;
        }
    }

}