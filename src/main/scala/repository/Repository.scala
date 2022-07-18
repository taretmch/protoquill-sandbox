package protoquill.sandbox.repository

import io.getquill._

trait Repository extends Encoding:

  def connect = new MysqlJdbcContext(SnakeCase, "sample.context")
