package com.example.webviewinterface;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);

    WebView webView = (WebView) findViewById(R.id.webview);


    webView.addJavascriptInterface(new WebAppInterface(this), "AndroidInterface"); // To call
    // methods in Android from using js in the html, AndroidInterface.showToast, AndroidInterface.getAndroidVersion etc


    WebSettings webSettings = webView.getSettings();
    webSettings.setDomStorageEnabled(true);
    webSettings.setJavaScriptEnabled(true);
   // webView.setWebViewClient(new MyWebViewClient());
    webView.setWebChromeClient(new MyWebViewClient1());
    webView.getSettings().setAllowFileAccess(true);
    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    webView.getSettings().setUseWideViewPort(true);
    webView.getSettings().setLoadWithOverviewMode(true);
    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    webView.loadUrl("file:///android_asset/index.html");
  }


  private class MyWebViewClient extends WebViewClient {
    @Override
    public void onPageFinished(WebView view, String url) {
      //Calling a javascript function in html page
      //view.loadUrl("javascript:alert(showVersion('called by Android'))");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

      if (request.getUrl().toString().startsWith("http:") || request.getUrl().toString()
          .startsWith("https:")) {
        return false;
      }
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse(request.getUrl().toString()));
      startActivity(intent);
      return true;
    }
  }

  private class MyWebViewClient1 extends WebChromeClient {
    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
      Log.i("WebviewActivity", "onConsoleMessage: " + consoleMessage.message());
      return super.onConsoleMessage(consoleMessage);
    }
  }
}
