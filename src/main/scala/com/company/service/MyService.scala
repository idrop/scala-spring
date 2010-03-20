package com.company.service

import org.springframework.stereotype.Service
import com.company.model._

@Service
class MyService {

  private var ious: List[IOU] = Nil

  def addNewIOU(ower: User, owed: User, amount: Double) = {
    val iou = IOU(ower, owed, amount)
    ious synchronized {
      ious = iou :: (ious filterNot (_ == iou))
    }
    iou
  }

  def iousForUser(u: User) = {
    ious synchronized {
      ious filter (_.ower == u)
    }
  }

}