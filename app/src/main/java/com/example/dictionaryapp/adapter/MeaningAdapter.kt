package com.example.dictionaryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.data.model.Meaning
import kotlinx.android.synthetic.main.meaning_item.view.*

class MeaningAdapter(private val meaningList: MutableList<Meaning>) :
    RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {
    class MeaningViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val partOfSpeech = itemView.partOfSpeech
        val defList = itemView.definitionRv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MeaningViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.meaning_item, parent, false)
    )

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        val item = meaningList[position]

        holder.apply {
            partOfSpeech.text = item.partOfSpeech

            defList.apply {
                addItemDecoration(
                    DividerItemDecoration(
                        holder.itemView.context,
                        DividerItemDecoration.VERTICAL
                    )
                )
                adapter = item.definitions?.let { DefinitionAdapter(it) }
                layoutManager = LinearLayoutManager(holder.itemView.context)
                setHasFixedSize(true)
            }
        }

    }

    override fun getItemCount() = meaningList.size
}