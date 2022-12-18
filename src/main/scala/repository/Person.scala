package protoquill.sandbox.repository

import io.getquill.*
import protoquill.sandbox.entity.Person

class PersonRepository(using db: Database) extends CustomEncoding:

  import db.ctx.*

  inline def schema = quote {
    querySchema[Person]("person",
      _.name.firstName -> "first_name",
      _.name.lastName  -> "last_name"
    )
  }

  def findAll: Seq[Person] =
    run(schema)

  def get(id: Person.Id): Option[Person] =
    run(schema.filter(_.id == lift(id))).headOption

  def add(data: Person): Person.Id =
    run(schema.insertValue(lift(data)).returningGenerated(_.id))

  def update(data: Person): Option[Person] =
    for
      old <- run(schema.filter(_.id == lift(data.id))).headOption
      _    = run(schema.filter(_.id == lift(data.id)).updateValue(lift(data)))
    yield
      old

  def delete(id: Person.Id): Option[Person] =
    for
      old <- run(schema.filter(_.id == lift(id))).headOption
      _    = run(schema.filter(_.id == lift(id)).delete)
    yield
      old

end PersonRepository
