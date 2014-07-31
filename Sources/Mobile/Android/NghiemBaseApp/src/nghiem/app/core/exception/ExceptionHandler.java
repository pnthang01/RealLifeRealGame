package nghiem.app.core.exception;

import nghiem.app.core.utils.Logger;

/**
 * The handler of exceptions, and process sending mail to developer's mail
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler
{
    @Override
    public void uncaughtException(Thread thread, Throwable throwable)
    {
        new Logger(ExceptionHandler.class).errorAndSendMail(throwable);
    }
}
