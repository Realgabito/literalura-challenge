package com.literalura.literalura.repository;

import com.literalura.literalura.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
