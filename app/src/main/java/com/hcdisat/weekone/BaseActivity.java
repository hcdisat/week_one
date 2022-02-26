package com.hcdisat.weekone;

import androidx.appcompat.app.AppCompatActivity;

import com.hcdisat.weekone.models.StorageRepository;

public class BaseActivity extends AppCompatActivity {

    protected final StorageRepository mStorageRepository = new StorageRepository(this);
}
