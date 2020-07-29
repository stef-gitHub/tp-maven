import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class TestLog4j {

    private static final Logger LOG = (Logger) LogManager.getLogger(TestLog4j.class);

    public static void main(String[] args) {
        LOG.debug("This Will Be Printed On Debug");
        LOG.info("This Will Be Printed On Info");
        LOG.warn("This Will Be Printed On Warn");
        LOG.error("This Will Be Printed On Error");
        LOG.fatal("This Will Be Printed On Fatal");
        LOG.info("Appending string: {}.", "Hello, World");
    }
}
