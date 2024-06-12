error id: file://<WORKSPACE>/src/main/scala/Equipment.scala:[13..18) in Input.VirtualFile("file://<WORKSPACE>/src/main/scala/Equipment.scala", "sealed trait trait Equipment {
  var wear: Boolean = false
  var param: Int = 0
//  effect:
}
case class Sword() extends Equipment {
  wear = true
  param = 100
}
case class Armor() extends Equipment {
  wear = true
  param = 10
}
case class Shield() extends Equipment {
  wear = true
  param = 50
}
")
file://<WORKSPACE>/src/main/scala/Equipment.scala
file://<WORKSPACE>/src/main/scala/Equipment.scala:1: error: expected identifier; obtained trait
sealed trait trait Equipment {
             ^
#### Short summary: 

expected identifier; obtained trait