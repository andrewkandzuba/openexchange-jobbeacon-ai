package io.openexchange.jobbeacon.ai.service;

import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.model.CandidateEntity;
import io.openexchange.jobbeacon.ai.model.Registration;
import io.openexchange.jobbeacon.ai.repository.CandidatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CandidatesService {
    @Autowired
    private CandidatesRepository candidatesRepository;

    public Candidate create(Registration registration) {
        var ce = candidatesRepository.save(CandidateEntity.builder()
                .email(registration.getEmail())
                .firstName(registration.getFirstName())
                .lastName(registration.getLastName())
                .city(registration.getCity())
                .country(registration.getCountry())
                .zip(registration.getZip())
                .build());
        return new Candidate(
                ce.getEmail(),
                ce.getFirstName(),
                ce.getLastName(),
                ce.getCity(),
                ce.getCountry(),
                ce.getZip(),
                ce.getId());
    }

    public Optional<Candidate> findById(Long id) {
        var opt = candidatesRepository.findById(id);
        if (opt.isPresent()) {
            var ce = opt.get();
            return Optional.of(
                    new Candidate(
                            ce.getEmail(),
                            ce.getFirstName(),
                            ce.getLastName(),
                            ce.getCity(),
                            ce.getCountry(),
                            ce.getZip(),
                            ce.getId()));
        }
        return Optional.empty();
    }

    public Optional<Candidate> updateCandidate(Long id, Candidate update) {
        var opt = candidatesRepository.findById(id);
        if (opt.isPresent()) {
            var ce = opt.get();
            ce.setCity(update.getCity());
            ce.setEmail(update.getEmail());
            ce.setZip(update.getZip());
            ce.setLastName(update.getLastName());
            ce.setFirstName(update.getFirstName());
            ce.setCountry(update.getCountry());
            candidatesRepository.save(ce);
            return Optional.of(update);
        }
        return Optional.empty();
    }

    public List<Candidate> listCandidates() {
        return StreamSupport.stream(candidatesRepository.findAll().spliterator(), false)
                .map(candidateEntity -> new Candidate(
                        candidateEntity.getEmail(),
                        candidateEntity.getFirstName(),
                        candidateEntity.getLastName(),
                        candidateEntity.getCity(),
                        candidateEntity.getCountry(),
                        candidateEntity.getZip(),
                        candidateEntity.getId())).toList();
    }
}
