package com.ec.cinema.repository;

import com.ec.cinema.domain.entity.BillboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillboardRepository extends JpaRepository<BillboardEntity, Long> {

}
