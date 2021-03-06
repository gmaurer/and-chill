package com.joe.chill.activities;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.joe.chill.R;
import com.joe.chill.adapters.MessageArrayAdapter;
import com.joe.chill.structs.ChatMessage;
import com.joe.chill.structs.MatchCard;

import java.util.Date;
import java.util.Random;

public class PrivateMessageActivity extends AppCompatActivity {

  private static final String TAG = "PrivateMessageActivity";

  private MessageArrayAdapter mMessageArrayAdapter;
  private ListView mListView;
  private EditText mEditText;
  private Button mButtonSend;
  private Toolbar mToolbar;
  private ActionBar mActionBar;
  private MatchCard mUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_private_message);

    Intent intent = getIntent();
    mUser = intent.getParcelableExtra(ChatListActivity.TAG);

    mToolbar = (Toolbar) findViewById(R.id.toolbar);

    setSupportActionBar(mToolbar);
    mActionBar = getSupportActionBar();
    mActionBar.setDisplayHomeAsUpEnabled(true);
    mActionBar.setTitle("  " + mUser.getName());

    Glide.with(this).load(mUser.getImageUrls().get(0))
        .asBitmap()
        .into(new SimpleTarget<Bitmap>(64, 64) {
          @Override
          public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
            RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            dr.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);

            mActionBar.setIcon(dr);
          }
        });

    mButtonSend = (Button) findViewById(R.id.buttonSend);

    mListView = (ListView) findViewById(R.id.listViewMessages);

    mMessageArrayAdapter = new MessageArrayAdapter(getApplicationContext(), R.layout.sender_message);
    mListView.setAdapter(mMessageArrayAdapter);

    mEditText = (EditText) findViewById(R.id.editTextMessage);
    mEditText.setOnKeyListener(new View.OnKeyListener() {
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
          return sendChatMessage();
        }
        return false;
      }
    });
    mButtonSend.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View arg0) {
        sendChatMessage();
      }
    });

    mListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
    mListView.setAdapter(mMessageArrayAdapter);

    mMessageArrayAdapter.registerDataSetObserver(new DataSetObserver() {
      @Override
      public void onChanged() {
        super.onChanged();
        mListView.setSelection(mMessageArrayAdapter.getCount() - 1);
      }
    });
  }

  private boolean sendChatMessage() {
    String message = mEditText.getText().toString();
    if (message.length() > 0) {
      mMessageArrayAdapter.add(new ChatMessage(true, message));
      String resp = "oO";
      if (message.contains("movie") && message.contains("favorite")) {
        Random r = new Random(new Date().getTime());
        int n = r.nextInt(5);
        switch (n) {
          case 0:
            resp = "My favorite movie is Star Wars!";
            break;
          case 1:
            resp = "My favorite movie is Titanic!";
            break;
          case 2:
            resp = "My favorite movie is National Treasure III!";
            break;
          case 3:
            resp = "My favorite movie is The Notebook!";
            break;
          case 4:
            resp = "My favorite movie is The Human Centipede!";
            break;

        }
      } else if (message.contains("hate")) {
        Random r = new Random(new Date().getTime());
        int n = r.nextInt(5);
        switch (n) {
          case 0:
            resp = "I hate you.";
            break;
          case 1:
            resp = "Please stop.";
            break;
          case 2:
            resp = "That's not nice";
            break;
          case 3:
            resp = ":'(";
            break;
          case 4:
            resp = "Yo momma so fat";
            break;
        }
      } else {
        Random r = new Random(new Date().getTime());
        int n = r.nextInt(5);
        switch (n) {
          case 0:
            resp = "I can't wait to meet you!";
            break;
          case 1:
            resp = "Let's chill ;)";
            break;
          case 2:
            resp = "heyyyyyy";
            break;
          case 3:
            resp = "what's up?";
            break;
          case 4:
            resp = "noooooooo";
            break;
        }
      }
      mEditText.setText("");

      mMessageArrayAdapter.add(new ChatMessage(false, resp));
      //side = !side;

    }
    return true;
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
    //
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
