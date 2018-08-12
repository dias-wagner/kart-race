package dias.wagner.kartrace;

import java.io.IOException;
import java.security.Permission;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

    protected static class ExitException extends SecurityException {
        private static final long serialVersionUID = -5082961930121897964L;

		public final int status;

        public ExitException(int status) {
            super("Exit status: " + status);
            this.status = status;
        }
    }

    private static class NoExitSecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            // allow anything.
        }

        @Override
        public void checkPermission(Permission perm, Object context) {
            // allow anything.
        }

        @Override
        public void checkExit(int status) {
            super.checkExit(status);
            throw new ExitException(status);
        }
    }

    @Before
    public void setUp() throws Exception {
        System.setSecurityManager(new NoExitSecurityManager());
    }

    @After
    public void tearDown() throws Exception {
        System.setSecurityManager(null);
    }

    @Test
    public void testAppSuccess() throws IOException {
        final String projectDir = System.getProperty("user.dir");

        App.main(new String[] { projectDir + "/target/classes/race-log.txt" });
    }

    @Test(expected=ExitException.class)
    public void testAppInvalidFile() throws IOException {
        App.main(new String[] { "bla" });
    }

    @Test(expected=ExitException.class)
    public void testAppNoFile() throws IOException {
        String[] args = new String[0];

        App.main(args);
    }
}