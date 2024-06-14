package com.adilegngr.mobile_health_app.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.adilegngr.mobile_health_app.uiactivity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Query("SELECT * FROM notes WHERE userId = :userId ORDER BY timestamp DESC")
    fun getAllNotes(userId: String): Flow<List<Note>>

    // Diğer veritabanı işlemleri buraya eklenebilir
}
