package galgamesite;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NamingStructureTest {

    private static final Path SRC_MAIN_JAVA = Path.of("src", "main", "java", "galgamesite");
    private static final Path FRONTEND_SRC = Path.of("..", "frontend", "src");

    @Test
    void backendShouldUseConcreteServiceAndControllerNames() {
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("service", "GameService.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("service", "UserService.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("service", "TwelveVotingService.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("service", "VoteQuotaService.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("service", "TierMakerRankService.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("service", "TierMakerSubjectService.java"))));

        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("controller", "general", "GameController.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("controller", "activity", "TwelveVotingController.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("controller", "activity", "VoteQuotaController.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("controller", "activity", "TierMakerController.java"))));

        assertFalse(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("service", "Impl"))));
        assertFalse(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("service", "IGalGameService.java"))));
        assertFalse(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("service", "IUserService.java"))));
        assertFalse(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("controller", "general", "GalGameController.java"))));
        assertFalse(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("controller", "activity", "GalGameTwelveVotingController.java"))));
    }

    @Test
    void backendShouldUseShortMapperAndXmlNames() {
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("mapper", "GameMapper.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("mapper", "TwelveVotingMapper.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("mapper", "VoteQuotaMapper.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("mapper", "TierMakerRankMapper.java"))));
        assertTrue(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("mapper", "TierMakerSubjectMapper.java"))));

        assertTrue(Files.exists(Path.of("src", "main", "resources", "mapper", "GameMapper.xml")));
        assertTrue(Files.exists(Path.of("src", "main", "resources", "mapper", "TwelveVotingMapper.xml")));

        assertFalse(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("mapper", "GalGameMapper.java"))));
        assertFalse(Files.exists(SRC_MAIN_JAVA.resolve(Path.of("mapper", "GalGameTwelveVotingMapper.java"))));
        assertFalse(Files.exists(Path.of("src", "main", "resources", "mapper", "GalGameMapper.xml")));
    }

    @Test
    void frontendShouldUsePluralAndDomainDrivenApiFileNames() {
        assertTrue(Files.exists(FRONTEND_SRC.resolve(Path.of("apis", "user", "users.ts"))));
        assertTrue(Files.exists(FRONTEND_SRC.resolve(Path.of("apis", "general", "games.ts"))));
        assertTrue(Files.exists(FRONTEND_SRC.resolve(Path.of("apis", "activity", "tier-maker.ts"))));
        assertTrue(Files.exists(FRONTEND_SRC.resolve(Path.of("apis", "activity", "twelve-voting.ts"))));
        assertTrue(Files.exists(FRONTEND_SRC.resolve(Path.of("apis", "activity", "vote-quota.ts"))));

        assertFalse(Files.exists(FRONTEND_SRC.resolve(Path.of("apis", "user", "user.ts"))));
        assertFalse(Files.exists(FRONTEND_SRC.resolve(Path.of("apis", "general", "galgame.ts"))));
        assertFalse(Files.exists(FRONTEND_SRC.resolve(Path.of("apis", "activity", "galGameTwelveVoting.ts"))));
    }
}
