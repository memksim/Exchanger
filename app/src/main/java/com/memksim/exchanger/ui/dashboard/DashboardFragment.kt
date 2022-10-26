package com.memksim.exchanger.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.memksim.exchanger.R
import com.memksim.exchanger.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val binding by viewBinding(FragmentDashboardBinding::bind)

    private val viewModel by viewModels<DashboardViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dashboardAdapter = DashboardAdapter()

        viewModel.liveData.observe(viewLifecycleOwner) {
            dashboardAdapter.items = it.itemStateList.toMutableList()
        }

        with(binding) {

            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.refresh -> {
                        viewModel.loadNetworkData()
                        true
                    }

                    else -> false
                }
            }

            currencyList.run {
                adapter = dashboardAdapter

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