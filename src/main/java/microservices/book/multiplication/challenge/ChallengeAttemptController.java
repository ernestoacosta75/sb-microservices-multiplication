package microservices.book.multiplication.challenge;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class provides a REST API to POST the attempts from users.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptController {
    private final IChallengeService challengeService;

    @PostMapping
    ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDto challengeAttemptDto) {
        return ResponseEntity.ok(challengeService.verifyAttempt(challengeAttemptDto));
    }

    @GetMapping
    ResponseEntity<List<ChallengeAttempt>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(
                challengeService.getStatsForUser(alias)
        );
    }
}
