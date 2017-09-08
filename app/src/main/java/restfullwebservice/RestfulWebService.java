package restfullwebservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Device;
import entity.ResultGetDevices;
import entity.ResultWebService;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

/**
 * Clase que proporciona la logica de negocio para interactuar con el servidor.
 */

public class RestfulWebService extends RestfulWeb {

	private static int TIME_OUT_CONNEXION = 0; // 12 segundos
	private static int TIME_OUT_SOCKET = 60000; // 12 segundos

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	// Para registrar una Neo en el WebService
	public static ResultWebService registNeo(String nameDevice, String numOpe) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ResultWebService result = new ResultWebService();
		result.setError(0);

		try {
			nameDevice = URLEncoder.encode(nameDevice, "UTF-8");
			numOpe = URLEncoder.encode(numOpe, "UTF-8");

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(HTTP_REST_INVENTORY + "registNeo?token="
				+ TAG_TOKEN + "&numSap=" + numOpe + "&nameNeo=" + nameDevice);
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				TIME_OUT_CONNEXION);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOCKET);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpResponse response;

		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			JSONObject jsonObj = getJSONFromInputStream(entity.getContent());

			// Primero miramos el status.
			String status = jsonObj.getString(TAG_STATUS);
			if (status.equals("error")) {
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setError(1);
				result.setMessage(message);
				System.out.println("El dispositivo no existe");
			} else {
				System.out.println("Entra aqui");
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setMessage(message);
				// device.setMessage(message);
				/*
				 * frase.setTextoFrase(dataObj.getString(TAG_TEXTO));
				 * frase.setAutorFrase(dataObj.getString(TAG_AUTOR));
				 */

			}
		} catch (ClientProtocolException e) {
			Log.e("ClientProtocolException", e.getMessage());
			result.setError(2);
		} catch (ConnectTimeoutException e) {
			Log.e("ConnectTimeoutException", e.getMessage());
			result.setError(2);
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
			result.setError(2);
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
			result.setError(2);
		} catch (Exception e) {
			Log.e("Exception", e.getMessage());
			result.setError(2);
		}

		return result;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	// Para registrar una Tab en el WebService
	public static ResultWebService registTab(String nameDevice, String numOpe) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ResultWebService result = new ResultWebService();
		result.setError(0);

		try {
			nameDevice = URLEncoder.encode(nameDevice, "UTF-8");
			numOpe = URLEncoder.encode(numOpe, "UTF-8");

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(HTTP_REST_INVENTORY
				+ "registTablet?token=" + TAG_TOKEN + "&numSap=" + numOpe
				+ "&nameTablet=" + nameDevice);
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				TIME_OUT_CONNEXION);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOCKET);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpResponse response;

		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			JSONObject jsonObj = getJSONFromInputStream(entity.getContent());

			// Primero miramos el status.
			String status = jsonObj.getString(TAG_STATUS);
			if (status.equals("error")) {
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setError(1);
				result.setMessage(message);
				System.out.println("El dispositivo no existe");
			} else {
				System.out.println("Entra aqui");
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setMessage(message);
				// device.setMessage(message);
				/*
				 * frase.setTextoFrase(dataObj.getString(TAG_TEXTO));
				 * frase.setAutorFrase(dataObj.getString(TAG_AUTOR));
				 */

			}
		} catch (ClientProtocolException e) {
			Log.e("ClientProtocolException", e.getMessage());
			result.setError(2);
		} catch (ConnectTimeoutException e) {
			Log.e("ConnectTimeoutException", e.getMessage());
			result.setError(2);
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
			result.setError(2);
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
			result.setError(2);
		} catch (Exception e) {
			Log.e("Exception", e.getMessage());
			result.setError(2);
		}

		return result;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	// Para registrar una TabPrinter en el WebService
	public static ResultWebService registTabPrinter(String nameDevice,
			String numOpe) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ResultWebService result = new ResultWebService();
		result.setError(0);

		try {
			nameDevice = URLEncoder.encode(nameDevice, "UTF-8");
			numOpe = URLEncoder.encode(numOpe, "UTF-8");

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(HTTP_REST_INVENTORY
				+ "registPrinterTablet?token=" + TAG_TOKEN + "&numSap="
				+ numOpe + "&namePrinter=" + nameDevice);
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				TIME_OUT_CONNEXION);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOCKET);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpResponse response;

		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			JSONObject jsonObj = getJSONFromInputStream(entity.getContent());

			// Primero miramos el status.
			String status = jsonObj.getString(TAG_STATUS);
			if (status.equals("error")) {
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setError(1);
				result.setMessage(message);
				System.out.println("El dispositivo no existe");
			} else {
				System.out.println("Entra aqui");
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setMessage(message);
				// device.setMessage(message);
				/*
				 * frase.setTextoFrase(dataObj.getString(TAG_TEXTO));
				 * frase.setAutorFrase(dataObj.getString(TAG_AUTOR));
				 */

			}
		} catch (ClientProtocolException e) {
			Log.e("ClientProtocolException", e.getMessage());
			result.setError(2);
		} catch (ConnectTimeoutException e) {
			Log.e("ConnectTimeoutException", e.getMessage());
			result.setError(2);
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
			result.setError(2);
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
			result.setError(2);
		} catch (Exception e) {
			Log.e("Exception", e.getMessage());
			result.setError(2);
		}

		return result;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	// Para registrar una VocalPrinter en el WebService
	public static ResultWebService registVocalPrinter(String nameDevice,
			String numOpe) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ResultWebService result = new ResultWebService();
		result.setError(0);

		try {
			nameDevice = URLEncoder.encode(nameDevice, "UTF-8");
			numOpe = URLEncoder.encode(numOpe, "UTF-8");

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(HTTP_REST_INVENTORY
				+ "registPrinterVocal?token=" + TAG_TOKEN + "&numSap=" + numOpe
				+ "&namePrinter=" + nameDevice);
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				TIME_OUT_CONNEXION);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOCKET);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpResponse response;

		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			JSONObject jsonObj = getJSONFromInputStream(entity.getContent());

			// Primero miramos el status.
			String status = jsonObj.getString(TAG_STATUS);
			if (status.equals("error")) {
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setError(1);
				result.setMessage(message);
				System.out.println("El dispositivo no existe");
			} else {
				System.out.println("Entra aqui");
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setMessage(message);
				// device.setMessage(message);
				/*
				 * frase.setTextoFrase(dataObj.getString(TAG_TEXTO));
				 * frase.setAutorFrase(dataObj.getString(TAG_AUTOR));
				 */

			}
		} catch (ClientProtocolException e) {
			Log.e("ClientProtocolException", e.getMessage());
			result.setError(2);
		} catch (ConnectTimeoutException e) {
			Log.e("ConnectTimeoutException", e.getMessage());
			result.setError(2);
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
			result.setError(2);
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
			result.setError(2);
		} catch (Exception e) {
			Log.e("Exception", e.getMessage());
			result.setError(2);
		}

		return result;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	// Para registrar una Vocal en el WebService
	public static ResultWebService registMc(String nameDevice, String numOpe) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ResultWebService result = new ResultWebService();
		result.setError(0);

		try {
			nameDevice = URLEncoder.encode(nameDevice, "UTF-8");
			numOpe = URLEncoder.encode(numOpe, "UTF-8");

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(HTTP_REST_INVENTORY + "registMc?token="
				+ TAG_TOKEN + "&numSap=" + numOpe + "&nameMc=" + nameDevice);
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				TIME_OUT_CONNEXION);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOCKET);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpResponse response;

		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			JSONObject jsonObj = getJSONFromInputStream(entity.getContent());

			// Primero miramos el status.
			String status = jsonObj.getString(TAG_STATUS);
			if (status.equals("error")) {
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setError(1);
				result.setMessage(message);
				System.out.println("El dispositivo no existe");
			} else {
				System.out.println("Entra aqui");
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setMessage(message);
				// device.setMessage(message);
				/*
				 * frase.setTextoFrase(dataObj.getString(TAG_TEXTO));
				 * frase.setAutorFrase(dataObj.getString(TAG_AUTOR));
				 */

			}
		} catch (ClientProtocolException e) {
			Log.e("ClientProtocolException", e.getMessage());
			result.setError(2);
		} catch (ConnectTimeoutException e) {
			Log.e("ConnectTimeoutException", e.getMessage());
			result.setError(2);
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
			result.setError(2);
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
			result.setError(2);
		} catch (Exception e) {
			Log.e("Exception", e.getMessage());
			result.setError(2);
		}

		return result;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	// Para registrar una Vocal en el WebService
	public static ResultWebService registHelmet(String nameDevice, String numOpe) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ResultWebService result = new ResultWebService();
		result.setError(0);

		try {
			nameDevice = URLEncoder.encode(nameDevice, "UTF-8");
			numOpe = URLEncoder.encode(numOpe, "UTF-8");

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(HTTP_REST_INVENTORY
				+ "registHelmet?token=" + TAG_TOKEN + "&numSap=" + numOpe
				+ "&nameHelmet=" + nameDevice);
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				TIME_OUT_CONNEXION);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOCKET);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpResponse response;

		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			JSONObject jsonObj = getJSONFromInputStream(entity.getContent());

			// Primero miramos el status.
			String status = jsonObj.getString(TAG_STATUS);
			if (status.equals("error")) {
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setError(1);
				result.setMessage(message);
				System.out.println("El dispositivo no existe");
			} else {
				System.out.println("Entra aqui");
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setMessage(message);
				// device.setMessage(message);
				/*
				 * frase.setTextoFrase(dataObj.getString(TAG_TEXTO));
				 * frase.setAutorFrase(dataObj.getString(TAG_AUTOR));
				 */

			}
		} catch (ClientProtocolException e) {
			Log.e("ClientProtocolException", e.getMessage());
			result.setError(2);
		} catch (ConnectTimeoutException e) {
			Log.e("ConnectTimeoutException", e.getMessage());
			result.setError(2);
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
			result.setError(2);
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
			result.setError(2);
		} catch (Exception e) {
			Log.e("Exception", e.getMessage());
			result.setError(2);
		}

		return result;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	// Para registrar una Vocal en el WebService
	public static ResultWebService registBattery(String nameDevice,
			String numOpe) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ResultWebService result = new ResultWebService();
		result.setError(0);

		try {
			nameDevice = URLEncoder.encode(nameDevice, "UTF-8");
			numOpe = URLEncoder.encode(numOpe, "UTF-8");

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(HTTP_REST_INVENTORY
				+ "registBattery?token=" + TAG_TOKEN + "&numSap=" + numOpe
				+ "&nameBattery=" + nameDevice);
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				TIME_OUT_CONNEXION);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOCKET);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpResponse response;

		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			JSONObject jsonObj = getJSONFromInputStream(entity.getContent());

			// Primero miramos el status.
			String status = jsonObj.getString(TAG_STATUS);
			if (status.equals("error")) {
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setError(1);
				result.setMessage(message);
				System.out.println("El dispositivo no existe");
			} else {
				System.out.println("Entra aqui");
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setMessage(message);
				// device.setMessage(message);
				/*
				 * frase.setTextoFrase(dataObj.getString(TAG_TEXTO));
				 * frase.setAutorFrase(dataObj.getString(TAG_AUTOR));
				 */

			}
		} catch (ClientProtocolException e) {
			Log.e("ClientProtocolException", e.getMessage());
			result.setError(2);
		} catch (ConnectTimeoutException e) {
			Log.e("ConnectTimeoutException", e.getMessage());
			result.setError(2);
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
			result.setError(2);
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
			result.setError(2);
		} catch (Exception e) {
			Log.e("Exception", e.getMessage());
			result.setError(2);
		}

		return result;
	}

	// Para registrar una Vocal en el WebService
	public static ResultWebService registRfidReader(String nameDevice,
			String numOpe) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ResultWebService result = new ResultWebService();
		result.setError(0);

		try {
			nameDevice = URLEncoder.encode(nameDevice, "UTF-8");
			numOpe = URLEncoder.encode(numOpe, "UTF-8");

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(HTTP_REST_INVENTORY
				+ "registRfidReader?token=" + TAG_TOKEN + "&numSap=" + numOpe
				+ "&nameRfidReader=" + nameDevice);
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				TIME_OUT_CONNEXION);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOCKET);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpResponse response;

		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			JSONObject jsonObj = getJSONFromInputStream(entity.getContent());

			// Primero miramos el status.
			String status = jsonObj.getString(TAG_STATUS);
			if (status.equals("error")) {
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setError(1);
				result.setMessage(message);
				System.out.println("El dispositivo no existe");
			} else {
				System.out.println("Entra aqui");
				String message = jsonObj.getString(TAG_MESSAGE);
				result.setMessage(message);
				// device.setMessage(message);
				/*
				 * frase.setTextoFrase(dataObj.getString(TAG_TEXTO));
				 * frase.setAutorFrase(dataObj.getString(TAG_AUTOR));
				 */

			}
		} catch (ClientProtocolException e) {
			Log.e("ClientProtocolException", e.getMessage());
			result.setError(2);
		} catch (ConnectTimeoutException e) {
			Log.e("ConnectTimeoutException", e.getMessage());
			result.setError(2);
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
			result.setError(2);
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
			result.setError(2);
		} catch (Exception e) {
			Log.e("Exception", e.getMessage());
			result.setError(2);
		}

		return result;
	}

	public static ResultGetDevices deviceReturned(String numSap) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ResultGetDevices resultDevices = new ResultGetDevices();
		resultDevices.setError(1);

		// HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(HTTP_REST_INVENTORY
				+ "deviceReturned?token=" + TAG_TOKEN + "&numSap=" + numSap);
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				TIME_OUT_CONNEXION);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOCKET);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpResponse response;

		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			JSONObject jsonObj = getJSONFromInputStream(entity.getContent());

			// Primero miramos el status.
			System.out.println("Entra aqui");
			List<Device> devices = new ArrayList<Device>();
			System.out.println("Objeto: " + jsonObj.toString());
			if(!jsonObj.isNull("nada")){
				System.out.println("nada");
				resultDevices.setError(3);
			}
			// Tablet
			if (!jsonObj.isNull(TAG_TABLET)) {
				System.out.println("Entra aqui2");
					JSONObject json2 = jsonObj.getJSONObject(TAG_TABLET);
					//JSONObject json2 = jsonObj.getJSONObject(TAG_TABLET);
					System.out.println("Json: " + json2.toString());
				
				Device device = new Device();
				device.setIdDevice(new String(json2.getString(TAG_ID2).getBytes(
						"ISO-8859-1"), "UTF-8"));
				device.setNameDevice(new String(json2.getString(TAG_NAME).getBytes(
						"ISO-8859-1"), "UTF-8"));
				devices.add(device);

				resultDevices.setError(0);
				resultDevices.setDevice(devices);
			}

			// ReaderRFID
			if (!jsonObj.isNull(TAG_READER_RFID)) {
				System.out.println("Entra aqui2");
				JSONObject json2 = jsonObj.getJSONObject(TAG_READER_RFID);
				System.out.println("Json: " + json2.getString("ID"));
				Device device = new Device();
				device.setIdDevice(new String(json2.getString(TAG_ID2).getBytes(
						"ISO-8859-1"), "UTF-8"));
				device.setNameDevice(new String(json2.getString(TAG_NAME).getBytes(
						"ISO-8859-1"), "UTF-8"));
				devices.add(device);

				resultDevices.setError(0);
				resultDevices.setDevice(devices);
			}

			// TabletPrinter
			if (!jsonObj.isNull(TAG_TAB_PRINTER)) {

				System.out.println("Entra aqui2");
				JSONObject json2 = jsonObj.getJSONObject(TAG_TAB_PRINTER);
				System.out.println("Json: " + json2.getString("ID"));
				Device device = new Device();
				device.setIdDevice(new String(json2.getString(TAG_ID2).getBytes(
						"ISO-8859-1"), "UTF-8"));
				device.setNameDevice(new String(json2.getString(TAG_NAME).getBytes(
						"ISO-8859-1"), "UTF-8"));
				devices.add(device);

				resultDevices.setError(0);
				resultDevices.setDevice(devices);
			}

			// Battery
			if (!jsonObj.isNull(TAG_BATTERY)) {
				System.out.println("Entra aqui2");
				JSONObject json2 = jsonObj.getJSONObject(TAG_BATTERY);
				System.out.println("Json: " + json2.getString("ID"));
				Device device = new Device();
				device.setIdDevice(new String(json2.getString(TAG_ID2).getBytes(
						"ISO-8859-1"), "UTF-8"));
				device.setNameDevice(new String(json2.getString(TAG_NAME).getBytes(
						"ISO-8859-1"), "UTF-8"));
				devices.add(device);

				resultDevices.setError(0);
				resultDevices.setDevice(devices);
			}

			// MC
			if (!jsonObj.isNull(TAG_MC)) {
				System.out.println("Entra aqui2");
				JSONObject json2 = jsonObj.getJSONObject(TAG_MC);
				System.out.println("Json: " + json2.getString("ID"));
				Device device = new Device();
				device.setIdDevice(new String(json2.getString(TAG_ID2).getBytes(
						"ISO-8859-1"), "UTF-8"));
				device.setNameDevice(new String(json2.getString(TAG_NAME).getBytes(
						"ISO-8859-1"), "UTF-8"));
				devices.add(device);

				resultDevices.setError(0);
				resultDevices.setDevice(devices);
			}

			// VocalPrinter
			if (!jsonObj.isNull(TAG_PRINTER_VOCAL)) {
				System.out.println("Entra aqui2");
				JSONObject json2 = jsonObj.getJSONObject(TAG_PRINTER_VOCAL);
				System.out.println("Json: " + json2.getString("ID"));
				Device device = new Device();
				device.setIdDevice(new String(json2.getString(TAG_ID2).getBytes(
						"ISO-8859-1"), "UTF-8"));
				device.setNameDevice(new String(json2.getString(TAG_NAME).getBytes(
						"ISO-8859-1"), "UTF-8"));
				devices.add(device);

				resultDevices.setError(0);
				resultDevices.setDevice(devices);
			}

			// Helmet
			if (!jsonObj.isNull(TAG_HELMET)) {
				System.out.println("Entra aqui2");
				JSONObject json2 = jsonObj.getJSONObject(TAG_HELMET);
				System.out.println("Json: " + json2.getString("ID"));
				Device device = new Device();
				device.setIdDevice(new String(json2.getString(TAG_ID2).getBytes(
						"ISO-8859-1"), "UTF-8"));
				device.setNameDevice(new String(json2.getString(TAG_NAME).getBytes(
						"ISO-8859-1"), "UTF-8"));
				devices.add(device);

				resultDevices.setError(0);
				resultDevices.setDevice(devices);
			}

			System.out.println("Dispositivos: " + devices);

		} catch (ClientProtocolException e) {
			Log.e("ClientProtocolException", e.getMessage());
			resultDevices.setError(2);
		} catch (ConnectTimeoutException e) {
			Log.e("ConnectTimeoutException", e.getMessage());
			resultDevices.setError(2);
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
			resultDevices.setError(2);
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
			resultDevices.setError(2);
		} catch (Exception e) {
			Log.e("Exception", e.getMessage());
			resultDevices.setError(2);
		}

		return resultDevices;
	}

	// Com�n para todos
	private static JSONObject getJSONFromInputStream(InputStream is) {
		JSONObject jObj = null;
		String json = null;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			is.close();
			json = sb.toString();

			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON getJSONFromInputS",
					"JSONException " + e.toString());
		} catch (Exception e) {
			Log.e("Error getJSONFromInputS", "Exception " + e.toString());
		}

		return jObj;
	}
}