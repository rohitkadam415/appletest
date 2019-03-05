package com.example.webviewinterface;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;



public class WebAppInterface {
    Context mContext;
    String TAG=WebAppInterface.class.getSimpleName();
    // Instantiate the interface and set the context
    WebAppInterface(Context c) {
        mContext = c;
    }

    // Show a toast from the web page
    @JavascriptInterface
    public void showToast(String data) {
        Log.i(TAG, "Android Data: "+data);
    }

    @JavascriptInterface
    public int getAndroidVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    @JavascriptInterface
    public void showAndroidVersion(String versionName) {
        Toast.makeText(mContext, versionName, Toast.LENGTH_SHORT).show();
    }

}