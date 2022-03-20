package com.memksim.exchanger.ui.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.memksim.exchanger.R
import androidx.navigation.fragment.navArgs
import com.memksim.exchanger.TAG
import com.memksim.exchanger.model.Valute
import com.memksim.exchanger.ui.stateHolders.ExchangePageViewModel

class ExchangePageFragment: Fragment(R.layout.fragment_exchange_page) {

    private lateinit var rubInput: EditText
    private lateinit var valuteTitle: TextView
    private lateinit var valuteInput: EditText

    private lateinit var navController: NavController
    private val args: ExchangePageFragmentArgs by navArgs()

    private lateinit var viewModel: ExchangePageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ExchangePageViewModel::class.java]
        viewModel.setState(args.valute)

        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            rubInput.setText(it.sumInRub.toString(), TextView.BufferType.EDITABLE)
            valuteInput.setText(it.sumInValute.toString(), TextView.BufferType.EDITABLE)
        })

        valuteTitle = view.findViewById(R.id.valuteTitle)
        rubInput = view.findViewById(R.id.rubInput)
        valuteInput = view.findViewById(R.id.valuteInput)

        valuteTitle.setText(viewModel.liveData.value!!.valute.charCode)
        rubInput.setText(viewModel.liveData.value!!.sumInRub.toString(), TextView.BufferType.EDITABLE)
        valuteInput.setText(viewModel.liveData.value!!.sumInValute.toString(), TextView.BufferType.EDITABLE)


    }
}