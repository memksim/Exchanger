package com.memksim.exchanger.ui.inner_exchange

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.memksim.exchanger.R
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar

class InnerExchangePageFragment: Fragment(R.layout.fragment_exchange_inner_page) {

    private lateinit var rubInput: EditText
    private lateinit var valuteTitle: TextView
    private lateinit var valuteInput: EditText
    private lateinit var convertRubToValute: ImageView
    private lateinit var convertValuteToRub: ImageView
    private lateinit var description: TextView
    private lateinit var toolBar: MaterialToolbar

    private lateinit var navController: NavController
    private val args: InnerExchangePageFragmentArgs by navArgs()

    private lateinit var viewModel: InnerExchangePageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[InnerExchangePageViewModel::class.java]

        if(savedInstanceState == null){
            viewModel.setState(args.valute)
        }

        navController = findNavController()

        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            rubInput.setText(it.sumInRub.toString(), TextView.BufferType.EDITABLE)
            valuteInput.setText(it.sumInValute.toString(), TextView.BufferType.EDITABLE)
        })

        valuteTitle = view.findViewById(R.id.valuteTitle)
        rubInput = view.findViewById(R.id.rubInput)
        valuteInput = view.findViewById(R.id.valuteInput)
        convertRubToValute = view.findViewById(R.id.iconRubToValute)
        convertValuteToRub = view.findViewById(R.id.iconValuteToRub)
        description = view.findViewById(R.id.valuteDescription)
        toolBar = view.findViewById(R.id.toolbar)

        valuteTitle.setText(viewModel.liveData.value!!.valute.charCode)
        rubInput.setText(viewModel.liveData.value!!.sumInRub.toString(), TextView.BufferType.EDITABLE)
        valuteInput.setText(viewModel.liveData.value!!.sumInValute.toString(), TextView.BufferType.EDITABLE)
        description.setText(viewModel.liveData.value!!.valute.name)

        convertRubToValute.setOnClickListener {
            convertRubToValute(rubInput.text.toString().toDouble())
        }

        convertValuteToRub.setOnClickListener {
            convertValuteToRub(valuteInput.text.toString().toDouble())
        }

        toolBar.setNavigationOnClickListener {
            goBack()
        }

    }

    private fun convertRubToValute(sumInRub: Double){
        viewModel.convertRubToValute(sumInRub)
    }

    private fun convertValuteToRub(sumInValute: Double){
        viewModel.convertValuteToRub(sumInValute)
    }

    private fun goBack(){
        navController.navigate(R.id.action_innerExchangePageFragment_to_listPageFragment)
    }
}