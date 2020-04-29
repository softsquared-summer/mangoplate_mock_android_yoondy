package com.softsquared.mangoplate.src;

import android.app.ProgressDialog;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public ProgressDialog mProgressDialog;

    public void showCustomToast(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showProgressDialog() {
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("로딩중...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
