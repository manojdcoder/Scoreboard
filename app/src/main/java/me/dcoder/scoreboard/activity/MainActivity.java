package me.dcoder.scoreboard.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import me.dcoder.scoreboard.R;
import me.dcoder.scoreboard.util.Constants;
import me.dcoder.scoreboard.util.Preference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtScoreA;
    private TextView txtScoreB;
    private RadioGroup rbBy;

    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbBy = findViewById(R.id.rbBy);
        txtScoreA = findViewById(R.id.txtScoreA);
        txtScoreB = findViewById(R.id.txtScoreB);

        preference = new Preference(this);
    }

    @Override
    protected void onPause() {
        if (preference.getBoolean(R.string.preference_key_save)) {
            preference.setString(R.string.preference_key_score_a, txtScoreA.getText().toString());
            preference.setString(R.string.preference_key_score_b, txtScoreB.getText().toString());
            preference.setInt(R.string.preference_key_increase_by, rbBy.getCheckedRadioButtonId());
        } else {
            preference.clear();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (preference.getBoolean(R.string.preference_key_save)
                && preference.contains(R.string.preference_key_increase_by)) {
            txtScoreA.setText(preference.getString(R.string.preference_key_score_a));
            txtScoreB.setText(preference.getString(R.string.preference_key_score_b));
            rbBy.check(preference.getInt(R.string.preference_key_increase_by));
        }
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public void onInfoMenuItemClick(MenuItem menuItem) {
        Toast.makeText(this, String.format(getString(R.string.toast_info), Constants.DEVELOPER_NAME), Toast.LENGTH_LONG).show();
    }

    public void onSettingsMenuItemClick(MenuItem menuItem) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void onClick(View v) {
        int countBy = 0;

        switch (rbBy.getCheckedRadioButtonId()){
            case R.id.rbBy1:
                countBy = 1;
                break;
            case R.id.rbBy2:
                countBy = 2;
                break;
            case R.id.rbBy4:
                countBy = 4;
                break;
            case R.id.rbBy6:
                countBy = 6;
                break;
        }

        int currentScoreA = Integer.parseInt(txtScoreA.getText().toString());
        int currentScoreB = Integer.parseInt(txtScoreB.getText().toString());

        switch (v.getId()) {
            case R.id.btnUpA:
                currentScoreA += countBy;
                break;
            case R.id.btnDownA:
                currentScoreA -= countBy;
                break;
            case R.id.btnUpB:
                currentScoreB += countBy;
                break;
            case R.id.btnDownB:
                currentScoreB -= countBy;
                break;
        }

        if (currentScoreA < 0 ){
            currentScoreA = 0;
        }

        if (currentScoreB < 0 ){
            currentScoreB = 0;
        }

        txtScoreA.setText(String.valueOf(currentScoreA));
        txtScoreB.setText(String.valueOf(currentScoreB));
    }
}
