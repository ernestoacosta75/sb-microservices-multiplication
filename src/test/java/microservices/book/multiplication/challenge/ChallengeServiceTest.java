package microservices.book.multiplication.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

public class ChallengeServiceTest {
    private IChallengeService challengeService;

    @BeforeEach
    void setUp() {
        challengeService = new ChallengeService();
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50, 60, "john_doe", 3000);

        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDto);

        // then
        then(resultAttempt.isCorrect()).isTrue();
    }

    @Test
    public void checkWrongAttemptTest() {
        // given
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50, 60, "john_doe", 5000);

        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDto);

        // then
        then(resultAttempt.isCorrect()).isFalse();
    }
}
