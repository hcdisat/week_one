package com.hcdisat.weekone;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hcdisat.weekone.models.Account;
import com.hcdisat.weekone.models.StorageRepository;
import com.hcdisat.weekone.validation.EmailFormatWatcher;
import com.hcdisat.weekone.validation.IValidate;

public class MainActivity extends BaseActivity {

    private Button mBtnLogin;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_new_account).setOnClickListener(v -> {
            Class<?> startActivity = mStorageRepository.isEmpty()
                    ? NewAccountActivity.class
                    : LoginActivity.class;

            startActivity(new Intent(this, startActivity));
        });
    }
}