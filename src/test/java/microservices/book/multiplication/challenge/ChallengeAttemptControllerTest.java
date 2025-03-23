package microservices.book.multiplication.challenge;

import microservices.book.multiplication.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @ExtendWith(SpringExtension.class) makes sure that our JUnint5 test loads the extensions for Spring
 * so we can use a test context.
 *
 * @AutoConfigureJsonTesters tells Spring to configure beans of type JacksonTester for some fields we
 * declare in the test. In our case, we use @Autowired to inject two JacksonTester beans from the test context.
 * Spring Boot, when instructed via this annotation, takes care of building these utility classes. A JacksonTester
 * may be used to serialize and deserialize objects using the same configuration (ex.: ObjectMapper) as the app would
 * fo in runtime.
 *
 * @WebMvcTest, wht the controller class as a parameter, makes Spring treat this as a presentation layer test. Thus, it'll
 * load only the relevant configuration arount the controller: validation, serializers, security, error handlers, etc.
 *
 * @MockitoBean comes with the spring Boot Test module and helps to develop proper unit tests bt allowing to mock other layers
 * and bean we're not testing. In our case, we replace the service bean in the context by a mock. We set the expected return values
 * within the test methods, using BDDMockito's given().
 *
 * @Autowired allows to make it inject (or wire) a bean in the context to the field.
 *
 * The MockMvc class is what we use in Spring to simulate requests to the presentation layer when we make a test that doesn't load
 * a real server. It's provided by the test context so we cana just inject it in our test.
 */

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ChallengeAttemptController.class)
public class ChallengeAttemptControllerTest {
    @MockitoBean
    private IChallengeService challengeService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ChallengeAttemptDto> jsonRequestAttempt;

    @Autowired
    private JacksonTester<ChallengeAttempt> jsonResultAttempt;

    @Test
    public void postValidResult() throws Exception {
        // given
        User user = new User(1L, "john");
        long attemptId = 5L;
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50, 70, "john", 3500);
        ChallengeAttempt expectedResponse = new ChallengeAttempt(attemptId, user, 50, 70, 3500, true);

        given(challengeService
                .verifyAttempt(eq(attemptDto)))
                .willReturn(expectedResponse);

        // when
        MockHttpServletResponse response = mvc.perform(
                post("/attempts").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestAttempt.write(attemptDto).getJson()))
                .andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                jsonResultAttempt.write(expectedResponse).getJson());
    }

    @Test
    void postInvalidResult() throws Exception {
        // given an attempt with invalid input data
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(2000, -70, "john", 1);

        // when
        MockHttpServletResponse response = mvc.perform(
                        post("/attempts").contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequestAttempt.write(attemptDto).getJson()))
                .andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
