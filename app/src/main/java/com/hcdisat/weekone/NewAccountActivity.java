package com.hcdisat.weekone;

import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hcdisat.weekone.models.Account;
import com.hcdisat.weekone.validation.EmailAccountWatcher;
import com.hcdisat.weekone.validation.EmailFormatWatcher;
import com.hcdisat.weekone.validation.IValidate;
import com.hcdisat.weekone.validation.PasswordWatcher;

public class NewAccountActivity extends BaseActivity implements TextWatcher, View.OnClickListener {

    public static final String EXTRA_EMAIL = "EXTRA_EMAIL",
            EXTRA_PASSWORD = "EXTRA_PASSWORD";

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mRepeatPasswordEditText;
    private Button mBtnNext;
    private boolean mIsEmailValid;
    private boolean mIsPasswordValid;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        mEmailEditText = findViewById(R.id.editText_login_emailAddress);
        mEmailEditText.addTextChangedListener(this);

        mPasswordEditText = findViewById(R.id.editText_login_password);
        mRepeatPasswordEditText = findViewById(R.id.editText_RepeatPassword);
        mPasswordEditText.addTextChangedListener(this);
        mRepeatPasswordEditText.addTextChangedListener(this);

        findViewById(R.id.btn_back).setOnClickListener(this);
        mBtnNext = findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);
    }

    @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    /**
     * Text changed handler
     * @param editable editable object containing the input contents
     */
    @Override public void afterTextChanged(Editable editable) {
        String textToValidate = editable.toString();
        if (mEmailEditText.hasFocus()) {
            View container = findViewById(R.id.incorrect_password_alert);
            IValidate emailFormatValidator = new EmailFormatWatcher(textToValidate, R.string.email_invalid_error_message);
            EmailAccountWatcher emailAccountValidator = new EmailAccountWatcher(textToValidate, R.string.email_error_message);
            emailAccountValidator.setStorageRepository(mStorageRepository);

            if (!emailAccountValidator.validate())
                setInputErrorState(container, mEmailEditText, emailAccountValidator.getMessageId());
            else if (!emailFormatValidator.validate())
                setInputErrorState(container, mEmailEditText, emailFormatValidator.getMessageId());
            else {
                setValidInputState(container, mEmailEditText);
                mIsEmailValid = true;
            }
        }

        if (mPasswordEditText.hasFocus() || mRepeatPasswordEditText.hasFocus()) {

            View container = findViewById(R.id.password_error);
            String repeatedPassword =
                    ((EditText)findViewById(R.id.editText_RepeatPassword))
                            .getText().toString();
            String password =
                    ((EditText)findViewById(R.id.editText_login_password))
                            .getText().toString();

            IValidate passwordValidator =
                    new PasswordWatcher(password, repeatedPassword, R.string.passwords_error_message);

            updateInputState(container, mPasswordEditText, passwordValidator);
            updateInputState(container, mRepeatPasswordEditText, passwordValidator);
            mIsPasswordValid = passwordValidator.validate();
        }

        View btnBackground = findViewById(R.id.btn_container);
        if (mIsEmailValid && mIsPasswordValid) {
            mBtnNext.setEnabled(true);
            btnBackground.setBackgroundResource(R.drawable.gradient_button_background);
            return;
        }

        mBtnNext.setEnabled(false);
        btnBackground.setBackgroundResource(R.drawable.disabled_button_background);
    }

    /**
     *
     * @param viewContainer View group that wraps the error message
     * @param targetInput Input to be decorated with green/red borders
     * @param validator Validates the input using predefined rules
     * @return true if valid, false invalid
     */
    private boolean updateInputState(View viewContainer, EditText targetInput, IValidate validator) {
        if (validator.validate()) {
            setValidInputState(viewContainer, targetInput);

            return true;
        }

        setInputErrorState(viewContainer, targetInput, validator.getMessageId());

        return false;
    }

    private void setInputErrorState(View viewContainer, EditText targetInput, int errorMessageId) {
        viewContainer.setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.error_message_text))
                .setText(getString(errorMessageId));
        targetInput.setBackground(
                AppCompatResources.getDrawable(this, R.drawable.input_error_background));
        targetInput.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
    }

    private void setValidInputState(View viewContainer, EditText targetInput) {
        viewContainer.setVisibility(View.GONE);
        targetInput.setBackground(
                AppCompatResources.getDrawable(this, R.drawable.input_success_background));
        targetInput.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick2x, 0);
    }

    @Override public void onClick(View view) {

        if (view.getId() == R.id.btn_back) {
            finish();
            return;
        }

        mStorageRepository.write(new Account(
                mEmailEditText.getText().toString(),
                mPasswordEditText.getText().toString()));

        setResult(RESULT_OK, new Intent());
        finish();
    }
}