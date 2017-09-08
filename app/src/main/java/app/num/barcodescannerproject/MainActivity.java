package app.num.barcodescannerproject; // Paquete con la aplicación Android.

// Librerías de Android.
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import entity.Device;
import entity.ResultGetDevices;
import entity.ResultWebService;
import restfullwebservice.RestfulWebService;

// Clase principal de la aplicación.
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    // Declaración de variables.
    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;
    private Integer typeQR; // caso dispositivo nuevo
    private String nameNeo = "PRATNEO";
    private String nameTab = "PRATTAB";
    private String namePrinterTab = "PRATZ";
    private String namePrinterVocal = "PRATVL";
    private String nameRfidReader = "EMPAI";
    private String nameMc = "PRATMC";
    private String nameBattery = "BATERIA";
    private String nameHelmet = "CASCOS";
    private Button btn;
    private Button btnReset;
    private Button btnReturnDevice;
    // Almacenamos valores para pasarlos al WebService.
    private String nameDevice;
    private String numOpe;
    private String numHead;
    // ---------
    private ProgressDialog dialog = null;
    private ResultWebService resultWebService;
    private ResultGetDevices resultDevice;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private MediaPlayer mp;
    private AlertDialog alertDialog;
    private AlertDialog errorDialog;
    private boolean buttonDevice;
    private boolean cameraActivated;

    // Proceso del botón
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.qrscan_btn);
        btnReset = (Button) findViewById(R.id.reset_btn);
        btnReturnDevice = (Button) findViewById(R.id.dreturn_btn);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/angrybirds.ttf");
        btn.setTypeface(face);
        btnReset.setTypeface(face);
        btnReturnDevice.setTypeface(face);
        cameraActivated = false;

        selectTextButton();
    //Esto es una prueba

    }

    private void selectTextButton() {
        int typeQR1 = preferences.getInt("typeQR", 0);
        switch (typeQR1) {
            case 0:
                btn.setText("Escanear código QR Dispositivo");
                break;
            case 1:
                btn.setText("Escanear código QR Operario");
                break;
            case 2:
                btn.setText("Escanear código QR Dispositivo");
                break;
        }
    }

    public void resetProcess(View v) {
        clear();
    }

    public void deviceReturned(View v) {
        editor.putInt("typeQR", 2);
        editor.apply();
        mScannerView = new ZXingScannerView(this);
        mScannerView.setAutoFocus(true);// Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
        cameraActivated = true;
    }

    // Proceso lanza escaner QR.
    public void launchQRScanner(View v) {

        mScannerView = new ZXingScannerView(this);
        mScannerView.setAutoFocus(true);// Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
        cameraActivated = true;
    }

    public void handleResult(Result rawResult) {
        // Do something with the result here

        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode
        /*
		 * switch (requestCode) { case ZBAR_SCANNER_REQUEST: case
		 * ZBAR_QR_SCANNER_REQUEST:
		 */
            String str = rawResult.getText();
            str = str.trim();
            // str= str.trim(); // lo reinicia a un valor sin caracteres
            // prohibidos.
            Log.w("qrinv1", "ha leido: " + str + " valor typeQR: " + typeQR);

            int typeQR = preferences.getInt("typeQR", 0);
        Log.w("qrinv1", "ha leido: " + str + " valor typeQR: " + typeQR);

            // para coger el dispositivo.
            switch (typeQR) {
                case 0:
                    // if (typeQR.equals(0)) {
                    // Toast.makeText(this,
                    // "código dispositivo",Toast.LENGTH_LONG).show();

                    // caso Neo
                    if (str.contains(nameNeo)) {
                        // Alert Dialog pregunta si es vocal
                        Log.w("qrinv2", "ha leido: " + str + " valor typeQR: "
                                + typeQR);
                        btn.setText("Escanear código QR Operario"); // Cambiar el
                        // texto del
                        // botón.
                        editor.putInt("typeQR", 1);
                        editor.putString("nameDevice", str);
                        editor.putString("numHead", "");
                        editor.apply();
                        // Toast.makeText(this, "Pistola Neo  " + nameDevice,
                        // Toast.LENGTH_LONG).show();
                        // showAlertDialogVocal(); // Lanza el desplegable del
                        // vocal.

                        // caso Tablet
                    } else if (str.contains(nameTab)) {
                        btn.setText("Escanear código QR Operario");
                        editor.putInt("typeQR", 1);
                        editor.putString("nameDevice", str);
                        editor.apply();

                        // Toast.makeText(this, "Tablet  " + nameDevice,
                        // Toast.LENGTH_LONG).show();

                        // caso Impresora Neo
                    } else if (str.contains(namePrinterTab)) {
                        btn.setText("Escanear código QR Operario");
                        editor.putInt("typeQR", 1);
                        editor.putString("nameDevice", str);
                        editor.apply();

                        // Toast.makeText(this, "Printer Neo  " + nameDevice,
                        // Toast.LENGTH_LONG).show();

                        // caso Impresora Tablet
                    } else if (str.contains(namePrinterVocal)) {
                        btn.setText("Escanear código QR Operario");
                        editor.putInt("typeQR", 1);
                        editor.putString("nameDevice", str);
                        editor.apply();

                        // Toast.makeText(this, "Printer Tablet  " + nameDevice,
                        // Toast.LENGTH_LONG).show();

                        // No es ninguno de los casos anteriores.
                    } else if (str.contains(nameRfidReader)) {
                        btn.setText("Escanear código QR Operario");
                        editor.putInt("typeQR", 1);
                        editor.putString("nameDevice", str);
                        editor.apply();

                        // Toast.makeText(this, "Printer Tablet  " + nameDevice,
                        // Toast.LENGTH_LONG).show();

                        // No es ninguno de los casos anteriores.
                    } else if (str.contains(nameMc)) {
                        btn.setText("Escanear código QR Operario");
                        editor.putInt("typeQR", 1);
                        editor.putString("nameDevice", str);
                        editor.apply();

                        // Toast.makeText(this, "Printer Tablet  " + nameDevice,
                        // Toast.LENGTH_LONG).show();

                        // No es ninguno de los casos anteriores.
                    } else if (str.contains(nameBattery)) {
                        btn.setText("Escanear código QR Operario");
                        editor.putInt("typeQR", 1);
                        editor.putString("nameDevice", str);
                        editor.apply();

                        // Toast.makeText(this, "Printer Tablet  " + nameDevice,
                        // Toast.LENGTH_LONG).show();

                        // No es ninguno de los casos anteriores.
                    } else if (str.contains(nameHelmet)) {
                        btn.setText("Escanear código QR Operario");
                        editor.putInt("typeQR", 1);
                        editor.putString("nameDevice", str);
                        editor.apply();

                        // Toast.makeText(this, "Printer Tablet  " + nameDevice,
                        // Toast.LENGTH_LONG).show();

                        // No es ninguno de los casos anteriores.
                    } else {
                        // cambiar a Alert dialog
                        Log.w("qrinv4", "ha leido: " + str + " valor typeQR: "
                                + typeQR);
                        mp = MediaPlayer.create(this, R.raw.erroroperador);
                        mp.start();
                        Toast.makeText(
                                this,
                                "PRIMERO CÓDIGO DISPOSITIVO, DESPUÉS CÓDIGO OPERADOR !!!",
                                Toast.LENGTH_LONG).show();

                        // alertDialogError(1);
                    }

                    break;
                // caso ya hemos metido el dispositvo, ahora toca el operador.
                // }else if (typeQR.equals(1)) {
                case 1:
                    Log.w("qrinv3", "ha leido: " + str + " valor typeQR: " + typeQR);
                    if (isNumeric(str)) {
                        // new registerDevice().execute();
                        btn.setText("Escanear código QR dispositivo");
                        editor.putInt("typeQR", 0);
                        editor.putString("numOpe", str);
                        editor.apply();
                        // almacenamos el ID de operador
					/*
					 * Toast.makeText(this,"Código operario  " + numOpe,
					 * Toast.LENGTH_LONG).show();
					 */
                        new registerDevice().execute();

                    } else {
					/*
					 * mp = MediaPlayer.create(this, R.raw.errordispositivo);
					 * mp.start();
					 */
                        Toast.makeText(
                                this,
                                "CUIDADO!! ES POSIBLE QUE EL OPERARIO ANTERIOR NO HAYA REGISTRADO CORRECTAMENTE EL DISPOSITIVO, ES POR ELLO QUE SE RESETEARÁ EL PROCESO",
                                Toast.LENGTH_LONG).show();
                        btn.setText("Escanear código QR Operario");
                        Log.w("qrinv5", "ha leido: " + str + " valor typeQR: "
                                + typeQR);
                        alertDialogError(2);
                    }
                    break;
                case 2:
                    if (isNumeric(str)) {
                        // new registerDevice().execute();
                        btn.setText("Escanear código QR dispositivo");
                        editor.putInt("typeQR", 0);
                        editor.putString("numOpe", str);
                        editor.apply();
                        // almacenamos el ID de operador
					/*
					 * Toast.makeText(this,"Código operario  " + numOpe,
					 * Toast.LENGTH_LONG).show();
					 */
                        new deviceReturned().execute();

                    }
                    break;
            }

		/*
		 * break; }
		 */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.removeAllViews(); //<- here remove all the views, it will make an Activity having no View
                mScannerView.stopCamera(); //<- then stop the camera
                resetView();
            }
        }, 100);

        // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
    }

    private void alertDialogError(int type) {
        // TODO Auto-generated method stub
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom);
        dialog.setCancelable(false);
        dialog.setTitle("ERROR");

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText("CUIDADO!! ES POSIBLE QUE EL OPERARIO ANTERIOR NO HAYA REGISTRADO CORRECTAMENTE EL DISPOSITIVO, ES POR ELLO QUE SE RESETEARÁ EL PROCESO!");
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_launcher);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // Comprueba si es solo numérico para comprobar que es un código de
    // operador.
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // Registra dispositivo.
    public class registerDevice extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPreExecute() {
            if (dialog == null) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setCancelable(false);
                dialog.setMessage("Registrando nuevo dispositivo!");
            }
            dialog.show();

        }

        private void dismissProgressDialog() {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected Void doInBackground(Void... unused) {
            numOpe = preferences.getString("numOpe", "");
            nameDevice = preferences.getString("nameDevice", "");
            if (nameDevice.contains(nameNeo)) {
                resultWebService = RestfulWebService.registNeo(nameDevice,
                        numOpe);
            } else if (nameDevice.contains(nameTab)) {
                resultWebService = RestfulWebService.registTab(nameDevice,
                        numOpe);
            } else if (nameDevice.contains(namePrinterTab)) {
                resultWebService = RestfulWebService.registTabPrinter(
                        nameDevice, numOpe);
            } else if (nameDevice.contains(namePrinterVocal)) {
                resultWebService = RestfulWebService.registVocalPrinter(
                        nameDevice, numOpe);
            } else if (nameDevice.contains(nameRfidReader)) {
                resultWebService = RestfulWebService.registRfidReader(
                        nameDevice, numOpe);
            } else if (nameDevice.contains(nameMc)) {
                resultWebService = RestfulWebService.registMc(nameDevice,
                        numOpe);
            } else if (nameDevice.contains(nameBattery)) {
                resultWebService = RestfulWebService.registBattery(nameDevice,
                        numOpe);
            } else if (nameDevice.contains(nameHelmet)) {
                resultWebService = RestfulWebService.registHelmet(nameDevice,
                        numOpe);
            }
            return (null);
        }

        @Override
        protected void onPostExecute(Void unused) {

            if (resultWebService != null) {
                switch (resultWebService.getError()) {
                    case 0:
                        // NO HAY ERROR
                        try {
                            dialog.dismiss();
                            dialog = null;
                            Toast.makeText(MainActivity.this,
                                    resultWebService.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            mp = MediaPlayer.create(MainActivity.this, R.raw.ok);
                            mp.start();
                        } catch (Exception e) {
                            // nothing
                        }
                        break;

                    case 1:
                        try {
                            dialog.dismiss();
                            dialog = null;
                            mp = MediaPlayer.create(MainActivity.this,
                                    R.raw.errorserver);
                            mp.start();
                            Toast.makeText(MainActivity.this,
                                    resultWebService.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            // goToTarget = false;
                        } catch (Exception e) {
                            // nothing
                        }
                        // ERROR WEBSERVICE
                        System.out.println("Error WebService");
                        break;
                    case 2:
                        try {
                            dialog.dismiss();
                            dialog = null;
                            mp = MediaPlayer.create(MainActivity.this,
                                    R.raw.errorserver);
                            mp.start();
                            Toast.makeText(
                                    MainActivity.this,
                                    "Error al registrar dispositivo, vuelva a probar mas tarde",
                                    Toast.LENGTH_LONG).show();
                            // goToTarget = false;
                        } catch (Exception e) {
                            // nothing
                        }
                        // ERROR WEBSERVICE
                        System.out.println("Error WebService");
                        break;

                    default:
                        break;
                }
            }
        }

    }

    public class deviceReturned extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPreExecute() {
            if (dialog == null) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setCancelable(false);
                dialog.setMessage("Buscando dispositivos sin registrar!");
            }
            dialog.show();

        }

        private void dismissProgressDialog() {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected Void doInBackground(Void... unused) {
            numOpe = preferences.getString("numOpe", "");
            resultDevice = RestfulWebService.deviceReturned(numOpe);
            return (null);
        }

        @Override
        protected void onPostExecute(Void unused) {

            if (resultDevice != null) {
                switch (resultDevice.getError()) {
                    case 0:
                        // NO HAY ERROR
                        try {
                            dialog.dismiss();
                            dialog = null;
                            StringBuilder builder = new StringBuilder();

                            for (Device i : resultDevice.getDevice()) {
                                builder.append("Falta por devolver: "
                                        + i.getNameDevice() + "\n");
                            }

                            Toast.makeText(MainActivity.this, builder,
                                    Toast.LENGTH_LONG).show();
                            mp = MediaPlayer.create(MainActivity.this, R.raw.ok);
                            mp.start();
                        } catch (Exception e) {
                            // nothing
                        }
                        break;

                    case 1:
                        try {
                            dialog.dismiss();
                            dialog = null;
                            // goToTarget = false;
                        } catch (Exception e) {
                            // nothing
                        }
                        // ERROR WEBSERVICE
                        System.out.println("Error WebService");
                        break;
                    case 2:
                        try {
                            dialog.dismiss();
                            dialog = null;
                            // goToTarget = false;
                        } catch (Exception e) {
                            // nothing
                        }
                        // ERROR WEBSERVICE
                        System.out.println("Error WebService");
                        break;
                    case 3:
                        try {
                            dialog.dismiss();
                            dialog = null;
                            Toast.makeText(MainActivity.this, "Has devuelto todos los dispositivos correctamente!",
                                    Toast.LENGTH_LONG).show();
                            mp = MediaPlayer.create(MainActivity.this, R.raw.ok);
                            mp.start();
                            // goToTarget = false;
                        } catch (Exception e) {
                            // nothing
                        }
                        // ERROR WEBSERVICE
                        System.out.println("Error WebService");
                        break;
                    default:
                        break;
                }
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraActivated = false;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        if (dialog != null) {
            dialog.dismiss();
        }
        if (errorDialog != null) {
            errorDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        cameraActivated = false;
        try {
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            if (dialog != null) {
                dialog.dismiss();
            }
            if (errorDialog != null) {
                errorDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }


    public void clear() {
        // here you get your prefrences by either of two methods
        editor.clear();
        editor.commit();
        cameraActivated = false;
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void onBackPressed(){
        if(!cameraActivated){
            android.os.Process.killProcess(android.os.Process.myPid());
        }else{
            resetView();
        }

    }

    public void resetView(){
        cameraActivated = false;
        mScannerView.stopCamera();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
