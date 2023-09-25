package io.openexchange.jobbeacon.ai.repository;

import io.openexchange.jobbeacon.ai.model.CandidateEntity;
import org.springframework.data.repository.CrudRepository;

public interface CandidatesRepository extends CrudRepository<CandidateEntity, Long> {
}
