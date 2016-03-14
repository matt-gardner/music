package com.gardner.music.transcription

/**
 * Represents a note as found in an audio signal.
 *
 * TODO(matt): not sure why I have both windows and time here, as they are isomorphic.
 */
case class TranscribedNote(
  startWindow: Int,
  endWindow: Int,
  startTime: Double,
  endTime: Double,
  frequency: Double
)
