package com.company.service

import org.springframework.stereotype.Service
import com.company.model._

@Service
class MyService {

  Storage start

  def addNewIOU(ower: User, owed: User, amount: Double) = {
    val iou = IOU(ower, owed, amount)
    Storage ! PutIOU(iou) // async
    iou
  }

  def iousForUser(u: User) = {
    val future = Storage !! GetIOUs(u)
    future().asInstanceOf[List[IOU]]
  }

}

