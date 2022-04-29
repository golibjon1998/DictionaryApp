package com.example.dictionaryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.ext.browse
import kotlinx.android.synthetic.main.source_item.view.*

class SourceAdapter(
    private val sources: MutableList<String>
) : RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {


    class SourceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val source = itemView.tvSource
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SourceViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.source_item, parent, false)
    )

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val item = sources[position]

        holder.source.text = item

        holder.itemView.setOnClickListener {
            it.context.browse(item)
        }
    }

    override fun getItemCount() = sources.size
}