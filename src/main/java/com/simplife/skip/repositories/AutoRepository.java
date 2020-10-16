package com.simplife.skip.repositories;

import com.simplife.skip.models.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AutoRepository extends JpaRepository<Auto, Long> {


}
