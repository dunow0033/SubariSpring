package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Food;

public interface CartRepository extends JpaRepository<Food,Integer> {
}
