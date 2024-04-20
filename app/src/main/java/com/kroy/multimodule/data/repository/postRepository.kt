package com.kroy.multimodule.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.kroy.multimodule.data.api.APIservice
import com.kroy.multimodule.data.paging.PostPagingSource
import com.kroy.multimodule.domain.Utils
import javax.inject.Inject

class postRepository @Inject constructor(val apIservice: APIservice) {

    fun getPosts() = Pager(
        config = PagingConfig(pageSize = Utils.PER_PAGE_LIMIT, maxSize = 50), // maxsize is the max items at any given instance
        pagingSourceFactory = {PostPagingSource(apIservice = apIservice)}
    ).liveData
}