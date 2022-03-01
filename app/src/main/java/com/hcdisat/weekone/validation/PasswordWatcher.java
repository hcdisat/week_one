package com.hcdisat.weekone.validation;

public class PasswordWatcher implements IValidate {

    private final CharSequence _target, _textToCheck;

    private final int _messageId;

    public PasswordWatcher(CharSequence _target, CharSequence _textToCheck, int messageId) {
        this._target = _target;
        this._textToCheck = _textToCheck;
        _messageId = messageId;
    }

    private boolean passwordsMatches(CharSequence password, CharSequence repeatedPassword) {
        return !password.toString().isEmpty()
                && !repeatedPassword.toString().isEmpty()
                && password.equals(repeatedPassword);
    }

    @Override public boolean validate() {
        return passwordsMatches(_target, _textToCheck);
    }

    @Override public int getMessageId() {
        return _messageId;
    }
}
