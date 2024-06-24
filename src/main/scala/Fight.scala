import scala.io.StdIn._

case class Fight(player: Creature, enemy: Creature) {
  def run(): Creature = {
    while player.isAlive && enemy.isAlive do {
      println(
        s"${player.name}:${player.hp} ${enemy.name}:${enemy.hp} potions: ${player.potions}"
      )
      turn()
    }
    val combatants = (player.isAlive, enemy.isAlive)
    combatants match {
      case (true, false) => player
      case (false, true) => enemy
      case _             => player
    }
  }
  private def skipTurn() = ()
  private def turn(): Unit = {
    enemy.chooseAction()
    player.chooseAction()
    player.action match {
      case Some(Hit) =>
        player.hit(
          if player.sword.isDefined then { player.sword.get.damage }
          else { 10 },
          enemy
        )
      case Some(Block) =>
        player.block(if player.shield.isDefined then {
          player.shield.get.blockAmount
        } else { 5 })
      case Some(Heal) => player.heal()

      case None =>
        skipTurn()

        if enemy.isAlive then
          enemy.action match {
            case Some(Hit) =>
              enemy.hit(
                if enemy.sword.isDefined then { enemy.sword.get.damage }
                else { 10 },
                player
              )
            case Some(Block) =>
              enemy.block(if enemy.shield.isDefined then {
                enemy.shield.get.blockAmount
              } else { 5 })
            case Some(Heal) => enemy.heal()
            case None       => skipTurn()
          }
        player.isBlocking = false
        enemy.isBlocking = false
    }
  }
}
