package com.joe.chill.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.joe.chill.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MatchDetailActivity extends AppCompatActivity {

  CarouselView mCarouselView;
  ImageListener mImageListener;
  int[] mDummyImages = {R.drawable.dummy_profile_1, R.drawable.dummy_profile_2, R.drawable
      .dummy_profile_3};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_match_detail);

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
}
