package com.company.controller

import com.company.service.MyService
import com.company.model._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus._
import org.springframework.stereotype.Controller
import org.json.{JSONObject, JSONArray}
import org.springframework.web.bind.annotation._
import RequestMethod.{GET, POST} // relative import

@Controller
@Autowired
@RequestMapping(Array("/ious"))
class MyController(val myService: MyService) {

  /**
   * Always useful to have an endpoint to ping
   * Invoke at http://localhost:9090/magic/ious/alive
   */
  @RequestMapping(method = Array(GET), value = Array("alive"))
  @ResponseBody
  def alive = "alive at %s".format(new java.util.Date)

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
   * Returns ordered json array of ious for ower
   * Invoked via a GET to http://localhost:9090/magic/ious
   */
  @RequestMapping(method = Array(GET), value = Array("{ower}"))
  @ResponseBody
  def get(@PathVariable ower: String) = {
    val ious = myService.iousForUser(User(ower))
    ious2JSON(ious.sortWith((l, r) => l.amount > r.amount))
  }


  private def ious2JSON(ious: List[IOU]) = {

    val arr = new JSONArray
    ious foreach {
      iou =>
        val o = new JSONObject
        o put ("ower", iou.ower.id)
        o put ("owed", iou.owed.id)
        o put ("amount", iou.amount)
        arr put o
    }

    arr.toString
  }

}