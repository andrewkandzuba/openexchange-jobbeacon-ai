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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidatesServiceUpdateTest {

    @Mock
    private CandidatesRepository candidatesRepository;

    @InjectMocks
    private CandidatesService candidatesService;

    @Test
    public void testUpdateCandidate() {

        var existingCandidate = new Candidate("email@example.com", "John", "Doe", "San Francisco", "USA", "94105", 1L);

        var candidateEntity = CandidateEntity.builder()
                .email("email@example.com")
                .firstName("First")
                .lastName("Last")
                .city("City")
                .country("Country")
                .zip("12345")
                .id(1L)
                .build();

        // Creating stubs for the mocked repository
        when(candidatesRepository.findById(1L)).thenReturn(Optional.of(candidateEntity));
        when(candidatesRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        // Executing the updateCandidate method
        var updatedCandidate = candidatesService.updateCandidate(1L, existingCandidate);

        // Verifying the interactions
        verify(candidatesRepository, times(1)).findById(1L);
        verify(candidatesRepository, times(1)).save(candidateEntity);

        // Asserting that the updated candidate matches the new candidate
        assertTrue(updatedCandidate.isPresent());
        assertEquals(existingCandidate, updatedCandidate.get(), "The updated candidate should match the new candidate");
    }

    @Test
    public void testUpdateNotExistingCandidate() {

        var existingCandidate = new Candidate("email@example.com", "John", "Doe", "San Francisco", "USA", "94105", 1L);

        var candidateEntity = CandidateEntity.builder()
                .email("email@example.com")
                .firstName("First")
                .lastName("Last")
                .city("City")
                .country("Country")
                .zip("12345")
                .id(1L)
                .build();

        // Creating stubs for the mocked repository
        when(candidatesRepository.findById(1L)).thenReturn(Optional.empty());

        // Executing the updateCandidate method
        var updatedCandidate = candidatesService.updateCandidate(1L, existingCandidate);

        // Verifying the interactions
        verify(candidatesRepository, times(1)).findById(1L);
        verify(candidatesRepository, times(0)).save(candidateEntity);

        // Asserting that the updated candidate matches the new candidate
        assertTrue(updatedCandidate.isEmpty());
    }
}