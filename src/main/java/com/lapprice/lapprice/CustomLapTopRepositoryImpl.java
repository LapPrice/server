package com.lapprice.lapprice;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.lapprice.lapprice.dto.GetOptionBySelectRequest;
import com.lapprice.lapprice.dto.GetSelectOptionResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomLapTopRepositoryImpl implements CustomLapTopRepository {
	private final EntityManager entityManager;

	public GetSelectOptionResponse getLapTopByCustom(GetOptionBySelectRequest request){
		StringBuilder jpqlBuilder = new StringBuilder();

		jpqlBuilder.append("select L from LapTop L");

		if(request.cpuName()==null
			&&request.inch()==null
			&&request.ram()==null
			&&request.brand()==null
			&&request.ssd()==null) return execQuery(jpqlBuilder.toString(), request);

		jpqlBuilder.append(" where ");
		Boolean exist= false;
		if(request.cpuName()!=null){
			jpqlBuilder.append("L.cpu = :cpuName");
			exist= true;
		}
		if(request.inch()!=null){
			if(exist) {
				jpqlBuilder.append(" and ");
			}
			jpqlBuilder.append("L.inch = :inch");
			exist= true;
		}
		if(request.ram()!=null){
			if(exist) {
				jpqlBuilder.append(" and ");
			}
			jpqlBuilder.append("L.ram = :ram");
			exist= true;
		}
		if(request.brand()!=null){
			if(exist) {
				jpqlBuilder.append(" and ");
			}
			jpqlBuilder.append("L.brand = :brand");
			exist= true;
		}
		if(request.ssd()!=null){
			if(exist) {
				jpqlBuilder.append(" and ");
			}
			jpqlBuilder.append("L.ssd = :ssd");
		}

		return execQuery(jpqlBuilder.toString(), request);
	}

	private GetSelectOptionResponse execQuery(String query, GetOptionBySelectRequest request) {
		TypedQuery<LapTop> typedQuery = entityManager.createQuery(query, LapTop.class);

		if (request.cpuName() != null) {
			typedQuery.setParameter("cpuName", request.cpuName());
		}
		if (request.inch() != null) {
			typedQuery.setParameter("inch", request.inch());
		}
		if (request.ram() != null) {
			typedQuery.setParameter("ram", request.ram());
		}
		if (request.brand() != null) {
			typedQuery.setParameter("brand", request.brand());
		}
		if (request.ssd() != null) {
			typedQuery.setParameter("ssd", request.ssd());
		}

		List<LapTop> resultList = typedQuery.getResultList();
		List<String> brands = resultList.stream().map(LapTop::getBrand).distinct().toList();
		List<String> cpus = resultList.stream().map(LapTop::getCpu).distinct().toList();
		List<Integer> rams = resultList.stream().map(LapTop::getRam).distinct().toList();
		List<Integer> ssds = resultList.stream().map(LapTop::getSsd).distinct().toList();
		List<Integer> inches = resultList.stream().map(LapTop::getInch).distinct().toList();

		return GetSelectOptionResponse.builder()
			.brand(brands)
			.cpu(LapTopService.getCpuMap(cpus))
			.ram(rams)
			.ssd(ssds)
			.inch(inches).build();
	}

	@Override
	public List<LapTop> findLaptopsByDynamicSpecs(String brand, String cpu, Integer ssd, Integer ram, Integer inch) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<LapTop> query = cb.createQuery(LapTop.class);
		Root<LapTop> root = query.from(LapTop.class);

		List<Predicate> predicates = new ArrayList<>();

		// 조건이 null이 아닌 경우만 추가
		if (brand != null) {
			System.out.println("Adding brand condition: " + brand);
			predicates.add(cb.equal(root.get("brand"), brand));
		}
		if (cpu != null) {
			System.out.println("Adding CPU condition: " + cpu);
			predicates.add(cb.equal(root.get("cpu"), cpu));
		}
		if (ssd != null) {
			System.out.println("Adding SSD condition: " + ssd);
			predicates.add(cb.equal(root.get("ssd"), ssd));
		}
		if (ram != null) {
			System.out.println("Adding RAM condition: " + ram);
			predicates.add(cb.equal(root.get("ram"), ram));
		}
		if (inch != null) {
			System.out.println("Adding Inch condition: " + inch);
			predicates.add(cb.equal(root.get("inch"), inch));
		}

		// 동적 조건 적용
		query.select(root);
		if (!predicates.isEmpty()) {
			query.where(cb.and(predicates.toArray(new Predicate[0])));
		}

		System.out.println("Executing dynamic query with predicates: " + predicates);

		// 쿼리 실행
		TypedQuery<LapTop> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}






}
