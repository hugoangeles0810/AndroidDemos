package io.github.hugoangeles0810.statusbariconsdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

  private SeekBar sbRed, sbBlue, sbGreen;
  private int red, blue, green;

  private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
    @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
      switch (seekBar.getId()) {
        case R.id.sbReb:
          red = progress;
          break;
        case R.id.sbBlue:
          blue = progress;
          break;
        case R.id.sbGreen:
          green = progress;
          break;
      }

      setColors();
    }

    @Override public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override public void onStopTrackingTouch(SeekBar seekBar) {}
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ui();
  }

  private void ui() {
    sbRed = (SeekBar) findViewById(R.id.sbReb);
    sbGreen = (SeekBar) findViewById(R.id.sbGreen);
    sbBlue = (SeekBar) findViewById(R.id.sbBlue);

    sbRed.setOnSeekBarChangeListener(seekBarChangeListener);
    sbGreen.setOnSeekBarChangeListener(seekBarChangeListener);
    sbBlue.setOnSeekBarChangeListener(seekBarChangeListener);
  }

  private void setColors() {
    int color = Color.rgb(red, green, blue);
    int red = Color.red(color);
    int green = Color.green(color);
    int blue = Color.blue(color);
    getWindow().getDecorView().setBackgroundColor(color);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getWindow().setStatusBarColor(color);
      ActionBar supportActionBar = getSupportActionBar();
      if (supportActionBar != null) {
        supportActionBar.setBackgroundDrawable(new ColorDrawable(color));
      }

      View decor = getWindow().getDecorView();
      boolean shouldChangeStatusBarTintToDark = (red * 0.299 + green * 0.587 + blue * 0.114) > 186;

      if (shouldChangeStatusBarTintToDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
      } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          decor.setSystemUiVisibility(0);
        }
      }

    }
  }
}
