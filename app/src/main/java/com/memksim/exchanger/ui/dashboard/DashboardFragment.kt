package com.memksim.exchanger.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.memksim.exchanger.R
import com.memksim.exchanger.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val binding by viewBinding(FragmentDashboardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dashboardAdapter = DashboardAdapter()

        ViewModelProvider(this)[DashboardViewModel::class.java]
            .also {
                it.updateData()

                it.liveData.observe(viewLifecycleOwner) { item ->
                    dashboardAdapter.items = item.currencyList
                }
            }

        with(binding.currencyList) {
            adapter = dashboardAdapter
        }
    }
}