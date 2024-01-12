package io.openexchange.jobbeacon.ai.api;

import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.service.CandidatesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CandidatesControllerListTest {
    @InjectMocks
    CandidatesController candidatesController;

    @Mock
    CandidatesService candidatesService;

    @Test
    public void listCandidatesTest() {
        Candidate candidate1 = new Candidate();
        Candidate candidate2 = new Candidate();

        List<Candidate> candidatesList = Arrays.asList(candidate1, candidate2);

        when(candidatesService.listCandidates()).thenReturn(candidatesList);
        ResponseEntity<List<Candidate>> response = candidatesController.listCandidates();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody()).contains(candidate1, candidate2);

        verify(candidatesService, times(1)).listCandidates();
    }
}
