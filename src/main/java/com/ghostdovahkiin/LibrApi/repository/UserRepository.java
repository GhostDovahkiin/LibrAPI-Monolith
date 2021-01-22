package com.ghostdovahkiin.LibrApi.repository;

import com.ghostdovahkiin.LibrApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
