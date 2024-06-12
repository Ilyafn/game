import scala.io.StdIn._

case class Fight(player: Creature, enemy: Creature) {
  def run(): Creature = {
    while player.isAlive && enemy.isAlive do {
      println(s"${player.name}:${player.hp} ${enemy.name}:${enemy.hp} potions: ${player.potions}")
      turn()
    }
    val combatants = (player.isAlive, enemy.isAlive)
    combatants match {
      case (true, false) => player
      case (false, true) => enemy
      case _             => player
    }
  } 
  private def turn(): Unit = {
    println("1 => hit")
    println("2 => block")
    println("3 => heal")
    readLine match {
      case "1" => player.hit(player.sword.damage, enemy)
      case "2" => player.block(player.shield.blockAmount)
      case "3" => player.heal()
      case _ => player.hit(player.sword.damage, enemy)
    }
    if enemy.isAlive then enemy.hit(enemy.sword.damage, player)
    player.isBlocking = false
    enemy.isBlocking = false
  }
}
