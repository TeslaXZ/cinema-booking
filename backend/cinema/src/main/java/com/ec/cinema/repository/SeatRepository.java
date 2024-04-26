package com.ec.cinema.repository;

import com.ec.cinema.domain.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeatRepository extends JpaRepository <SeatEntity, Long> {}
