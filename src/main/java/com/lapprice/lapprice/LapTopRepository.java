package com.lapprice.lapprice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LapTopRepository extends JpaRepository<LapTop, Long> {
	@Query("SELECT DISTINCT brand FROM LapTop")
	List<String> findDistinctBrands();

	@Query("SELECT DISTINCT cpuName FROM LapTop")
	List<String> findDistinctCPUs();

	@Query("SELECT DISTINCT ram FROM LapTop")
	List<Integer> findDistinctRAMs();

	@Query("SELECT DISTINCT disk FROM LapTop")
	List<Integer> findDistinctDisks();

	@Query("SELECT DISTINCT inch FROM LapTop")
	List<Integer> findDistinctInches();

}
