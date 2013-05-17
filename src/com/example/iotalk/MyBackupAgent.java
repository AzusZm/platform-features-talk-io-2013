
package com.example.iotalk;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupHelper;
import android.app.backup.FileBackupHelper;
import android.app.backup.SharedPreferencesBackupHelper;

public class MyBackupAgent extends BackupAgentHelper {
    private static final String PREFS_NAME = "mail_preferences";
    private static final String PREFS_BACKUP_KEY = "prefs";

    private static final String FILE_NAME = "app.state";
    private static final String FILE_BACKUP_KEY = "file";

    // TODO: Add your own backup API key to your manifest

    @Override
    public void onCreate() {
        final BackupHelper prefsHelper = new SharedPreferencesBackupHelper(this, PREFS_NAME);
        addHelper(PREFS_BACKUP_KEY, prefsHelper);

        final BackupHelper fileHelper = new FileBackupHelper(this, FILE_NAME);
        addHelper(FILE_BACKUP_KEY, fileHelper);
    }
}
