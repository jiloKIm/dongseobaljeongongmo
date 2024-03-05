package com.example.demo.Repository;

import com.example.demo.Entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildRepository extends JpaRepository<Building, Long> {}

