package com.joe.chill.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.joe.chill.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MatchDetailActivity extends AppCompatActivity {

  private Toolbar mToolbar;
  CarouselView mCarouselView;
  ImageListener mImageListener;
  int[] mDummyImages = {R.drawable.dummy_profile_1, R.drawable.dummy_profile_2, R.drawable
      .dummy_profile_3};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_match_detail);


    mToolbar = (Toolbar) findViewById(R.id.toolbar);

    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    mCarouselView = (CarouselView) findViewById(R.id.carouselView);
    mCarouselView.setPageCount(mDummyImages.length);

    mImageListener = new ImageListener() {
      @Override
      public void setImageForPosition(int position, ImageView imageView) {
        imageView.setImageResource(mDummyImages[position]);
      }
    };

    mCarouselView.setImageListener(mImageListener);
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      Intent intent = new Intent(this, ProfileSettingsActivity.class);
      startActivity(intent);
    }

    if (id == R.id.action_Chat) {
      Intent intent = new Intent(this, PrivateMessageActivity.class);
      startActivity(intent);
    }
    return super.onOptionsItemSelected(item);
  }
}
