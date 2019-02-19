package nl.uscki.appcki.wilson.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomappbar.BottomAppBar;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.helpers.UserHelper;
import nl.uscki.appcki.wilson.ui.MenuBottomSheet;
import nl.uscki.appcki.wilson.api.ServiceGenerator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ServiceGenerator.init();
        UserHelper.getInstance().init(this.getSharedPreferences("user", MODE_PRIVATE));

        BottomAppBar appBar = findViewById(R.id.bar);
        appBar.setNavigationOnClickListener(view -> new MenuBottomSheet().show(getSupportFragmentManager(), "menuBottomSheet"));

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.loginFragment) {
                // With no token to request anything the user is now logged out
                UserHelper.getInstance().setToken("");

                appBar.setVisibility(View.GONE);
            } else {
                if (appBar.getVisibility() == View.GONE)
                    appBar.setVisibility(View.VISIBLE);
            }
        });

        if (UserHelper.getInstance().getToken().equals("")) {
            navController.navigate(R.id.action_global_logout);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        if (UserHelper.getInstance().getToken().equals("") && navController.getCurrentDestination().getId() == R.id.loginFragment)
            return true;

        return navController.navigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onStop();

        UserHelper.getInstance().destroy();
    }
}
