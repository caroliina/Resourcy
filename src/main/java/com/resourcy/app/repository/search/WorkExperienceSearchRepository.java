package com.resourcy.app.repository.search;

import com.resourcy.app.domain.WorkExperience;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the WorkExperience entity.
 */
public interface WorkExperienceSearchRepository extends ElasticsearchRepository<WorkExperience, Long> {
}
