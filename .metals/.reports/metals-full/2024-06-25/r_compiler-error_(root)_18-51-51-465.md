file://<WORKSPACE>/src/main/scala/World.scala
### dotty.tools.dotc.ast.Trees$UnAssignedTypeException: type of Ident(Some) is not assigned

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 1491
uri: file://<WORKSPACE>/src/main/scala/World.scala
text:
```scala
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

      worldMap.map(optE => optE.map(_.update()))
      turn = turn + 1
    }
  }
  var worldMap: Array[Option[Entity]] = Array()
  def optEnemy(): Option[Entity] = {
    val ran: Int = scala.util.Random.nextInt(15)
    ran match {
      case 0 => Option(Normal(200))
      case 1 => Option(Attacker(200))
      case 2 => Option(Blocker(200))
      case _ => None
    }
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
  private def toStringHelper(optP: Option[Entity]): String = {optP match {
    case Some[@@]
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

```



#### Error stacktrace:

```
dotty.tools.dotc.ast.Trees$Tree.tpe(Trees.scala:74)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:207)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:104)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:88)
	dotty.tools.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:53)
	dotty.tools.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:391)
```
#### Short summary: 

dotty.tools.dotc.ast.Trees$UnAssignedTypeException: type of Ident(Some) is not assigned