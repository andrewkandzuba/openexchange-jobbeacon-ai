package io.openexchange.jobbeacon.ai.api;

import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.model.Registration;
import io.openexchange.jobbeacon.ai.service.CandidatesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Validated
@Tag(name = "Candidate", description = "Candidate controller")
@RestController
public class CandidatesController implements CandidatesApi {

    @Autowired
    private CandidatesService candidatesService;

    @Override
    public ResponseEntity<Candidate> registerCandidate(Registration registration) {
        var ce = candidatesService.create(registration);
        var c = new Candidate(ce.getEmail(), ce.getFirstName(), ce.getLastName(), ce.getCity(), ce.getCountry(), ce.getZip(), ce.getId());
        return ResponseEntity.status(201).body(c);
    }

    @Override
    public ResponseEntity<Candidate> findById(Long id) {
        var ce = candidatesService.findById(id);
        return ce.map(c -> ResponseEntity.status(HttpStatusCode.valueOf(200)).body(c))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Override
    public ResponseEntity<Candidate> updateCandidate(Long id, Candidate candidate) {
        Optional<Candidate> opt = candidatesService.updateCandidate(id, candidate);
        return opt.map(c -> ResponseEntity.status(HttpStatusCode.valueOf(200)).body(c))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<Candidate>> listCandidates() {
        return ResponseEntity.ok(candidatesService.listCandidates());
    }
}
