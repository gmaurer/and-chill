package com.joe.chill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joe.chill.R;
import com.joe.chill.structs.MatchCard;

import java.util.List;

/**
 * Created by Joe on 4/2/16.
 */
public class ContactArrayAdapter extends ArrayAdapter<MatchCard> {

  public ContactArrayAdapter(Context context, List<MatchCard> contacts) {
    super(context, 0, contacts);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    MatchCard item = getItem(position);
    LayoutInflater inflater = LayoutInflater.from(getContext());
    convertView = inflater.inflate(R.layout.contect_card, parent, false);
    convertView.setTag(item);
    TextView textViewName = (TextView) convertView.findViewById(R.id.textViewContactName);
    ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewContactThumbnail);

    textViewName.setText(item.getName());
    Glide.with(getContext())
        .load(item.getImageUrls().get(0))
        .centerCrop()
        .placeholder(R.drawable.heart_on)
        .dontAnimate()
        .thumbnail( 0.1f)
        .into(imageView);
    imageView.setImageResource(R.drawable.dummy_profile_1);

    return convertView;
  }
}
