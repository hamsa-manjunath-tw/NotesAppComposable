package com.example.notesappcompose.di

import android.app.Application
import androidx.room.Room
import com.example.notesappcompose.data.datasource.LocalDatasource
import com.example.notesappcompose.data.datasource.LocalDatasourceImpl
import com.example.notesappcompose.data.datasource.NoteDao
import com.example.notesappcompose.data.datasource.NoteDataBase
import com.example.notesappcompose.data.repoImpl.NotesRepositoryImpl
import com.example.notesappcompose.domain.repoInterface.NotesRepository
import com.example.notesappcompose.viewmodel.NotesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        NotesViewModel(get())
    }

    single<NotesRepository> {
        NotesRepositoryImpl(get())
    }

    single<LocalDatasource>{
        LocalDatasourceImpl(get())
    }

    fun provideDataBase(application: Application): NoteDataBase {
        return Room.databaseBuilder(application, NoteDataBase::class.java, "notes_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: NoteDataBase): NoteDao {
        return dataBase.noteDao
    }
    single { provideDataBase(androidApplication()) }
    //single { provideDao(get()) }

    single<NoteDao> {
        val database = get<NoteDataBase>()
        database.noteDao
    }



}