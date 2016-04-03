package com.joe.chill.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joe.chill.R;
import com.joe.chill.structs.MatchCard;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MatchDetailActivity extends AppCompatActivity {

  public static final String TAG = "MatchDetailActivity";
  private Toolbar mToolbar;
  CarouselView mCarouselView;
  ImageListener mImageListener;
  MatchCard mUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    mUser = intent.getParcelableExtra(TAG);

    setContentView(R.layout.activity_match_detail);


    mToolbar = (Toolbar) findViewById(R.id.toolbar);

    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(mUser.getName());

    mCarouselView = (CarouselView) findViewById(R.id.carouselView);
    mCarouselView.setPageCount(mUser.getImageUrls().size());

    mImageListener = new ImageListener() {
      @Override
      public void setImageForPosition(int position, ImageView imageView) {
        setImage(mUser.getImageUrls().get(position), imageView);
      }
    };

    mCarouselView.setImageListener(mImageListener);

    TextView textViewName = (TextView) findViewById(R.id.textViewName);
    textViewName.setText(mUser.getName() + ", ");

    TextView textViewAbout = (TextView) findViewById(R.id.textViewAbout);
    textViewAbout.setText("About " + mUser.getName());


    TextView textViewAge = (TextView) findViewById(R.id.textViewAge);
    textViewAge.setText(String.valueOf(mUser.getAge()));


    TextView textViewBio = (TextView) findViewById(R.id.textViewBio);
    textViewBio.setText(mUser.getUserBio());
  }

  private void setImage(String url, ImageView imageView) {
    Glide.with(this).load(url).fitCenter().into(imageView);
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.detail_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.

    Intent intent;

    switch (item.getItemId()) {
      case R.id.action_match:
        return true;
      case R.id.action_pass:
        return true;
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }

  }
}
