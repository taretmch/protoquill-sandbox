import io.getquill._

@main def delete =
  val ctx = new MysqlJdbcContext(SnakeCase, "sample.context")
  case class TestTable(id: Long, name: String)
  import ctx._

  ctx.run(quote {
    query[TestTable].filter(_.id == 1).delete
  })
