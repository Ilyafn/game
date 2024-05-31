sealed trait Entity {
  var hp: Int
  val name: String
  var pos: Int = 0

  def move(s: String, world: World): Array[Option[Entity]] = {
    world.worldMap(pos) = None
    pos = getNewPos(s)
    if pos <= -1 then pos = world.size - 1
    else if pos >= world.size then pos = 0
    world.worldMap(pos) match {
      case None => { world.worldMap(pos) = Option(this) }
      case Some(enemy) => {
        world.worldMap(pos) = Option(Fight(this, enemy).run())
      }
    }
    world.worldMap
  }

  def getNewPos(s: String): Int

  var isAlive: Boolean = true

  def damage(s: Int): Unit = {
    hp = hp - s
    if 0 >= hp then isAlive = false
  }

  def hit(s: Int, victim: Entity): Int = {
    victim.damage(s)
    victim.hp
  }

  def kill(): Unit = isAlive = false

}
case class Enemy(
    var hp: Int,
    name: String
) extends Entity {

  override def getNewPos(s: String): Int = pos
}

case class Player(
    var hp: Int,
    name: String
) extends Entity {

  override def getNewPos(s: String): Int = s match {
    case "." | "ю" => { pos + 1 }
    case "," | "б" => { pos - 1 }
  }
}
