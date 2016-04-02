package com.joe.chill.activities;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.joe.chill.R;
import com.joe.chill.adapters.MessageArrayAdapter;
import com.joe.chill.structs.ChatMessage;

public class PrivateMessageActivity extends AppCompatActivity {

  private static final String TAG = "PrivateMessageActivity";

  private MessageArrayAdapter mMessageArrayAdapter;
  private ListView mListView;
  private EditText mEditText;
  private Button mButtonSend;
  private boolean side = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_private_message);

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
    mMessageArrayAdapter.add(new ChatMessage(side, mEditText.getText().toString()));
    mEditText.setText("");
    side = !side;
    return true;
  }
}
