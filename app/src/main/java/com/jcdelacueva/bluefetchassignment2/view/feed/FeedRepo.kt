package com.jcdelacueva.bluefetchassignment2.view.feed

import com.jcdelacueva.bluefetchassignment2.data.model.Feed
import com.jcdelacueva.bluefetchassignment2.data.task.FeedTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FeedRepo(val token: String) {
    val feeds = mutableListOf<Feed>()
    var limit = 25

    fun getFeeds(callback: (error: Exception?) -> Unit) {
        val job = Job()
        val scope = CoroutineScope(Dispatchers.Main + job)
        scope.launch {
            try {
                val newFeeds = FeedTask.getFeed(token, limit)
                feeds.clear()
                feeds.addAll(newFeeds)
                callback(null)
            } catch (e: Exception) {
                callback(e)
            }
        }
    }
}
