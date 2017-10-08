package io.github.hugoangeles0810.menuanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  private static final int MARGIN_Y = 56;
  private static final double OFFSET = 56.0;
  private static final int DURATION = 750;
  private ViewGroup menuContainer;
  private ViewGroup homeContainer;
  private Button btnMenu;
  private boolean isMenuOpen = false;
  private float scaleX;
  private float scaleY;
  private float translateX;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ui();
    setupAnimations();
  }

  private void ui() {
    menuContainer = (ViewGroup) findViewById(R.id.menu_container);
    homeContainer = (ViewGroup) findViewById(R.id.home_container);
    btnMenu = (Button) findViewById(R.id.btn_menu);
  }

  private void setupAnimations() {
    menuContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override public void onGlobalLayout() {
        menuContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int initialWidth = menuContainer.getWidth();
        int initialHeight = menuContainer.getHeight();
        float ratio = (float) initialWidth / initialHeight;

        float density = displayMetrics.scaledDensity;

        int endHeight = (int) (initialHeight - 2 * MARGIN_Y * density);
        int endWidth = (int) (endHeight * ratio);

        scaleX = (float) endWidth / initialWidth;
        scaleY = (float) endHeight / initialHeight;
        translateX = (int) (initialWidth - OFFSET * density - MARGIN_Y * density * ratio);

        btnMenu.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View view) {
            if (isMenuOpen) {
              closeMenuAnimation();
            } else {
              openMenuAnimation();
            }
          }
        });
      }
    });
  }

  private void openMenuAnimation() {
    homeContainer.animate()
        .translationX(translateX)
        .scaleX(scaleX)
        .scaleY(scaleY)
        .setInterpolator(new BounceInterpolator())
        .withEndAction(new Runnable() {
          @Override public void run() {
            isMenuOpen = !isMenuOpen;
          }
        })
        .setDuration(DURATION)
        .start();
  }

  private void closeMenuAnimation() {
    homeContainer.animate()
        .translationX(0)
        .scaleX(1)
        .scaleY(1)
        .setInterpolator(new BounceInterpolator())
        .withEndAction(new Runnable() {
          @Override public void run() {
            isMenuOpen = !isMenuOpen;
          }
        })
        .setDuration(DURATION)
        .start();
  }
}
