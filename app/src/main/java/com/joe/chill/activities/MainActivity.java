package com.joe.chill.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.joe.chill.R;
import com.joe.chill.adapters.CardStackAdapter;
import com.joe.chill.interfaces.JsonHandler;
import com.joe.chill.structs.MatchCard;
import com.joe.chill.tasks.HttpGetTask;
import com.like.LikeButton;
import com.like.OnLikeListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class MainActivity extends AppCompatActivity implements JsonHandler {

  public static final String TAG = "MainActivity";
  private SwipeStack mSwipeStack;
  private CardStackAdapter mCardStackAdapter;
  private List<MatchCard> mMatchCardList;
  private LikeButton mLikeButtonYes;
  private LikeButton mLikeButtonNo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mMatchCardList = new ArrayList<>();
//    mMatchCardList.add(new MatchCard("Amy", 23, new ArrayList<String>()));
//    mMatchCardList.add(new MatchCard("Gabriel", 22, new ArrayList<String>()));
//    mMatchCardList.add(new MatchCard("Susan", 24, new ArrayList<String>()));
    mCardStackAdapter = new CardStackAdapter(this, mMatchCardList);

    mSwipeStack = (SwipeStack) findViewById(R.id.cardStack);
    mSwipeStack.setAdapter(mCardStackAdapter);

    mSwipeStack.setListener(new SwipeStack.SwipeStackListener() {
      @Override
      public void onViewSwipedToLeft(int position) {
      }

      @Override
      public void onViewSwipedToRight(int position) {
        mLikeButtonYes.performClick();

      }

      @Override
      public void onStackEmpty() {
        getNewOptions();
      }
    });

    mLikeButtonYes = (LikeButton) findViewById(R.id.imageButtonYes);
    mLikeButtonNo = (LikeButton) findViewById(R.id.imageButtonNo);

    mLikeButtonYes.setOnLikeListener(new OnLikeListener() {
      @Override
      public void liked(LikeButton likeButton) {

      }

      @Override
      public void unLiked(LikeButton likeButton) {

      }
    });
    getNewOptions();
  }


  private void getNewOptions() {
    new HttpGetTask(this).execute("http://www.mocky.io/v2/56ffe4fb1300006b2b151d6b");
  }

  @Override
  public void processJson(String json) {
    try {
      JSONArray jsonArray = new JSONArray(json);
      for (int i = 0; i < jsonArray.length(); i++) {
        String name = jsonArray.getJSONObject(i).getString("name");
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        born.setTime(new Date(jsonArray.getJSONObject(i).getLong("dob")));
        now.setTime(new Date());
        int age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
        if (born.get(Calendar.MONTH) > now.get(Calendar.MONTH)) {
          age--;
        }
        JSONArray urlArray = jsonArray.getJSONObject(i).getJSONArray("pics");
        List<String> urls = new ArrayList<>();
        for (int j = 0; j < urlArray.length(); j++) {
          urls.add(urlArray.getString(j));
        }
        mCardStackAdapter.add(new MatchCard(name, age, urls));
      }
      mCardStackAdapter.notifyDataSetChanged();
    } catch (JSONException e) {
      Log.e(TAG, e.toString());
    }

  }
}
