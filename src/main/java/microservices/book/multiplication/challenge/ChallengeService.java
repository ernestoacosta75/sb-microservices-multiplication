package microservices.book.multiplication.challenge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.user.IUserRepository;
import microservices.book.multiplication.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeService implements IChallengeService{

    private final IUserRepository userRepository;
    private final IChallengeAttemptRepository attemptRepository;

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDto attemptDto) {
        // Check if the user already exists for that alias, otherwise create it
        User user = userRepository.findByAlias(attemptDto.getUserAlias())
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}",
                            attemptDto.getUserAlias());

                    return userRepository.save(new User(attemptDto.getUserAlias()));
                });

        boolean isCorrect = attemptDto.getGuess() == attemptDto.getFactorA() * attemptDto.getFactorB();

        // Builds the domain object. Null id for now.
        ChallengeAttempt checkedAttempt = new ChallengeAttempt(
                null,
                user,
                attemptDto.getFactorA(),
                attemptDto.getFactorB(),
                attemptDto.getGuess(),
                isCorrect
        );

        // Stores the attempt
        ChallengeAttempt storedAttempt = attemptRepository.save(checkedAttempt);

        return checkedAttempt;
    }

    @Override
    public List<ChallengeAttempt> getStatsForUser(final String userAlias) {
        return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
    }
}
