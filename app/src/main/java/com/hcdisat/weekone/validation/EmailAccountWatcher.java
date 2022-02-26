package com.hcdisat.weekone.validation;

import android.app.Application;
import android.util.Log;

import com.hcdisat.weekone.models.StorageRepository;

public class EmailAccountWatcher implements IValidate{

    private final CharSequence _target;

    private final int _messageId;

    private StorageRepository mStorageRepository;

    public EmailAccountWatcher(CharSequence _target, int messageId) {
        this._target = _target;
        _messageId = messageId;
    }

    @Override
    public boolean validate() {
        return mStorageRepository.read(_target.toString()) == null;
    }

    @Override
    public int getMessageId() {
        return _messageId;
    }

    public void setStorageRepository(StorageRepository storageRepository) {
        mStorageRepository = storageRepository;
    }
}
