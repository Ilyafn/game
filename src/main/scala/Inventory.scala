case class Inventory(var items: List[Item] = List()) {
  def addItems(loot: List[Item]): Unit = { items = items ::: loot }
  override def toString(): String =
    items.zipWithIndex
      .map(i => s"${i._2}\t${i._1}")
      .mkString("\n")
  def delete(n: Int): Unit = {
    items = items.take(n) ::: items.drop(n + 1)
  }
  def equip(n: Int, player: Creature): Unit = {
    items(n) match {
      case item: Equipment => {
        item.equip(player).map(i => items = items.appended(i))
        delete(n)
      }
    }
  }

  def show(player: Creature): Unit = {
    var x = ""
    while x != "i" do {
      println(this)
      x = scala.io.StdIn.readLine
      val n = x.toIntOption.getOrElse(-100)
      if n >= 0 && n < items.length then {
        items(n).showActions()

        val action = scala.io.StdIn.readLine

        if items(n).actions().contains(action) then
          action match {
            case "d" => delete(n)
            case "e" => equip(n, player)
          }
      }
    }
  }
}
