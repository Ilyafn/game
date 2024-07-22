import Factory._
trait VariantEnemy extends Enemy {
  def loadout: Unit
}

case class Normal(var hp: Int) extends VariantEnemy {
  override def chooseAction(): Unit = {
    action =
      if scala.util.Random.nextInt(2) == 0
      then Some(Hit)
      else Some(Block)
  }
  val repr: String = "X"
  override def loadout: Unit = {
    shield = makeShield()
    armor = makeArmor()
    sword = makeSword()
  }
}

case class Attacker(var hp: Int) extends VariantEnemy {
  override def chooseAction(): Unit = action = Some(Hit)
  val repr: String = "A"
  override def loadout: Unit = {
    sword = makeSword()
  }
}

case class Blocker(var hp: Int) extends VariantEnemy {
  override def chooseAction(): Unit = action = Some(Block)
  val repr: String = "B"
  override def loadout: Unit = {
    shield = makeShield()
    armor = makeArmor()
  }
}
