package com.example.mytaskweek9.repository;

import com.example.mytaskweek9.model.Connection;
import com.example.mytaskweek9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
