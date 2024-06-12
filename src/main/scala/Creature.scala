sealed trait Creature extends Entity {
  var hp: Int
  val name: String
  var pos: Int = 0
  var potions: Int = 5
  var isBlocking: Boolean = false
  val sword: Sword = Sword(100)
  val shield: Shield = Shield(50)
  val armor: Armor = Armor(10)

  def move(s: String, world: World): Array[Option[Entity]] = {
    world.worldMap(pos) match {
      case Some(place: Visitable) => place.letOut()
      case _                      => world.worldMap(pos) = None
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
    if victim.isBlocking then victim.damage(s - 50) else victim.damage(s)
    victim.hp
  }

  def block(blockAmount: Int): Unit = {
    isBlocking = true
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
  val queue: List[Action] = List[Action]()
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
