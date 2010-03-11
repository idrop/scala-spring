package com.company.selenium

import _root_.org.specs.SpecificationWithJUnit
import com.company.util.BrowserSpeak
import BrowserSpeak._
/**
 * taken from post by Pavol Vaskovic on newsgroup scala-user@listes.epfl.ch
 */
object IOUUISpecTest extends SpecificationWithJUnit {

  def beAbleTo = addToSusVerb("beAbleTo")
  
  val iouUrl = "localhost:9090"

  "IOU App User" should beAbleTo {

    doBefore {startBrowser}
    doAfter {closeBrowser}

    "see a new iou added" in {
      go to iouUrl
      page.title must_== "scala-spring webapp"
      to field "ower" enter "phil"
      to field "owed" enter "warren"
      to field "amount" enter "12"
      click button "button"
      page textAt ("//div[@id='ious']") must_== "phil owes warren the sum of $12"
    }

  }
}