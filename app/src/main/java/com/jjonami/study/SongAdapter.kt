package com.jjonami.study

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jjonami.study.db.Song

class SongAdapter(val context: Context, val songs: List<Song>) : RecyclerView.Adapter<SongAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(songs[position])
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        val tvTitle = itemView?.findViewById<TextView>(R.id.tvTitle)
        val tvSinger = itemView?.findViewById<TextView>(R.id.tvSinger)
        val tvAlbum = itemView?.findViewById<TextView>(R.id.tvAlbum)
        val tvDate = itemView?.findViewById<TextView>(R.id.tvDate)

        fun bind(song: Song){
            tvTitle?.text = song.title
            tvSinger?.text = song.singer
            tvAlbum?.text = song.album
            tvDate?.text = song.releaseDate
        }

    }
}