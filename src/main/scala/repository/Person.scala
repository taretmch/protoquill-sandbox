package protoquill.sandbox.repository

import io.getquill._
import protoquill.sandbox.entity.Person

class PersonRepository extends Repository:

  inline def schema = quote {
    querySchema[Person]("person",
      _.name.firstName -> "first_name",
      _.name.lastName  -> "last_name"
    )
  }

  def findAll: Seq[Person] =
    val ctx = connect
    import ctx._

    val res = run(schema)
    ctx.close
    res

  def get(id: Person.Id): Option[Person] =
    val ctx = connect
    import ctx._

    val res = run(schema.filter(_.id == lift(id))).headOption
    ctx.close
    res

  def add(data: Person): Person.Id =
    val ctx = connect
    import ctx._

    val res = run(schema.insertValue(lift(data)).returningGenerated(_.id))
    ctx.close
    res

  def update(data: Person): Option[Person] =
    val ctx = connect
    import ctx._

    for
      old <- run(schema.filter(_.id == lift(data.id))).headOption
      _    = run(schema.filter(_.id == lift(data.id)).updateValue(lift(data)))
    yield
      ctx.close
      old

  def delete(id: Person.Id): Option[Person] =
    val ctx = connect
    import ctx._

    for
      old <- run(schema.filter(_.id == lift(id))).headOption
      _    = run(schema.filter(_.id == lift(id)).delete)
    yield
      ctx.close
      old

end PersonRepository
