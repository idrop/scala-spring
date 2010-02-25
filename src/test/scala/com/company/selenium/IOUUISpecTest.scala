package com.company.selenium

import com.company.util.BrowserSpeak
import BrowserSpeak._
import org.specs.Specification

/**
 * taken from post by Pavol Vaskovic on newsgroup scala-user@listes.epfl.ch
 */
object IOUUISpecTest extends Specification {

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