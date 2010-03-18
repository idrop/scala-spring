package com.company.model

case class User(id: String) {
  override def toString = id
}

case class IOU(ower: User, owed: User, amount: Double) {

  require(amount > 0)
  require(ower != owed)

  /**
   * One IOU equals another if ower and owed are the same
   */
  override def equals(a: Any) = a match {
    case other: IOU => other.ower == ower && other.owed == owed
    case _ => false
  }

  override def hashCode = (41 * (41 + ower.hashCode)) + owed.hashCode

  override def toString = "%s owes %s %s".format(ower, owed, amount)

}