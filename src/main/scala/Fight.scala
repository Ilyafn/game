import scala.io.StdIn._

case class Fight(player: Entity, enemy: Entity) {
  def run(): Entity = {
    while player.isAlive && enemy.isAlive do {
      println(s"${player.name}:${player.hp} ${enemy.name}:${enemy.hp} ")
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
      case "1" => player.hit(100, enemy)
      case "2" => player.hit(100, enemy)
      case "3" => player.hit(100, enemy)
      case _ => player.hit(100, enemy)
    }
    if enemy.isAlive then enemy.hit(100, player)
  }
}
