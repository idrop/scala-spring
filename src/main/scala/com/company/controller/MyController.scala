package com.company.controller

import com.company.service.MyService
import com.company.model._
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.http.HttpServletResponse

import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.stereotype.Controller
import org.json.{JSONObject, JSONArray}
import org.springframework.web.bind.annotation.{ResponseBody, RequestParam, RequestMapping, PathVariable}
import org.springframework.web.bind.WebDataBinder

@Controller
@Autowired
@RequestMapping(Array("/ious"))
class MyController(val myService: MyService) {

  /**
   * Invoke at http://localhost:9090/magic/alive 
   */
  @RequestMapping(method = Array(GET), value = Array("/alive"))
  def alive(res: HttpServletResponse) {

    res.setContentType("text/plain")
    res.getWriter.print("alive")

  }

  /**
   * Creates or replaces an existing IOU.
   */
  @RequestMapping(method = Array(POST))
  def post(
    @RequestParam("ower") ower: String,
    @RequestParam("owed") owed: String,
    @RequestParam("amount") amount: Double) {

    myService.addNewIOU(User(ower), User(owed), amount)

  }

  /**
   * Creates or replaces an existing IOU.
   * Returns json array of current ious
   * Invoked in index.html
   */
  @RequestMapping(method = Array(GET), value = Array("{ower}"))
  def get(@PathVariable ower :String) = {

    var ious = myService.iousForUser(User(ower))
    ious = ious.sortWith( (l,r) => l.amount > r.amount )
    ious2JSON(ious)

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