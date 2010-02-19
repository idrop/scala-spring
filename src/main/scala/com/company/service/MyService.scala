package com.company.service

import org.springframework.stereotype.Service
import com.company.model._

@Service
class MyService {
  
  private var ious: List[IOU] = Nil

  def addNewIOU(ower: User, owed: User, amount: Double) = {
    val newIOU = IOU(ower, owed, amount)
    ious = newIOU :: (ious filterNot (newIOU == _))
    newIOU
  }

  def iousForUser(ower :User) = ious.filter(_.ower == ower)

}