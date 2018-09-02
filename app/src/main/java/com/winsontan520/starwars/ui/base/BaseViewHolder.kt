package com.winsontan520.starwars.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<D>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(item: D)

}