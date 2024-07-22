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
//  effect:
case class Sword(damage: Int) extends Equipment {
  override def equip(
      player: Creature
  ): Option[Item] = {
    val swordOpt = player.sword
    player.sword = Some(this)
    swordOpt
  }
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
