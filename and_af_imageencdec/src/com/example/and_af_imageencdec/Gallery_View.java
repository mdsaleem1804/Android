package com.example.and_af_imageencdec;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;

public class Gallery_View extends Activity {
 @SuppressWarnings("deprecation")
 Gallery Imagegallery;
 Integer[] GalleryImagesList = 
 {
 R.drawable.one,
 R.drawable.two,
 R.drawable.three,
 R.drawable.four,
 R.drawable.five,
 R.drawable.six,
 R.drawable.seven
 };
 ImageView imgGalleryImage;
 
 @Override
 protected void onCreate(Bundle savedInstanceState) 
 {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.galleryview);
 
 imgGalleryImage = (ImageView)findViewById(R.id.imgGalleryImage);
 imgGalleryImage.setImageResource(R.drawable.one);
 
 Imagegallery = (Gallery)findViewById(R.id.gallery);
 Imagegallery.setAdapter(new ImageAdapter(this));
 Imagegallery.setOnItemClickListener(new OnItemClickListener()
 {
 @Override
 public void onItemClick(AdapterView<?> parent, View view,
 int position, long id) 
 {
 imgGalleryImage.setImageResource(GalleryImagesList[position]);
 }
 });
 }
 
 private class ImageAdapter extends BaseAdapter
 {
 Context context;
 public ImageAdapter(Context context) 
 {
 this.context = context;
 }
 @Override
 public int getCount() 
 {
 return GalleryImagesList.length;
 }

 @Override
 public Object getItem(int position) 
 {
 return GalleryImagesList[position];
 }

 @Override
 public long getItemId(int position) 
 {
 return position;
 }

 @Override
 public View getView(int position, View convertView, ViewGroup parent) 
 {
 ImageView imageView = new ImageView(this.context);
 imageView.setImageResource(GalleryImagesList[position]);
 imageView.setLayoutParams(new Gallery.LayoutParams(150, 200));
 imageView.setScaleType(ScaleType.FIT_XY);
 
 return imageView;
 }
 
 }
}