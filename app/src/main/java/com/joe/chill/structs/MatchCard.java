package com.joe.chill.structs;

import java.util.List;

/**
 * Created by Joe on 4/2/16.
 */
public class MatchCard {
  private String mName;
  private List<String> mImageUrls;
  private int mAge;

  public MatchCard(String name, int age, List<String> imageUrls) {
    setName(name);
    setImageUrls(imageUrls);
    setAge(age);
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  public void setImageUrls(List<String> imageUrls) {
    mImageUrls = imageUrls;
  }

  public List<String> getImageUrls() {
    return mImageUrls;
  }

  public void setAge(int age) {
    mAge = age;
  }

  public int getAge() {
    return mAge;
  }
}
