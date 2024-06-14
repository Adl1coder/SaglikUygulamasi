package com.adilegngr.mobile_health_app.model

import android.content.Context
import com.adilegngr.mobile_health_app.uiactivity.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(context: Context) {

    private val database = AppDatabase.getDatabase(context)
    private val noteDao = database.noteDao()

    suspend fun addNote(userId: String, content: String) {
        val note = Note(userId = userId, content = content)
        noteDao.insert(note)
    }

    fun getAllNotes(userId: String): Flow<List<Note>> {
        return noteDao.getAllNotes(userId)
    }
}
