package com.joe.chill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joe.chill.R;
import com.joe.chill.structs.MatchCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 4/1/16.
 */
public class CardStackAdapter extends ArrayAdapter<MatchCard> {

  private int top = 0;

  public CardStackAdapter(Context context, List<MatchCard> data) {
    super(context, 0, data);
  }

  @Override
  public void add(MatchCard matchCard) {
    super.add(matchCard);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    MatchCard item = getItem(position);
    LayoutInflater inflater = LayoutInflater.from(getContext());
    convertView = inflater.inflate(R.layout.match_card, parent, false);
    convertView.setTag(item);
    TextView textViewName = (TextView) convertView.findViewById(R.id.cardStackName);
    TextView textViewAge = (TextView) convertView.findViewById(R.id.cardStackAge);
    ImageView imageView = (ImageView) convertView.findViewById(R.id.cardStackImage);

    textViewName.setText(item.getName() + ",");
    textViewAge.setText(String.valueOf(item.getAge()));
    Glide.with(getContext())
        .load(item.getImageUrls().get(0))
        .centerCrop()
        .placeholder(R.drawable.heart_on)
        .crossFade()
        .into(imageView);
    imageView.setImageResource(R.drawable.dummy_profile_1);

    return convertView;
  }

  public void pop() {
    top++;
  }

  public MatchCard top() {
    return getItem(top);
  }
}