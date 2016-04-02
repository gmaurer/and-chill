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
  public CardStackAdapter(Context context, List<MatchCard> data) {
    super(context, 0, data);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(getContext());
    convertView = inflater.inflate(R.layout.match_card, parent, false);
    TextView textViewName = (TextView) convertView.findViewById(R.id.cardStackName);
    TextView textViewAge = (TextView) convertView.findViewById(R.id.cardStackAge);
    ImageView imageView = (ImageView) convertView.findViewById(R.id.cardStackImage);

    textViewName.setText(getItem(position).getName() + ",");
    textViewAge.setText(String.valueOf(getItem(position).getAge()));
    Glide.with(getContext())
        .load(getItem(position).getImageUrls().get(0))
        .centerCrop()
        .placeholder(R.drawable.heart_on)
        .crossFade()
        .into(imageView);
    imageView.setImageResource(R.drawable.dummy_profile_1);


    return convertView;
  }
}