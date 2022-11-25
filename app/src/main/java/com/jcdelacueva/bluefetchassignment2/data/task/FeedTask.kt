package com.jcdelacueva.bluefetchassignment2.data.task

import com.jcdelacueva.bluefetchassignment2.data.apiInterface
import com.jcdelacueva.bluefetchassignment2.data.model.Feed

object FeedTask {
    suspend fun getFeed(token: String, limit: Int): List<Feed> {
        return apiInterface.getFeed(token, limit)
    }
}
