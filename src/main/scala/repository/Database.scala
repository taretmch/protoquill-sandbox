package repository

import io.getquill.*

class Database:

  val ctx = new MysqlJdbcContext(SnakeCase, "sample.context")
