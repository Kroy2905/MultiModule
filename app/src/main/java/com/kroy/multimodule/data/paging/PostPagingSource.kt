package com.kroy.multimodule.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kroy.multimodule.data.api.APIservice
import com.kroy.multimodule.data.models.postResponse
import com.kroy.multimodule.data.models.postResponseItem
import com.kroy.multimodule.domain.Utils


class PostPagingSource (val apIservice: APIservice) :PagingSource<Int,postResponseItem> (){

    // anchorPosition - the recently accessed  index in paging
    // closestPageToPosition (anchorPosition:Int) - a function that returns the loaded page that is closest
    // to the anchor position
    override fun getRefreshKey(state: PagingState<Int, postResponseItem>): Int? {
       return  state.anchorPosition?.let {
           state.closestPageToPosition(it)?.prevKey?.plus(1)
               ?:state.closestPageToPosition(it)?.nextKey?.minus(1)
       }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, postResponseItem> {
      return  try{
            val position = params.key ?:1 //  params.key returns which page to load , if null ,load 1st page
            val response = apIservice.getPosts(position,Utils.PER_PAGE_LIMIT)
             LoadResult.Page(
                data = response.body() ?: emptyList(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position > Utils.MAX_PAGE) null else position + 1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }

    }
}