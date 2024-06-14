package com.adilegngr.mobile_health_app.uiactivity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)
