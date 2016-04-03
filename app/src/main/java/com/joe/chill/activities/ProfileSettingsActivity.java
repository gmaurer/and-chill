package com.joe.chill.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.joe.chill.R;
import com.joe.chill.ToolbarUtility;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner mGenderSpinner;
    Button mGenreButton;
    Button mGenderPreferenceButton;
    Toolbar mToolbar;

    HashMap<String, Integer> mGenderMap;
    String[] mGenresArray = {"Drama", "Action", "Anime", "Comedy"};
    ArrayList<String> mSelectedGenresArrayList = new ArrayList<>();
    ArrayList<String> mSelectedGenderPreferenceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mGenderMap = new HashMap<>();
        mGenderMap.put("Male", 1);
        mGenderMap.put("Female", 2);
        mGenderMap.put("Other", 4);
        mGenderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                mGenderMap.keySet().toArray(new String[mGenderMap.size()]));
        mGenderSpinner.setAdapter(genderAdapter);

        mGenreButton = (Button) findViewById(R.id.genreButton);
        mGenreButton.setOnClickListener(this);

        mGenderPreferenceButton =  (Button) findViewById(R.id.genderPreferenceButton);
        mGenderPreferenceButton.setOnClickListener(this);
    }

    @Override

    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.genreButton:
                showGenreChoicesDialog();
                break;
            case R.id.genderPreferenceButton:
                showGenderPreference();
            default:
                break;

        }

    }

    protected void showGenreChoicesDialog() {
        boolean[] checkedGenres = new boolean[mGenresArray.length];

        int count = mGenresArray.length;

        for(int i = 0; i < count; i++) {
            checkedGenres[i] = mSelectedGenresArrayList.contains(mGenresArray[i]);
        }

        DialogInterface.OnMultiChoiceClickListener genresDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
            @Override

            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {

                if (isChecked)
                    mSelectedGenresArrayList.add(mGenresArray[which]);
                else
                    mSelectedGenresArrayList.remove(mGenresArray[which]);

                onChangeSelectedGenres();
            }
        };

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Select Genres");
        dialogBuilder.setMultiChoiceItems(mGenresArray, checkedGenres, genresDialogListener);
        dialogBuilder.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    protected void onChangeSelectedGenres() {
        StringBuilder stringBuilder = new StringBuilder();

        if(mSelectedGenresArrayList.size() > 2) {
            stringBuilder.append(mSelectedGenresArrayList.get(0) + ", ");
            stringBuilder.append(mSelectedGenresArrayList.get(1) + "...");
        }
        else {
            int i;
            for (i = 0; i < mSelectedGenresArrayList.size() - 1; i++) {
                stringBuilder.append(mSelectedGenresArrayList.get(i) + ", ");
            }
            stringBuilder.append(mSelectedGenresArrayList.get(i++));
        }

        mGenreButton.setText(stringBuilder.toString());
    }

    protected void showGenderPreference() {
        boolean[] checkedGenders = new boolean[mGenderMap.size()];

        int count = mGenderMap.size();
        final String[] genders = mGenderMap.keySet().toArray(new String[mGenderMap.size()]);

        for(int i = 0; i < count; i++) {
            checkedGenders[i] = mSelectedGenderPreferenceList.contains(genders[i]);
        }

        DialogInterface.OnMultiChoiceClickListener genderDialogListner = new DialogInterface.OnMultiChoiceClickListener() {
            @Override

            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {

                if (isChecked)
                    mSelectedGenderPreferenceList.add(genders[which]);
                else
                    mSelectedGenderPreferenceList.remove(genders[which]);

                onChangeSelectedGenderPreferences();
            }
        };

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Select Perferred Genders");
        dialogBuilder.setMultiChoiceItems(genders, checkedGenders, genderDialogListner);
        dialogBuilder.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    protected void onChangeSelectedGenderPreferences() {
        StringBuilder stringBuilder = new StringBuilder();

        if(mSelectedGenderPreferenceList.size() > 2) {
            stringBuilder.append(mSelectedGenderPreferenceList.get(0) + ", ");
            stringBuilder.append(mSelectedGenderPreferenceList.get(1) + "...");
        }
        else {
            int i;
            for (i = 0; i < mSelectedGenderPreferenceList.size() - 1; i++) {
                stringBuilder.append(mSelectedGenderPreferenceList.get(i) + ",");
            }
            stringBuilder.append(mSelectedGenderPreferenceList.get(i++));
        }
        mGenderPreferenceButton.setText(stringBuilder.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return ToolbarUtility.onOptionsItemSelected(this, item);

    }

}
