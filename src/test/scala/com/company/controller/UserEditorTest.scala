package com.company.controller

import _root_.com.company.model.User
import org.junit.Assert._
import org.junit.Test

@Test
class UserEditorTest {

  val userEditor = new UserEditor

  @Test
  def transformGoodStringToUser = {
    userEditor.setAsText("phil")
    val user = userEditor.getValue.asInstanceOf[User]
    assertEquals("phil",user.id)
  }

  @Test
  def transformEvilStringToUser = {
    userEditor.setAsText("<phil>")
    val user = userEditor.getValue.asInstanceOf[User]
    assertEquals("&lt;phil&gt;",user.id)
  }

  @Test(expected=classOf[IllegalArgumentException])
  def nullStringForUser = userEditor.setAsText(null)

  @Test(expected=classOf[IllegalArgumentException])
  def emptyStringForUser = userEditor.setAsText("")

}