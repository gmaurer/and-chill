package com.joe.chill.structs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Joe on 4/2/16.
 */
public class MatchCard implements Parcelable {
  private String mUserId;
  private String mName;
  private String mBio;
  private List<String> mImageUrls;
  private int mAge;

  public MatchCard(String userId, String name, String bio, int age, List<String> imageUrls) {
    setUserId(userId);
    setName(name);
    setImageUrls(imageUrls);
    setUserBio(bio);
    setAge(age);
  }

  protected MatchCard(Parcel in) {
    mUserId = in.readString();
    mName = in.readString();
    mAge = in.readInt();
    mImageUrls = in.createStringArrayList();
    mBio = in.readString();
  }

  public static final Creator<MatchCard> CREATOR = new Creator<MatchCard>() {
    @Override
    public MatchCard createFromParcel(Parcel in) {
      return new MatchCard(in);
    }

    @Override
    public MatchCard[] newArray(int size) {
      return new MatchCard[size];
    }
  };

  public String getUserId() {
    return mUserId;
  }

  public void setUserId(String userId) {
    mUserId = userId;
  }

  public String getUserBio() {
    return mBio;
  }

  public void setUserBio(String bio) {
    mBio = bio;
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(getUserId());
    dest.writeString(getName());
    dest.writeInt(getAge());
    dest.writeStringList(getImageUrls());
    dest.writeString(getUserBio());
  }
}
