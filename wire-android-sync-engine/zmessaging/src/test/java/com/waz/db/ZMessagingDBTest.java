package com.waz.db;

import android.content.Context;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.waz.content.ZmsDatabase;
import com.waz.model.UserData;
import com.waz.model.UserId;
import com.waz.service.tracking.DummyTrackingService;
import com.waz.zclient.storage.db.UserDatabase;
import com.waz.zclient.storage.db.users.model.UserEntity;
import com.waz.zclient.storage.di.StorageModule;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import scala.Option;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ZMessagingDBTest {

    private static String DB_NAME = "14897932743491238932723984723142341234";

    private ZMessagingDB zMessagingDB;
    private ZmsDatabase zmsDatabase;
    protected Context context;

    @CallSuper
    @Before
    public void beforeTest() {
        context = Robolectric.application;
    }

    @CallSuper
    @After
    public void afterTest() {
        if (zMessagingDB != null) {
            zMessagingDB.close();
            context.getDatabasePath(zMessagingDB.getDatabaseName()).delete();
        }
        if (zmsDatabase != null) {
            zmsDatabase.dbHelper().close();
            context.getDatabasePath(zmsDatabase.dbHelper().getDatabaseName()).delete();
        }
    }

    private SupportSQLiteDatabase getZMessagingSQLiteDatabase() {
        if (zMessagingDB == null) {
            zMessagingDB = new ZMessagingDB(context, DB_NAME, new DummyTrackingService());
        }
        return zMessagingDB.getWritableDatabase();
    }

    private SupportSQLiteDatabase getZmsSQLiteDatabase() {
        if (zmsDatabase == null) {
            zmsDatabase = new ZmsDatabase(UserId.apply(DB_NAME), context, new DummyTrackingService());
        }
        return zmsDatabase.dbHelper().getWritableDatabase();
    }

    protected void closeZMessagingDatabase() {
        zMessagingDB.close();
    }

    protected UserDatabase getUserDatabase() {
        return StorageModule.getUserDatabase(context, DB_NAME, UserDatabase.getMigrations());
    }

    protected void insertLegacyUserData(@NonNull String userId, @NonNull String name) {
        LegacyDaoUtil.insertUserData(getZMessagingSQLiteDatabase(), userId, name);
    }

    protected boolean verifyLegacyUserDataRead(final UserEntity userEntity) {
        Option<UserData> userData = LegacyDaoUtil.getUserData(getZmsSQLiteDatabase(), userEntity.getId());
        return LegacyDaoUtil.verifyUserData(userData, userEntity);
    }
}
