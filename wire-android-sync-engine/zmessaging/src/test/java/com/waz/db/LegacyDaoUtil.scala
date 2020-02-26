package com.waz.db

import androidx.sqlite.db.SupportSQLiteDatabase
import com.waz.model.UserData.UserDataDao
import com.waz.model.{UserData, UserId}
import com.waz.utils.wrappers.DB
import com.waz.zclient.storage.db.users.model.UserEntity

object LegacyDaoUtil {

  def insertUserData(database: SupportSQLiteDatabase,
                     userId: String,
                     name: String): Unit = {
    implicit val db = DB(database)
    UserDataDao.insertOrReplace(UserData(UserId(userId), name))
  }

  def getUserData(database: SupportSQLiteDatabase,
                  userId: String): Option[UserData] = {
    implicit val db = DB(database)
    UserDataDao.get(UserId(userId)).orElse(null)
  }

  def verifyUserData(userData: Option[UserData], userEntity: UserEntity) =
    userData.fold(false)(data =>
        data.name.str == userEntity.getName && data.deleted == userEntity.getDeleted
    )

}
