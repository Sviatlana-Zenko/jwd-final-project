package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.criteria.CinemaProductCriteria;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import java.sql.Connection;
import java.util.List;

/**
 * An interface extends the set of standard CRUD methods
 * described inside the {@link AppEntityDao} interface.
 * This methods were designed to work with {@link CinemaProduct} objects
 * taken from the database.
 */
public interface CinemaProductDao extends AppEntityDao<CinemaProduct, CinemaProductCriteria> {

    /**
     * Returns a List of CinemaProduct id numbers of elements
     * with a specific title (complete or partial match),
     * or an empty {@link List<Long>} if there is no such
     * elements in the database.
     *
     * @param title {@link CinemaProduct} title to find
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return the List of id numbers
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<Long> findIdByTitle(String title, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns a List of CinemaProduct objects describing the elements
     * of the database with a specific type.
     *
     * @param type a {@link ProductType} to find
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return the List of {@link CinemaProduct} objects with a specific type
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<CinemaProduct> findAllByType(ProductType type, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns a specified number of CinemaProduct objects describing the
     * elements of the database with a specific type and sorted by given
     * field and direction (ascending or descending).
     *
     * @param type a {@link ProductType} to find
     * @param startIndex the element index that specifies
     *                   the offset of the first element to return
     * @param number the number of element to return
     * @param field the field name as a parameter to sort
     * @param dir the sorting direction
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return the a specified number of {@link CinemaProduct} objects
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<CinemaProduct> findConcreteAmountByType(ProductType type, long startIndex, int number,
            String field, String dir, Connection connection) throws DatabaseInteractionException;

    /**
     * Returns the total number of CinemaProduct objects with
     * a specific type that are present in the database.
     *
     * @param productType a {@link ProductType} to find
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return total number of {@link CinemaProduct} objects
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    int getNumberOfProducts(ProductType productType, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns a List of CinemaProduct objects found by a specific search request
     * (complete or partial match), or an empty {@link List<CinemaProduct>}
     * if there is no matching elements in the database.
     *
     * @param searchRequest a search request string
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return the List of {@link CinemaProduct} objects
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<CinemaProduct> findBySearchRequest(String searchRequest, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns a List of randomly selected CinemaProduct objects.
     *
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return returns a List of randomly selected objects
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<CinemaProduct> findRecommendations(Connection connection)
            throws DatabaseInteractionException;

    /**
     * Updates current rating field for CinemaProduct object with a specified id number.
     *
     * @param productId the object id to update
     * @param newRating the new value for current rating field
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if this field has been updated
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    boolean updateProductRating(Long productId, Double newRating, Connection connection)
            throws DatabaseInteractionException;

}
