package protoquill.sandbox.repository

import io.getquill._
import protoquill.sandbox.entity.Person

class PersonRepository extends Repository[Person]:

  inline def schema = quote {
    querySchema[Person]("person",
      _.name.firstName -> "first_name",
      _.name.lastName  -> "last_name"
    )
  }

  def findAll: Seq[Person] =
    withSession { ctx ?=>
      import ctx._

      run(schema)
    }

  def get(id: Person.Id): Option[Person] =
    withSession { ctx ?=>
      import ctx._

      run(schema.filter(_.id == lift(id))).headOption
    }

  def add(data: Person): Person.Id =
    withSession { ctx ?=>
      import ctx._

      run(schema.insertValue(lift(data)).returningGenerated(_.id))
    }

  def update(data: Person): Option[Person] =
    withSession { ctx ?=>
      import ctx._

      for
        old <- run(schema.filter(_.id == lift(data.id))).headOption
        _    = run(schema.filter(_.id == lift(data.id)).updateValue(lift(data)))
      yield
        old
    }

  def delete(id: Person.Id): Option[Person] =
    withSession { ctx ?=>
      import ctx._

      for
        old <- run(schema.filter(_.id == lift(id))).headOption
        _    = run(schema.filter(_.id == lift(id)).delete)
      yield
        old
    }

end PersonRepository
