import io.getquill._

object DbSample:

  @main def getTestTable =
    val ctx = new MysqlJdbcContext(SnakeCase, "sample.context")
    import ctx._

    case class TestTable(id: Long, name: String)

    val res = ctx.run(quote {
      query[TestTable]
    })
    println("The result set is:")
    println(res)

  @main def insertTestTable =
    val ctx = new MysqlJdbcContext(SnakeCase, "sample.context")
    import ctx._

    case class TestTable(id: Long, name: String)

    val res1 = ctx.run(quote {
      query[TestTable].insert(_.name -> "サンプル")
    })
    val res2 = ctx.run(quote {
      query[TestTable]
    })
    println("The result of insert is:")
    println(res1)
    println("The result set is:")
    println(res2)
