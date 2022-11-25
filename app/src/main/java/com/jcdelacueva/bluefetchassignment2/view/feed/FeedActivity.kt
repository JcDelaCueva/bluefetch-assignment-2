package com.jcdelacueva.bluefetchassignment2.view.feed

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcdelacueva.bluefetchassignment2.R
import com.jcdelacueva.bluefetchassignment2.data.Token
import com.jcdelacueva.bluefetchassignment2.data.model.Feed
import com.jcdelacueva.bluefetchassignment2.databinding.ActivityFeedBinding
import com.jcdelacueva.bluefetchassignment2.isNetworkAvailable
import com.jcdelacueva.bluefetchassignment2.view.login.LoginActivity
import retrofit2.HttpException

class FeedActivity : AppCompatActivity(), FeedContract.View {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var presenter: FeedContract.Presenter
    private lateinit var adapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        FeedPresenter(this, FeedRepo(Token.currentToken!!))

        adapter = FeedAdapter(presenter)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(getViewContext())
            adapter = this@FeedActivity.adapter
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_logout -> {
                    presenter.logout()
                    true
                }

                else -> false
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navigationDrawer.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.isChecked) {
                return@setNavigationItemSelectedListener true
            }

            when (menuItem.itemId) {
                R.id.menu_post_5 -> {
                    presenter.setLimit(LIMIT_5)
                }
                R.id.menu_post_10 -> {
                    presenter.setLimit(LIMIT_10)
                }
                R.id.menu_post_15 -> {
                    presenter.setLimit(LIMIT_15)
                }
                R.id.menu_post_20 -> {
                    presenter.setLimit(LIMIT_20)
                }
                R.id.menu_post_25 -> {
                    presenter.setLimit(LIMIT_25)
                }
                else -> {}
            }
            presenter.loadFeed()
            binding.drawerLayout.close()
            true
        }

        presenter.loadFeed()
    }

    override fun onNotifyDataUpdate() {
        adapter.notifyDataSetChanged()
    }

    override fun onLogout() {
        LoginActivity.start(getViewContext())
        finishAffinity()
    }

    override fun setPresenter(presenter: FeedContract.Presenter) {
        this.presenter = presenter
    }

    override fun showFullScreenProgress() {

    }

    override fun hideFullScreenProgress() {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showSuccess(message: String) {

    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showError(e: Exception) {
        if (e is HttpException) {
            when (e.code()) {
                401 -> showMessage("Invalid Request")
                403 -> showMessage("Not Authorized")
            }
        } else {
            showMessage(e.message ?: "")
        }
    }

    override fun onDisableEditing() {

    }

    override fun onEnableEditing() {

    }

    override fun hideKeyboard() {

    }

    override fun getViewContext(): Context {
        return this
    }

    override fun isViewAlive(): Boolean {
        return !(isFinishing || isDestroyed)
    }

    override fun isOnline(): Boolean {
        return isNetworkAvailable(this)
    }

    override fun onSessionError() {

    }

    companion object {

        const val LIMIT_5 = 5
        const val LIMIT_10 = 10
        const val LIMIT_15 = 15
        const val LIMIT_20 = 20
        const val LIMIT_25 = 25

        fun start(context: Context) {
            context.startActivity(Intent(context, FeedActivity::class.java))
        }
    }
}