package com.jcdelacueva.bluefetchassignment2.view.feed

import com.jcdelacueva.bluefetchassignment2.data.Token
import com.jcdelacueva.bluefetchassignment2.data.model.Feed

class FeedPresenter(private val view: FeedContract.View, private val repo: FeedRepo): FeedContract.Presenter {
    init {
        view.setPresenter(this)
    }

    override fun setLimit(limit: Int) {
        repo.limit = limit
    }

    override fun loadFeed() {
        repo.getFeeds { error ->
            if (!view.isViewAlive()) {
                return@getFeeds
            }

            if (error == null) {
                view.onNotifyDataUpdate()
            } else {
                error.printStackTrace()
                view.showError(error)
            }
        }
    }

    override fun getFeeds(): List<Feed> {
        return repo.feeds
    }

    override fun logout() {
        Token.currentToken = ""
        view.onLogout()
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }
}
