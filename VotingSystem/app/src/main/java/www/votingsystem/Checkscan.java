package www.votingsystem;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DELL PC on 19-01-2017.
 */

public class Checkscan extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;

    private Button scanButton;
    private ImageScanner scanner;

    ProgressDialog progressDialog;
    private boolean barcodeScanned = false;
    private boolean previewing = true;

    ArrayList<HashMap<String,String>> NameListArr = new ArrayList<HashMap<String, String>>();

    JSONParser jsonParser = new JSONParser();

    JSONObject json;

    JSONArray jsonArray = null;

    String get_id,get_mobile,get_shop_location,get_cate,get_subcate,get_division,get_subdivision,get_quantity,get_company;

    String get_barcode="",get_product_id="",location,scanResult="",insert_url="",get_product_number="",get_price="",get_product_name="";

    static {
        System.loadLibrary("iconv");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkscan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initControls();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            get_id= extras.getString("id");
            get_mobile= extras.getString("mobile");

        }

    }

    private void initControls() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();
        mCamera = getCameraInstance();

        // Instance barcode scanner
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        mPreview = new CameraPreview(Checkscan.this, mCamera, previewCb,
                autoFocusCB);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview2);
        preview.addView(mPreview);

        scanButton = (Button) findViewById(R.id.ScanButton2);

        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (barcodeScanned) {
                    barcodeScanned = false;
                    mCamera.setPreviewCallback(previewCb);
                    mCamera.startPreview();
                    previewing = true;
                    mCamera.autoFocus(autoFocusCB);
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            releaseCamera();
            onBackPressed();
        }
        return true;

    }

    @Override
    public void onBackPressed() {
        // If Statement used to get out of my camera view and back to my MainActivity - Same Class
        super.onBackPressed();
        Intent i1 = new Intent(Checkscan.this, ScanActivity.class);
        i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i1);
    }


    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
        }
        return c;
    }

    /* private void releaseCamera() {
         if (mCamera != null) {
             previewing = false;
             mCamera.setPreviewCallback(null);
             mCamera.release();
             mCamera = null;
         }
     }*/
    // release Camera for other applications
    private void releaseCamera() {
        // check if Camera instance exists
        if (mCamera != null) {
            previewing = false;
            // first stop preview
            mCamera.stopPreview();
            // then cancel its preview callback
            mCamera.setPreviewCallback(null);
            // and finally release it
            mCamera.release();
            // sanitize you Camera object holder
            mCamera = null;
        }
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    Camera.PreviewCallback previewCb = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Parameters parameters = camera.getParameters();
            Camera.Size size = parameters.getPreviewSize();

            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);

            Log.d("checkdatalist" ,""+data);

            int result = scanner.scanImage(barcode);

            Log.d("Datalist" ,""+scanner);

            if (result != 0) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();

                SymbolSet syms = scanner.getResults();
                for (Symbol sym : syms) {

                    Log.i("<<<<<<Asset Code>>>>> ",
                            "<<<<Bar Code>>> " + sym.getData());
                    scanResult  = sym.getData().trim();

                    showAlertDialog(scanResult);

                    //Toast.makeText(Scanner_Barcode.this, scanResult,Toast.LENGTH_SHORT).show();

                    barcodeScanned = true;

                    break;
                }
            }
        }
    };

    // Mimic continuous auto-focusing
    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };


    private void showAlertDialog(String message) {

        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.app_name))
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //new loadurl(scanResult).execute();

                        Intent ii = new Intent(getApplicationContext() ,Userprofile.class);
                        ii.putExtra("msg" ,scanResult);
                        startActivity(ii);

                    }
                })

                .show();
    }

}
