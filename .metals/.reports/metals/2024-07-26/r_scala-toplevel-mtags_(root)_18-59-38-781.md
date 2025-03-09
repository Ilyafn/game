error id: file://<WORKSPACE>/src/main/scala/Visitable.scala:[568..576) in Input.VirtualFile("file://<WORKSPACE>/src/main/scala/Visitable.scala", "import scala.util.Random
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
  val repr: String = "F"

}
case class Shop() extends Visitable {
  private def
  override def letIn(visitor: Creature) = {}
  override def update(turn: Int): Entity = {
    turn%5 match {
      case turn 0 => {
        
      }
      case _: Int => ()
    }
  }
  val repr: String = "M"
}
")
file://<WORKSPACE>/src/main/scala/Visitable.scala
file://<WORKSPACE>/src/main/scala/Visitable.scala:26: error: expected identifier; obtained override
  override def letIn(visitor: Creature) = {}
  ^
#### Short summary: 

expected identifier; obtained override