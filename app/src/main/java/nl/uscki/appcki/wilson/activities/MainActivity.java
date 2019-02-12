package nl.uscki.appcki.wilson.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomappbar.BottomAppBar;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.UserHelper;
import nl.uscki.appcki.wilson.activities.ui.main.MenuBottomSheet;
import nl.uscki.appcki.wilson.api.ServiceGenerator;

public class MainActivity extends AppCompatActivity {
    private MenuBottomSheet menuBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ServiceGenerator.init();
        UserHelper.getInstance().init(this.getSharedPreferences("user", MODE_PRIVATE));

        if (!UserHelper.getInstance().getToken().equals("")) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.newsFragment);

            setupAppBar();
            setupMenuBottomSheet();
        } else {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.loginFragment);
        }
    }

    private void setupAppBar () {
        BottomAppBar appBar = findViewById(R.id.bar);
        appBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.menuBottomSheet.show(getSupportFragmentManager(), "menuBottomSheet");
            }
        });
    }

    private void setupMenuBottomSheet () {
        this.menuBottomSheet = new MenuBottomSheet();

        final NavController controller = Navigation.findNavController(this, R.id.nav_host_fragment);

        this.menuBottomSheet.setOnMenuItemClickedListener(new MenuBottomSheet.OnMenuItemClickedListener() {
            @Override
            public void onMenuItemClicked(Integer menuId) {
                switch (menuId) {
                    case R.id.menu_news:
                        controller.navigate(R.id.newsFragment);
                        break;
                    case R.id.menu_agenda:
                        controller.navigate(R.id.mainFragment);
                        break;
                    case R.id.menu_roephoek:
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onStop();

        UserHelper.getInstance().destroy();
    }
}
