package lvnghiem.app.core.utils;

import lvnghiem.app.core.mail.Mail;
import android.os.AsyncTask;

public final class EmailUtils
{
	private static final Logger LOG = new Logger(EmailUtils.class);

	private static final String DEFAULT_EMAIL_FROM = "le.vinhnghiem@gmail.com";
	private static final String DEFAULT_EMAIL_TO = "le.vinhnghiem@gmail.com";
	private static final String DEFAULT_EMAIL_PASS = "He!1@ngeI";

	private EmailUtils()
	{
	}

	/**
	 * Send the email with the message.
	 * 
	 * @param message
	 * 
	 * @see for configuring: {@link Config#ERROR_SEND_FROM_EMAIL},
	 *      {@link Config#ERROR_SEND_FROM_PASSWORD},
	 *      {@link Config#ERROR_SEND_SUBJECT},
	 *      {@link Config#ERROR_SEND_TO_EMAILS}
	 */
	public static void sendMail(String title, String message)
	{
		EmailSendTask emailSendTask = new EmailSendTask();
		emailSendTask.execute(title, message);
	}

	private static class EmailSendTask extends AsyncTask<String, Void, Boolean>
	{
		@Override
		protected Boolean doInBackground(String... params)
		{
			String title = params[0];
			String message = params[1];
			String subject = title;

			Mail mail = new Mail(DEFAULT_EMAIL_FROM, DEFAULT_EMAIL_PASS);
			mail.setTos(new String[] { DEFAULT_EMAIL_TO });
			mail.setSubject(subject);
			mail.setBody(message);

			try
			{
				return mail.send();
			}
			catch (Throwable throwable)
			{
				LOG.error("Could not send email", throwable);
			}

			return false;
		}

	}

}
