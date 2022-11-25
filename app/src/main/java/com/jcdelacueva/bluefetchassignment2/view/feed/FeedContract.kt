package com.jcdelacueva.bluefetchassignment2.view.feed

import com.jcdelacueva.bluefetchassignment2.base.BasePresenter
import com.jcdelacueva.bluefetchassignment2.base.BaseView
import com.jcdelacueva.bluefetchassignment2.data.model.Feed

interface FeedContract {
    interface View :
        BaseView<Presenter> {

        fun onNotifyDataUpdate()

        fun onLogout()
    }

    interface Presenter: BasePresenter {

        fun setLimit(limit: Int)

        fun loadFeed()

        fun getFeeds(): List<Feed>

        fun logout()
    }
}