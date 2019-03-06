package com.example.webviewinterface;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.GeolocationPermissions;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AppleWebViewWithChromeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_apple_webview);
    WebView webView = findViewById(R.id.webview);
    webView.addJavascriptInterface(new AppleWebAppInterface(this), "AndroidInterface");
    webView.setWebContentsDebuggingEnabled(true);
    webView.setWebChromeClient(new MyChromeClient());
    WebSettings webSettings = webView.getSettings();
    webSettings.setDomStorageEnabled(true);
    webSettings.setJavaScriptEnabled(true);
    webSettings.setAllowFileAccess(true);
    webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    webSettings.setUseWideViewPort(true);
    webSettings.setLoadWithOverviewMode(true);
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

  private class MyChromeClient extends WebChromeClient {

    /**
     * Notify the host application that web content from the specified origin
     * is attempting to use the Geolocation API, but no permission state is
     * currently set for that origin. The host application should invoke the
     * specified callback with the desired permission state. See
     * {@link GeolocationPermissions} for details.
     *
     * <p>Note that for applications targeting Android N and later SDKs
     * (API level > {@link Build.VERSION_CODES#M})
     * this method is only called for requests originating from secure
     * origins such as https. On non-secure origins geolocation requests
     * are automatically denied.</p>
     *
     * @param origin   The origin of the web content attempting to use the
     *                 Geolocation API.
     * @param callback The callback to use to set the permission state for the
     */
    @Override
    public void onGeolocationPermissionsShowPrompt(String origin,
                                                   GeolocationPermissions.Callback callback) {
      super.onGeolocationPermissionsShowPrompt(origin, callback);
    }

    /**
     * Notify the host application that a request for Geolocation permissions,
     * made with a previous call to
     * {@link #onGeolocationPermissionsShowPrompt(String, GeolocationPermissions.Callback) onGeolocationPermissionsShowPrompt()}
     * has been canceled. Any related UI should therefore be hidden.
     */
    @Override
    public void onGeolocationPermissionsHidePrompt() {
      super.onGeolocationPermissionsHidePrompt();
    }

    /**
     * Notify the host application that web content is requesting permission to
     * access the specified resources and the permission currently isn't granted
     * or denied. The host application must invoke {@link PermissionRequest#grant(String[])}
     * or {@link PermissionRequest#deny()}.
     * <p>
     * If this method isn't overridden, the permission is denied.
     *
     * @param request the PermissionRequest from current web content.
     */
    @Override
    public void onPermissionRequest(PermissionRequest request) {
      super.onPermissionRequest(request);
    }

    /**
     * Notify the host application that the given permission request
     * has been canceled. Any related UI should therefore be hidden.
     *
     * @param request the PermissionRequest that needs be canceled.
     */
    @Override
    public void onPermissionRequestCanceled(PermissionRequest request) {
      super.onPermissionRequestCanceled(request);
    }
  }
}
