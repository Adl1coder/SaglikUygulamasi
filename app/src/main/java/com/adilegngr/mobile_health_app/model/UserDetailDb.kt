package com.adilegngr.mobile_health_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDetailDb(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "mobile") val mobile: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "name") val name: String?,
)
