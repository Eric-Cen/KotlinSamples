package com.mcarving.roomwordsample

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class WordRepository(private val wordDao: WordDao){

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    // @WorkerThread annotation to mark this method needs to be called from a
    // non-UI thread

    // suspend modifier to tell the compiler that this needs to be called
    // from a coroutine or another suspending function
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }
}