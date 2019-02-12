package nl.uscki.appcki.wilson.activities.ui.login;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.UserHelper;
import nl.uscki.appcki.wilson.api.Callback;
import nl.uscki.appcki.wilson.api.Services;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        final TextInputLayout username = view.findViewById(R.id.username);
        final TextInputLayout password = view.findViewById(R.id.password);

        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        password.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String usernameText = username.getEditText().getText().toString();
                String passwordText = password.getEditText().getText().toString();

                Services.getInstance()
                        .userService.login(usernameText, passwordText)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onSucces(Response<Void> response) {
                                UserHelper.getInstance().setToken(response.headers().get("X-AUTH-TOKEN"));

                                navController.navigate(
                                        R.id.newsPageFragment,
                                        null,
                                        new NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                                );
                            }
                        });

                return true;
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
