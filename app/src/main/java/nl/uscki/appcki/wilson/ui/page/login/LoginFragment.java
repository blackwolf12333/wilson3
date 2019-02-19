package nl.uscki.appcki.wilson.ui.page.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.api.Callback;
import nl.uscki.appcki.wilson.api.Services;
import nl.uscki.appcki.wilson.helpers.UserHelper;
import retrofit2.Response;

/**
 * LoginFragment
 * We should never directly navigate to this fragment but always use the @id/action_global_logout
 * Except in the main activity when we detect there is no token yet and we have to navigate here
 */
public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        final TextInputLayout username = view.findViewById(R.id.username);
        final TextInputLayout password = view.findViewById(R.id.password);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity == null)
            return view;

        final NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);

        password.getEditText().setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i != EditorInfo.IME_ACTION_GO)
                return false;

            String usernameText = username.getEditText().getText().toString();
            String passwordText = password.getEditText().getText().toString();

            Services.getInstance()
                    .userService.login(usernameText, passwordText)
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onSucces(Response<Void> response) {
                            String token = response.headers().get("Authorization");

                            if (token != null && !token.equals("")) {
                                UserHelper.getInstance().setToken(response.headers().get("Authorization"));

                                InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

                                // check if no view has focus:
                                View currentFocusedView = activity.getCurrentFocus();
                                if (currentFocusedView != null) {
                                    inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                                }

                                navController.popBackStack();
                            }
                        }

                        @Override
                        public void onError(Response<Void> response) {
                            super.onError(response);

                            password.setError("Username or password are invalid!");
                        }
                    });

            return true;
        });

        return view;
    }
}
