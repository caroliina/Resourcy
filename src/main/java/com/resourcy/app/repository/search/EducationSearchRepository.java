package com.resourcy.app.repository.search;

import com.resourcy.app.domain.Education;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Education entity.
 */
public interface EducationSearchRepository extends ElasticsearchRepository<Education, Long> {
}
