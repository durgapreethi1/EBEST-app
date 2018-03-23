package com.eserv.client;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

public class changepasswordActivity extends Activity {
    private static final String TAG = "changepasswordActivity";

    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.old_password) EditText _oldpasswordText;
    @Bind(R.id.new_password) EditText _newpasswordText;
    @Bind(R.id.new_password1) EditText _newpasswordText1;
    @Bind(R.id.btn_change) Button _changeButton;
    @Bind(R.id.link_forgot) TextView _forgotLink;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        ButterKnife.bind(this);

        _changeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _forgotLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), resetpasswordActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Changepassword");

        if (!validate()) {
            onChangeFailed();
            return;
        }

        _changeButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(changepasswordActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Processing...");
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onChangeSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {


                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onChangeSuccess() {

        String email = _emailText.getText().toString();
        String old_password = _oldpasswordText.getText().toString();
        String newpassword = _newpasswordText.getText().toString();
        String confirmpassword = _newpasswordText1.getText().toString();
        String type = "changepassword";
        WSHandler backgroundWorker = new WSHandler(this);
        String p = null;
        try {
            p = (backgroundWorker.execute(type, email,old_password,newpassword ,confirmpassword)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String k = p.toString();
        if(k.equals("\"Password changed successfully\"}")) {
            Intent intent = new Intent(changepasswordActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Signup Success", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Intent intent = new Intent(changepasswordActivity.this, changepasswordActivity.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(),k, Toast.LENGTH_LONG).show();
            finish();
        }
        _changeButton.setEnabled(true);
        finish();
    }

    public void onChangeFailed() {
        Toast.makeText(getBaseContext(), "change password failed", Toast.LENGTH_LONG).show();

        _changeButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String old_password = _oldpasswordText.getText().toString();
        String newpassword = _newpasswordText.getText().toString();
        String confirmpassword = _newpasswordText1.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (old_password.isEmpty() || old_password.length() < 6 ) {
            _oldpasswordText.setError("please enter valid password");
            valid = false;
        } else {
            _oldpasswordText.setError(null);
        }
        if (newpassword.isEmpty() || newpassword.length() < 6 ) {
            _newpasswordText.setError("please enter valid password");
            valid = false;
        } else {
            _newpasswordText.setError(null);
        }
        if (confirmpassword.isEmpty() || confirmpassword.length() < 6 ) {
            _newpasswordText1.setError("please enter valid password");
            valid = false;
        } else {
            _newpasswordText1.setError(null);
        }

        return valid;
    }
}
