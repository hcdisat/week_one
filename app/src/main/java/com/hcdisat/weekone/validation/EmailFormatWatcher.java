package com.hcdisat.weekone.validation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Pattern;

public class EmailFormatWatcher implements IValidate {

    private final CharSequence _target;

    private final int _messageId;

    public EmailFormatWatcher(String target, int messageId) {
        _target = target;
        _messageId = messageId;
    }

    @Override public boolean validate() {
        String _regexPattern = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";

        return Pattern.compile(_regexPattern).matcher(_target).matches();
    }

    @Override public int getMessageId() {
        return _messageId;
    }
}
