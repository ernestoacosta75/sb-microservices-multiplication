package microservices.book.multiplication.challenge;

import java.util.List;

/**
 * This interface is used to verify attempts from users
 */
public interface IChallengeService {
    /**
     *
     * @param resultAttempt
     * @return the resulting ChallengeAttempt object
     */
    ChallengeAttempt verifyAttempt(ChallengeAttemptDto resultAttempt);

    /**
     * Gets the statistics for a given user.
     *
     * @param userAlias the user's alias
     * @return a list of the last 10 {@link ChallengeAttempt}
     * objects created by the user.
     */
    List<ChallengeAttempt> getStatsForUser(final String userAlias);
}
