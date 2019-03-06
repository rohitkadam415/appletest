package com.example.webviewinterface;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AppleWebViewActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_apple_webview);

    WebView webView = findViewById(R.id.webview);
    webView.addJavascriptInterface(new AppleWebAppInterface(this), "AndroidInterface");
    webView.setWebContentsDebuggingEnabled(true);
    webView.setWebViewClient(new MyWebViewClient());

    WebSettings webSettings = webView.getSettings();
    webSettings.setDomStorageEnabled(true);
    webSettings.setJavaScriptEnabled(true);
    webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    webSettings.setAllowFileAccess(true);
    webSettings.setPluginState(WebSettings.PluginState.ON);
    webSettings.setUseWideViewPort(true);
    webSettings.setAllowContentAccess(true);
    webSettings.setAllowFileAccess(true);
    webSettings.setAllowFileAccessFromFileURLs(true);
    webSettings.setAllowUniversalAccessFromFileURLs(true);
    webSettings.setLoadWithOverviewMode(true);
    webView.loadUrl("file:///android_asset/index.html");
  }


  private class MyWebViewClient extends WebViewClient {
    private final String TAG = MyWebViewClient.class.getName();

    @Override
    public void onPageFinished(WebView view, String url) {
      //Calling a javascript function in html page
      //view.loadUrl("javascript:alert(showVersion('called by Android'))");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

      Log.i(TAG, "shouldOverrideUrlLoading: " + request.getUrl());
      /*if (request.getUrl().toString().startsWith("http:") || request.getUrl().toString()
          .startsWith("https:")) {
        return false;
      }
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse(request.getUrl().toString()));
      startActivity(intent);
      return true;*/
      view.loadUrl(request.getUrl().toString());
      return false;
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
      Log.i(TAG, "shouldInterceptRequest: " + request.getUrl());
      return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
      Log.i(TAG, "onReceivedError: ");
      super.onReceivedError(view, request, error);
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request,
                                    WebResourceResponse errorResponse) {
      Log.i(TAG, "onReceivedHttpError: ");
      super.onReceivedHttpError(view, request, errorResponse);
    }

    @Override
    public void onFormResubmission(WebView view, Message dontResend, Message resend) {
      Log.i(TAG, "onFormResubmission: ");
      super.onFormResubmission(view, dontResend, resend);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
      Log.i(TAG, "onReceivedSslError: ");
      super.onReceivedSslError(view, handler, error);
    }

    @Override
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
      Log.i(TAG, "onReceivedClientCertRequest: ");
      super.onReceivedClientCertRequest(view, request);
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host,
                                          String realm) {
      Log.i(TAG, "onReceivedHttpAuthRequest: ");
      super.onReceivedHttpAuthRequest(view, handler, host, realm);
    }

    @Override
    public void onReceivedLoginRequest(WebView view, String realm, @Nullable String account,
                                       String args) {
      Log.i(TAG, "onReceivedLoginRequest: ");
      super.onReceivedLoginRequest(view, realm, account, args);
    }

    @Override
    public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
      Log.i(TAG, "onRenderProcessGone: ");
      return super.onRenderProcessGone(view, detail);
    }
  }
}
