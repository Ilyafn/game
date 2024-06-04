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
  override def update(): Entity = {
    if potions < 5 then potions + 1
    else potions = 5
    this
  }
}
