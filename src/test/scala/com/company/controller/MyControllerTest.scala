package com.company.controller

import org.junit.Assert._
import org.junit.Test
import com.company.service.MyService

class MyControllerTest {

  val controller = new MyController(new MyService)

  @Test
  def postAndGetAnIOU = {
    controller.post("phil","warren",10d)
    val json = controller.get("phil")
    assertEquals("""[{"amount":10,"ower":"phil","owed":"warren"}]""",json)
  }

  @Test
  def getOrderedIOUs = {
    controller.post("aaa","bbb", 10d)
    controller.post("aaa","ccc", 11d)
    controller.post("aaa","ddd", 9d)
    val json1 = controller.get("aaa")
    assertEquals("""[{"amount":11,"ower":"aaa","owed":"ccc"},{"amount":10,"ower":"aaa","owed":"bbb"},{"amount":9,"ower":"aaa","owed":"ddd"}]""",json1)
  }

}