package com.example.scalerfollowalong

import android.app.Activity
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdaptor(val context: Activity, val dataList: List<Data>): RecyclerView.Adapter<MyAdaptor.MyViewHolder>() {
    private var mediaPlayer: MediaPlayer? = null
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val title: TextView
        val play: ImageButton
        val pause: ImageButton

        init {

            image = itemView.findViewById(R.id.musicImage)
            title = itemView.findViewById(R.id.musicTitle)
            play = itemView.findViewById(R.id.buttonPlay)
            pause = itemView.findViewById(R.id.buttonPause)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create the view in case the layout manager fails
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // populate the data into the view
        val currentData = dataList[position]
        //val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())
        holder.title.text = currentData.title
        Picasso.get().load(currentData.album.cover).into(holder.image)

        holder.play.setOnClickListener {
            playAudio(currentData.preview)
        }

        holder.pause.setOnClickListener {
            pauseAudio()
        }
    }
    private fun playAudio(audioUrl: String) {
        try {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(context, audioUrl.toUri())
            } else {
                mediaPlayer?.reset()
                mediaPlayer?.setDataSource(context, audioUrl.toUri())
                mediaPlayer?.prepare()
            }
            mediaPlayer?.start()
        } catch (e: Exception) {
            Log.e("MyAdaptor", "Error playing audio: ${e.message}")

        }
    }
    private fun pauseAudio() {
        mediaPlayer?.pause()
    }


    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mediaPlayer?.release()
        mediaPlayer = null

    }
}