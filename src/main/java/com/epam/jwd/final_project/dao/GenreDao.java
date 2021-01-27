package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.domain.Genre;
import com.epam.jwd.final_project.pool.ConnectionPool;

import java.sql.Connection;
import java.util.List;

public interface GenreDao {

    boolean updateUserGenres(List<Genre> newGenres, Connection connection);


    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    List<Genre> findUserGenres(Long userId);

    List<Genre> findCinemaProductGenres(Long productId);

    boolean createUserGenres(Long userId, List<Genre> genres);

    boolean createCinemaProductGenres(Long productId, List<Genre> genres);

}
