package com.example.dictionaryapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.adapter.DefinitionAdapter
import com.example.dictionaryapp.adapter.MeaningAdapter
import com.example.dictionaryapp.data.model.Meaning
import kotlinx.android.synthetic.main.fragment_meaning.*

class MeaningFragment : Fragment() {

    private var meaningList: MutableList<Meaning>? = null

    private lateinit var meaningAdapter: MeaningAdapter
    private lateinit var definitionAdapter: DefinitionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meaning, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meaningList = arguments?.getParcelableArrayList("meaningList")

        initMeaning()
    }

    private fun initMeaning() {
        meaningAdapter = MeaningAdapter(meaningList ?: mutableListOf())

        meaningRv.apply {
            setHasFixedSize(true)
            adapter = meaningAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}