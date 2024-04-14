package com.imageProcess.data.repo

import com.core.network.dataproviders.NetworkDataProviders
import com.imageProcess.data.mapper.toDomainImageList
import com.imageProcess.domain.model.imageItem
import com.imageProcess.domain.repo.imgeRepository
import javax.inject.Inject

/**
 * This repository stores data in the desired form
 * we need only id and image url , other stuffs are of no use to us
 *
 */

class ImageRepoImpl  @Inject constructor(private val networkDataProviders: NetworkDataProviders) :
     imgeRepository{
     override suspend fun getImageList(clientId: String, page: Int, perPage: Int): List<imageItem> {
          return networkDataProviders.getImageList(
               clientId = clientId,
               page = page,
               perPage = perPage
          ).toDomainImageList()
     }
}