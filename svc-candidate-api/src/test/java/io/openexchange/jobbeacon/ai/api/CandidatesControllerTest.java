package io.openexchange.jobbeacon.ai.api;

import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.model.Registration;
import io.openexchange.jobbeacon.ai.service.CandidatesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CandidatesControllerTest {

    @Autowired
    private CandidatesController candidatesController;

    @MockBean
    private CandidatesService candidatesService;

    @Test
    public void testRegisterCandidate() {
        var registration = new Registration() {{
            setEmail("email@example.com");
            setFirstName("First");
            setLastName("Last");
            setCity("City");
            setCountry("Country");
            setZip("12345");
        }};
        // fill registration details
        var expectedCandidate = new Candidate() {{
            setEmail(registration.getEmail());
            setFirstName(registration.getFirstName());
            setLastName(registration.getLastName());
            setCity(registration.getCity());
            setCountry(registration.getCountry());
            setZip(registration.getZip());
            setId(1L);
        }};
        // fill expectedCandidate details
        when(candidatesService.create(registration)).thenReturn(expectedCandidate);
        ResponseEntity<Candidate> response = candidatesController.registerCandidate(registration);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1L, response.getBody().getId());

        verify(candidatesService, times(1)).create(registration);
    }
}
