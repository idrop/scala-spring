package com.company.controller

import com.company.service.{Storage, MyService}
import org.junit.{Before, Test}
import com.company.model.User
import org.junit.Assert._
class MyControllerTest {

  val controller = new MyController(new MyService)
  
  val phil = User("phil")
  val warren = User("warren")
  val julie = User("julie")
  val shaun = User("shaun")

  @Before
  def before = Storage.clear

  @Test
  def postAndGetIOUs = {

    controller.post(phil, warren, 10d)

    val json = controller.get(phil)

    assertEquals("""[{"amount":10,"ower":"phil","owed":"warren"}]""", json)
  }

  @Test
  def getOrderedIOUs = {

    controller.post(phil, warren, 10d)
    controller.post(phil, julie, 11d)
    controller.post(phil, shaun, 9d)
    
    val json1 = controller.get(phil)

    assertEquals("""[{"amount":11,"ower":"phil","owed":"julie"},{"amount":10,"ower":"phil","owed":"warren"},{"amount":9,"ower":"phil","owed":"shaun"}]""", json1)
  }

}