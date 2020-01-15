package com.example.jetpackexampleapp.data.db

import android.app.Application
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jetpackexampleapp.data.model.People
import com.example.jetpackexampleapp.data.model.Post
import com.example.jetpackexampleapp.data.net.PeopleInfoProvider
import com.raywenderlich.android.imet.data.db.PeopleDao

@Database(entities = [People::class, Post::class], version = 1)
abstract class PeopleDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao
    abstract fun postDao(): PostDao
    // 2
    companion object {
        private val lock = Any()
        private const val DB_NAME = "People.db"
        private var INSTANCE: PeopleDatabase? = null

        // 3
        fun getInstance(application: Application): PeopleDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(application, PeopleDatabase::class.java, DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                }
                return INSTANCE!!
            }
        }


//        fun prePopulate(database: PeopleDatabase, peopleList: List<People>) {
//            for (people in peopleList) {
//                AsyncTask.execute { database.peopleDao().insert(people) }
//            }
//        }
    }
}