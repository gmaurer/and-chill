package com.joe.chill.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.joe.chill.R;
import com.joe.chill.adapters.ContactArrayAdapter;
import com.joe.chill.interfaces.JsonHandler;
import com.joe.chill.structs.MatchCard;
import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity implements JsonHandler {
  public static final String TAG = "ChatListActivity";
  private Toolbar mToolbar;
  private ContactArrayAdapter mContactArrayAdapter;
  private List<MatchCard> mMatchCardList;
  private ListView mListView;
  private boolean mFetchingData = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat_list);

    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mListView = (ListView) findViewById(R.id.listViewContacts);

    mMatchCardList = new ArrayList<>();
    mContactArrayAdapter = new ContactArrayAdapter(this, mMatchCardList);
    mListView.setAdapter(mContactArrayAdapter);

    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MatchCard matchCard = mContactArrayAdapter.getItem(position);
        launchConversation(matchCard);
      }
    });

    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Messages");

    getMatches();
  }

  private void launchConversation(MatchCard matchCard) {
    Intent intent = new Intent(this, PrivateMessageActivity.class);
    intent.putExtra(TAG, matchCard);
    startActivity(intent);
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
        onBackPressed();
        return true;
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }

  }

  private void getMatches() {
    mFetchingData = true;
    processJson("");
  }

  @Override
  public void processJson(String json) {
    List<String> urls = new ArrayList<>();
    urls.add("http://www.eonline.com/eol_images/Entire_Site/201564/rs_600x600-150704101830-600.kim-kardashian.cm.7415.jpg");

    mContactArrayAdapter.add(new MatchCard("1234","Kim", "bio", 12, urls));
    mContactArrayAdapter.notifyDataSetChanged();

    mFetchingData = false;
    if (mContactArrayAdapter.isEmpty()) {

    }
  }
}
