package com.example.blindapp.data.repository

import com.example.blindapp.data.model.ContentMapper.toContent
import com.example.blindapp.data.model.ContentMapper.toEntity
import com.example.blindapp.data.model.ContentMapper.toRequest
import com.example.blindapp.data.source.local.dao.ContentDao
import com.example.blindapp.data.source.remote.api.ContentService
import com.example.blindapp.domain.model.Content
import com.example.blindapp.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService,
    private val contentDao: ContentDao
) : ContentRepository {

    override fun loadList(): Flow<List<Content>> {
        return flow {

            contentDao.selectAll().collect{ list ->
                emit(list.map{it.toContent()})
            }

            emit(
                try{
                    contentService.getList().data.map { it.toContent() }
                } catch (e:IOException){
                    emptyList()
                }
            )
        }
    }

    override suspend fun save(item: Content): Boolean {
        return try{
            contentService.saveItem(item.toRequest()) // save 될때 api 통신도 되고
            contentDao.insert(item.toEntity()) // 저장도 함
            true
        } catch (e:IOException){
            false
        }
    }

    override suspend fun update(item: Content): Boolean {
        return try{
            contentService.updateItem(item.toRequest())
            contentDao.insert(item.toEntity()) // 충돌시 replace이기 때문에 똑같이 insert 메소드 써주면 됨
            true
        } catch (e:IOException){
            false
        }
    }


}