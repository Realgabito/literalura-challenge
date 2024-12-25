package com.literalura.literalura.repository;

import com.literalura.literalura.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
    Optional<Books> findByTitleContainingIgnoreCase(String bookTitle);
    List<Books> findByLanguagesContainingIgnoreCase(String language);
}
