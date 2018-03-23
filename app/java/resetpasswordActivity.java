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


public class resetpasswordActivity extends Activity {
    private static final String TAG = "resetpasswordActivity";

    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.btn_reset) Button _resetButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        ButterKnife.bind(this);

        _resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login() {
        Log.d(TAG, "Reset");

        if (!validate()) {
            onResetFailed();
            return;
        }

        _resetButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(resetpasswordActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Processing...");
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onResetSuccess();
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

    public void onResetSuccess() {

        String email = _emailText.getText().toString();
        String type = "resetpassword";
        WSHandler backgroundWorker = new WSHandler(this);
        String p = null;
        try {
            p = (backgroundWorker.execute(type, email)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String k = p.toString();
        if(k.equals("Your temporary password is emailed to your registered email ID")) {
            Intent intent = new Intent(resetpasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Your temporary password is emailed to your registered email ID", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Intent intent = new Intent(resetpasswordActivity.this, resetpasswordActivity.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(),k, Toast.LENGTH_LONG).show();
            finish();
        }


        // Create instance of AsyncWSHandler
        // Call submit method
        // Read output
        _resetButton.setEnabled(true);
        finish();
    }

    public void onResetFailed() {
        Toast.makeText(getBaseContext(), "reset password failed", Toast.LENGTH_LONG).show();

        _resetButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        return valid;
    }
}
