package io.openexchange.jobbeacon.ai.service;

import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.model.CandidateEntity;
import io.openexchange.jobbeacon.ai.model.Registration;
import io.openexchange.jobbeacon.ai.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository repository;

    public Candidate create(Registration registration) {
        var ce = repository.save(CandidateEntity.builder()
                .email(registration.getEmail())
                .firstName(registration.getFirstName())
                .lastName(registration.getLastName())
                .city(registration.getCity())
                .country(registration.getCountry())
                .zip(registration.getZip())
                .build());
        return new Candidate(
                ce.getId(),
                ce.getEmail(),
                ce.getFirstName(),
                ce.getLastName(),
                ce.getCity(),
                ce.getCountry(),
                ce.getZip());
    }

    public Optional<Candidate> findById(Long id) {
        var opt = repository.findById(id);
        if (opt.isPresent()) {
            var ce = opt.get();
            return Optional.of(
                    new Candidate(
                            ce.getId(),
                            ce.getEmail(),
                            ce.getFirstName(),
                            ce.getLastName(),
                            ce.getCity(),
                            ce.getCountry(),
                            ce.getZip()));
        }
        return Optional.empty();
    }
}
