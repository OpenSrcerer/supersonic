import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import personal.opensrcerer.launch.ReactorApplicationLaunchWrapper;
import personal.opensrcerer.launch.ReactorApplicationRuntimeConstants;

import javax.security.auth.login.LoginException;

public class StartupTests {

    @Test
    public void checkEnvironmentVariables() throws LoginException {
        ReactorApplicationLaunchWrapper.run();
        Assertions.assertTrue(ReactorApplicationRuntimeConstants.isDefined("DISCORD_TOKEN"));
    }
}
