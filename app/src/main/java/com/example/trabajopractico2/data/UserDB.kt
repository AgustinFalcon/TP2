package com.example.trabajopractico2.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trabajopractico2.TpDesaMobileApp
import com.example.trabajopractico2.constants.Constants
import com.example.trabajopractico2.model.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class UserDB: RoomDatabase() {

    abstract fun userDao(): UserDao



    companion object {
        @Volatile
        private var INSTANCE: UserDB? = null

        fun getInstanceDatabase(): UserDB {
            val temporalInstance = INSTANCE

            if (temporalInstance != null) {
                return temporalInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    TpDesaMobileApp.instance.applicationContext,
                    UserDB::class.java,
                    Constants.DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}