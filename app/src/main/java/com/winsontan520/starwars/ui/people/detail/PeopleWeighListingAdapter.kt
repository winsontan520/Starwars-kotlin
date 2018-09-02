package com.winsontan520.starwars.ui.people.detail

import android.view.View
import com.winsontan520.starwars.R
import com.winsontan520.starwars.data.model.PeopleWeight
import com.winsontan520.starwars.ui.base.BaseAdapter
import com.winsontan520.starwars.ui.base.BaseViewHolder
import com.winsontan520.starwars.util.DateUtil
import kotlinx.android.synthetic.main.people_weight_item.view.*

class PeopleWeighListingAdapter : BaseAdapter<PeopleWeight, PeopleWeighListingAdapter.PeopleWeightViewHolder>() {
    interface OnClickListener {
        fun onClickDelete(item: PeopleWeight)
    }

    private var listener: OnClickListener? = null

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun getItemViewId(): Int = R.layout.people_weight_item

    override fun instantiateViewHolder(view: View?): PeopleWeightViewHolder = PeopleWeightViewHolder(view)

    override fun onBindViewHolder(holder: PeopleWeighListingAdapter.PeopleWeightViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.tv_delete.setOnClickListener {
            listener?.onClickDelete(getItem(position))
        }
    }

    fun addWeight(it: PeopleWeight) {
        dataSource.add(0, it)
        notifyItemInserted(0)
    }

    class PeopleWeightViewHolder(itemView: View?) : BaseViewHolder<PeopleWeight>(itemView) {
        override fun onBind(item: PeopleWeight) {
            itemView.tv_weight.text = item.weight
            itemView.tv_last_update.text = getLastUpdated(item.lastUpdate)
        }

        private fun getLastUpdated(edited: String): CharSequence? {
            val date = DateUtil.getDateFromString(edited, DateUtil.DATE_FORMAT_FROM_SERVER)
            return DateUtil.getStringFromDate(date, DateUtil.DATE_FORMAT_LAST_UPDATE)
        }
    }
}