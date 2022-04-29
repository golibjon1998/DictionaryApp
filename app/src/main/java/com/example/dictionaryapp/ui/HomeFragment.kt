package com.example.dictionaryapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.adapter.OnItemClick
import com.example.dictionaryapp.adapter.WordDefinitionAdapter
import com.example.dictionaryapp.data.model.WordResponseItem
import com.example.dictionaryapp.ext.*
import com.example.dictionaryapp.viewmodel.DictionaryViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel by activityViewModels<DictionaryViewModel>()
    private lateinit var defAdapter: WordDefinitionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        defAdapter = WordDefinitionAdapter(object : OnItemClick {
            override fun onClick(item: WordResponseItem, position: Int) {
                findNavController().navigate(
                    R.id.action_homeFragment_to_detailFragment,
                    bundleOf("word" to item)
                )
            }
        })

        initRv()

        searchView.setOnQueryTextListener(this@HomeFragment)
    }

    private fun initRv() {
        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = defAdapter
            setHasFixedSize(true)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        search(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false
    }

    fun search(query: String?) {
        // reset loader, swap cursor, etc.

        viewModel.getWordDefinitionList(query).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    progress.show()
                }
                is Resource.Success -> {
                    progress.hide()
                    val list = it.data
                    defAdapter.updateList(list)
                    requireActivity().hideKeyBoard()
                    showToast("Success")

                }
                is Resource.Failure -> {
                    progress.hide()
                    showToast("Something wrong :${it.exception.message}")
                }
            }
        }
    }

}

