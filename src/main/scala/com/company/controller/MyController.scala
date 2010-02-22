package com.company.controller

import com.company.service.MyService
import com.company.model._
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.http.HttpServletResponse


import org.springframework.http.HttpStatus._
import org.springframework.stereotype.Controller
import org.json.{JSONObject, JSONArray}
import org.springframework.web.bind.annotation._
import RequestMethod.{GET,POST} // relative import
import org.springframework.web.bind.WebDataBinder

@Controller
@Autowired
@RequestMapping(Array("/ious"))
class MyController(val myService: MyService) {

  /**
   * Always useful to have an endpoint to ping
   * Invoke at http://localhost:9090/magic/ious/alive
   */
  @RequestMapping(method = Array(GET), value=Array("alive"))
  @ResponseBody
  def alive = "alive"

  /**
   * Creates or replaces an existing IOU.
   * Invoked via a POST to http://localhost:9090/magic/ious
   */
  @RequestMapping(method = Array(POST))
  @ResponseStatus(CREATED)
  def post(
    @RequestParam("ower") ower: String,
    @RequestParam("owed") owed: String,
    @RequestParam("amount") amount: Double) {

    myService.addNewIOU(User(ower), User(owed), amount)
  }

  /**
   * Creates or replaces an existing IOU.
   * Returns json array of current ious
   * Invoked via a GET to http://localhost:9090/magic/ious
   */
  @RequestMapping(method = Array(GET), value = Array("{ower}"))
  @ResponseBody
  def get(@PathVariable ower :String) = {
    val ious = myService.iousForUser(User(ower))
    ious2JSON(ious.sortWith( (l,r) => l.amount > r.amount ))
  }


  private def ious2JSON(ious :List[IOU]) = {

    val arr = new JSONArray
    ious foreach { iou =>
      val o = new JSONObject
      o put("ower",iou.ower.id)
      o put("owed",iou.owed.id)
      o put("amount",iou.amount)
      arr put o
    }

    arr.toString
  }

}