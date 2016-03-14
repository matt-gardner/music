package com.gardner.music.core

import collection.mutable

sealed trait Duration {
  def beats: Double
}

object Duration {
  case object WHOLE extends Duration {val beats = 4.0}
  case object HALF extends Duration {val beats = 2.0}
  case object QUARTER extends Duration {val beats = 1.0}
  case object EIGHTH extends Duration {val beats = 0.5}
  case object SIXTEENTH extends Duration {val beats = 0.25}
  case object THIRTY_SECOND extends Duration {val beats = 0.125}
  case class DOTTED(duration: Duration) { def beats = duration.beats * .5 }
}

sealed trait Pitch extends Ordered[Pitch] {
  def name: String
  def octave: Int

  override def toString() = name + octave.toString

  def intervalAbove(interval: Interval): Pitch = {
    val semitone = Pitch.bySemitoneFromLowest.indexOf(this)
    val newSemitone = semitone + interval.semitones
    if (newSemitone >= Pitch.bySemitoneFromLowest.length) throw new RuntimeException("Note is too high!")
    Pitch.bySemitoneFromLowest(newSemitone)
  }

  def intervalBelow(interval: Interval): Pitch = {
    val semitone = Pitch.bySemitoneFromLowest.indexOf(this)
    val newSemitone = semitone - interval.semitones
    if (newSemitone < 0) throw new RuntimeException("Note is too low!")
    Pitch.bySemitoneFromLowest(newSemitone)
  }

  def compare(that: Pitch) = {
    val octaveCompare = octave.compare(that.octave)
    if (octaveCompare != 0) {
      octaveCompare
    } else {
      name.compare(that.name)
    }
  }
}

