package com.gardner.music.core

import org.scalatest._

class PitchTest extends FlatSpecLike with Matchers {
  "Pitch" should "be ordered correctly" in {
    Seq[Pitch](Pitch.A4, Pitch.A0, Pitch.A1).sorted should be(Seq[Pitch](Pitch.A0, Pitch.A1, Pitch.A4))
    Seq[Pitch](Pitch.B4, Pitch.A4, Pitch.F4).sorted should be(Seq[Pitch](Pitch.A4, Pitch.B4, Pitch.F4))
    Seq[Pitch](Pitch.B4, Pitch.A4, Pitch.A4_#).sorted should be(Seq[Pitch](Pitch.A4, Pitch.A4_#, Pitch.B4))
    Seq[Pitch](Pitch.C4, Pitch.C3_#, Pitch.D5).sorted should be(Seq[Pitch](Pitch.C3_#, Pitch.C4, Pitch.D5))
  }
}
