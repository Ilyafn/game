import scala.io.StdIn._

case class Fight(player: Creature, enemy: Creature) {
  private def skipTurn() = ()
  def run(): Creature = {
    while player.isAlive && enemy.isAlive do {
      println(
        s"${player.name}:${player.hp} ${enemy.name}:${enemy.hp} potions: ${player.potions}"
      )
      turn()
    }
    val combatants = (player.isAlive, enemy.isAlive)
    combatants match {
      case (true, false) => {
        player.pickUp(enemy.inventory)
        player.gainXp(enemy.xp)
        player
      }
      case (false, true) => {
        enemy.pickUp(player.inventory)
        enemy.gainXp(player.xp)
        enemy
      }
      case _ => player  
    }
  }

  private def turn(): Unit = {
    enemy.chooseAction()
    println(enemy.action)
    player.chooseAction()
    player.action match {
      case Some(Hit) =>
        player.hit(
          player.weapon match {
            case Some(weapon) => weapon.calculateDamage(player.stats)
            case None        => 10*player.stats.strength
          },
          enemy
        )
      case Some(Block) =>
        player.block()
      case Some(Heal) => player.heal()

      case None => skipTurn()
    }
    enemy.isBlocking = false
    if enemy.isAlive then
      enemy.action match {
        case Some(Hit) =>
          enemy.hit(
            enemy.weapon match {
              case Some(weapon) => weapon.calculateDamage(enemy.stats)
              case None        => 10*enemy.stats.strength
            },
            player
          )
        case Some(Block) =>
          enemy.block()
        case Some(Heal) => enemy.heal()
        case None       => skipTurn()
      }
    player.isBlocking = false

  }
}