// Having now tried a few different ways of doing this, and seen how this turned out, I'm not
// certain I'm happy with doing it this way.  I thought I liked the idea of an enumeration for
// this, but maybe just a class would be better...
object Pitch {
  val bySemitoneFromLowest = new mutable.ArrayBuffer[Pitch]
  case object A0 extends Pitch {val name = "A"; val octave = 0}; bySemitoneFromLowest += A0
  case object A0_# extends Pitch {val name = "A#"; val octave = 0}; bySemitoneFromLowest += A0_#
  case object B0 extends Pitch {val name = "B"; val octave = 0}; bySemitoneFromLowest += B0
  case object C0 extends Pitch {val name = "C"; val octave = 0}; bySemitoneFromLowest += C0
  case object C0_# extends Pitch {val name = "C#"; val octave = 0}; bySemitoneFromLowest += C0_#
  case object D0 extends Pitch {val name = "D"; val octave = 0}; bySemitoneFromLowest += D0
  case object D0_# extends Pitch {val name = "D#"; val octave = 0}; bySemitoneFromLowest += D0_#
  case object E0 extends Pitch {val name = "E"; val octave = 0}; bySemitoneFromLowest += E0
  case object F0 extends Pitch {val name = "F"; val octave = 0}; bySemitoneFromLowest += F0
  case object F0_# extends Pitch {val name = "F#"; val octave = 0}; bySemitoneFromLowest += F0_#
  case object G0 extends Pitch {val name = "G"; val octave = 0}; bySemitoneFromLowest += G0
  case object G0_# extends Pitch {val name = "G#"; val octave = 0}; bySemitoneFromLowest += G0_#
  case object A1 extends Pitch {val name = "A"; val octave = 1}; bySemitoneFromLowest += A1
  case object A1_# extends Pitch {val name = "A#"; val octave = 1}; bySemitoneFromLowest += A1_#
  case object B1 extends Pitch {val name = "B"; val octave = 1}; bySemitoneFromLowest += B1
  case object C1 extends Pitch {val name = "C"; val octave = 1}; bySemitoneFromLowest += C1
  case object C1_# extends Pitch {val name = "C#"; val octave = 1}; bySemitoneFromLowest += C1_#
  case object D1 extends Pitch {val name = "D"; val octave = 1}; bySemitoneFromLowest += D1
  case object D1_# extends Pitch {val name = "D#"; val octave = 1}; bySemitoneFromLowest += D1_#
  case object E1 extends Pitch {val name = "E"; val octave = 1}; bySemitoneFromLowest += E1
  case object F1 extends Pitch {val name = "F"; val octave = 1}; bySemitoneFromLowest += F1
  case object F1_# extends Pitch {val name = "F#"; val octave = 1}; bySemitoneFromLowest += F1_#
  case object G1 extends Pitch {val name = "G"; val octave = 1}; bySemitoneFromLowest += G1
  case object G1_# extends Pitch {val name = "G#"; val octave = 1}; bySemitoneFromLowest += G1_#
  case object A2 extends Pitch {val name = "A"; val octave = 2}; bySemitoneFromLowest += A2
  case object A2_# extends Pitch {val name = "A#"; val octave = 2}; bySemitoneFromLowest += A2_#
  case object B2 extends Pitch {val name = "B"; val octave = 2}; bySemitoneFromLowest += B2
  case object C2 extends Pitch {val name = "C"; val octave = 2}; bySemitoneFromLowest += C2
  case object C2_# extends Pitch {val name = "C#"; val octave = 2}; bySemitoneFromLowest += C2_#
  case object D2 extends Pitch {val name = "D"; val octave = 2}; bySemitoneFromLowest += D2
  case object D2_# extends Pitch {val name = "D#"; val octave = 2}; bySemitoneFromLowest += D2_#
  case object E2 extends Pitch {val name = "E"; val octave = 2}; bySemitoneFromLowest += E2
  case object F2 extends Pitch {val name = "F"; val octave = 2}; bySemitoneFromLowest += F2
  case object F2_# extends Pitch {val name = "F#"; val octave = 2}; bySemitoneFromLowest += F2_#
  case object G2 extends Pitch {val name = "G"; val octave = 2}; bySemitoneFromLowest += G2
  case object G2_# extends Pitch {val name = "G#"; val octave = 2}; bySemitoneFromLowest += G2_#
  case object A3 extends Pitch {val name = "A"; val octave = 3}; bySemitoneFromLowest += A3
  case object A3_# extends Pitch {val name = "A#"; val octave = 3}; bySemitoneFromLowest += A3_#
  case object B3 extends Pitch {val name = "B"; val octave = 3}; bySemitoneFromLowest += B3
  case object C3 extends Pitch {val name = "C"; val octave = 3}; bySemitoneFromLowest += C3
  case object C3_# extends Pitch {val name = "C#"; val octave = 3}; bySemitoneFromLowest += C3_#
  case object D3 extends Pitch {val name = "D"; val octave = 3}; bySemitoneFromLowest += D3
  case object D3_# extends Pitch {val name = "D#"; val octave = 3}; bySemitoneFromLowest += D3_#
  case object E3 extends Pitch {val name = "E"; val octave = 3}; bySemitoneFromLowest += E3
  case object F3 extends Pitch {val name = "F"; val octave = 3}; bySemitoneFromLowest += F3
  case object F3_# extends Pitch {val name = "F#"; val octave = 3}; bySemitoneFromLowest += F3_#
  case object G3 extends Pitch {val name = "G"; val octave = 3}; bySemitoneFromLowest += G3
  case object G3_# extends Pitch {val name = "G#"; val octave = 3}; bySemitoneFromLowest += G3_#
  case object A4 extends Pitch {val name = "A"; val octave = 4}; bySemitoneFromLowest += A4
  case object A4_# extends Pitch {val name = "A#"; val octave = 4}; bySemitoneFromLowest += A4_#
  case object B4 extends Pitch {val name = "B"; val octave = 4}; bySemitoneFromLowest += B4
  case object C4 extends Pitch {val name = "C"; val octave = 4}; bySemitoneFromLowest += C4
  case object C4_# extends Pitch {val name = "C#"; val octave = 4}; bySemitoneFromLowest += C4_#
  case object D4 extends Pitch {val name = "D"; val octave = 4}; bySemitoneFromLowest += D4
  case object D4_# extends Pitch {val name = "D#"; val octave = 4}; bySemitoneFromLowest += D4_#
  case object E4 extends Pitch {val name = "E"; val octave = 4}; bySemitoneFromLowest += E4
  case object F4 extends Pitch {val name = "F"; val octave = 4}; bySemitoneFromLowest += F4
  case object F4_# extends Pitch {val name = "F#"; val octave = 4}; bySemitoneFromLowest += F4_#
  case object G4 extends Pitch {val name = "G"; val octave = 4}; bySemitoneFromLowest += G4
  case object G4_# extends Pitch {val name = "G#"; val octave = 4}; bySemitoneFromLowest += G4_#
  case object A5 extends Pitch {val name = "A"; val octave = 5}; bySemitoneFromLowest += A5
  case object A5_# extends Pitch {val name = "A#"; val octave = 5}; bySemitoneFromLowest += A5_#
  case object B5 extends Pitch {val name = "B"; val octave = 5}; bySemitoneFromLowest += B5
  case object C5 extends Pitch {val name = "C"; val octave = 5}; bySemitoneFromLowest += C5
  case object C5_# extends Pitch {val name = "C#"; val octave = 5}; bySemitoneFromLowest += C5_#
  case object D5 extends Pitch {val name = "D"; val octave = 5}; bySemitoneFromLowest += D5
  case object D5_# extends Pitch {val name = "D#"; val octave = 5}; bySemitoneFromLowest += D5_#
  case object E5 extends Pitch {val name = "E"; val octave = 5}; bySemitoneFromLowest += E5
  case object F5 extends Pitch {val name = "F"; val octave = 5}; bySemitoneFromLowest += F5
  case object F5_# extends Pitch {val name = "F#"; val octave = 5}; bySemitoneFromLowest += F5_#
  case object G5 extends Pitch {val name = "G"; val octave = 5}; bySemitoneFromLowest += G5
  case object G5_# extends Pitch {val name = "G#"; val octave = 5}; bySemitoneFromLowest += G5_#
  case object A6 extends Pitch {val name = "A"; val octave = 6}; bySemitoneFromLowest += A6
  case object A6_# extends Pitch {val name = "A#"; val octave = 6}; bySemitoneFromLowest += A6_#
  case object B6 extends Pitch {val name = "B"; val octave = 6}; bySemitoneFromLowest += B6
  case object C6 extends Pitch {val name = "C"; val octave = 6}; bySemitoneFromLowest += C6
  case object C6_# extends Pitch {val name = "C#"; val octave = 6}; bySemitoneFromLowest += C6_#
  case object D6 extends Pitch {val name = "D"; val octave = 6}; bySemitoneFromLowest += D6
  case object D6_# extends Pitch {val name = "D#"; val octave = 6}; bySemitoneFromLowest += D6_#
  case object E6 extends Pitch {val name = "E"; val octave = 6}; bySemitoneFromLowest += E6
  case object F6 extends Pitch {val name = "F"; val octave = 6}; bySemitoneFromLowest += F6
  case object F6_# extends Pitch {val name = "F#"; val octave = 6}; bySemitoneFromLowest += F6_#
  case object G6 extends Pitch {val name = "G"; val octave = 6}; bySemitoneFromLowest += G6
  case object G6_# extends Pitch {val name = "G#"; val octave = 6}; bySemitoneFromLowest += G6_#
  case object A7 extends Pitch {val name = "A"; val octave = 7}; bySemitoneFromLowest += A7
  case object A7_# extends Pitch {val name = "A#"; val octave = 7}; bySemitoneFromLowest += A7_#
  case object B7 extends Pitch {val name = "B"; val octave = 7}; bySemitoneFromLowest += B7
  case object C7 extends Pitch {val name = "C"; val octave = 7}; bySemitoneFromLowest += C7
  case object C7_# extends Pitch {val name = "C#"; val octave = 7}; bySemitoneFromLowest += C7_#
  case object D7 extends Pitch {val name = "D"; val octave = 7}; bySemitoneFromLowest += D7
  case object D7_# extends Pitch {val name = "D#"; val octave = 7}; bySemitoneFromLowest += D7_#
  case object E7 extends Pitch {val name = "E"; val octave = 7}; bySemitoneFromLowest += E7
  case object F7 extends Pitch {val name = "F"; val octave = 7}; bySemitoneFromLowest += F7
  case object F7_# extends Pitch {val name = "F#"; val octave = 7}; bySemitoneFromLowest += F7_#
  case object G7 extends Pitch {val name = "G"; val octave = 7}; bySemitoneFromLowest += G7
  case object G7_# extends Pitch {val name = "G#"; val octave = 7}; bySemitoneFromLowest += G7_#
  val lowest = A0
  val highest = G7_#

