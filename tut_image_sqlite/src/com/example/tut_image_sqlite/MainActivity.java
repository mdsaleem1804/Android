package com.example.tut_image_sqlite;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
 
public class MainActivity extends Activity implements OnClickListener{
 
    private ImageView imageview=null;
    private Button btninsert=null;
    private Button btnretrive=null;
    private Button btncaptureorselect=null;
    private DbConnection mdb=null;
    private SQLiteDatabase db=null;
    private Cursor c=null;
    private byte[] img=null;
    private static final String DATABASE_NAME = "ImageDb.db";
    public static final int DATABASE_VERSION = 1;
    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        btninsert=(Button)findViewById(R.id.button_insert);
           btnretrive= (Button)findViewById(R.id.button_retrieve);
        btncaptureorselect= (Button)findViewById(R.id.btncaptureorselect);
        imageview= (ImageView)findViewById(R.id.imageView_image);
        btninsert.setOnClickListener(this);
        btnretrive.setOnClickListener(this);
        btncaptureorselect.setOnClickListener(this);
        mdb=new DbConnection(getApplicationContext(), DATABASE_NAME,null, DATABASE_VERSION);
        db=mdb.getWritableDatabase();
      
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
     
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
     
            Uri uri = data.getData();
     
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                img=bos.toByteArray();
                imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onClick(View arg0) {
         
        if(btncaptureorselect==arg0)
        {
        	Intent intent = new Intent();
        	// Show only images, no videos or anything else
        	intent.setType("image/*");
        	intent.setAction(Intent.ACTION_GET_CONTENT);
        	// Always show the chooser (if there are multiple options available)
        	startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
           
        }
        else if(btninsert==arg0)
        {
        	 ContentValues cv=new ContentValues();
             cv.put("image", img);
             db.insert("tableimage", null, cv);
             Toast.makeText(this, "inserted successfully", Toast.LENGTH_SHORT).show();
        }
        else if(btnretrive==arg0)
        {
        	try
        	{
        		
        	
                String[] col={"image"};
                c=db.query("tableimage", col, null, null, null, null, null);
                
                if(c!=null){
                    c.moveToFirst();
                    do{
                        img=c.getBlob(c.getColumnIndex("image"));
                       }while(c.moveToNext());
                }
                Bitmap b1=BitmapFactory.decodeByteArray(img, 0, img.length);
              
                 imageview.setImageBitmap(b1);
                 Toast.makeText(this, "Retrive successfully", Toast.LENGTH_SHORT).show();
            
        }
    	catch(Exception e)
    	{
    		String xError=e.toString();
    		   Toast.makeText(this, xError, Toast.LENGTH_SHORT).show();
    	}
        }
 
}}