package com.memksim.exchanger.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memksim.exchanger.databinding.CurrencyItemBinding

class DashboardAdapter() : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    var items: List<DashboardItemUiState> = emptyList()

    inner class DashboardViewHolder(
        private val binding: CurrencyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            with(binding){
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

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) = holder.onBind(position)

    override fun getItemCount(): Int = items.size

}