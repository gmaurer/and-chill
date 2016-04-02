package com.joe.chill.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.joe.chill.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      mToolbar = (Toolbar) findViewById(R.id.toolbar);

      setSupportActionBar(mToolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
