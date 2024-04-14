package com.imageProcess.domain.use_cases

import com.core.common.UiEvent
import com.imageProcess.domain.model.imageItem
import com.imageProcess.domain.repo.imgeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetImageListUseCase @Inject constructor(val imgeRepository: imgeRepository) {
    operator  fun invoke(clientId:String,page:Int,perPage:Int) = flow<UiEvent<List<imageItem>>>{
        emit(UiEvent.Loading())
        emit(UiEvent.Success(imgeRepository.getImageList(
            clientId = clientId,
            page = page,
            perPage = perPage
        )))
    }.catch {
        emit(UiEvent.Failure(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}