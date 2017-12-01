package com.guitar.db.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Manufacturer;

public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer, Long> {

	List<Manufacturer> findByFoundedDateBefore(Date date);
}
