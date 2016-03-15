package com.resourcy.app.repository;

import com.resourcy.app.domain.Technology;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Technology entity.
 */
public interface TechnologyRepository extends JpaRepository<Technology,Long> {

}
