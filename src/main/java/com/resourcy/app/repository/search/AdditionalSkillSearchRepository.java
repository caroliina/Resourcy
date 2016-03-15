package com.resourcy.app.repository.search;

import com.resourcy.app.domain.AdditionalSkill;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the AdditionalSkill entity.
 */
public interface AdditionalSkillSearchRepository extends ElasticsearchRepository<AdditionalSkill, Long> {
}
