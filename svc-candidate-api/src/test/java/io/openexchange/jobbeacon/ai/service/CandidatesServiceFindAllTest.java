package io.openexchange.jobbeacon.ai.service;

import io.openexchange.jobbeacon.ai.model.Candidate;
import io.openexchange.jobbeacon.ai.model.CandidateEntity;
import io.openexchange.jobbeacon.ai.repository.CandidatesRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CandidatesServiceFindAllTest {
    @Mock
    private CandidatesRepository candidatesRepository;

    @InjectMocks
    private CandidatesService candidatesService;

    @Test
    public void testListCandidates() {
        // Arrange
        var candidateEntity = CandidateEntity.builder()
                .email("email@example.com")
                .firstName("First")
                .lastName("Last")
                .city("City")
                .country("Country")
                .zip("12345")
                .build();
        // Mock repository response
        when(candidatesRepository.findAll()).thenReturn(Lists.list(candidateEntity));

        // Act
        List<Candidate> actual = candidatesService.listCandidates();

        // Assert
        assertEquals(1, actual.size());
        assertEquals("email@example.com", actual.getFirst().getEmail());
    }
}