trait Equipment extends Item {
  override def showActions(): Unit = {
    super.showActions()
    println("equip")
  }
  def equip(
      player: Creature
  ): Option[Item]
  override def actions(): List[String] = super.actions() ::: List("e")
}
trait Weapon extends Equipment {
  override def equip(
      player: Creature
  ): Option[Item] = {
    val weaponOpt = player.weapon
    player.weapon = Some(this)
    weaponOpt
  }
  val damage: Int

  def calculateDamage(stats: Stats): Int
}
case class Armor(armor: Int) extends Equipment {
  override def equip(
      player: Creature
  ): Option[Item] = {
    val armorOpt = player.armor
    player.armor = Some(this)
    armorOpt
  }
}
case class Shield(blockAmount: Int) extends Equipment {
  override def equip(
      player: Creature
  ): Option[Item] = {
    val shieldOpt = player.shield
    player.shield = Some(this)
    shieldOpt
  }
}

case class Sword(val damage: Int) extends Weapon {
  override def calculateDamage(stats: Stats): Int = {
    stats.strength * 3 + damage
  }
}
case class Bow(val damage: Int) extends Weapon {
  override def calculateDamage(stats: Stats): Int = {
    stats.dexterity * 3 + damage
  }
}
case class Wand(val damage: Int) extends Weapon {
  override def calculateDamage(stats: Stats): Int = {
    stats.intelligence * 3 + damage
  }
}
