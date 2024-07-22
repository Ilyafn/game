trait Item {
  def showActions(): Unit = println("delete")
  def actions():List[String] = List("d")
}

case class Apple() extends Item