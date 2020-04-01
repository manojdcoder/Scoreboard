package me.dcoder.scoreboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.dcoder.scoreboard.R;
import me.dcoder.scoreboard.util.Constants;
import me.dcoder.scoreboard.util.Preference;

public class ScoreFragment extends Fragment implements View.OnClickListener {

    private TextView txtScoreA;
    private Button btnUpA;
    private Button btnDownA;
    private TextView txtScoreB;
    private Button btnUpB;
    private Button btnDownB;
    private RadioGroup rbBy;

    private Preference preference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = new Preference(getContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuInfo:
                Toast.makeText(getContext(), String.format(getString(R.string.toast_info), Constants.DEVELOPER_NAME), Toast.LENGTH_LONG).show();
                return true;
        }
        return  false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtScoreA = view.findViewById(R.id.txtScoreA);
        btnUpA = view.findViewById(R.id.btnUpA);
        btnDownA = view.findViewById(R.id.btnDownA);
        txtScoreB = view.findViewById(R.id.txtScoreB);
        btnUpB = view.findViewById(R.id.btnUpB);
        btnDownB = view.findViewById(R.id.btnDownB);
        rbBy = view.findViewById(R.id.rbBy);

        btnUpA.setOnClickListener(this);
        btnDownA.setOnClickListener(this);
        btnUpB.setOnClickListener(this);
        btnDownB.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        preference.setString(R.string.preference_key_score_a, txtScoreA.getText().toString());
        preference.setString(R.string.preference_key_score_b, txtScoreB.getText().toString());
        preference.setInt(R.string.preference_key_increase_by, rbBy.getCheckedRadioButtonId());
        super.onPause();
    }

    @Override
    public void onResume() {
        if (preference.contains(R.string.preference_key_increase_by)) {
            txtScoreA.setText(preference.getString(R.string.preference_key_score_a));
            txtScoreB.setText(preference.getString(R.string.preference_key_score_b));
            rbBy.check(preference.getInt(R.string.preference_key_increase_by));
        }
        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
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