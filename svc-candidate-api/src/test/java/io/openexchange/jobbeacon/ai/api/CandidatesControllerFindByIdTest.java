package io.openexchange.jobbeacon.ai.api;


import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.service.CandidatesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CandidatesControllerFindByIdTest {
    @InjectMocks
    CandidatesController candidatesController;

    @Mock
    CandidatesService candidatesService;

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Candidate candidate = new Candidate();
        candidate.setId(id);

        when(candidatesService.findById(id)).thenReturn(Optional.of(candidate));
        ResponseEntity<Candidate> response = candidatesController.findById(id);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody().getId()).isEqualTo(id);

        verify(candidatesService, times(1)).findById(id);
    }

    @Test
    public void findByIdNotFoundTest() {
        Long id = 1L;

        when(candidatesService.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<Candidate> response = candidatesController.findById(id);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        verify(candidatesService, times(1)).findById(id);
    }
}
