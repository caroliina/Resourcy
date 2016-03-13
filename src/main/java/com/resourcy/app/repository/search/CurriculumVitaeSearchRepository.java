package com.resourcy.app.repository.search;

import com.resourcy.app.domain.CurriculumVitae;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the CurriculumVitae entity.
 */
public interface CurriculumVitaeSearchRepository extends ElasticsearchRepository<CurriculumVitae, Long> {
}
