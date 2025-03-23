package microservices.book.multiplication.challenge;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {

    /**
     * @param userAlias
     * @return the last 10 attempts for a given user, identified by their alias.
     */
    List<ChallengeAttempt> findTop10ByUserAliasOrderByIdDesc(String userAlias);
}
