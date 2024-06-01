file://<WORKSPACE>/src/main/scala/World.scala
### dotty.tools.dotc.ast.Trees$UnAssignedTypeException: type of Select(Ident(Option),empty) is not assigned

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 888
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
        case "exit"                => scala.util.boundary.break()
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
        case Some(enemy: Enemy) | Option.empty[@@]=> optEnemy()
        case Some(player)       => Some(player)

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

trait A {
  def f(): Int
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

dotty.tools.dotc.ast.Trees$UnAssignedTypeException: type of Select(Ident(Option),empty) is not assigned