package com.hcdisat.weekone.models;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

public class StorageRepository {

    public static final String FILE_NAME = "com.hcdisat.weekone.FILE_NAME";

    private StorageRepository mInstance;

    private final Context mContext;

    public StorageRepository(Context context) {
        mContext = context;
    }

    /**
     * Writes the account into the file
     * @param account account to be written
     */
    public void write(Account account) {
        SharedPreferences handler = getSharedPreferences();
        SharedPreferences.Editor editor = getEditor();
        editor.putString(account.getEmail(), account.getPassword());
        editor.apply();
    }

    @Nullable
    public Account read(String email) {
        String password = getSharedPreferences().getString(email, null);
        if (password == null) return null;

        return new Account(email, password);
    }

    public boolean isEmpty() {
        return getSharedPreferences().getAll().isEmpty();
    }

    public SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }
}
