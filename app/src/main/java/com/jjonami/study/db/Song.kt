package com.jjonami.study.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room 3가지 구성요소
 * 1. Entity : DB table을 class로 나타낸 것
 * 2. DAO : Data Access Object
 * 3. Database : 앱에 영구 저장되는 데이터와 기본 연결을 위한 주 액세스 지정
 */
//tableName을 안주면 class명으로 설정
//PrimaryKey는 반드시 있어야 한다.
@Entity(tableName = "song")
data class Song(
    @PrimaryKey(autoGenerate = true) var idx: Int?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "singer") var singer: String?,
    @ColumnInfo(name = "album") var album: String?,
    @ColumnInfo(name = "releaseDate") var releaseDate: String?
){
    constructor(): this(null,"","","","")
}