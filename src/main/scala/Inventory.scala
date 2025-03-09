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
}
