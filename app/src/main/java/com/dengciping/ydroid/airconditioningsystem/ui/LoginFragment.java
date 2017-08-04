package com.dengciping.ydroid.airconditioningsystem.ui;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.dengciping.ydroid.airconditioningsystem.Bean.UserBean;
import com.dengciping.ydroid.airconditioningsystem.Config;
import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.data.netwark.UserRepository;
import com.dengciping.ydroid.airconditioningsystem.databinding.FragmentLoginBinding;
import com.dengciping.ydroid.airconditioningsystem.utils.UI;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment;

import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.utils.DisplayUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends RxAppCompatDialogFragment {

    private FragmentLoginBinding loginBinding;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics displayMetrics = DisplayUtil.getDisplayMetrics(getContext());
        getDialog().getWindow().setLayout((int) Math.floor(displayMetrics.widthPixels * 0.6), (int) Math.floor(displayMetrics.heightPixels * 0.8));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        loginBinding.password.setOnEditorActionListener((textView, id, keyEvent) -> {
                    if (id == R.id.login || id == EditorInfo.IME_NULL) {
                        attemptLogin();
                        return true;
                    }
                    return false;
                }
        );

        loginBinding.email.setText(Config.userName);

        loginBinding.password.setText(Config.password);

        loginBinding.emailSignInButton.setOnClickListener((view) -> attemptLogin());

        return loginBinding.getRoot();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        loginBinding.email.setError(null);
        loginBinding.password.setError(null);

        // Store values at the time of the login attempt.
        String userName = loginBinding.email.getText().toString();
        String password = loginBinding.password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            loginBinding.password.setError(getString(R.string.error_invalid_password));
            focusView = loginBinding.password;
            cancel = true;
        }

        // Check for a valid userName address.
        if (TextUtils.isEmpty(userName)) {
            loginBinding.email.setError(getString(R.string.error_field_required));
            focusView = loginBinding.email;
            cancel = true;
        } else if (!isEmailValid(userName)) {
            loginBinding.email.setError(getString(R.string.error_invalid_email));
            focusView = loginBinding.email;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return;
        }

        // Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        showProgress(true);

        ApiSubscriber<UserBean> subscriber = new ApiSubscriber<UserBean>() {
            @Override
            protected void onFail(NetError error) {
                showProgress(false);

                if (error.getType() == NetError.AuthError
                        || NetError.BusinessError == error.getType()) {

                    loginBinding.password.setError(error.getMessage());

                } else {

                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onNext(UserBean userBean) {
                showProgress(false);
                dismiss();
                userBean.setUserName(userName);
                BusProvider.getBus().post(userBean);
            }
        };
        new UserRepository()
                .login(userName, password)
                .compose(this.bindUntilEvent(FragmentEvent.PAUSE))
                .subscribe(subscriber);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return Config.userName.equals(email);
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginBinding.loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            loginBinding.loginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginBinding.loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            loginBinding.loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            loginBinding.loginProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginBinding.loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            loginBinding.loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            loginBinding.loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}
