package com.resourcy.app.repository;

import com.resourcy.app.domain.GovernmentWorkExperience;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the GovernmentWorkExperience entity.
 */
public interface GovernmentWorkExperienceRepository extends JpaRepository<GovernmentWorkExperience,Long> {

}
