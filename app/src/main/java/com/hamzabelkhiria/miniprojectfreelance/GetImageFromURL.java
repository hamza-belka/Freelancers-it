package com.hamzabelkhiria.miniprojectfreelance;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

public class GetImageFromURL extends AsyncTask<String,Void,Bitmap>
{
     Bitmap bitmap;
    ImageView imgView;
    public GetImageFromURL(ImageView imgv)
    {
        this.imgView=imgv;
    }
    @Override
    protected Bitmap doInBackground(String... url) {
        String urldisplay=url[0];
        bitmap=null;

        try{

            InputStream ist=new java.net.URL(urldisplay).openStream();
            bitmap= BitmapFactory.decodeStream(ist);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){

        super.onPostExecute(bitmap);
        imgView.setImageBitmap(bitmap);
    }
}
