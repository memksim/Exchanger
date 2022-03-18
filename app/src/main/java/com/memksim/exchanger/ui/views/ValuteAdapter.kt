package com.memksim.exchanger.ui.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.memksim.exchanger.R
import com.memksim.exchanger.model.Valute

class ValuteAdapter(private val context: Context): RecyclerView.Adapter<ValuteAdapter.ValuteViewHolder>() {

    var items: List<Valute> = emptyList()

    class ValuteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val valuteTitle: TextView = itemView.findViewById(R.id.valuteTitle)
        val valuteValue: TextView = itemView.findViewById(R.id.valuteValue)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValuteViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.valute_item, parent, false)

        return ValuteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ValuteViewHolder, position: Int) {
        holder.valuteTitle.text = items[position].charCode
        holder.valuteValue.text = items[position].value.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

}