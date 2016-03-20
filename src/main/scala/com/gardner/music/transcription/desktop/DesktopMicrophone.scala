package com.gardner.music.transcription.desktop

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

import com.gardner.music.transcription.Microphone;

class DesktopMicrophone extends Microphone {
  val sampleRate = 44100
  val bitRate = 8
  val bytesPerFrame = bitRate / 8
  val bufferSize = 1024 * 4 * bytesPerFrame * 16
  val format = new AudioFormat(sampleRate, bitRate, 1, true, true)

  var line: TargetDataLine = null
  var out: ByteArrayOutputStream = null
  var saveToFile = false

  override def initialize {
    val mixerInfos = AudioSystem.getMixerInfo()
    var i = 0
    for (info <- mixerInfos) {
      println(info)
      val m = AudioSystem.getMixer(info)
      if (m.getTargetLineInfo().length != 0) {
        val lineInfo = m.getTargetLineInfo()(0)
        println(lineInfo)
        line = m.getLine(lineInfo).asInstanceOf[TargetDataLine]
        //if (i == 1) break
        i += 1
      }
    }
  }

  def setSaveToFile(saveToFile: Boolean) {
    if (!saveToFile && out != null) {
      out.close()
      out = null
    }
    if (saveToFile) {
      out = new ByteArrayOutputStream()
    }
    this.saveToFile = saveToFile
  }

  override def getSampleRate = sampleRate
  override def getBufferSize = bufferSize
  override def getBytesPerFrame = bytesPerFrame

  override def start {
    line.open(format, bufferSize)
    line.start()
  }

  override def sample(buffer: Array[Byte]) = {
    val bytes = line.read(buffer, 0, buffer.length)
    if (saveToFile && bytes != -1) {
      out.write(buffer, 0, bytes)
    }
    bytes
  }

  override def stop = line.close()

  def saveAudio {
    out.close()
    val stream = new AudioInputStream(
      new ByteArrayInputStream(out.toByteArray()),
      format,
      out.toByteArray().length / format.getFrameSize())
    val outfile = new File("audio_out.wav")
    AudioSystem.write(stream, AudioFileFormat.Type.WAVE, outfile)
    out = new ByteArrayOutputStream()
  }
}
