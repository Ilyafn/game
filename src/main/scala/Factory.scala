import scala.util.Random._
object Factory {
  def makeShield(): Option[Shield] = {
    if nextInt(9) == 0 then None else Some(Shield(between(30, 50)))
  }
  def makeWeapon(): Option[Weapon] = {
    if nextInt(9) == 0 then None else Some(Sword(between(60, 80)))
  }
  def makeArmor(): Option[Armor] = {
    if nextInt(9) == 0 then None else Some(Armor(between(8, 20)))
  }
  def makeItem(): Item = {
    val a: Int = nextInt(3)
    a match {
      case 0 => Sword(between(70, 90))
      case 1 => Shield(between(40, 60))
      case 2 => Armor(between(16, 30))
    }
  }
  def makeItems(numItems: Int): List[Item] = {
    (1 to numItems).toList.map(_ => makeItem())
  }
}
