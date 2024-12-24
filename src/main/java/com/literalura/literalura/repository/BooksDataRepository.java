package com.literalura.literalura.repository;

import com.literalura.literalura.model.BooksData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksDataRepository extends JpaRepository<BooksData, Long> {

}
