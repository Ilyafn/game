sealed trait Creature extends Entity {
  var hp: Int
  val name: String
  var pos: Int = 0
  var potions: Int = 5
  var isBlocking: Boolean = false
  var sword: Option[Sword] = None
  var shield: Option[Shield] = None
  var armor: Option[Armor] = None

  def move(s: String, world: World): Array[Option[Entity]] = {
    world.worldMap(pos) match {
      case Some(place: Visitable) => place.letOut()
      case _                      => world.worldMap(pos) = None
    }
    pos = getNewPos(s)
    if pos <= -1 then pos = world.size - 1
    else if pos >= world.size then pos = 0
    world.worldMap(pos) match {

      case Some(enemy: Creature) =>
        world.worldMap(pos) = Option(Fight(this, enemy).run())
      case Some(place: Visitable) => place.letIn(this)
      case _                      => { world.worldMap(pos) = Option(this) }
    }
    world.worldMap
  }

  def getNewPos(s: String): Int

  var isAlive: Boolean = true

  def damage(s: Int): Unit = {
    def calculateDamage(armor: Int, damage: Int): Int = {
      if damage < armor then 0
      else damage - armor
    }

    if armor.isDefined
    then hp = hp - calculateDamage(armor.get.armor, s)
    else hp = hp - s
    if 0 >= hp then isAlive = false
  }

  def hit(s: Int, victim: Creature): Unit = {
    if victim.isBlocking then victim.damage(s - 50) else victim.damage(s)
    // victim.hp
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

  def chooseAction(): Unit

  var action: Option[Action] = None

}
trait Enemy extends Creature {
  val name: String = "Enemy"
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
  def chooseAction(): Unit = {
    println("1 => hit")
    println("2 => block")
    println("3 => heal")
    scala.io.StdIn.readLine match {

      case "1" => action = Some(Hit)

      case "2" => action = Some(Block)

      case "3" => action = Some(Heal)

      case _ => action = Some(Hit)
    }
  }
}
