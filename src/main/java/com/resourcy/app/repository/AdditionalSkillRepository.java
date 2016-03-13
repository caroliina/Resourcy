package com.resourcy.app.repository;

import com.resourcy.app.domain.AdditionalSkill;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AdditionalSkill entity.
 */
public interface AdditionalSkillRepository extends JpaRepository<AdditionalSkill,Long> {

}
