package nl.uscki.appcki.wilson.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.google.android.material.bottomappbar.BottomAppBar;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.UserHelper;
import nl.uscki.appcki.wilson.activities.ui.main.MenuBottomSheet;
import nl.uscki.appcki.wilson.api.ServiceGenerator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ServiceGenerator.init();
        UserHelper.getInstance().init(this.getSharedPreferences("user", MODE_PRIVATE));

        if (!UserHelper.getInstance().getToken().equals("")) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.newsPageFragment);
        } else {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.loginFragment);
        }

        setupAppBar();
    }

    private void setupAppBar () {
        BottomAppBar appBar = findViewById(R.id.bar);
        appBar.setNavigationOnClickListener(view -> new MenuBottomSheet().show(getSupportFragmentManager(), "menuBottomSheet"));
    }

    @Override
    protected void onDestroy() {
        super.onStop();

        UserHelper.getInstance().destroy();
    }
}
