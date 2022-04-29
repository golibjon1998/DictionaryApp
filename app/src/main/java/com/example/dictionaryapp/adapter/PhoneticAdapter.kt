package com.example.dictionaryapp.adapter

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.data.model.Phonetic
import kotlinx.android.synthetic.main.phonetic_item.view.*

class PhoneticAdapter(
    private val phonetics: MutableList<Phonetic>,
    private val listener: OnPhoneticListener
) :
    RecyclerView.Adapter<PhoneticAdapter.PhoneticViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null

    class PhoneticViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val phoneticText = itemView.tvText
        val phoneticUrl = itemView.tvUrl
        val audioBtn = itemView.play
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhoneticViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.phonetic_item, parent, false)
        )

    override fun onBindViewHolder(holder: PhoneticViewHolder, position: Int) {
        val item = phonetics[position]

        holder.apply {
            phoneticText.text = item.text
            phoneticUrl.text = item.sourceUrl

            audioBtn.setOnClickListener {

                val audioUrl = item.audio
                if (!audioUrl.isNullOrEmpty()) {
                    if (!item.isPlaying) {
                        playAudio(audioUrl, audioBtn, item)
                        item.isPlaying = true
                        audioBtn.setImageResource(R.drawable.ic_pause)
                    } else {
                        // If music is playing this line will stop it
                        mediaPlayer?.stop()
                        mediaPlayer?.reset()
                        mediaPlayer?.release()

                        //Toast is used to show that music has paused

                        item.isPlaying = false
                        audioBtn.setImageResource(R.drawable.ic_play)

                    }
                } else {
                    Toast.makeText(itemView.context, "No source audio", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            phoneticUrl.setOnClickListener {
                listener.onPathClick(item.sourceUrl)
            }


        }


    }

    private fun playAudio(audioUrl: String, audioBtn: ImageView, item: Phonetic) {
        mediaPlayer = MediaPlayer()

        mediaPlayer?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )

        try {
            // below line is use to set our url to our media player.
            mediaPlayer?.setDataSource(audioUrl)
            // below line is use to prepare
            // and start our media player.
            mediaPlayer?.prepareAsync()
            mediaPlayer?.setOnPreparedListener { mp ->
                // This line is used to play the music
                mp.start()
            }
            mediaPlayer?.setOnCompletionListener {
                it.stop()
                item.isPlaying = false
                audioBtn.setImageResource(R.drawable.ic_play)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun getItemCount() = phonetics.size

}

interface OnPhoneticListener {
    fun onPathClick(item: String?)
}

