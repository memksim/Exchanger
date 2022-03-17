package com.memksim.exchanger.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.memksim.exchanger.R
import com.memksim.exchanger.model.Valute
import com.memksim.exchanger.ui.state.ListPageViewModel

class ListPageFragment: Fragment(R.layout.fragment_list_page) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ValuteAdapter

    private lateinit var viewModel: ListPageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListPageViewModel::class.java)
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            adapter = ValuteAdapter(it, requireContext())
        })

        recyclerView = view.findViewById(R.id.currencyRecyclerView)
        recyclerView.adapter = adapter

        
    }

}