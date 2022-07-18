package protoquill.sandbox

import io.getquill._

object BasicQueries:

  val ctx = new SqlMirrorContext(MySQLDialect, Literal)
  import ctx._

  def select1 = quote {
    query[Person].filter(_.firstName == "Joe")
  }

  def insert1 = quote {
    query[Person].insertValue(lift(Person("tomoki", "mizogami", 24)))
  }

  def insert2 = quote {
    query[Person].insert(_.firstName -> "test")
  }

  def insert3 = quote {
    query[Person].insertValue(lift(Person("tomoki", "mizogami", 24))).returning(_.firstName)
  }

  inline def runSelect1 = ctx.run(select1)
  inline def runInsert1 = ctx.run(insert1)
  inline def runInsert2 = ctx.run(insert2)
  inline def runInsert3 = ctx.run(insert3)

  @main def runBasicQueries =
    println(runSelect1)
    println(runInsert1)
    println(runInsert2)
    println(runInsert3)
