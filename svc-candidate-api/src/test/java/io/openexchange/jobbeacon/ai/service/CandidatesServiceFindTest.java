package io.openexchange.jobbeacon.ai.service;

import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.model.CandidateEntity;
import io.openexchange.jobbeacon.ai.repository.CandidatesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidatesServiceFindTest {

    @Mock
    private CandidatesRepository candidatesRepository;

    @InjectMocks
    private CandidatesService candidatesService;

    @Test
    public void testFindById() {
        CandidateEntity candidateEntity = CandidateEntity.builder()
                .email("email@example.com")
                .firstName("First")
                .lastName("Last")
                .city("City")
                .country("Country")
                .zip("12345")
                .build();

        when(candidatesRepository.findById(anyLong())).thenReturn(Optional.of(candidateEntity));

        Optional<Candidate> result = candidatesService.findById(1L);

        assertTrue(result.isPresent());
        Candidate candidate = result.get();

        assertEquals(candidate.getEmail(), candidateEntity.getEmail());
        assertEquals(candidate.getFirstName(), candidateEntity.getFirstName());
        assertEquals(candidate.getLastName(), candidateEntity.getLastName());
        assertEquals(candidate.getCity(), candidateEntity.getCity());
        assertEquals(candidate.getCountry(), candidateEntity.getCountry());
        assertEquals(candidate.getZip(), candidateEntity.getZip());
    }

    @Test
    public void testFindByIdNotFound() {
        when(candidatesRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Candidate> result = candidatesService.findById(1L);

        assertTrue(result.isEmpty());
    }
}