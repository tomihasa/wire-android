package com.waz.db

import com.waz.zms.BuildConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersTableRoomMigrationTest : ZMessagingDBTest() {

    @Test
    fun test_usersTableLegacyInsert_canBeReadFromRoom() {
        if (BuildConfig.KOTLIN_CORE) {
            val userId = "someId"
            val userName = "someName"
            insertLegacyUserData(userId, userName)
            closeLegacyDatabase()
            runBlocking {
                userDatabase.userDbService().byId(userId).take(1).collect {
                    assert(it.name == userName)
                }
            }
        }
    }

    @Test
    fun test_usersTableRoomInsert_canBeReadFromLegacy() {

    }
}
