package com.memksim.exchanger.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.memksim.exchanger.R
import com.memksim.exchanger.databinding.CurrencyItemBinding

class HomePageDiffCallback(
    private val oldList: List<HomePageItemUiState>,
    private val newList: List<HomePageItemUiState>
) : DiffUtil.Callback() {
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

class HomePageAdapter : RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>() {

    var items: MutableList<HomePageItemUiState> = mutableListOf()
        set(value) {
            val result = DiffUtil.calculateDiff(
                HomePageDiffCallback(
                    field,
                    value
                )
            )
            field = value
            result.dispatchUpdatesTo(this)
        }

    inner class HomePageViewHolder(
        private val binding: CurrencyItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            var item = items[position]
            with(binding) {
                valuteNominal.text = item.nominal
                valuteTitle.text = item.charCode
                rubNominal.text = item.value.toString()

                bookmarkIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context, if (item.isBookmarked) {
                            R.drawable.ic_baseline_bookmark_24
                        } else {
                            R.drawable.ic_baseline_bookmark_border_24
                        }
                    )
                )

                trendingIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context, if (item.isTrendingUp) {
                            R.drawable.ic_baseline_trending_up_24
                        } else {
                            R.drawable.ic_baseline_trending_down_24
                        }
                    )
                )

                bookmarkIcon.setOnClickListener {
                    item = item.copy(isBookmarked = !item.isBookmarked)
                    bookmarkIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            context, if (item.isBookmarked) {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.saved),
                                    Toast.LENGTH_SHORT
                                ).show()
                                R.drawable.ic_baseline_bookmark_24
                            } else {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.removed),
                                    Toast.LENGTH_SHORT
                                ).show()
                                R.drawable.ic_baseline_bookmark_border_24
                            }
                        )
                    )
                    items[position] = item
                    item.onRemoveBookmark(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrencyItemBinding.inflate(inflater)

        return HomePageViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) =
        holder.onBind(position)

    override fun getItemCount(): Int = items.size
}

