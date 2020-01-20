package com.antcolony.social.di

import android.app.Application
import androidx.room.Room
import com.antcolony.social.data.db.PostDao
import com.antcolony.social.data.db.UserDao
import com.antcolony.social.data.db.UserDatabase
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
    internal fun provideDatabase(application: Application): UserDatabase {
        var INSTANCE = Room.databaseBuilder(
            application, UserDatabase::class.java, "User.db"
        )
            .allowMainThreadQueries()
            .build()
        return INSTANCE
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(appDatabase: UserDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    internal fun providePostDao(appDatabase: UserDatabase): PostDao {
        return appDatabase.postDao()
    }
}