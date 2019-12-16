package com.jjonami.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjonami.study.db.Song
import com.jjonami.study.db.SongDB
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var songDB: SongDB? = null
    private var songList = listOf<Song>()
    lateinit var adapter: SongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitle.text = "Let's study Room!"

        songDB = SongDB.getInstance(this)
        getSongList()

        btnAdd.setOnClickListener{
            addSong()
        }
    }

    private fun getSongList(){
        //main thread에서 Room DB에 접근하려고 하면 에러 발생 > IllegalStateException
        //Data를 읽고 쓸 때는 Thread 사용
        var runnable = Runnable {
            songList = songDB?.songDao()?.selectAllList()!!
            adapter = SongAdapter(this, songList)
            adapter.notifyDataSetChanged()

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)

        }
        val thread = Thread(runnable)
        thread.start()
        setSongList()
    }

    private fun setSongList(){
        var adapter = SongAdapter(this, songList)
        adapter.notifyDataSetChanged()
        recyclerView.adapter = adapter
    }

    private fun addSong(){
        val addRunnable = Runnable {
            var newSong = Song()
            newSong.title = inputTitle.text.toString()
            newSong.singer = inputSinger.text.toString()
            newSong.album = inputAlbum.text.toString()

            val cal = Calendar.getInstance()
            cal.time = Date()
            val df = SimpleDateFormat("yyyy-MM-dd")
            newSong.releaseDate =  df.format(cal.time).toString()

            songDB?.songDao()?.insert(newSong)
        }

        var addThread = Thread(addRunnable)
        addThread.start()
    }
}
