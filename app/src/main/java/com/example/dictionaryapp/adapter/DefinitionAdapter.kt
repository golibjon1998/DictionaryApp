package com.example.dictionaryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.data.model.Definition
import kotlinx.android.synthetic.main.definition_item.view.*

class DefinitionAdapter(private val defList: MutableList<Definition>) :
    RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder>() {

    class DefinitionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val definition = itemView.tvDefinition
        val number = itemView.tvNumber
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DefinitionViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.definition_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        val item = defList[position]

        holder.apply {
            definition.text = item.definition
            number.text = "${absoluteAdapterPosition.plus(1)}."
        }

    }

    override fun getItemCount() = defList.size
}