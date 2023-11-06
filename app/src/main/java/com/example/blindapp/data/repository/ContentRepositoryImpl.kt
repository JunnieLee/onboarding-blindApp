package com.example.blindapp.data.repository

import com.example.blindapp.data.model.ContentMapper.toRequest
import com.example.blindapp.data.source.remote.api.ContentService
import com.example.blindapp.domain.model.Content
import com.example.blindapp.domain.repository.ContentRepository
import java.io.IOException
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService
) : ContentRepository {
    override suspend fun save(item: Content): Boolean {
        return try{
            contentService.saveItem(item.toRequest())
            true
        } catch (e:IOException){
            false
        }
    }


}