package com.literalura.literalura.repository;

import com.literalura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameIgnoreCase(String name);
    List<Author> findByDateOfBirthLessThanEqualAndDateOfDeathGreaterThanEqual(Integer dateOfBirth , Integer dateOfDeath);

    @Query("SELECT a FROM Author a WHERE a.dateOfBirth <= :year AND (a.dateOfDeath >= :year OR a.dateOfDeath IS NULL)")
    List<Author> findAliveInYear(@Param("year") int year);
}
