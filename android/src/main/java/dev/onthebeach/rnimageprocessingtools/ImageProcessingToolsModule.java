package dev.onthebeach.rnimageprocessingtools;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.IOException;

public class ImageProcessingToolsModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public ImageProcessingToolsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    //Récupération d'une image d'une URL au format bitmap
    public static Bitmap getBitmapFromURL(String imageSource) {
        try {
            URL imageUrl = new URL(imageSource);
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap imageAsBitmap = BitmapFactory.decodeStream(input);
            return imageAsBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
    
    public static Bitmap applyGreyscale(Bitmap bitmapSource) {
        //Définition des nuances pour obenir un noir et blanc
        final double GS_RED = 0.299;
        final double GS_GREEN = 0.587;
        final double GS_BLUE = 0.114;
        
        Bitmap bitmapOutput = Bitmap.createBitmap(bitmapSource.getWidth(), bitmapSource.getHeight(), bitmapSource.getConfig());
        int A, R, G, B;
        int pixel;
        
        // Récupération de la taille de l'image
        int width = bitmapSource.getWidth();
        int height = bitmapSource.getHeight();
 
        // On va modifier les pixels de l'image
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                //Récupération du pixel de l'image
                pixel = bitmapSource.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
                //Récupération du pixel transformé
                bitmapOutput.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
 
        // Renvoie du Bitmap transformé
        return bitmapOutput;
    }

    @Override
    public String getName() {
        return "ImageProcessingTools";
    }

    @ReactMethod
    public void getBlackAndWhiteImageFromUrl(String imageUrl, Callback callback) {
        Bitmap imageBitmap = applyGreyscale(getBitmapFromURL(imageUrl));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String imageDataAsString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        callback.invoke(imageDataAsString);
    }
}
