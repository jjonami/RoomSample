package com.jjonami.study.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface SongDao {
    @Query("SELECT * FROM song")
    fun selectAllList(): List<Song>

    //Flowable 형태로 return
    @Query("SELECT * FROM song WHERE idx=:idx")
    fun selectOneByIdx(vararg idx: Int): Flowable<Song>

    //Primary Key 중복 처리 > onConflict = REPLACE
    @Insert(onConflict = REPLACE)
    fun insert(song: Song)

    @Query("DELETE from song WHERE idx=:idx")
    fun delete(vararg idx: Int)

    //PK가 매칭되는 데이터 삭제
    @Delete
    fun deleteSong(vararg song: Song)
}