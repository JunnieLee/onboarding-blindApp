package com.example.blindapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.blindapp.data.model.entity.ContentEntity
import com.example.blindapp.data.source.local.dao.ContentDao

@Database(entities=[ContentEntity::class], version=1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase:RoomDatabase() {

    abstract fun contentDao(): ContentDao
}