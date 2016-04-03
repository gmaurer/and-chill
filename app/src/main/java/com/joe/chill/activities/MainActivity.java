package com.joe.chill.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.joe.chill.R;
import com.joe.chill.ToolbarUtility;
import com.joe.chill.Utility;
import com.joe.chill.adapters.CardStackAdapter;
import com.joe.chill.interfaces.JsonHandler;
import com.joe.chill.structs.MatchCard;
import com.joe.chill.tasks.HttpGetTask;
import com.joe.chill.tasks.HttpPostTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import at.markushi.ui.CircleButton;
import link.fls.swipestack.SwipeStack;

public class MainActivity extends AppCompatActivity implements JsonHandler {

  public static final String TAG = "MainActivity";
  private SwipeStack mSwipeStack;
  private CardStackAdapter mCardStackAdapter;
  private List<MatchCard> mMatchCardList;
  private CircleButton mButtonYes;
  private CircleButton mButtonNo;
  private CircleButton mButtonInfo;
  private MatchCard mUser;
  private ActionBar mActionBar;

    private Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    FacebookSdk.sdkInitialize(this.getApplicationContext());
    updateWithToken(AccessToken.getCurrentAccessToken());

    setContentView(R.layout.activity_main);


    mToolbar = (Toolbar) findViewById(R.id.toolbar);

    setSupportActionBar(mToolbar);
    mActionBar = getSupportActionBar();
    mActionBar.setDisplayHomeAsUpEnabled(true);

    mMatchCardList = new ArrayList<>();
    mCardStackAdapter = new CardStackAdapter(this, mMatchCardList);

    mSwipeStack = (SwipeStack) findViewById(R.id.cardStack);
    mSwipeStack.setAdapter(mCardStackAdapter);

    mSwipeStack.setListener(new SwipeStack.SwipeStackListener() {
      @Override
      public void onViewSwipedToLeft(int position) {
        mCardStackAdapter.pop();
      }

      @Override
      public void onViewSwipedToRight(int position) {
        mCardStackAdapter.pop();
        ChatListActivity.mMatches.add(mCardStackAdapter.getItem(position));
      }

      @Override
      public void onStackEmpty() {
        stackEmpty();
      }
    });

    mButtonYes = (CircleButton) findViewById(R.id.buttonYes);
    mButtonNo = (CircleButton) findViewById(R.id.buttonNo);
    mButtonInfo = (CircleButton) findViewById(R.id.buttonInfo);

    mButtonInfo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        launchDetailActivity(mCardStackAdapter.top());
      }
    });

    mButtonNo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mSwipeStack.swipeTopViewToLeft();
      }
    });

    mButtonYes.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mSwipeStack.swipeTopViewToRight();
      }
    });
  }

  private void stackEmpty() {
    Toast toast = new Toast(this);
    toast.setText("You're out of potential matches!");
    toast.setDuration(Toast.LENGTH_LONG);
    toast.show();
  }

  private void updateWithToken(AccessToken currentAccessToken) {
    if (currentAccessToken == null) {
      Intent intent = new Intent(this, LoginActivity.class);
      startActivity(intent);
    } else {
      GraphRequest request = GraphRequest.newMeRequest(
          currentAccessToken,
          new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(
                JSONObject object,
                GraphResponse response) {
              String name = "";
              long age = new Date().getTime();
              String bio = "";
              String id = "";
              List<String> urls = new ArrayList<String>();
              if (object != null) {
                try {
                  name = object.getString("first_name");
                  id = object.getString("id");
                  String pic = object.getJSONObject("picture").getJSONObject("data").getString("url");
                  urls.add(pic);
                } catch (JSONException e) {
                  Log.e(TAG, e.getMessage());
                }
                mUser = new MatchCard(id, name, bio, age, urls, new ArrayList<String>());
                Utility.setPrefsFromUser(getApplicationContext(), mUser);
                initializeActionBar();
              }
            }
          });
      Bundle parameters = new Bundle();
      parameters.putString("fields", "id,first_name,gender,birthday,picture");
      request.setParameters(parameters);
      request.executeAsync();
    }
  }

  private void initializeActionBar() {
    if (mUser.getImageUrls().size() > 0) {
      Glide.with(this).load(mUser.getImageUrls().get(0))
          .asBitmap()
          .fitCenter()
          .into(new SimpleTarget<Bitmap>(64, 64) {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
              RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
              dr.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
              mActionBar.setIcon(dr);
            }
          });
    }
    getNewOptions();
    mActionBar.setTitle("&chill");
  }

  private void launchDetailActivity(MatchCard userId) {
    Intent intent = new Intent(this, MatchDetailActivity.class);
    intent.putExtra(MatchDetailActivity.TAG, userId);
    startActivity(intent);
  }

  private void getNewOptions() {
    new HttpGetTask(this).execute("http://www.mocky.io/v2/5700ba08120000601b7709b7");
  }

  @Override
  public void processJson(String json) {
    try {
      JSONArray jsonArray = new JSONArray(json);
      for (int i = 0; i < jsonArray.length(); i++) {
        String name = jsonArray.getJSONObject(i).getString("name");
        String userid = jsonArray.getJSONObject(i).getString("id");
        String bio = jsonArray.getJSONObject(i).getString("bio");
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        born.setTime(new Date(jsonArray.getJSONObject(i).getLong("dob")));
        now.setTime(new Date());
        int age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
        if (born.get(Calendar.MONTH) > now.get(Calendar.MONTH)) {
          age--;
        }
        JSONArray genreArray = jsonArray.getJSONObject(i).getJSONArray("genres");
        List<String> genres = new ArrayList<>();
        for (int j = 0; j < genreArray.length(); j++) {
          genres.add(genreArray.getString(j));
        }
        JSONArray urlArray = jsonArray.getJSONObject(i).getJSONArray("pics");
        List<String> urls = new ArrayList<>();
        for (int j = 0; j < urlArray.length(); j++) {
          urls.add(urlArray.getString(j));
        }
        mCardStackAdapter.add(new MatchCard(userid, name, bio, age, urls, genres));
      }
      mCardStackAdapter.notifyDataSetChanged();
    } catch (JSONException e) {
      Log.e(TAG, e.toString());
    }
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

      Intent intent;

       switch (item.getItemId()) {
         case R.id.action_settings:
           intent = new Intent(this, ProfileSettingsActivity.class);
           startActivity(intent);
           return true;
         case R.id.action_Chat:
           intent = new Intent(this, ChatListActivity.class);
           startActivity(intent);
           return true;
         case android.R.id.home:
           onBackPressed();
           return true;
          default:
            return super.onOptionsItemSelected(item);
       }
    }
}
