package protoquill.sandbox

import io.getquill.*

object BasicQueries:

  case class Person(firstName: String, lastName: String, age: Int)

  val ctx = new SqlMirrorContext(MySQLDialect, Literal)
  import ctx._

  def select1 = quote {
    query[Person].filter(_.firstName == "Joe")
  }

  def insert1 = quote {
    query[Person].insertValue(lift(Person("tomoki", "mizogami", 25)))
  }

  def insert2 = quote {
    query[Person].insert(_.firstName -> "test")
  }

  def insert3 = quote {
    query[Person].insertValue(lift(Person("tomoki", "mizogami", 25))).returning(_.firstName)
  }

  def update1 = 
    val updated = Person("友貴", "溝上", 25)
    quote {
      query[Person].filter(p => p.firstName == "tomoki" && p.lastName == "mizogami").updateValue(lift(updated))
    }

  def delete1 =
    quote {
      query[Person].filter(_.lastName == "mizogami").delete
    }

  inline def runSelect1 = ctx.run(select1)
  inline def runInsert1 = ctx.run(insert1)
  inline def runInsert2 = ctx.run(insert2)
  inline def runInsert3 = ctx.run(insert3)
  inline def runUpdate1 = ctx.run(update1)
  inline def runDelete1 = ctx.run(delete1)

  @main def runBasicQueries =
    println(runSelect1)
    println(runInsert1)
    println(runInsert2)
    println(runInsert3)
    println(runUpdate1)
    println(runDelete1)
