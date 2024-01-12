package io.openexchange.jobbeacon.ai.api;

import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.model.Registration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CandidatesApTest {

    private NativeWebRequest webRequest;
    private MockHttpServletRequest servletRequest;
    private CandidatesApi candidatesApi;

    @BeforeEach
    void setUp() {
        servletRequest = new MockHttpServletRequest();
        servletRequest.addHeader("Accept", "application/json");
        webRequest = new ServletWebRequest(servletRequest, new MockHttpServletResponse());
        candidatesApi = new CandidatesApi() {
            @Override
            public Optional<NativeWebRequest> getRequest() {
                return Optional.of(webRequest);
            }
        };
    }

    @Test
    void testGetRequestWhenRequestIsPresent() {
        // Asserting that getRequest method should return non-empty Optional
        assertTrue(candidatesApi.getRequest().isPresent());
        // Asserting that the NativeWebRequest returned by getRequest method is same as the mock web request created above.
        assertEquals(webRequest, candidatesApi.getRequest().get());
    }

    @Test
    void testGetRequestWhenRequestIsNotPresent() {
        // Asserting that getRequest method should return empty Optional when no web request is present.
        assertFalse(new CandidatesApi() {
        }.getRequest().isPresent());
    }

    @Test
    public void testCandidateGetById() {
        ResponseEntity<Candidate> resp = candidatesApi.findById(1L);
        Assertions.assertEquals(HttpStatus.NOT_IMPLEMENTED, resp.getStatusCode());

        servletRequest.removeHeader("Accept");
        servletRequest.addHeader("Accept", "application/octet-stream");

        resp = candidatesApi.findById(1L);
        Assertions.assertEquals(HttpStatus.NOT_IMPLEMENTED, resp.getStatusCode());
    }

    @Test
    public void testListCandidates() {
        ResponseEntity<List<Candidate>> resp = candidatesApi.listCandidates();
        Assertions.assertEquals(HttpStatus.NOT_IMPLEMENTED, resp.getStatusCode());

        servletRequest.removeHeader("Accept");
        servletRequest.addHeader("Accept", "application/octet-stream");

        resp = candidatesApi.listCandidates();
        Assertions.assertEquals(HttpStatus.NOT_IMPLEMENTED, resp.getStatusCode());
    }

    @Test
    public void testRegisterCandidate() {
        ResponseEntity<Candidate> resp = candidatesApi.registerCandidate(
                new Registration(
                        "test@email.com",
                        "Tester",
                        "Testoman",
                        "TestCity",
                        "TestCountry",
                        "12345")
        );
        Assertions.assertEquals(HttpStatus.NOT_IMPLEMENTED, resp.getStatusCode());

        servletRequest.removeHeader("Accept");
        servletRequest.addHeader("Accept", "application/octet-stream");

        resp = candidatesApi.registerCandidate(
                new Registration(
                        "test@email.com",
                        "Tester",
                        "Testoman",
                        "TestCity",
                        "TestCountry",
                        "12345")
        );
        Assertions.assertEquals(HttpStatus.NOT_IMPLEMENTED, resp.getStatusCode());
    }

    @Test
    public void testUpdateCandidate() {
        ResponseEntity<Candidate> resp = candidatesApi.updateCandidate(
                1L,
                Candidate.builder().build()
        );
        Assertions.assertEquals(HttpStatus.NOT_IMPLEMENTED, resp.getStatusCode());

        servletRequest.removeHeader("Accept");
        servletRequest.addHeader("Accept", "application/octet-stream");

        resp = candidatesApi.updateCandidate(
                1L,
                Candidate.builder().build()
        );
        Assertions.assertEquals(HttpStatus.NOT_IMPLEMENTED, resp.getStatusCode());
    }
}