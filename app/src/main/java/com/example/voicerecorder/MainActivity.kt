package com.example.voicerecorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.playback.AndroidAudioPlayer
import com.example.voicerecorder.recorder.AndroidAudioRecorder
import com.example.voicerecorder.ui.theme.VoiceRecorderTheme
import java.io.File
import android.Manifest

class MainActivity : ComponentActivity() {

    private val recorder by lazy{
        AndroidAudioRecorder(this)
    }
    private val player by lazy{
        AndroidAudioPlayer(this)
    }
    private var audioFile: File?  = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )
        setContent {
            VoiceRecorderTheme {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){

                    Card(shape = CircleShape, modifier = Modifier
                        .size(100.dp)
                        .clickable { File(cacheDir, "audio.mp3").also{
                            recorder.start(it)
                            audioFile = it
                        } },
                        colors = CardDefaults.cardColors(
                            Color.Black)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Start", fontSize = 20.sp, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Card(shape = CircleShape, modifier = Modifier.size(100.dp).clickable { recorder.stop() }, colors = CardDefaults.cardColors(
                        Color.Black)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Stop", fontSize = 20.sp, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Card(shape = CircleShape, modifier = Modifier.size(100.dp).clickable {  player.playFile(audioFile?: return@clickable)}, colors = CardDefaults.cardColors(
                        Color.Black)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Play", fontSize = 20.sp, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Card(shape = CircleShape, modifier = Modifier.size(100.dp).clickable { player.stop() }, colors = CardDefaults.cardColors(
                        Color.Black)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Pause", fontSize = 20.sp, color = Color.White)
                        }
                    }

                }
            }
        }
    }
}

