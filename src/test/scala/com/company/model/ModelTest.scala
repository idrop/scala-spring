package com.company.model

import org.junit.Assert._
import org.junit._

@Test
class ModelTest {

  val phil = User("phil")
  val warren = User("warren")

  @Test(expected=classOf[IllegalArgumentException])
  def negativeAmountDisallowed {
    IOU(phil, warren, -1)
  }

  @Test(expected=classOf[IllegalArgumentException])
  def owedCannotBeOwer {
    IOU(phil, phil, 1)
  }

  @Test
  def userEquality {
    assertEquals(phil,User("phil"))
  }

  @Test
  def userNonEquality {
    assertFalse(phil == warren)
  }

  @Test
  def iouEquality {
    val iou1: IOU = IOU(phil, warren, 10)
    val iou2: IOU = IOU(phil, warren, 100000000)
    assertEquals(iou1, iou2)
  }

  @Test
  def iouNonEquality {
    val iou1: IOU = IOU(warren, phil, 10)
    val iou2: IOU = IOU(phil, warren, 10)
    assertFalse(iou1 == iou2)
  }

  @Test
  def iouToString {
    assertEquals("phil owes warren 10.0", IOU(phil, warren, 10).toString)
  }

}