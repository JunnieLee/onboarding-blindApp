package com.example.blindapp.domain.useCase

import com.example.blindapp.domain.model.Content
import com.example.blindapp.domain.repository.ContentRepository
import javax.inject.Inject

class ContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {

    suspend fun save(item:Content) = contentRepository.save(item)

    fun loadList() = contentRepository.loadList()

    suspend fun delete(item: Content) = contentRepository.delete(item)
}