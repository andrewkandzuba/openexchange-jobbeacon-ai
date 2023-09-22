package io.openexchange.jobbeacon.ai.api;

import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.model.Registration;
import io.openexchange.jobbeacon.ai.service.CandidateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Tag(name = "Candidate", description = "Candidate controller")
@RestController
public class CandidateController implements CandidateApi {

    @Autowired
    private CandidateService candidateService;

    @Override
    public ResponseEntity<Candidate> registerCandidate(Registration registration) {
        var ce = candidateService.create(registration);
        var c = new Candidate(ce.getId(), ce.getEmail(), ce.getFirstName(), ce.getLastName(), ce.getCity(), ce.getCountry(), ce.getZip());
        return ResponseEntity.status(201).body(c);
    }

    @Override
    public ResponseEntity<Candidate> findById(Long id) {
        var ce = candidateService.findById(id);
        return ce.map(candidate -> ResponseEntity.status(HttpStatusCode.valueOf(200)).body(candidate))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
