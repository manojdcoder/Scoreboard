package me.dcoder.scoreboard.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import me.dcoder.scoreboard.R;
import me.dcoder.scoreboard.fragment.ListFragment;
import me.dcoder.scoreboard.fragment.ScoreFragment;
import me.dcoder.scoreboard.fragment.SettingsFragment;
import me.dcoder.scoreboard.util.Preference;

public class MainActivity extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigateTo(new ScoreFragment(), false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSettings:
                navigateTo(new SettingsFragment(), true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateTo(Fragment fragment, boolean stack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frgContainer, fragment);
        if (stack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onListFragmentInteraction(Fragment fragment) {
        navigateTo(fragment, false);
    }

    @Override
    protected void onDestroy() {
        Preference preference = new Preference(this);
        if (!preference.getBoolean(R.string.preference_key_save)) {
            preference.clear();
        }
        super.onDestroy();
    }
}
