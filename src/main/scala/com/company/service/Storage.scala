package com.company.service

import com.company.model.{User, IOU}
import actors.Actor
import Actor._

object Storage extends Actor {

  private var ious: List[IOU] = Nil

  /**
   * danger danger!
   */
  def clear = ious = Nil

  override def act {
    loop {
      react {

        case PutIOU(iou) => ious = iou :: (ious filterNot (_ == iou))

        case GetIOUs(ower) => reply(ious filter (_.ower == ower))
        
      }
    }
  }
}

case class PutIOU(iou: IOU)
case class GetIOUs(ower: User)