package com.joe.chill.structs;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

/**
 * Created by Joe on 4/2/16.
 */
public class ActionBarTarget implements Target {
  @Override
  public void onLoadStarted(Drawable placeholder) {

  }

  @Override
  public void onLoadFailed(Exception e, Drawable errorDrawable) {

  }

  @Override
  public void onResourceReady(Object resource, GlideAnimation glideAnimation) {

  }

  @Override
  public void onLoadCleared(Drawable placeholder) {

  }

  @Override
  public void getSize(SizeReadyCallback cb) {

  }

  @Override
  public void setRequest(Request request) {

  }

  @Override
  public Request getRequest() {
    return null;
  }

  @Override
  public void onStart() {

  }

  @Override
  public void onStop() {

  }

  @Override
  public void onDestroy() {

  }
}
