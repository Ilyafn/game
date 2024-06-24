trait VariantEnemy extends Enemy {}

case class Normal(var hp: Int) extends VariantEnemy {
  override def chooseAction(): Unit = {
    action =
      if scala.util.Random.nextInt(2) == 0
      then Some(Hit)
      else Some(Block)
  }
}

case class Attacker(var hp: Int) extends VariantEnemy {
  override def chooseAction(): Unit = action = Some(Hit)

}

case class Blocker(var hp: Int) extends VariantEnemy {
  override def chooseAction(): Unit = action = Some(Block)
}
