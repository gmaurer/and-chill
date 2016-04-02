package com.joe.chill.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.joe.chill.R;
import com.joe.chill.adapters.CardStackAdapter;
import com.joe.chill.structs.MatchCard;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class MainActivity extends AppCompatActivity {

  private SwipeStack mSwipeStack;
  private CardStackAdapter mCardStackAdapter;
  private List<MatchCard> mMatchCardList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mMatchCardList = new ArrayList<>();
    mMatchCardList.add(new MatchCard("test1"));
    mMatchCardList.add(new MatchCard("test2"));
    mMatchCardList.add(new MatchCard("test3"));
    mCardStackAdapter = new CardStackAdapter(this, mMatchCardList);

    mSwipeStack = (SwipeStack) findViewById(R.id.cardStack);
    mSwipeStack.setAdapter(mCardStackAdapter);
  }
}
