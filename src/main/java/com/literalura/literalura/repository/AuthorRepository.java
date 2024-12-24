package com.literalura.literalura.repository;

import com.literalura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
