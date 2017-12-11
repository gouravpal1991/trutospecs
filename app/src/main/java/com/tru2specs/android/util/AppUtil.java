package com.tru2specs.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Selva
 */

public class AppUtil {

    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static File getMainDirectoryName(Context context) {

        File mainDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Screen");

        if (!mainDir.exists()) {
            if (mainDir.mkdir())
                Log.i("Create Directory", "Main Directory Created : " + mainDir);
        }
        return mainDir;
    }

    /*  Store taken screenshot into given path  */
    public static File store(Bitmap bm, String fileName, File saveFilePath) {
        File dir = new File(saveFilePath.getAbsolutePath());
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(saveFilePath.getAbsolutePath(), fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    // get device screen height in dp
    public static int getScreenHeight() {

        int height = 0;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        height = Math.round(displayMetrics.heightPixels / displayMetrics.density);
        Log.d("getScreenHeight", "screen height : " + height + " dp");
        return height;
    }

    // convert dp to px
    public static int dpToPx(int dp) {
        return (int) (dp* getApplicationContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int pxToDp(int px) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, displayMetrics);
        return dp;
    }
}
