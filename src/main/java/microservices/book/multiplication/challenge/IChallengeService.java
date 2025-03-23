package microservices.book.multiplication.challenge;

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
}
