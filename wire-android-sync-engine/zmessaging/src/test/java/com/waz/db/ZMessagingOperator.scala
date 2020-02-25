package com.waz.db

import com.waz.model.UserData.UserDataDao
import com.waz.model.{UserData, UserId}
import com.waz.utils.wrappers.DB

object ZMessagingOperator {

  def insertUserData(zMessagingDb: ZMessagingDB, userId: String, name: String): Unit = {
    implicit val db = DB(zMessagingDb.getWritableDatabase)
    UserDataDao.insertOrReplace(UserData(UserId(userId), name))
  }


}
