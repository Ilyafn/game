import scala.io.StdIn._
object Game extends App {
  val size: Int = readInt
  val world: World = World(size)

  val player: Player = Player(
    500,
    "Van Punchman"
  )
  println()

  world.init()
  world.worldMap(0) = Some(player)
  println(world)
  world.run(player)
}
