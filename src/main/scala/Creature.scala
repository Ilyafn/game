import scala.io.StdIn.readLine

sealed trait Creature extends Entity {
  var hp: Int
  var mana: Int = 0
  val name: String
  var pos: Int = 0
  var potions: Int = 5
  var isBlocking: Boolean = false
  var weapon: Option[Weapon] = None
  var shield: Option[Shield] = None
  var armor: Option[Armor] = None
  val inventory: Inventory = Inventory()
  var xp: Int = 0
  var level: Int = 0
  val stats: Stats = Stats()

  def gainXp(amount: Int): Unit = {
    xp = xp + amount
    val nextlevelXpThreshold = 10 * math.pow(2, level).toInt
    if xp >= nextlevelXpThreshold then {
      level = level + 1
      println(s"Level up")
      println(
        "Повысьте характеристику:" +
          "1 сила" +
          "2 ловкость" +
          "3 интелект"
      )
      var a: String = "0"
      while a != "1" || a != "2" || a != "3" do {
        var a: String = readLine
        a match {
          case "1" => stats.strength + 1
          case "2" => stats.dexterity + 1
          case "3" => stats.intelligence + 1
          case "_" => ()
        }
      }
      xp = xp - nextlevelXpThreshold
    }
  }

  def showInventory(): Unit = ()

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

  def getNewPos(s: String): Int = pos

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
    val loot: List[Item] = List(weapon, shield, armor).flatten
    inventory.addItems(loot)
    isAlive = false
  }

  def heal(): Unit = {
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
  val queue: List[Action] = List[Action]()
}

case class Player(
    var hp: Int,
    name: String
) extends Creature {

  override def showInventory(): Unit = {
    var x = ""
    while x != "i" do {
      println(inventory)
      x = scala.io.StdIn.readLine
      val n = x.toIntOption.getOrElse(-100)
      if n >= 0 && n < inventory.items.length then {
        inventory.items(n).showActions()

        val action = scala.io.StdIn.readLine

        if inventory.items(n).actions().contains(action) then
          action match {
            case "d" => inventory.delete(n)
            case "e" => inventory.equip(n, this)
          }
      }
    }
  }

  override val repr: String = "P"
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
case class NPC(
    var hp: Int = 999999,
    name: String
) extends Creature{

  override def showInventory(): Unit = {
    var x = ""
    while x != "i" do {
      println(inventory)
      x = scala.io.StdIn.readLine
      val n = x.toIntOption.getOrElse(-100)
      if n >= 0 && n < inventory.items.length then {
        inventory.items(n).showActions()

        val action = scala.io.StdIn.readLine

        if inventory.items(n).actions().contains(action) then
          action match {
            case "" => inventory.delete(n)
          }
      }
    }
  }


  override def chooseAction(): Unit = ???

  override val repr: String = ???

}
