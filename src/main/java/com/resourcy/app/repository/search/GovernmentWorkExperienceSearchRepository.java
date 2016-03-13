package com.resourcy.app.repository.search;

import com.resourcy.app.domain.GovernmentWorkExperience;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the GovernmentWorkExperience entity.
 */
public interface GovernmentWorkExperienceSearchRepository extends ElasticsearchRepository<GovernmentWorkExperience, Long> {
}
