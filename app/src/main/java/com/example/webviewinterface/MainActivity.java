package com.example.webviewinterface;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void openWebView(View v) {
      startActivity(new Intent(this, WebViewActivity.class));
    //String url = "http://208.109.123.113:9009/test.html";
    //startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(url)), 121);
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Log.i("tag", "onActivityResult: " + data.getData());
  }
}
