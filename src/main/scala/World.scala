import scala.io.StdIn._
import scala.util.boundary
case class World(size: Int = 20) {
  var turn: Int = 0
  def run(player: Creature): Unit = boundary {
    while player.isAlive do {
      if turn % (size / 2) == 0 then populate()
      var dir: String = readLine
      dir match {
        case "exit" | "q"          => scala.util.boundary.break()
        case "." | "," | "б" | "ю" => this.worldMap = player.move(dir, this)
        case _                     => {}
      }
      // print("\u001b[2J")
      print(this)
      turn = turn + 1
    }
  }
  var worldMap: Array[Option[Creature]] = Array()
  def optEnemy(): Option[Creature] = {
    val ran: Int = scala.util.Random.nextInt(5)
    if ran == 0 then Option(Enemy(200, "Enemy"))
    else None
  }
  def populate() = {
    val res = for {
      w <- worldMap
    } yield {
      w match {
        case Some(enemy: Enemy) => optEnemy()
        case None               => optEnemy()
        case Some(otherCreature)  => Some(otherCreature)
      }
    }
    worldMap = res
  }
  def init(): Unit = {
    worldMap = Array.fill(size)(None)
  }
  private def toStringHelper(optP: Option[Creature]): String = {
    optP match {
      case None                 => "_"
      case Some(player: Player) => "P"
      case Some(enemy: Enemy)   => "X"
    }
  }
  override def toString(): String = {
    val res = for {
      w <- worldMap
    } yield {
      toStringHelper(w)
    }

    res.mkString("")
  }

}