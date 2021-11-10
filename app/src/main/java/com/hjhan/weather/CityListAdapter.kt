package com.hjhan.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hjhan.weather.data.CityItem
import com.hjhan.weather.databinding.ItemCityBinding

class CityListAdapter(private val itemClicked: (Long) -> Unit) :
    ListAdapter<CityItem, CityListAdapter.GoodsViewHolder>(itemDiff) {

    interface OnClickItemListener {
        fun onAddLike(selectedItem: CityItem)
        fun onDeleteLike(selectedItem: CityItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val binding =
            ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        currentList[position]?.let { holder.bind(it) }
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].id
    }

    inner class GoodsViewHolder(itemView: ItemCityBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: CityItem) {
            binding.cityName.text = data.name
            itemView.setOnClickListener {
                itemClicked.invoke(data.id)
            }
        }
    }
}

private val itemDiff = object : DiffUtil.ItemCallback<CityItem>() {
    override fun areItemsTheSame(oldItem: CityItem, newItem: CityItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CityItem,
        newItem: CityItem
    ): Boolean {
        return oldItem == newItem
    }
}