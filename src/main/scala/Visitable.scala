trait Visitable extends Entity {
  var occupant: Option[Creature] = None
  def letIn(visitor: Creature): Unit
  def letOut(): Unit = {
    occupant = None
  }
}
case class Fountain() extends Visitable {
  override def letIn(visitor: Creature) = {
    occupant = Option(visitor)
    visitor.potions = potions
    potions = 0
  }
  var potions = 0
  override def update(turn: Int): Entity = {
    if potions < 5 then potions + 1
    else potions = 5
    this
  }
  override val repr: String = "F"

}
case class Shop() extends Visitable {
  val shopkeep: NPC = NPC(name = "Shopkeep")
  private def restock(): Unit = {
    var newStock: List[Item] = Factory.makeItems(10)
    shopkeep.inventory.items = newStock
  }
  override def letIn(visitor: Creature) = {}
  override def update(turn: Int): Entity = {
    turn % 5 match {
      case 0      => { restock() }
      case _: Int => ()
    }
    shopkeep
  }
  override val repr: String = "M"
}
