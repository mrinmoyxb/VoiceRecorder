package com.example.voicerecorder.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}