  /**
   * Name should be encoded here as A4, C#2, etc., with the last character being the octave number.
   */
  def fromName(nameWithOctave: String): Option[Pitch] = {
    val octave = nameWithOctave.last.asDigit
    val name = nameWithOctave.dropRight(1)
    bySemitoneFromLowest.find(p => p.name == name && p.octave == octave)
  }
}

sealed trait Interval {
  def semitones: Int
}
object Interval {
  case object UNISON extends Interval {val semitones = 0}
  case object HALF_STEP extends Interval {val semitones = 1}
  case object MINOR_SECOND extends Interval {val semitones = 1}
  case object WHOLE_STEP extends Interval {val semitones = 2}
  case object MAJOR_SECOND extends Interval {val semitones = 2}
  case object MINOR_THIRD extends Interval {val semitones = 3}
  case object MAJOR_THIRD extends Interval {val semitones = 4}
  case object FOURTH extends Interval {val semitones = 5}
  case object TRITONE extends Interval {val semitones = 6}
  case object FIFTH extends Interval {val semitones = 7}
  case object MINOR_SIXTH extends Interval {val semitones = 8}
  case object MAJOR_SIXTH extends Interval {val semitones = 9}
  case object MINOR_SEVENTH extends Interval {val semitones = 10}
  case object MAJOR_SEVENTH extends Interval {val semitones = 11}
  case object OCTAVE extends Interval {val semitones = 12}
}

case class Note(pitch: Pitch, duration: Duration)

case class Rest(duration: Duration)

case class Chord(pitches: Set[Pitch], duration: Duration) {
  def asNotes: Set[Note] = pitches.map(p => Note(p, duration))
}

case class Voice(chords: Seq[Either[Chord, Rest]])
