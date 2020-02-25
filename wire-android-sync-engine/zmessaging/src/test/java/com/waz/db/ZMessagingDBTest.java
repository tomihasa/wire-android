package com.waz.db;

import android.content.Context;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.waz.service.tracking.DummyTrackingService;
import com.waz.zclient.storage.db.UserDatabase;
import com.waz.zclient.storage.di.StorageModule;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ZMessagingDBTest {

    private static String DB_NAME = "test_db";

    private ZMessagingDB zMessagingDB;
    protected Context context;

    @CallSuper
    @Before
    public void beforeTest() {
        context = Robolectric.application;
        zMessagingDB = new ZMessagingDB(context, DB_NAME, new DummyTrackingService());
    }

    @CallSuper
    @After
    public void afterTest() {
        zMessagingDB.close();
        context.getDatabasePath(zMessagingDB.getDatabaseName()).delete();
    }

    protected void closeLegacyDatabase() {
        zMessagingDB.close();
    }

    protected UserDatabase getUserDatabase() {
        return StorageModule.getUserDatabase(context, DB_NAME, UserDatabase.getMigrations());
    }

    protected void insertLegacyUserData(@NonNull String userId, @NonNull String name) {
        ZMessagingOperator.insertUserData(zMessagingDB, userId, name);
    }
}
