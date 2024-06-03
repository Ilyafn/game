sealed trait Creature extends Entity {
  var hp: Int
  val name: String
  var pos: Int = 0
  var potions: Int = 5

  def move(s: String, world: World): Array[Option[Entity]] = {
    world.worldMap(pos) match {
    case Some(place: Visitable) => place.letOut()
    case _ => world.worldMap(pos) = None
    }
    pos = getNewPos(s)
    if pos <= -1 then pos = world.size - 1
    else if pos >= world.size then pos = 0
    world.worldMap(pos) match {
      case None => { world.worldMap(pos) = Option(this) }
      case Some(enemy: Creature) =>
        world.worldMap(pos) = Option(Fight(this, enemy).run())
      case Some(place: Visitable) => place.letIn(this)
    }
    world.worldMap
  }

  def getNewPos(s: String): Int

  var isAlive: Boolean = true

  def damage(s: Int): Unit = {
    hp = hp - s
    if 0 >= hp then isAlive = false
  }

  def hit(s: Int, victim: Creature): Int = {
    victim.damage(s)
    victim.hp
  }

  def kill(): Unit = isAlive = false

  def heal(): Unit = {
    // if potions > 5 then potions = 5
    val amount: Int = 200
    if potions > 0 then {
      hp = hp + amount
      potions = potions - 1
    }
    if hp > 500 then hp = 500
  }

}
case class Enemy(
    var hp: Int,
    name: String
) extends Creature {

  override def getNewPos(s: String): Int = pos
}

case class Player(
    var hp: Int,
    name: String
) extends Creature {

  override def getNewPos(s: String): Int = s match {
    case "." | "ю" => { pos + 1 }
    case "," | "б" => { pos - 1 }
  }
}
