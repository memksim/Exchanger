package com.memksim.exchanger.ui.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.memksim.exchanger.R
import com.memksim.exchanger.model.Valute

interface ActionListener{

    fun onItemClicked(valute: Valute)

}

class ValuteAdapter(
    private val context: Context,
    private val listener: ActionListener):
    RecyclerView.Adapter<ValuteAdapter.ValuteViewHolder>() {

    var items: List<Valute> = emptyList()

    class ValuteViewHolder(val itemView: View, val listener: ActionListener, val items: List<Valute>): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val valuteTitle: TextView = itemView.findViewById(R.id.valuteTitle)
        val valuteValue: TextView = itemView.findViewById(R.id.valuteValue)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onItemClicked(items[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValuteViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.valute_item, parent, false)

        return ValuteViewHolder(view, listener, items)
    }

    override fun onBindViewHolder(holder: ValuteViewHolder, position: Int) {
        holder.valuteTitle.text = items[position].charCode
        holder.valuteValue.text = items[position].value.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

}