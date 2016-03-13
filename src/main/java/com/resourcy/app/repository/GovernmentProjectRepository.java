package com.resourcy.app.repository;

import com.resourcy.app.domain.GovernmentProject;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the GovernmentProject entity.
 */
public interface GovernmentProjectRepository extends JpaRepository<GovernmentProject,Long> {

}
