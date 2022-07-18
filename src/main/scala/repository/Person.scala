package protoquill.sandbox.repository

import io.getquill._
import protoquill.sandbox.entity.Person

class PersonRepository extends Repository:

  inline def schema = quote(querySchema[Person]("person"))

  def findAll: Seq[Person] =
    val ctx = connect
    import ctx._

    run(schema)

  def get(id: Person.Id): Option[Person] =
    val ctx = connect
    import ctx._

    run(quote {
      schema.filter(_.id == lift(id))
    }).headOption

  def add(data: Person): Person.Id =
    val ctx = connect
    import ctx._

    run(schema.insertValue(lift(data)).returningGenerated(_.id))

  def update(data: Person): Option[Person] =
    val ctx = connect
    import ctx._

    run(schema.filter(_.id == lift(data.id)).updateValue(lift(data)).returning(v => Some(v)))

  def delete(id: Person.Id): Option[Person] =
    val ctx = connect
    import ctx._

    run(schema.filter(_.id == lift(id)).delete.returning(v => Some(v)))

end PersonRepository
