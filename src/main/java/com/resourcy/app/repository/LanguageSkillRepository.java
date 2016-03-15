package com.resourcy.app.repository;

import com.resourcy.app.domain.LanguageSkill;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LanguageSkill entity.
 */
public interface LanguageSkillRepository extends JpaRepository<LanguageSkill,Long> {

}
