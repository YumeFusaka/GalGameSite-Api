package galgamesite;

import galgamesite.controller.activity.TierMakerController;
import galgamesite.controller.activity.TwelveVotingController;
import galgamesite.controller.activity.VoteQuotaController;
import galgamesite.controller.general.GameController;
import galgamesite.controller.user.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiContractAnnotationsTest {

    @Test
    void userControllerShouldUseResourceStyleRoutes() {
        assertRequestMapping(UserController.class, "/users");
        assertHasPostMapping(UserController.class, "/sessions");
        assertHasGetMapping(UserController.class, "/me");
        assertHasGetMapping(UserController.class, "");
    }

    @Test
    void gameControllerShouldExposeUnifiedGameRoutes() {
        assertRequestMapping(GameController.class, "/games");
        assertHasGetMapping(GameController.class, "");
    }

    @Test
    void tierMakerControllerShouldUseActivityResourceRoutes() {
        assertRequestMapping(TierMakerController.class, "/activities/tier-maker");
        assertHasGetMapping(TierMakerController.class, "/records/me");
        assertHasPutMapping(TierMakerController.class, "/records/me");
    }

    @Test
    void twelveVotingControllersShouldUseResourceStyleRoutes() {
        assertRequestMapping(TwelveVotingController.class, "/activities/twelve-voting");
        assertHasGetMapping(TwelveVotingController.class, "/games");
        assertHasGetMapping(TwelveVotingController.class, "/results");
        assertHasGetMapping(TwelveVotingController.class, "/records/me");
        assertHasGetMapping(TwelveVotingController.class, "/summary/me");
        assertHasGetMapping(TwelveVotingController.class, "/games/{subjectId}/ballot");
        assertHasPutMapping(TwelveVotingController.class, "/games/{subjectId}/ballot");

        assertRequestMapping(VoteQuotaController.class, "/activities/twelve-voting/quota");
        assertHasGetMapping(VoteQuotaController.class, "/me");
        assertHasPutMapping(VoteQuotaController.class, "/me");
    }

    private static void assertRequestMapping(Class<?> controllerClass, String expectedPath) {
        RequestMapping requestMapping = controllerClass.getAnnotation(RequestMapping.class);
        assertEquals(expectedPath, firstValue(requestMapping.value()), controllerClass.getSimpleName());
    }

    private static void assertHasGetMapping(Class<?> controllerClass, String expectedPath) {
        assertTrue(
                Arrays.stream(controllerClass.getDeclaredMethods())
                        .anyMatch(method -> hasPath(method, GetMapping.class, expectedPath)),
                () -> controllerClass.getSimpleName() + " should expose GET " + expectedPath
        );
    }

    private static void assertHasPostMapping(Class<?> controllerClass, String expectedPath) {
        assertTrue(
                Arrays.stream(controllerClass.getDeclaredMethods())
                        .anyMatch(method -> hasPath(method, PostMapping.class, expectedPath)),
                () -> controllerClass.getSimpleName() + " should expose POST " + expectedPath
        );
    }

    private static void assertHasPutMapping(Class<?> controllerClass, String expectedPath) {
        assertTrue(
                Arrays.stream(controllerClass.getDeclaredMethods())
                        .anyMatch(method -> hasPath(method, PutMapping.class, expectedPath)),
                () -> controllerClass.getSimpleName() + " should expose PUT " + expectedPath
        );
    }

    private static boolean hasPath(Method method, Class<?> annotationType, String expectedPath) {
        if (annotationType == GetMapping.class) {
            GetMapping mapping = method.getAnnotation(GetMapping.class);
            return mapping != null && expectedPath.equals(firstValue(mapping.value()));
        }
        if (annotationType == PostMapping.class) {
            PostMapping mapping = method.getAnnotation(PostMapping.class);
            return mapping != null && expectedPath.equals(firstValue(mapping.value()));
        }
        if (annotationType == PutMapping.class) {
            PutMapping mapping = method.getAnnotation(PutMapping.class);
            return mapping != null && expectedPath.equals(firstValue(mapping.value()));
        }
        return false;
    }

    private static String firstValue(String[] values) {
        return values.length == 0 ? "" : values[0];
    }
}
