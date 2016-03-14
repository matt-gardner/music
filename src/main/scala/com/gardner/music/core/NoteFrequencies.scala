package com.gardner.music.core

import collection.mutable

/**
 * Defines a mapping from notes to frequencies in hertz.  The baseFrequency is what frequency is given
 * to A4, where 440 is the standard concert pitch in the US and UK.  In the current implementation,
 * this uses equal temperament.  If you want some other kind of temperament, subclass this.
 */
class NoteFrequencies(baseFrequency: Double = 440) {

  val frequencies: Map[Pitch, Double] = {
    val freqMap = new mutable.HashMap[Pitch, Double]
    val a4 = Pitch.A4
    freqMap(a4) = baseFrequency
    val multiplier = 1.0594630943592953  // this is 2^(1/12)
    var currentPitch: Pitch = a4
    var currentFrequency = baseFrequency
    while (currentPitch != Pitch.lowest) {
      currentPitch = currentPitch.intervalBelow(Interval.HALF_STEP)
      currentFrequency /= multiplier
      freqMap(currentPitch) = currentFrequency
    }
    currentPitch = a4
    currentFrequency = baseFrequency
    while (currentPitch != Pitch.highest) {
      currentPitch = currentPitch.intervalAbove(Interval.HALF_STEP)
      currentFrequency *= multiplier
      freqMap(currentPitch) = currentFrequency
    }
    freqMap.toMap
  }

  val frequencyList = frequencies.toList.sorted.toArray

  def getMinFrequency() = frequencies(Pitch.lowest)
  def getMaxFrequency() = frequencies(Pitch.highest)
  def getPitchFrequency(pitch: Pitch) = frequencies(pitch)

  def findClosestPitch(frequency: Double): Pitch = {
    if (frequency < getMinFrequency) return Pitch.lowest
    if (frequency > getMaxFrequency) return Pitch.highest
    var min = 0
    var max = frequencyList.length
    while (max > min) {
      val i = (max + min) / 2
      if (frequency > frequencyList(i)._2) {
        if (frequency - frequencyList(i)._2 < frequencyList(i+1)._2 - frequency) {
          return frequencyList(i)._1
        } else {
          min = i
        }
      } else if (frequency < frequencyList(i)._2) {
        if (frequencyList(i)._2 - frequency < frequency - frequencyList(i-1)._2) {
          return frequencyList(i)._1
        } else {
          max = i
        }
      } else {
        return frequencyList(i)._1
      }
    }
    throw new RuntimeException("Not sure how we got here...")
  }
}
