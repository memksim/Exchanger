package com.memksim.exchanger.ui.views

import android.net.wifi.p2p.WifiP2pManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.memksim.exchanger.R
import com.memksim.exchanger.model.Valute
import com.memksim.exchanger.ui.stateHolders.ListPageViewModel

class ListPageFragment: Fragment(R.layout.fragment_list_page), ActionListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ValuteAdapter
    private lateinit var topAppBar: MaterialToolbar

    private lateinit var viewModel: ListPageViewModel

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListPageViewModel::class.java]
        loadDataFromDb()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProvider(this)[ListPageViewModel::class.java]
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            adapter.notifyDataSetChanged()
            adapter.items = it
        })

        recyclerView = view.findViewById(R.id.currencyRecyclerView)
        adapter = ValuteAdapter(requireContext(), this)
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)

        topAppBar = view.findViewById(R.id.toolbar)
        topAppBar.setOnMenuItemClickListener {
            if(it.itemId == R.id.update){
                updateList()
                true
            }else{
                false
            }
        }

        navController = findNavController()

    }

    private fun updateList() {
        viewModel.getValuteFromNet()
    }

    private fun loadDataFromDb(){
        viewModel.getValuteFromDb()
    }

    override fun onItemClicked(valute: Valute) {
        val action = ListPageFragmentDirections.actionListPageFragmentToExchangePageFragment(valute)
        navController.navigate(action)

    }

}