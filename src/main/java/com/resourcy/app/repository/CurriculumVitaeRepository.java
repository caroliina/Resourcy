package com.resourcy.app.repository;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.LanguageType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the CurriculumVitae entity.
 */
public interface CurriculumVitaeRepository extends JpaRepository<CurriculumVitae,Long> {

   CurriculumVitae findByEmployeeIdAndLanguageType(Long id, LanguageType languageType);

}
