package com.company.controller

import com.company.model.User
import java.beans.PropertyEditorSupport
import UserEditor._

/**
 * transform a String user id to a sanitized User object
 **/
class UserEditor extends PropertyEditorSupport {

  override def setAsText(text: String) = {
    require(text != null)
    require(!text.isEmpty)
    // sanitize html input
    val cleaned = mapping.foldLeft(text)((text, kv) => text.replaceAll(kv._1, kv._2))
    setValue(User(cleaned))
  }

}

/**
 * companion singelton
 */
object UserEditor {

  val mapping = Map(">" -> "&gt;", "<" -> "&lt;")
}