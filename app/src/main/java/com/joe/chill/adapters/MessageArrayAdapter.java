package com.joe.chill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.joe.chill.R;
import com.joe.chill.structs.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 4/1/16.
 */
public class MessageArrayAdapter extends ArrayAdapter<ChatMessage> {

  private TextView mChatText;
  private List<ChatMessage> mChatMessageList = new ArrayList<ChatMessage>();
  private Context mContext;

  @Override
  public void add(ChatMessage object) {
    mChatMessageList.add(object);
    super.add(object);
  }

  public MessageArrayAdapter(Context context, int textViewResourceId) {
    super(context, textViewResourceId);
    mContext = context;
  }

  public int getCount() {
    return mChatMessageList.size();
  }

  public ChatMessage getItem(int index) {
    return mChatMessageList.get(index);
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    ChatMessage chatMessageObj = getItem(position);
    View row = convertView;
    LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (chatMessageObj.left) {
      row = inflater.inflate(R.layout.sender_message, parent, false);
    } else {
      row = inflater.inflate(R.layout.recipient_message, parent, false);
    }
    mChatText = (TextView) row.findViewById(R.id.msgr);
    mChatText.setText(chatMessageObj.message);
    return row;
  }
}