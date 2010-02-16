package com.company.service

import org.springframework.stereotype.Service
import com.company.model.{User, IOU}

@Service
class MyService {
  
  private var ious: List[IOU] = Nil

  def addNewIOU(ower: String, owed: String, amount: Double) = {
    val newIOU = IOU(User(ower), User(owed), amount)
    ious = newIOU :: (ious filterNot (newIOU == _))
    ious
  }

}