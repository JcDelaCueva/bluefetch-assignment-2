package com.jcdelacueva.bluefetchassignment2.view.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jcdelacueva.bluefetchassignment2.R
import com.jcdelacueva.bluefetchassignment2.base.BaseRecyclerViewHolder
import com.jcdelacueva.bluefetchassignment2.data.model.Feed
import java.text.SimpleDateFormat

class FeedAdapter(private val presenter: FeedContract.Presenter): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = presenter.getFeeds()[holder.adapterPosition]
        holder.bind(order)
    }

    override fun getItemCount(): Int {
        return presenter.getFeeds().size
    }

}

class ViewHolder(val view: View): BaseRecyclerViewHolder(view) {

    private val tvName = view.findViewById(R.id.tvName) as TextView
    private val tvText = view.findViewById(R.id.tvText) as TextView
    private val tvTime = view.findViewById(R.id.tvTime) as TextView
    private val imgUser = view.findViewById(R.id.imgUser) as AppCompatImageView

    override fun bind(item: Any) {
        val feed = item as? Feed
        feed?.let { feed ->
            tvText.text = feed.text

            //"Tue, 26 Jul 2022 10:13:18 GMT"
            feed.updatedAt?.let { updatedAt ->
                var simpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")
                val date = simpleDateFormat.parse(updatedAt)
                simpleDateFormat = SimpleDateFormat("dd-MMM-yyyy")
                val formattedDate = simpleDateFormat.format(date)
                tvTime.text = formattedDate
            }

            feed.user?.let { user ->
                tvName.text = "${user.firstName} ${user.lastName}"
                val imgURL = user.profilePic
                if (imgURL != null && imgURL.isNotBlank()) {
                    Glide.with(view).load(imgURL).into(imgUser)
                }
            }
        }
    }

    override fun prepareForReuse() {
        tvName.text = ""
        tvText.text = ""
        tvTime.text = ""
    }
}