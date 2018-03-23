package com.eserv.client;
//package com.eserv.client;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.sourcey.materiallogindemo.R;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;

;

public class SignupActivity extends Activity {
    private static final String TAG = "SignupActivity";
    //  UserDbHandler handler = new UserDbHandler(getBaseContext());
    @Bind(R.id.input_fname)
    EditText _fnameText;
    @Bind(R.id.input_pincode)
    EditText _pincodeText;
    @Bind(R.id.input_state)
    EditText _stateText;
    @Bind(R.id.input_city)
    EditText _cityText;
    @Bind(R.id.input_lname)
    EditText _lnameText;
    @Bind(R.id.input_address1)
    EditText _addressText1;
    @Bind(R.id.input_address2)
    EditText _addressText2;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_mobile)
    EditText _mobileText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.input_reEnterPassword)
    EditText _reEnterPasswordText;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.link_login)
    TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*  int count = handler.getUserCount();

        if(count > 0) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }*/
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");
     /*   int count = handler.getUserCount();
        if(count > 0) {
            onSignupFailed("another user is registered");
            return;
        }*/
        if (!validate()) {
            onSignupFailed("");
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String fname = _fnameText.getText().toString();
        String lname = _lnameText.getText().toString();
        String state = _stateText.getText().toString();
        String city = _cityText.getText().toString();
        String pincode = _pincodeText.getText().toString();
        String line1 = _addressText1.getText().toString();
        String line2 = _addressText2.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();



        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        onSignupSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess(){
        String fname = _fnameText.getText().toString();
        String lname = _lnameText.getText().toString();
        String state = _stateText.getText().toString();
        String city = _cityText.getText().toString();
        String pincode = _pincodeText.getText().toString();
        String line1 = _addressText1.getText().toString();
        String line2 = _addressText2.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();

        String type = "signup";
        WSHandler backgroundWorker = new WSHandler(this);
        String p = null;
        try {
            p = (backgroundWorker.execute(type, fname,lname,line1,line2,city,state,pincode,email,mobile, password)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String k = p.toString();
        if(k.equals("\"SUCCESS\"}")) {
            UserDbHandler handler = new UserDbHandler(getBaseContext());
            User user = new User(1,fname,email,password,Long.parseLong(mobile));
            handler.addUser(user);
            int count = handler.getUserCount();
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Signup Success", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Intent intent = new Intent(SignupActivity.this, SignupActivity.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(),k, Toast.LENGTH_LONG).show();
            finish();
        }
        _signupButton.setEnabled(true);
        finish();
    }


    public void onSignupFailed(String message) {
        Toast.makeText(getBaseContext(), "Signup failed" + (message!=""?", "+message:""), Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String fname = _fnameText.getText().toString();
        String lname = _lnameText.getText().toString();
        String line1 = _addressText1.getText().toString();
        String line2 = _addressText2.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();
        String pincode = _pincodeText.getText().toString();

        if (fname.isEmpty() || fname.length() < 3) {
            _fnameText.setError("at least 3 characters");
            valid = false;
        } else {
            _fnameText.setError(null);
        }

        if (line1.isEmpty()) {
            _addressText1.setError("Enter Valid Address");
            valid = false;
        } else {
            _addressText1.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (pincode.isEmpty() || pincode.length()!= 6) {
            _mobileText.setError("Enter Valid Pincode");
            valid = false;
        } else {
            _pincodeText.setError(null);
        }
        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            _passwordText.setError("minimum of 6 characters required");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 6 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }
}