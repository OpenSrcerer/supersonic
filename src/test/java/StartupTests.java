import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import personal.opensrcerer.launch.SupersonicLaunchWrapper;
import personal.opensrcerer.launch.SupersonicRuntimeConstants;

import javax.security.auth.login.LoginException;

public class StartupTests {

    @Test
    public void checkEnvironmentVariables() throws LoginException {
        SupersonicLaunchWrapper.run();
        Assertions.assertTrue(SupersonicRuntimeConstants.isDefined("DISCORD_TOKEN"));
    }
}
