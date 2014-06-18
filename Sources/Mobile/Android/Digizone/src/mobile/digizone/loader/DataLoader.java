package mobile.digizone.loader;

import mobile.digizone.D;
import mobile.digizone.parser.ParameterParser;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;

// Trình duyệt web service, ứng dụng thư viện ksoap2 để kết nối Internet
public class DataLoader extends AsyncTask<ParameterParser, Void, String>
{
	
	private String method;
	
	public DataLoader(String method)
	{
		super();
		this.method = method;
	}
	
	// Chạy nền
	public String doInBackground(final ParameterParser... params)
	{
		// Tạo đối tượng xử lý xml để truyền dữ liệu tới và nhận dữ liệu từ web
		// services
		SoapObject response, request = new SoapObject(D.service.namespace, method);
		if (params.length != 0)
		{
			for (PropertyInfo p : params[0].getParameters())
			{
				request.addProperty(p);
			}
		}
		
		// Gửi yêu cầu đi
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;
		
		// Nhận kết quả về
		try
		{
			new HttpTransportSE(D.service.host + D.service.wsdl, D.service.timeout).call(D.service.namespace + method, envelope);
			response = (SoapObject) envelope.bodyIn;
			if (response != null)
			{
				return response.getProperty(0).toString().replace("\\\"", "\"");
			}
			else
			{
				return null;
			}
		}
		catch (Exception e)
		{
			return e.toString();
		}
		finally
		{
			request = response = null;
			envelope = null;
			System.gc();
		}
	}
}