package com.resourcy.app.repository;

import com.resourcy.app.domain.CurriculumVitae;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CurriculumVitae entity.
 */
public interface CurriculumVitaeRepository extends JpaRepository<CurriculumVitae,Long> {

}
