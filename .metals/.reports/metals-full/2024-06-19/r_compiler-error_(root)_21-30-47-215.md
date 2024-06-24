file://<WORKSPACE>/src/main/scala/Creature.scala
### java.lang.AssertionError: assertion failed

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 81
uri: file://<WORKSPACE>/src/main/scala/Creature.scala
text:
```scala
sealhp: Int
  val name: String
  var pos: Int = 0
  var potions: Int = 5
  var is@@Blocking: Boolean = false
  var sword: Option[Sword]
  var shield: Option[Shield]
  var armored trait Creature extends Entity {
  var : Option[Armor]


  def move(s: String, world: World): Array[Option[Entity]] = {
    world.worldMap(pos) match {
      case Some(place: Visitable) => place.letOut()
      case _                      => world.worldMap(pos) = None
    }
    pos = getNewPos(s)
    if pos <= -1 then pos = world.size - 1
    else if pos >= world.size then pos = 0
    world.worldMap(pos) match {
      case None => { world.worldMap(pos) = Option(this) }
      case Some(enemy: Creature) =>
        world.worldMap(pos) = Option(Fight(this, enemy).run())
      case Some(place: Visitable) => place.letIn(this)
    }
    world.worldMap
  }

  def getNewPos(s: String): Int

  var isAlive: Boolean = true

  def damage(s: Int): Unit = {
    if  armor.isDefined 
    then hp = hp - calculateDamage(armor.get.armor,s)   
    else hp = hp  - s
    if 0 >= hp then isAlive = false
  }
  def calculateDamage(armor: Int, damage: Int): Int = {
    if damage < armor then 0
    else damage - armor
  }

  def hit(s: Int, victim: Creature): Int = {
    if victim.isBlocking then victim.damage(s - 50) else victim.damage(s)
    victim.hp
  }

  def block(blockAmount: Int): Unit = {
    isBlocking = true
  }

  def kill(): Unit = isAlive = false

  def heal(): Unit = {
    // if potions > 5 then potions = 5
    val amount: Int = 200
    if potions > 0 then {
      hp = hp + amount
      potions = potions - 1
    }
    if hp > 500 then hp = 500
  }

}
case class Enemy(
    var hp: Int,
    name: String
) extends Creature {

  override def getNewPos(s: String): Int = pos
  val queue: List[Action] = List[Action]()
}

case class Player(
    var hp: Int,
    name: String
) extends Creature {

  override def getNewPos(s: String): Int = s match {
    case "." | "ю" => { pos + 1 }
    case "," | "б" => { pos - 1 }
  }
}

```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:11)
	dotty.tools.dotc.core.Annotations$LazyAnnotation.tree(Annotations.scala:136)
	dotty.tools.dotc.core.Annotations$Annotation$Child$.unapply(Annotations.scala:242)
	dotty.tools.dotc.typer.Namer.insertInto$1(Namer.scala:479)
	dotty.tools.dotc.typer.Namer.addChild(Namer.scala:490)
	dotty.tools.dotc.typer.Namer$Completer.register$1(Namer.scala:932)
	dotty.tools.dotc.typer.Namer$Completer.registerIfChild$$anonfun$1(Namer.scala:941)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.typer.Namer$Completer.registerIfChild(Namer.scala:941)
	dotty.tools.dotc.typer.Namer$Completer.complete(Namer.scala:832)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:178)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:190)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:192)
	dotty.tools.dotc.core.Types$NamedType.info(Types.scala:2427)
	dotty.tools.dotc.core.Types$TermLambda.dotty$tools$dotc$core$Types$TermLambda$$_$compute$1(Types.scala:3945)
	dotty.tools.dotc.core.Types$TermLambda.foldArgs$2(Types.scala:3952)
	dotty.tools.dotc.core.Types$TermLambda.dotty$tools$dotc$core$Types$TermLambda$$_$compute$1(Types.scala:4562)
	dotty.tools.dotc.core.Types$TermLambda.dotty$tools$dotc$core$Types$TermLambda$$depStatus(Types.scala:3972)
	dotty.tools.dotc.core.Types$TermLambda.dependencyStatus(Types.scala:3986)
	dotty.tools.dotc.core.Types$TermLambda.isResultDependent(Types.scala:4008)
	dotty.tools.dotc.core.Types$TermLambda.isResultDependent$(Types.scala:3902)
	dotty.tools.dotc.core.Types$MethodType.isResultDependent(Types.scala:4047)
	dotty.tools.dotc.typer.TypeAssigner.assignType(TypeAssigner.scala:298)
	dotty.tools.dotc.typer.TypeAssigner.assignType$(TypeAssigner.scala:16)
	dotty.tools.dotc.typer.Typer.assignType(Typer.scala:120)
	dotty.tools.dotc.ast.tpd$.Apply(tpd.scala:48)
	dotty.tools.dotc.core.tasty.TreeUnpickler.dotty$tools$dotc$core$tasty$TreeUnpickler$TreeReader$$_$constructorApply$1(TreeUnpickler.scala:1330)
	dotty.tools.dotc.core.tasty.TreeUnpickler$TreeReader.readLengthTree$1(TreeUnpickler.scala:1372)
	dotty.tools.dotc.core.tasty.TreeUnpickler$TreeReader.readTree(TreeUnpickler.scala:1556)
	dotty.tools.dotc.core.tasty.TreeUnpickler.$anonfun$15$$anonfun$1(TreeUnpickler.scala:766)
	dotty.tools.dotc.core.tasty.TreeUnpickler$LazyReader.complete(TreeUnpickler.scala:1696)
	dotty.tools.dotc.core.tasty.TreeUnpickler.$anon$superArg$2$1$$anonfun$1(TreeUnpickler.scala:768)
	dotty.tools.dotc.core.Annotations$LazyAnnotation.tree(Annotations.scala:140)
	dotty.tools.dotc.core.Annotations$Annotation$Child$.unapply(Annotations.scala:242)
	dotty.tools.dotc.typer.Namer.insertInto$1(Namer.scala:479)
	dotty.tools.dotc.typer.Namer.addChild(Namer.scala:490)
	dotty.tools.dotc.typer.Namer$Completer.register$1(Namer.scala:932)
	dotty.tools.dotc.typer.Namer$Completer.registerIfChild$$anonfun$1(Namer.scala:941)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.typer.Namer$Completer.registerIfChild(Namer.scala:941)
	dotty.tools.dotc.typer.Namer$Completer.complete(Namer.scala:832)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:178)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:190)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:192)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.ensureCompleted(SymDenotations.scala:398)
	dotty.tools.dotc.typer.Typer.retrieveSym(Typer.scala:3060)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3085)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3196)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3274)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3278)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3300)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3346)
	dotty.tools.dotc.typer.Typer.typedPackageDef(Typer.scala:2923)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3147)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3197)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3274)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3278)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3389)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$1(TyperPhase.scala:47)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:477)
	dotty.tools.dotc.typer.TyperPhase.typeCheck(TyperPhase.scala:53)
	dotty.tools.dotc.typer.TyperPhase.$anonfun$4(TyperPhase.scala:99)
	scala.collection.Iterator$$anon$6.hasNext(Iterator.scala:479)
	scala.collection.Iterator$$anon$9.hasNext(Iterator.scala:583)
	scala.collection.immutable.List.prependedAll(List.scala:152)
	scala.collection.immutable.List$.from(List.scala:684)
	scala.collection.immutable.List$.from(List.scala:681)
	scala.collection.IterableOps$WithFilter.map(Iterable.scala:898)
	dotty.tools.dotc.typer.TyperPhase.runOn(TyperPhase.scala:100)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:315)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1323)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:337)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:350)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:360)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:69)
	dotty.tools.dotc.Run.compileUnits(Run.scala:360)
	dotty.tools.dotc.Run.compileSources(Run.scala:261)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:161)
	dotty.tools.pc.MetalsDriver.run(MetalsDriver.scala:47)
	dotty.tools.pc.HoverProvider$.hover(HoverProvider.scala:38)
	dotty.tools.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:345)
```
#### Short summary: 

java.lang.AssertionError: assertion failed