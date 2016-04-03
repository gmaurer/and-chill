package com.joe.chill;

import android.content.Context;
import android.content.SharedPreferences;
import com.joe.chill.structs.MatchCard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Joe on 4/3/16.
 */
public class Utility {
  public static MatchCard getUserFromPrefs(Context context) {
    SharedPreferences preferences = context.getSharedPreferences("Me", Context
        .MODE_PRIVATE);

    String name = preferences.getString("name", "");
    String bio = preferences.getString("bio","");
    String id = preferences.getString("id", "");
    long age = preferences.getLong("age", 0);
    List<String> urls = new ArrayList<>(preferences.getStringSet("urls", new HashSet<String>()));

    return new MatchCard(id, name, bio, age, urls, new ArrayList<String>());
  }

  public static void setPrefsFromUser(Context context, MatchCard matchCard) {
    SharedPreferences sharedPreferences = context.getSharedPreferences("Me", Context
        .MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("name", matchCard.getName());
    editor.putString("id", matchCard.getUserId());
    editor.putLong("age", matchCard.getAge());
    editor.putStringSet("urls", new HashSet<String>(matchCard.getImageUrls()));
    editor.putString("bio", matchCard.getUserBio());
    editor.commit();
  }
}
