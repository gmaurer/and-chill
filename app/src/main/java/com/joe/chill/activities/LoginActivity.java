package com.joe.chill.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.joe.chill.R;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    CallbackManager callbackManager;
    TextView info;
    LoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
      updateWithToken(AccessToken.getCurrentAccessToken());

      AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
          updateWithToken(newAccessToken);
        }
      };

        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
      //List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
      //loginButton.setReadPermissions(permissionNeeds);
      loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
          Log.i(TAG, "Login Success!");
          launchMain();

        }

        @Override
        public void onCancel() {
          System.out.println("onCancel");
          }

          @Override
          public void onError(FacebookException exception) {
            System.out.println(exception);
            Log.e("LoginActivity", exception.toString());
          }
        });
    }

    private void launchMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

  private void updateWithToken(AccessToken currentAccessToken) {
    if (currentAccessToken != null) {
      launchMain();
    } else {
    }
  }

    /*public void onClickBtn(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException exception) {
                info.setText("Login attempt failed.");
            }
        });


    }*/

    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //    callbackManager.onActivityResult(requestCode, resultCode, data);
    //}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


//    @Override
//    public View onCreateView(
//            LayoutInflater inflater,
//            ViewGroup container,
//            Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.splash, container, false);
//
//        loginButton = (LoginButton) view.findViewById(R.id.login_button);
//        loginButton.setReadPermissions("user_friends");
//        // If using in a fragment
//        //loginButton.setFragment(this);
//        // Other app specific specialization
//
//        // Callback registration
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });
//    }

}
