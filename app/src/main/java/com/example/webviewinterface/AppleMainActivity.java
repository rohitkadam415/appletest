package com.example.webviewinterface;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class AppleMainActivity extends AppCompatActivity implements View.OnClickListener {

  private final String url = "http://208.109.123.113:9009/test.html";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_apple_main);
    findViewById(R.id.open_browser).setOnClickListener(this);
    findViewById(R.id.open_browser_for_result).setOnClickListener(this);
    findViewById(R.id.open_web_activity).setOnClickListener(this);
    findViewById(R.id.open_web_activity_with_chrome_client).setOnClickListener(this);
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Log.i("tag", "onActivityResult: ");
  }

  /**
   * Called when a view has been clicked.
   *
   * @param v The view that was clicked.
   */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.open_browser:
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        break;
      case R.id.open_browser_for_result:
        startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(url)), 121);
        break;
      case R.id.open_web_activity:
        startActivity(new Intent(this, AppleWebViewActivity.class));
        break;
      case R.id.open_web_activity_with_chrome_client:
        startActivity(new Intent(this, AppleWebViewWithChromeActivity.class));
        break;
    }
  }
}
