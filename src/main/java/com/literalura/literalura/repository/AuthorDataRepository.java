package com.literalura.literalura.repository;

import com.literalura.literalura.model.AuthorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDataRepository extends JpaRepository<AuthorData, Long> {

}
