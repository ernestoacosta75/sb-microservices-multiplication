package microservices.book.multiplication.challenge;

import microservices.book.multiplication.user.User;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService implements IChallengeService{

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDto attemptDto) {
        // Check if the attempt is correct
        boolean isCorrect = attemptDto.getGuess() == attemptDto.getFactorA() * attemptDto.getFactorB();

        // We don't use identifies for now
        User user = new User(null, attemptDto.getUserAlias());

        // Builds the domain object. Null id for now.
        ChallengeAttempt checkedAttempt = new ChallengeAttempt(
                null,
                user,
                attemptDto.getFactorA(),
                attemptDto.getFactorB(),
                attemptDto.getGuess(),
                isCorrect
        );

        return checkedAttempt;
    }
}
