package protoquill.sandbox.repository

import io.getquill._
import io.getquill.generic.{ GenericDecoder, DecodingType }

trait Repository[T] extends CustomEncoding:

  type N = SnakeCase

  def connect: MysqlJdbcContext[N] = new MysqlJdbcContext(SnakeCase, "sample.context")

  def withSession[T](block: MysqlJdbcContext[N] ?=> T): T =
    val ctx = connect
    val res = block(using ctx)
    ctx.close
    res
