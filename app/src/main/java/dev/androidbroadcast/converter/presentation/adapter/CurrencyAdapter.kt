package dev.androidbroadcast.converter.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.androidbroadcast.converter.databinding.ItemCurrencyBinding
import dev.androidbroadcast.converter.presentation.model.CurrencyUIModel

class CurrencyAdapter : ListAdapter<CurrencyUIModel, CurrencyAdapter.CurrencyViewHolder>(CurrencyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CurrencyViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CurrencyUIModel) {
            binding.currencyName.text = item.name
            binding.currencyRate.text = item.rate.toString()
            binding.currencyChange.text = String.format("%.2f", item.change)
            // Если изменение положительное – фон зеленый, иначе – красный
            if (item.change > 0) {
                binding.root.setBackgroundColor(Color.GREEN)
            } else {
                binding.root.setBackgroundColor(Color.RED)
            }
        }
    }

    class CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyUIModel>() {
        override fun areItemsTheSame(oldItem: CurrencyUIModel, newItem: CurrencyUIModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CurrencyUIModel, newItem: CurrencyUIModel): Boolean {
            return oldItem == newItem
        }
    }
}