package com.memksim.exchanger.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.memksim.exchanger.R
import com.memksim.exchanger.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAdapter = HomePageAdapter()

        viewModel.liveData.observe(viewLifecycleOwner) {
            homeAdapter.items = it.itemStateList.toMutableList()
        }

        with(binding) {

            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.refresh -> {
                        if(homeAdapter.items.isNotEmpty()){
                            viewModel.loadNetworkData()
                        }
                        true
                    }

                    else -> false
                }
            }

            currencyList.run {
                adapter = homeAdapter

                val itemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
                    .also {
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.vertical_divider,
                            null
                        )?.let { dr ->
                            it.setDrawable(dr)
                        }
                    }


                addItemDecoration(
                    itemDecoration
                )
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }
}