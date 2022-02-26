package com.hcdisat.weekone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hcdisat.weekone.models.Account;
import com.hcdisat.weekone.models.StorageRepository;
import com.hcdisat.weekone.validation.EmailFormatWatcher;
import com.hcdisat.weekone.validation.IValidate;

public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    public static final String EXTRA_EMAIL = "EXTRA_EMAIL";
    private boolean mIsEmailValid, mIsPasswordValid;
    private EditText mEmail;
    private EditText mPassword;
    private View mNextBtn;

    private Account getAccount() {
        return new Account(
                mEmail.getText().toString(),
                mPassword.getText().toString()
        );
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNextBtn = findViewById(R.id.btn_login_next);
        mNextBtn.setOnClickListener(this);
        View backBtn = findViewById(R.id.btn_back);
        backBtn.setOnClickListener(this);

        mEmail = (EditText) findViewById(R.id.editText_login_emailAddress);
        mPassword = (EditText) findViewById(R.id.editText_login_password);
        mEmail.addTextChangedListener(this);
        mPassword.addTextChangedListener(this);
    }

    @Override public void onClick(View view) {
        if (view.getId() == R.id.btn_back) {
            finish();
            return;
        }

        Account account = mStorageRepository.read(mEmail.getText().toString());
        Intent intent;
        if (account == null) {
            intent = new Intent(this, NewAccountActivity.class);
            startActivity(intent);
            return;
        }

        if (account.getPassword().equals(mPassword.getText().toString())) {
            intent = new Intent(this, WelcomeActivity.class);
            intent.putExtra(EXTRA_EMAIL, account.getEmail());
            startActivity(intent);
            return;
        }

        findViewById(R.id.incorrect_password_alert).setVisibility(View.VISIBLE);
    }

    @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mEmail.hasFocus()) {
            IValidate emailValidator =
                    new EmailFormatWatcher(editable.toString(), R.string.email_invalid_error_message);
            mIsEmailValid = emailValidator.validate();
        }
        if (mPassword.hasFocus())
            mIsPasswordValid = !editable.toString().isEmpty();

        View btnBackground = findViewById(R.id.btn_container);
        if (mIsEmailValid && mIsPasswordValid) {
            btnBackground.setBackgroundResource(R.drawable.gradient_button_background);
            mNextBtn.setEnabled(true);
            return;
        }

        btnBackground.setBackgroundResource(R.drawable.disabled_button_background);
        mNextBtn.setEnabled(false);
    }
}