package com.jcdelacueva.bluefetchassignment2.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder(
    private val containerView: View
) : RecyclerView.ViewHolder(
    containerView
) {

    abstract fun bind(item: Any)

    abstract fun prepareForReuse()
}

