package com.joe.chill;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.joe.chill.activities.MainActivity;
import com.joe.chill.activities.PrivateMessageActivity;
import com.joe.chill.activities.ProfileSettingsActivity;

/**
 * Created by AlexWill on 4/2/16.
 */
public class ToolbarUtility {

    public static boolean onOptionsItemSelected(Context context, MenuItem item){
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_settings:
                intent = new Intent(context, ProfileSettingsActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.action_Chat:
                intent = new Intent(context, PrivateMessageActivity.class);
                context.startActivity(intent);
                return true;
            case android.R.id.home:
                intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                return true;
            default:
                return onOptionsItemSelected(context, item);
        }
    }
}
