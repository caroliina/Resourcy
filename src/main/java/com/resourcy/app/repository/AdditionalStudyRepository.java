package com.resourcy.app.repository;

import com.resourcy.app.domain.AdditionalStudy;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AdditionalStudy entity.
 */
public interface AdditionalStudyRepository extends JpaRepository<AdditionalStudy,Long> {

}
