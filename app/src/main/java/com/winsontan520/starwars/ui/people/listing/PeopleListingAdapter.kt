package com.winsontan520.starwars.ui.people.listing

import android.view.View
import com.winsontan520.starwars.R
import com.winsontan520.starwars.data.model.People
import com.winsontan520.starwars.ui.base.BaseAdapter
import com.winsontan520.starwars.ui.base.BaseViewHolder
import com.winsontan520.starwars.util.DateUtil
import kotlinx.android.synthetic.main.people_listing_item.view.*

class PeopleListingAdapter : BaseAdapter<People, PeopleListingAdapter.PeopleListingViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: People)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun getItemViewId(): Int = R.layout.people_listing_item

    override fun instantiateViewHolder(view: View?): PeopleListingViewHolder = PeopleListingViewHolder(view)

    override fun onBindViewHolder(holder: PeopleListingViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.setOnClickListener {
            listener?.onItemClick(getItem(position))
        }
    }

    class PeopleListingViewHolder(itemView: View?) : BaseViewHolder<People>(itemView) {
        override fun onBind(item: People) {
            itemView.tv_name.text = item.name
            itemView.tv_weight.text = item.mass
            itemView.tv_last_update.text = itemView.context.getString(R.string.last_updated, getLastUpdated(item.edited))
        }

        private fun getLastUpdated(edited: String): CharSequence? {
            val date = DateUtil.getDateFromString(edited, DateUtil.DATE_FORMAT_FROM_SERVER)
            return DateUtil.getStringFromDate(date, DateUtil.DATE_FORMAT_LAST_UPDATE)
        }
    }
}