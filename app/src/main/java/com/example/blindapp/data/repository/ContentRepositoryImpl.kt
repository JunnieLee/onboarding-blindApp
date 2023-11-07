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
            try {
                contentService.getList().data.also { list ->
                    contentDao.insertAll(list.map {it.toEntity()}) // 우리가 api 통신을 통해 받아온 리스트를 모두 내부 DB에 넣음
                } // 네트워크 통신이 끊기게 되면 이부분은 실행이 안되고, 아래 부분만 실행되어 기존에 내부 db 통해 저장된 내용들만 보여질것임
            } finally {
                contentDao.selectAll().collect{ list ->
                    emit(list.map{it.toContent()}) // 내부 db에 있는 데이터를 리턴시켜줌
                }
            }
        }
    }

    override suspend fun delete(item: Content): Boolean {
        return try {
            item.id?.let{ id ->
                contentService.deleteItem(id) // (1) api 상 삭제 요청
            }
            contentDao.delete(item.toEntity()) // (2) 내부 db 삭제 요청 : item을 entity로 바꾼후에, 해당 item이 내부 DB에 있으면 삭제
            true
        } catch (e:IOException){
            false
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