package utils.logger;

import java.util.logging.Logger;

public class Log {

    public static final Logger LOGGER = Logger.getLogger(
            Thread.currentThread().getStackTrace()[0].getClassName());
}
