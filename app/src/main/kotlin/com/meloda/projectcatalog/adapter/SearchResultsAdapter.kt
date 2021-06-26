package com.meloda.projectcatalog.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import coil.api.load
import com.meloda.projectcatalog.base.adapter.BaseAdapter
import com.meloda.projectcatalog.base.adapter.BindingHolder
import com.meloda.projectcatalog.databinding.ItemSearchResultBinding
import com.meloda.projectcatalog.item.SearchResult

class SearchResultsAdapter(context: Context, values: ArrayList<SearchResult>) :
    BaseAdapter<SearchResult, SearchResultsAdapter.Holder>(context, values) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemSearchResultBinding.inflate(inflater, parent, false))
    }

    inner class Holder(binding: ItemSearchResultBinding) :
        BindingHolder<ItemSearchResultBinding>(binding) {

        override fun bind(position: Int) {
            val item = getItem(position)

            binding.title.text = item.title
            binding.price.text = "%d ₽".format(item.price)

            binding.picture.load(item.pictureUrl) {
                crossfade(true)
                placeholder(ColorDrawable(Color.GREEN))
                error(ColorDrawable(Color.RED))
            }
        }
    }


}