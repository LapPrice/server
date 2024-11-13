package com.lapprice.lapprice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LapTopRepository extends JpaRepository<LapTop, Long> {
}
