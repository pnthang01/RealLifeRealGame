package lvnghiem.app.core.exception;

public class NghiemException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public NghiemException()
	{
		super();
	}

	public NghiemException(String message)
	{
		super(message);
	}

	public NghiemException(Throwable throwable)
	{
		super(throwable);
	}

	public NghiemException(String message, Throwable throwable)
	{
		super(message, throwable);
	}
}
