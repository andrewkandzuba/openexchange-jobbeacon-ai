package io.openexchange.jobbeacon.ai.service;

import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.model.CandidateEntity;
import io.openexchange.jobbeacon.ai.model.Registration;
import io.openexchange.jobbeacon.ai.repository.CandidatesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidatesServiceCreateTest {
    @Mock
    private CandidatesRepository candidatesRepository;

    @InjectMocks
    private CandidatesService candidatesService;

    @Test
    public void createTest() {
        Registration registration = new Registration("test@email.com", "Tester", "Testoman", "TestCity", "TestCountry", "12345");
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setEmail(registration.getEmail());
        candidateEntity.setFirstName(registration.getFirstName());
        candidateEntity.setLastName(registration.getLastName());
        when(candidatesRepository.save(any())).thenReturn(candidateEntity);

        Candidate created = candidatesService.create(registration);
        assertNotNull(created);
        assertEquals(created.getEmail(), candidateEntity.getEmail());
        assertEquals(created.getFirstName(), candidateEntity.getFirstName());
        assertEquals(created.getLastName(), candidateEntity.getLastName());
    }

    @Test
    public void createTestEntityNotFound() {
        Registration registration = new Registration("test@email.com", "Tester", "Testoman", "TestCity", "TestCountry", "12345");

        CandidateEntity candidateEntity = CandidateEntity.builder()
                .email(registration.getEmail())
                .firstName(registration.getFirstName())
                .lastName(registration.getLastName())
                .city(registration.getCity())
                .zip(registration.getZip())
                .country(registration.getCountry())
                .build();

        when(candidatesRepository.save(candidateEntity)).thenReturn(
                CandidateEntity.builder()
                        .id(1L)
                        .email(candidateEntity.getEmail())
                        .firstName(candidateEntity.getFirstName())
                        .lastName(candidateEntity.getLastName())
                        .city(candidateEntity.getCity())
                        .zip(candidateEntity.getZip())
                        .country(candidateEntity.getCountry())
                        .build()
        );

        Candidate created = candidatesService.create(registration);
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals(created.getEmail(), candidateEntity.getEmail());
        assertEquals(created.getFirstName(), candidateEntity.getFirstName());
        assertEquals(created.getLastName(), candidateEntity.getLastName());
        assertEquals(created.getCity(), candidateEntity.getCity());
        assertEquals(created.getCountry(), candidateEntity.getCountry());
        assertEquals(created.getZip(), candidateEntity.getZip());
    }
}