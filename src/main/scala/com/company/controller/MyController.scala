package com.company.controller

import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.http.HttpServletResponse
import com.company.service.MyService
import org.springframework.web.bind.annotation.{RequestParam, RequestMapping}
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.stereotype.Controller
import org.json.{JSONObject, JSONArray}

@Controller
class MyController {
  
  private val log = Logger.getLogger(classOf[MyController])

  @Autowired
  var myService: MyService = _

  /**
   * Invoke at http://localhost:9090/magic/alive 
   */
  @RequestMapping(method = Array(GET), value = Array("/alive"))
  def alive(res: HttpServletResponse) {

    log.info("alive invoked")
    res.setContentType("text/plain")
    res.getWriter.print("alive")

  }

  /**
   * Creates or replaces an existing IOU.
   * Returns json array of current ious
   * Invoked in index.html
   */
  @RequestMapping(method = Array(GET), value = Array("/newIOU"))
  def newIOU(
          @RequestParam("ower") ower: String,
          @RequestParam("owed") owed: String,
          @RequestParam("amount") amount: Double,
          res: HttpServletResponse) {

    res.setContentType("application/json")
    log.info("newIOU invoked")

    myService.addNewIOU(ower, owed, amount)

    val ious = myService.allIOUs

    val arr = new JSONArray

    ious foreach { iou =>
      val o = new JSONObject
      o put("ower",iou.ower.id)
      o put("owed",iou.owed.id)
      o put("amount",iou.amount)
      arr put o
    }

    res.getWriter.print(arr.toString)



  }


}