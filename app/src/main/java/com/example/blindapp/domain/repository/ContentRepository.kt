package com.example.blindapp.domain.repository

import com.example.blindapp.domain.model.Content
import kotlinx.coroutines.flow.Flow

interface ContentRepository {

    suspend fun save(item:Content):Boolean

    suspend fun update(item: Content): Boolean

    fun loadList(): Flow<List<Content>>

    /*
        suspend fun insert(item: Content): Boolean


        suspend fun delete(item: Content): Boolean

         */

}