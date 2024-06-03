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
    visitor.potions = 5
  }
}
