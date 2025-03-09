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
        case "i" | "ш"             => player.showInventory()
        case "w"                   => player.pickUp(Inventory(List(Sword(10))))
        case "a"                   => player.pickUp(Inventory(List(Apple())))
        case "e" => {
          println(String.format("\u001b[%dA", 10))
          println("\u001b[2K")
        }
        case _ => {}
      }
      print(this)

      worldMap.map(optE => optE.map(_.update(turn)))
      turn = turn + 1
    }
  }
  var worldMap: Array[Option[Entity]] = Array()
  def optEnemy(): Option[Entity] = {
    val ran: Int = scala.util.Random.nextInt(15)
    val x = ran match {
      case 0 => Option(Normal(200))
      case 1 => Option(Attacker(200))
      case 2 => Option(Blocker(200))
      case _ => None
    }
    x.map(enemy => enemy.loadout)
    x
  }
  def populate() = {
    val res = for {
      w <- worldMap
    } yield {
      w match {
        case Some(enemy: Enemy)  => optEnemy()
        case None                => optEnemy()
        case Some(otherCreature) => Some(otherCreature)
      }
    }
    worldMap = res
  }
  def init(): Unit = {
    worldMap = Array.fill(size)(None)
    val x = scala.util.Random.nextInt(size - 1)
    val myCoolFountain: Fountain = Fountain()
    worldMap(x) = Some(myCoolFountain)
    worldMap = worldMap.reverse
  }
  private def toStringHelper(optP: Option[Entity]): String = {
    optP match {
      case Some(entity) => entity.repr
      case None         => "_"
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
