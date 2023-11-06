package com.example.blindapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("Content")
data class ContentEntity(
    @PrimaryKey(false) // 서버와 무조건 id를 일원화시키기 위해, 내부 db에서는 자체적으로 primary key를 generate하지 않음
    val id: Int,
    @ColumnInfo
    var title: String,
    @ColumnInfo
    var content:String,
    @ColumnInfo
    var category:String,
    @ColumnInfo
    val createdDate: Date,
    @ColumnInfo
    val likeCount: Int,
    @ColumnInfo
    val commentCount: Int,
    @ColumnInfo
    val viewCount: Int,
): java.io.Serializable
