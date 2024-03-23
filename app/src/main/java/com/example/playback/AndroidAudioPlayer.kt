package com.example.playback

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import com.example.voicerecorder.recorder.AudioRecorder
import java.io.File

class AndroidAudioPlayer(private val context: Context): AudioPlayer {

    private var player: MediaPlayer? = null
    override fun playFile(file: File) {
        MediaPlayer.create(context, file.toUri()).apply{
            player = this
            start()
        }
    }

    override fun stop() {
        TODO("Not yet implemented")
    }
}