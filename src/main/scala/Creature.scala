sealed trait Creature extends Entity {
  var hp: Int
  val name: String
  var pos: Int = 0
  var potions: Int = 5
  var isBlocking: Boolean = false
  var sword: Option[Sword] = None
  var shield: Option[Shield] = None
  var armor: Option[Armor] = None
  val inventory: Inventory = Inventory()
  var xp: Int = 0
  var level: Int = 0

  def gainXp(amount: Int): Unit = {
    xp = xp + amount
    val nextlevelXpThreshold = 10*math.pow(2, level).toInt
    if xp >= nextlevelXpThreshold then 
      level = level + 1
      xp = xp - nextlevelXpThreshold
  }

  def showInventory(): Unit = {
    inventory.show(this)
  }

  def pickUp(loot: Inventory): Unit = {
    inventory.addItems(loot.items)
  }
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
    val shieldBlockAmount: Int =
      if isBlocking then
        shield match {
          case Some(shield) => shield.blockAmount
          case None         => 5
        }
      else 0
    val armorBlockAmount: Int = armor match {
      case Some(armor) => armor.armor
      case None        => 0
    }
    hp - calculateDamage(armorBlockAmount + shieldBlockAmount, s)
    if 0 >= hp then kill()
  }

  def hit(s: Int, victim: Creature): Unit = victim.damage(s)

  def block(): Unit = {
    isBlocking = true
  }

  def kill(): Unit = {
    val loot: List[Item] = List(sword, shield, armor).flatten
    inventory.addItems(loot)
    isAlive = false
  }

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

  val repr: String = "P"
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

      case _ => action = None
    }
  }
}
