package nellaibill.tnincometaxcalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("nellaibill.tnincometaxcalculator", appContext.getPackageName());
    }

    public static class Webform extends AppCompatActivity {
        WebView xWebView;
        WebSettings xWebSettings;
        String xUrl="http://nellaibill.com/incometax_calculator/";
        Bundle xBundle;
        Intent xIntent;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            setContentView(R.layout.webform);
            xWebView = (WebView) findViewById(R.id.webView1);

            xWebSettings = xWebView.getSettings();
            xWebSettings.setJavaScriptEnabled(true);
            xWebView.loadUrl(xUrl);
        }



    }
}
