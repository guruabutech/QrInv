package restfullwebservice;

public class RestfulWeb {

	/**
	 * Constante con la información del Servidor.
	 */
	protected final static String HTTP_REST_INVENTORY = "http://itteamprat.manitec.xyz/appservice/";
	/**
	 * Tag inicial de todas las llamadas, para saber si la llamada ha sido
	 * correcta.
	 */
	protected static final String TAG_STATUS = "status";
	
	protected static final String TAG_TOKEN = "HT0R4LfEqrSJkfKC17ib7T01C33XTfsv";
	
	protected static final String TAG_DATA = "devices";
	
	protected static final String TAG_DATA2 = "device";
	
	protected static final String TAG_MESSAGE = "message";

	/**
	 * Tag data, contiene una array con los datos en formato json.
	 */
	protected static final String TAG_ID = "id";
	protected static final String TAG_ID2 = "ID";

	/**
	 * Tag id de usuario
	 */
	protected static final String TAG_NAME = "name";

	/**
	 * Tag con la información del sexo del usuario
	 */
	protected static final String TAG_IP = "ip";
	
	protected static final String TAG_SERIAL = "serial_number";
	
	protected static final String TAG_PLACE = "place";
	
	protected static final String TAG_SECTOR = "sector";
	
	protected static final String TAG_DATE = "date_inventory";

	protected static final String TAG_BATTERY = "battery";
	protected static final String TAG_TABLET = "tablet";
	protected static final String TAG_READER_RFID = "readerRfid";
	protected static final String TAG_HELMET = "helmet";
	protected static final String TAG_MC = "mc";
	protected static final String TAG_TAB_PRINTER = "printerTablet";
	protected static final String TAG_VOCAL_PRINTER = "vocalPrinter";
	protected static final String TAG_PRINTER_VOCAL = "printerVocal";
}
