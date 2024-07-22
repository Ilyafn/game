file://<WORKSPACE>/src/main/scala/Inventory.scala
### java.lang.StringIndexOutOfBoundsException: String index out of range: 51

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 50
uri: file://<WORKSPACE>/src/main/scala/Inventory.scala
text:
```scala
case class Inventory(items: List[Item] = List()){}@@

```



#### Error stacktrace:

```
java.base/java.lang.StringLatin1.charAt(StringLatin1.java:48)
	java.base/java.lang.String.charAt(String.java:1512)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.isWhitespace$1(CommonMtagsEnrichments.scala:83)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trim$1$$anonfun$1(CommonMtagsEnrichments.scala:87)
	scala.Option.filter(Option.scala:319)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trim$1(CommonMtagsEnrichments.scala:87)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trimWhitespaceInRange(CommonMtagsEnrichments.scala:92)
	dotty.tools.pc.utils.MtagsEnrichments$.sourcePosition(MtagsEnrichments.scala:46)
	dotty.tools.pc.HoverProvider$.hover(HoverProvider.scala:44)
	dotty.tools.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:345)
```
#### Short summary: 

java.lang.StringIndexOutOfBoundsException: String index out of range: 51