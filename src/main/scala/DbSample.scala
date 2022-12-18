import io.getquill.*

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

  @main def liftQuerySample =
    val ctx = new MysqlJdbcContext(SnakeCase, "sample.context")
    import ctx._

    case class TestTable(id: Long, name: String)

    val res = ctx.run(quote {
      query[TestTable].filter(v => liftQuery(List(1L, 2L)).contains(v.id))
    })
    val res2 = ctx.run(quote {
      query[TestTable].filter(v => liftQuery(List("hoge", "a")).contains(v.name))
    })
    println("The result set is:")
    println(res)
    println(res2)

  @main def liftQuerySample2 =
    val ctx = new MysqlJdbcContext(SnakeCase, "sample.context")
    import ctx._

    case class TestTable(id: TestTable.Id, name: String)
    object TestTable:
      opaque type Id = Long
      object Id:
        def apply(id: Long): Id = id
        given MappedEncoding[Id, Long](id => id)
        given MappedEncoding[Long, Id](id => id)

    import TestTable.Id

    val res = ctx.run(quote {
      query[TestTable].filter(v => liftQuery(List(Id(1L), Id(2L))).contains(v.id))
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
