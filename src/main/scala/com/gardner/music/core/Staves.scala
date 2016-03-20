package com.gardner.music.core

sealed trait Clef {
  def getPitchPosition(pitch: Pitch): Int
}

case object TrebleClef extends Clef {
  def getPitchPosition(pitch: Pitch) = 0
}

case object BaseClef extends Clef {
  def getPitchPosition(pitch: Pitch) = 0
}

// We'll keep this simple for now; no changing clefs mid-staff.
class Staff(val clef: Clef) {
}
