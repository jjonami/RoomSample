package com.jjonami.study.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//RoomDatabase() 상속
@Database(
    entities = [Song::class],
    version = 1
)
abstract class SongDB: RoomDatabase() {
    abstract fun songDao(): SongDao

    companion object{
        private var INSTANCE: SongDB? = null

        fun getInstance(context: Context): SongDB? {
            if(INSTANCE == null){
               synchronized(SongDB::class){
                   INSTANCE = Room.databaseBuilder(context.applicationContext,
                       SongDB::class.java, "roomSample.db")
                       .fallbackToDestructiveMigration()
                       .build()
               }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE?.close()
            INSTANCE = null
        }

    }
}