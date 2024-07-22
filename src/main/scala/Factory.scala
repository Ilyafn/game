import scala.util.Random._
object Factory {
  def makeShield(): Option[Shield] = {
    if nextInt(9) == 0 then None else Some(Shield(between(30, 50)))
  }
  def makeSword(): Option[Sword] = {
    if nextInt(9) == 0 then None else Some(Sword(between(60, 80)))
  }
  def makeArmor(): Option[Armor] = {
    if nextInt(9) == 0 then None else Some(Armor(between(8, 20)))
  }
}
