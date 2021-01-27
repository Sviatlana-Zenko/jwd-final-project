package com.epam.jwd.final_project.service;

import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.domain.AppEntity;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import java.util.List;
import java.util.Optional;

public interface EntityService<T extends AppEntity, K extends Criteria<T>> {

    /**
     * Validates the element and inserts this element into the database
     * if element matches validation criteria.
     * @param t the element to validate and to add to the database
     * @return true if the element was added to the database, else false
     * @throws ValidationException if element does not match specified validation
     * criteria and cannot be created
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    boolean create(T t) throws ValidationException, DatabaseInteractionException;

    /**
     * Returns all elements of the specified type retrieved from the database.
     * @return {@link java.util.List} of elements
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    List<T> findAll() throws DatabaseInteractionException;

    /**
     * Returns an {@link java.util.Optional} describing the element found in
     * the database by a specific id number, or an empty
     * Optional if such element wasn't found.
     * @param id id of the element to find
     * @return an Optional describing some element, or an empty Optional
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    Optional<T> findById(Long id) throws DatabaseInteractionException;

    /**
     * Removes the specified element from the database.
     * @param t the element to remove
     * @return true if the element was removed, else false
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    boolean delete(T t) throws DatabaseInteractionException;

    /**
     * Validates the element and updates this element according to the given criteria.
     * @param t the element to update
     * @param k the criteria that contains information about how the element should be updated
     * @return updated element
     * @throws ValidationException if element does not match specified validation
     * criteria and cannot be updated
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    T updateByCriteria(T t, K k) throws ValidationException, DatabaseInteractionException;

}
