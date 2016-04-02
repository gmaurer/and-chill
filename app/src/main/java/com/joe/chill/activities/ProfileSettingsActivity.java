package com.joe.chill.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.joe.chill.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner mGenderSpinner;
    Button mGenreButton;

    HashMap<String, Integer> mGenderMap;
    String[] mGenresArray = {"Drama", "Action", "Anime", "Comedy"};
    ArrayList<String> mSelectedGenresArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
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
    }

    @Override

    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.genreButton:
                showGenreChoicesDialog();
                break;

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
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    protected void onChangeSelectedGenres() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String genre : mSelectedGenresArrayList)
            stringBuilder.append(genre + ",");

        mGenreButton.setText(stringBuilder.toString());
    }
}
