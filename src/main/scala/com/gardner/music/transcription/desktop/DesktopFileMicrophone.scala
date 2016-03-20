package com.gardner.music.transcription.desktop

import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.UnsupportedAudioFileException

import com.gardner.music.transcription.Microphone

/**
 * A Microphone implementation for use on desktop machines (as we use javax APIs, not Android APIs)
 * that reads from a file instead of a real microphone.
 */
class DesktopFileMicrophone(file: File) extends Microphone {
  val bufferSize = 1024 * 4
  lazy val stream = AudioSystem.getAudioInputStream(file);
  lazy val sampleRate = stream.getFormat().getSampleRate().toInt
  lazy val bitRate = stream.getFormat().getSampleSizeInBits()
  lazy val bytesPerFrame = bitRate / 8

    override def initialize = { }

    override def getSampleRate = sampleRate

    override def getBufferSize = bufferSize
    override def getBytesPerFrame = bytesPerFrame
    override def start = { }

    override def sample(buffer: Array[Byte]): Int = {
      val bytes = stream.read(buffer, 0, buffer.length)
      // These are unsigned bytes, but java is treating them as signed.  So we shift the
      // values.  TODOLATER: this is quite specific to some particular files I'm currently
      // working with.  If this gets used more broadly later, this bit of code should be
      // fixed.
      if (bitRate == 8) {
        for (i <- 0 until buffer.length) {
          if (buffer(i) > 0) {
            buffer(i) = (buffer(i) - 128).toByte
          } else {
            buffer(i) = (buffer(i) + 128).toByte
          }
        }
      }
      return bytes;
    }

    override def stop = { stream.close }
}
