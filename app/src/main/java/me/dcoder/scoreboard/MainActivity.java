package me.dcoder.scoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        int countBy = 0;

        switch (((RadioGroup) findViewById(R.id.rbBy)).getCheckedRadioButtonId()){
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

        TextView txtScoreA = (TextView) findViewById(R.id.txtScoreA);
        TextView txtScoreB = (TextView) findViewById(R.id.txtScoreB);

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
