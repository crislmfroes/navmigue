package com.crislmfroes.navmigue.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.crislmfroes.navmigue.model.Word
import com.crislmfroes.navmigue.model.WordRepository
import com.crislmfroes.navmigue.model.WordRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : WordRepository
    val allWords : LiveData<List<Word>>

    init {
        val wordDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordDao)
        allWords = repository.allWords
    }

    fun insert(word : Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}