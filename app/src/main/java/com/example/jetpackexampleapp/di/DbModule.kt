package com.example.jetpackexampleapp.di

import android.app.Application
import androidx.room.Room
import com.example.jetpackexampleapp.data.db.PeopleDatabase
import com.example.jetpackexampleapp.data.db.PostDao
import com.raywenderlich.android.imet.data.db.PeopleDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DbModule {

    /*
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): PeopleDatabase {
        var INSTANCE = Room.databaseBuilder(
            application, PeopleDatabase::class.java, "People.db")
            .allowMainThreadQueries()
            .build()
//        INSTANCE.let {
//            PeopleDatabase.prePopulate(it, PeopleInfoProvider.peopleList)
//        }
        return INSTANCE
    }

    /*
     * We need the MovieDao module.
     * For this, We need the AppDatabase object
     * So we will define the providers for this here in this module.
     * */
    @Provides
    @Singleton
    internal fun provideMovieDao(appDatabase: PeopleDatabase): PeopleDao {
        return appDatabase.peopleDao()
    }

    @Provides
    @Singleton
    internal fun providePostDao(appDatabase: PeopleDatabase): PostDao {
        return appDatabase.postDao()
    }
}