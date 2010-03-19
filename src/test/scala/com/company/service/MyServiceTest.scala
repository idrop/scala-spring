package com.company.service

import org.junit.Assert._
import org.junit._
import com.company.model.{User}

@Test
class MyServiceTest {
  
  val myService = new MyService

  val aaa = User("aaa")
  val bbb = User("bbb")
  val ccc = User("ccc")
  val ddd = User("ddd")

  @Test
  def addNewIOU {
    val iou = myService.addNewIOU(aaa, bbb, 10d)
    assertEquals(aaa, iou.ower)
    assertEquals(bbb, iou.owed)
    assertEquals(10d, iou.amount, 0)
  }

  @Test
  def allIOUsForAUser {
    myService.addNewIOU(aaa, bbb, 10d)
    myService.addNewIOU(aaa, ccc, 10d)
    val ious = myService.iousForUser(aaa)
    assertEquals(2, ious.length)
  }

  @Test
  def iouReplaced {
    myService.addNewIOU(aaa, bbb, 10d)
    myService.addNewIOU(aaa, bbb, 10d)
    val ious = myService.iousForUser(aaa)
    assertEquals(1, ious.length)
  }

  @Test
  def allIOUsForAUserAndExcludeForAnother {
    myService.addNewIOU(aaa, bbb, 10d)
    myService.addNewIOU(aaa, ccc, 10d)
    myService.addNewIOU(bbb, ccc, 9d)
    val ious = myService.iousForUser(aaa)
    assertEquals(2, ious.length)
  }

  @Test
  def noIOUSForAnotherUser {
    myService.addNewIOU(aaa, bbb, 10d)
    val ious = myService.iousForUser(ccc)
    assertEquals(0, ious.length)
  }

  @Test
  def noIOUSExist {
    val ious = myService.iousForUser(ccc)
    assertEquals(0, ious.length)
  }

}