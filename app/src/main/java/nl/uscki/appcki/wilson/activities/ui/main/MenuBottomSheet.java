package nl.uscki.appcki.wilson.activities.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

import nl.uscki.appcki.wilson.R;

public class MenuBottomSheet extends BottomSheetDialogFragment {
    private OnMenuItemClickedListener onMenuItemClickedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_sheet, container);

        NavigationView navigationView = view.findViewById(R.id.menu_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (onMenuItemClickedListener == null) {
                    Log.d("MenuBottomSheet", "Menu item click listener not set!");
                    return false;
                }

                MenuBottomSheet.this.onMenuItemClickedListener.onMenuItemClicked(item.getItemId());

                return true;
            }
        });

        return view;
    }

    public void setOnMenuItemClickedListener(OnMenuItemClickedListener onMenuItemClickedListener) {
        this.onMenuItemClickedListener = onMenuItemClickedListener;
    }

    public interface OnMenuItemClickedListener {
        void onMenuItemClicked(@IdRes Integer menuId);
    }
}
