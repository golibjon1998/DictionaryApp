package com.example.dictionaryapp.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.adapter.OnPhoneticListener
import com.example.dictionaryapp.adapter.PhoneticAdapter
import com.example.dictionaryapp.adapter.SourceAdapter
import com.example.dictionaryapp.data.model.Phonetic
import com.example.dictionaryapp.data.model.WordResponseItem
import com.example.dictionaryapp.ext.browse
import com.example.dictionaryapp.ext.hideAnimWithSlideDown
import com.example.dictionaryapp.ext.showAnimWithSlideUp
import com.example.dictionaryapp.ext.showToast
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private var word: WordResponseItem? = null
    private var phoneticList: MutableList<Phonetic>? = null
    private var sourceList: MutableList<String>? = null
    private lateinit var phoneticAdapter: PhoneticAdapter
    private lateinit var sourceAdapter: SourceAdapter
    var mediaPlayer: MediaPlayer? = null
    private var isPhoneticRecyclerShow = true
    private var isSourceRecyclerShow = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        word = arguments?.getParcelable("word")
        phoneticList = word?.phonetics
        sourceList = word?.sourceUrls

        tvWord.text = word?.word
        tvPhonetic.text = word?.phonetic

        tvGoMeaning.setOnClickListener {
            findNavController().navigate(
                R.id.action_detailFragment_to_meaningFragment,
                bundleOf("meaningList" to word?.meanings)
            )
        }

        initPhoneticList()

        initSourceList()


    }

    private fun initSourceList() {
        sourceAdapter = SourceAdapter(sourceList ?: mutableListOf())

        sourceRv.apply {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = sourceAdapter
        }

        expandSource.setOnClickListener {
            if (isSourceRecyclerShow) {
                sourceRv.hideAnimWithSlideDown()
                expandSource.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_down,
                    0
                )
            } else {
                sourceRv.showAnimWithSlideUp()
                expandSource.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_up,
                    0
                )
            }
            isSourceRecyclerShow = !isSourceRecyclerShow
        }

    }

    private fun initPhoneticList() {
        phoneticAdapter =
            PhoneticAdapter(phoneticList ?: mutableListOf(), object : OnPhoneticListener {

                override fun onPathClick(item: String?) = if (item.isNullOrEmpty()) {
                    showToast("No source")
                    requireContext().browse("https://google.com")
                } else {
                    requireContext().browse(item)
                }
            })

        phoneticRecycler.apply {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = phoneticAdapter
        }

        expandPhonetic.setOnClickListener {
            if (isPhoneticRecyclerShow) {
                phoneticRecycler.hideAnimWithSlideDown()
                expandPhonetic.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0)
            } else {
                phoneticRecycler.showAnimWithSlideUp()
                expandPhonetic.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up, 0)
            }
            isPhoneticRecyclerShow = !isPhoneticRecyclerShow
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {

            if (mediaPlayer!!.isPlaying) {
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()

            }

        }
    }

}