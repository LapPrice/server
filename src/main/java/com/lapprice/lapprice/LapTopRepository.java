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

	@Query("SELECT DISTINCT ram FROM LapTop ORDER BY ram ASC")
	List<Integer> findDistinctRAMsSorted();

	@Query("SELECT DISTINCT disk FROM LapTop ORDER BY disk ASC")
	List<Integer> findDistinctDisksSorted();

	@Query("SELECT DISTINCT inch FROM LapTop ORDER BY inch ASC")
	List<Integer> findDistinctInchesSorted();

}
