package nellaibill.tnincometaxcalculator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class Grid_HomePage  extends AppCompatActivity {
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;
    Intent xIntent;
    AdRequest xBanner;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_homepage);
            MobileAds.initialize(this, getString(R.string.admob_app_id));
            mAdView =(AdView) findViewById(R.id.adView);
            xBanner = new AdRequest.Builder()

                    // Add a test device to show Test Ads
                    //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    //.addTestDevice("CC5F2C72DF2B356BBF0DA198")
                    .build();
            mAdView.loadAd(xBanner);
        //set grid view item
            Bitmap xQuickTax = BitmapFactory.decodeResource(this.getResources(), R.drawable.taxprinter);
            Bitmap xTaxPrinter = BitmapFactory.decodeResource(this.getResources(), R.drawable.quicktax);
            Bitmap xTaxSlab = BitmapFactory.decodeResource(this.getResources(), R.drawable.taxslab);
            Bitmap xDeduction = BitmapFactory.decodeResource(this.getResources(), R.drawable.deduction);
            Bitmap xAssYear = BitmapFactory.decodeResource(this.getResources(), R.drawable.assyear);
            Bitmap xImportantLinks = BitmapFactory.decodeResource(this.getResources(), R.drawable.importantlinks);
            Bitmap xItForm = BitmapFactory.decodeResource(this.getResources(), R.drawable.itform);
            Bitmap xRefund = BitmapFactory.decodeResource(this.getResources(), R.drawable.refund);
            Bitmap xItNews = BitmapFactory.decodeResource(this.getResources(), R.drawable.itnews);
            Bitmap xContactUs = BitmapFactory.decodeResource(this.getResources(), R.drawable.contactus);
            Bitmap xShare = BitmapFactory.decodeResource(this.getResources(), R.drawable.share);
            Bitmap xDeveloper = BitmapFactory.decodeResource(this.getResources(), R.drawable.developer);

            gridArray.add(new Item(xQuickTax,"Tax Printer"));
            gridArray.add(new Item(xTaxPrinter,"Quick Tax"));
            gridArray.add(new Item(xTaxSlab,"Tax Slab"));
            gridArray.add(new Item(xDeduction,"Deduction "));
            gridArray.add(new Item(xAssYear,"Ass.Year"));
            gridArray.add(new Item(xImportantLinks,"Imp Link"));
            gridArray.add(new Item(xItForm,"IT Form"));
            gridArray.add(new Item(xRefund,"Ref.Status"));
            gridArray.add(new Item(xItNews,"IT News"));
            gridArray.add(new Item(xContactUs,"ContactUs"));
            gridArray.add(new Item(xShare,"Share"));
            gridArray.add(new Item(xDeveloper,"Developer"));

        gridView = (GridView) findViewById(R.id.gridView1);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Item item = (Item) parent.getItemAtPosition(position);

             if(position==0)
             {
                 xIntent = new Intent(Grid_HomePage.this, Webform.class);
                 xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/taxprinter.php");
             }
                if(position==1)
                {
                    xIntent = new Intent(Grid_HomePage.this, Webform.class);
                    xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/quickpay.php");
                }
                if(position==2)
                {
                    xIntent = new Intent(Grid_HomePage.this, Webform.class);
                    xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/taxslab.html");
                }
                if(position==3)
                {
                    xIntent = new Intent(Grid_HomePage.this, Webform.class);
                    xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/deduction.html");
                }
                if(position==4)
                {
                    xIntent = new Intent(Grid_HomePage.this, Webform.class);
                    xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/assyear.html");
                }
                if(position==5)
                {
                    xIntent = new Intent(Grid_HomePage.this, Webform.class);
                    xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/implinks.html");
                }
                if(position==6)
                {
                    xIntent = new Intent(Grid_HomePage.this, Webform.class);
                    xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/deduction.html");
                }
                if(position==7)
                {
                    xIntent = new Intent(Grid_HomePage.this, Webform.class);
                    xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/deduction.html");
                }    if(position==8)
                {
                    xIntent = new Intent(Grid_HomePage.this, Webform.class);
                    xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/deduction.html");
                }    if(position==9)
                {
                    xIntent = new Intent(Grid_HomePage.this, Webform.class);
                    xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/contactus.php");
                }    if(position==10)
                {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "TN INCOME TAX CALCULATOR");
                    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this site!");
                    startActivity(Intent.createChooser(intent, "Share"));    }
                if(position==11)
                {
                    xIntent = new Intent(Grid_HomePage.this, Webform.class);
                    xIntent.putExtra("url","http://nellaibill.com/incometax_calculator/developer.php");
                }



                startActivity(xIntent);
            }
        });
}
catch(Exception e)
{
    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
}
    }

    /**
     * Prepare some dummy data for gridview

    private ArrayList<Item> getData() {
        final ArrayList<Item> imageItems = new ArrayList<>();

        for (int i = 0; i < gridArray.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new Item(bitmap, "Image#" + i));
        }
        return imageItems;
    } */
}