package com.enigmacamp.hellospring.repository.spec;

import com.enigmacamp.hellospring.model.Course;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CourseSpecification implements Specification<Course> {
    private SearchCriteria searchCriteria;

    public CourseSpecification(SearchCriteria criteria) {
        this.searchCriteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (searchCriteria.getOperation()) {
            case LIKE:
                return criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
            case EQUALS:
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }
}
