package com.company.model

case class User(id: String) {
  override def toString = id
}

case class IOU(ower: User, owed: User, amount: Double) {

  require(amount > 0)

  override def equals(a: Any) = a match {
    case other: IOU => other.ower == ower && other.owed == owed
    case _ => false
  }

  override def hashCode = ower.hashCode * owed.hashCode

  override def toString = "%s owes %s %s".format(ower, owed, amount)

}