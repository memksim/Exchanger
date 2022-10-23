package com.memksim.exchanger.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.memksim.exchanger.databinding.CurrencyItemBinding

class DashboardDiffCallback(
    private val oldList: List<DashboardItemUiState>,
    private val newList: List<DashboardItemUiState>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].charCode == newList[newItemPosition].charCode
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}

class DashboardAdapter() : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    var items: List<DashboardItemUiState> = emptyList()
        set(value) {
           val result = DiffUtil.calculateDiff(DashboardDiffCallback(
                field,
                value
            ))
            field = value
            result.dispatchUpdatesTo(this)
        }

    inner class DashboardViewHolder(
        private val binding: CurrencyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            with(binding) {
                valuteNominal.text = items[position].nominal.toString()
                valuteTitle.text = items[position].charCode
                rubNominal.text = items[position].value.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrencyItemBinding.inflate(inflater)

        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) =
        holder.onBind(position)

    override fun getItemCount(): Int = items.size

}