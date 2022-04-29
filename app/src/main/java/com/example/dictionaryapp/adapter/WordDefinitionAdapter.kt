package com.example.dictionaryapp.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.data.model.WordResponseItem
import kotlinx.android.synthetic.main.word_item.view.*
import java.util.*


class WordDefinitionAdapter(private val listener: OnItemClick) :
    RecyclerView.Adapter<WordDefinitionAdapter.WordViewHolder>() {

    var list: MutableList<WordResponseItem> = mutableListOf()

    class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val word = itemView.tvWord
        val phonetic = itemView.tvPhonetic
        val source = itemView.tvSource
        val card = itemView.cardView
    }

    fun updateList(myList: MutableList<WordResponseItem>) {
        list.clear()
        list.addAll(myList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WordViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
    )

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = list[position]

        val r = Random()
        val red: Int = r.nextInt(255 - 0 + 1) + 0
        val green: Int = r.nextInt(255 - 0 + 1) + 0
        val blue: Int = r.nextInt(255 - 0 + 1) + 0

        val draw = GradientDrawable()
        draw.shape = GradientDrawable.RECTANGLE
        draw.cornerRadius = 20f
        draw.setColor(Color.rgb(red, green, blue))
//        holder..setBackground(draw)

        holder.apply {

            word.apply {
                text = item.word
                setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            }
            phonetic.apply {
                text = item.phonetic
                setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            }

            source.apply {
                text = item.sourceUrls?.last()
                setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            }

            card.background = draw

            itemView.setOnClickListener {
                listener.onClick(item, position)
            }
        }


    }

    override fun getItemCount() = list.size
}

interface OnItemClick {
    fun onClick(item: WordResponseItem, position: Int)
}