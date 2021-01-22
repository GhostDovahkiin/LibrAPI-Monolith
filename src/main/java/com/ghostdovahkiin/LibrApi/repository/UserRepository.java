package com.ghostdovahkiin.LibrApi.repository;

import com.ghostdovahkiin.LibrApi.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
