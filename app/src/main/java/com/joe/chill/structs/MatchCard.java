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
  private List<String> mGenres;
  private List<String> mMatches;

  public int getPrefGender() {
    return mPrefGender;
  }

  public void setPrefGender(int prefGender) {
    mPrefGender = prefGender;
  }

  public int getGender() {
    return mGender;
  }

  public void setGender(int gender) {
    mGender = gender;
  }

  private int mPrefGender;
  private int mGender;
  private long mAge;

  public MatchCard(String userId, String name, String bio, long age, List<String> imageUrls,
                   List<String> genres) {
    setUserId(userId);
    setName(name);
    setImageUrls(imageUrls);
    setUserBio(bio);
    setAge(age);
    setGenres(genres);
  }

  protected MatchCard(Parcel in) {
    mUserId = in.readString();
    mName = in.readString();
    mAge = in.readLong();
    mImageUrls = in.createStringArrayList();
    mGenres = in.createStringArrayList();
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

  public List<String> getGenres() {
    return mGenres;
  }

  public void setGenres(List<String> genres) {
    mGenres = genres;
  }

  public List<String> getImageUrls() {
    return mImageUrls;
  }

  public void setAge(long age) {
    mAge = age;
  }

  public long getAge() {
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
    dest.writeLong(getAge());
    dest.writeStringList(getImageUrls());
    dest.writeStringList(getGenres());
    dest.writeString(getUserBio());
  }
}
