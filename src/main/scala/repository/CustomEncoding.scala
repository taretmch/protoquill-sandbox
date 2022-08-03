package protoquill.sandbox.repository

import io.getquill._
import protoquill.sandbox.entity.Person

trait CustomEncoding:

  given MappedEncoding[Person.Gender, Short](_.code)
  given MappedEncoding[Short, Person.Gender](Person.Gender.apply _)
  given MappedEncoding[Person.Id, Long](_.toLong)
  given MappedEncoding[Long, Person.Id](Person.Id.apply _)
  given MappedEncoding[Person.Age, Int](_.toInt)
  given MappedEncoding[Int, Person.Age](Person.Age.apply _)
  given MappedEncoding[Person.FirstName, String](_.toString)
  given MappedEncoding[String, Person.FirstName](Person.FirstName.apply _)
  given MappedEncoding[Person.LastName, String](_.toString)
  given MappedEncoding[String, Person.LastName](Person.LastName.apply _)

end CustomEncoding
