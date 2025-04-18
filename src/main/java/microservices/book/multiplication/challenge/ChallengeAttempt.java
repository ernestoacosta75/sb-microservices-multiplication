package microservices.book.multiplication.challenge;

import jakarta.persistence.*;
import lombok.*;
import microservices.book.multiplication.user.User;

/**
 * Identifies the attempt from a {@link microservices.book.multiplication.user.User} to solve a challenge.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeAttempt {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
