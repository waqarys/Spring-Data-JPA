package com.guitar.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Location;

public interface LocationJpaRepository extends JpaRepository<Location, Long> {
	List<Location> findByStateLike(String stateName);
	List<Location> findByStateNotLike(String stateName);
	
	List<Location> findByStateOrCountry(String value, String value2);
	List<Location> findByStateAndCountry(String state, String country);
	
	
	List<Location> findByStateIsOrCountryEquals(String value, String value2);
	List<Location> findByStateNot(String state);
	
	List<Location> findByStateStartingWith(String stateName);
	List<Location> findByStateIgnoreCaseStartingWith(String stateName);
	
	List<Location> findByStateNotLikeOrderByStateAsc(String stateName);
	
	Location findFirstByStateIgnoreCaseStartingWith(String stateName);
}
