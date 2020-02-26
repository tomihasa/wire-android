package com.waz.db

import com.waz.zclient.storage.db.users.model.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersTableRoomMigrationTest : ZMessagingDBTest() {

    @Ignore("Database schemas need migration")
    @Test
    fun test_usersTableLegacyInsert_canBeReadFromRoom() {
        val userId = "someId"
        val userName = "someName"
        insertLegacyUserData(userId, userName)
        closeZMessagingDatabase()
        runBlocking {
            userDatabase.userDbService().byId(userId).take(1).collect {
                assert(it.name == userName)
            }
        }
    }

    @Test
    fun test_usersTableRoomInsert_canBeReadFromLegacy() {
        val userId = "userId"
        val userName = "userName"
        val userEntity = UserEntity(id = userId, name = userName, deleted = false,
            relation = "Other", verified = "UNKNOWN")
        runBlocking {
            userDatabase.userDbService().insert(userEntity)
            assert(verifyLegacyUserDataRead(userEntity))
        }
    }
}
