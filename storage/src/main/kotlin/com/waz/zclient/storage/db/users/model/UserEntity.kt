package com.waz.zclient.storage.db.users.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Users",
    indices = [Index(value = ["_id", "skey"])]
)
data class UserEntity(

    @ColumnInfo(name = "_id")
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "teamId")
    var teamId: String? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "email")
    var email: String? = null,

    @ColumnInfo(name = "phone")
    var phone: String? = null,

    @ColumnInfo(name = "tracking_id")
    var trackingId: String? = null,

    @ColumnInfo(name = "picture")
    var picture: String? = null,

    @ColumnInfo(name = "accent")
    var accentId: Int? = null,

    @ColumnInfo(name = "skey")
    var sKey: String? = null,

    @ColumnInfo(name = "connection")
    var connection: String? = null,

    @ColumnInfo(name = "conn_timestamp")
    var connectionTimestamp: Long? = null,

    @ColumnInfo(name = "conn_msg")
    var connectionMessage: String? = null,

    @ColumnInfo(name = "conversation")
    var conversation: String? = null,

    @ColumnInfo(name = "relation")
    var relation: String,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long? = null,

    @ColumnInfo(name = "verified")
    var verified: String,

    @ColumnInfo(name = "deleted")
    val deleted: Boolean,

    @ColumnInfo(name = "availability")
    var availability: Int? = null,

    @ColumnInfo(name = "handle")
    var handle: String? = null,

    @ColumnInfo(name = "provider_id")
    var providerId: String? = null,

    @ColumnInfo(name = "integration_id")
    var integrationId: String? = null,

    @ColumnInfo(name = "expires_at")
    var expiresAt: Int? = null,

    @ColumnInfo(name = "managed_by")
    var managedBy: String? = null,

    @ColumnInfo(name = "self_permissions")
    var selfPermission: Int? = null,

    @ColumnInfo(name = "copy_permissions")
    var copyPermission: Int? = null,

    @ColumnInfo(name = "created_by")
    var createdBy: String? = null
)
