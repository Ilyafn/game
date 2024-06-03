file://<WORKSPACE>/src/main/scala/Visitable.scala
### java.lang.StringIndexOutOfBoundsException: String index out of range: 158

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 130
uri: file://<WORKSPACE>/src/main/scala/Visitable.scala
text:
```scala
trait Visitable extends Entity {
    val occupant: Option[Creature] = None
    def letIn(visitor:Creature):Unit
    
}
case class @@Fountain() extends Visitable
```



#### Error stacktrace:

```
java.base/java.lang.StringLatin1.charAt(StringLatin1.java:48)
	java.base/java.lang.String.charAt(String.java:1512)
	dotty.tools.pc.completions.OverrideCompletions$.hasColon(OverrideCompletions.scala:507)
	dotty.tools.pc.completions.OverrideCompletions$.hasBracesOrColon(OverrideCompletions.scala:500)
	dotty.tools.pc.completions.OverrideCompletions$.implementAllFor(OverrideCompletions.scala:322)
	dotty.tools.pc.completions.OverrideCompletions$.implementAll$lzyINIT1$1$$anonfun$1(OverrideCompletions.scala:213)
	dotty.tools.pc.completions.OverrideCompletions$.implementAllAt(OverrideCompletions.scala:219)
	dotty.tools.pc.ScalaPresentationCompiler.implementAbstractMembers$$anonfun$1(ScalaPresentationCompiler.scala:253)
```
#### Short summary: 

java.lang.StringIndexOutOfBoundsException: String index out of range: 158