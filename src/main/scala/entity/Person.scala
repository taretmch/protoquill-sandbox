package protoquill.sandbox.entity

import Person.*
case class Person(
  id:     Id,
  name:   Name,
  age:    Age,
  gender: Gender
)

object Person:

  opaque type Id        = Option[Long]
  opaque type Age       = Int
  opaque type FirstName = String
  opaque type LastName  = String
  case class Name(firstName: FirstName, lastName: LastName)
  enum Gender(val code: Short):
    case MALE   extends Gender(code = 1)
    case FEMALE extends Gender(code = 2)
    case OTHER  extends Gender(code = 3)
  object Gender:
    def apply(code: Short): Gender =
      values.find(_.code == code).get

  object Id:
    def apply(id: Long): Id = Some(id)
    def apply(idOpt: Option[Long]): Id = idOpt
    def init: Id = None
    extension (id: Id)
      def toLong: Long = id.get
      def toOption: Option[Long] = id

  object FirstName:
    def apply(fn: String): FirstName = fn
    extension (fn: FirstName)
      def toString: String = fn

  object LastName:
    def apply(ln: String): LastName = ln
    extension (ln: LastName)
      def toString: String = ln

  object Age:
    def apply(age: Int): Age = if (age >= 0) age else throw new IllegalArgumentException
    extension (age: Age)
      def increment: Age = age + 1
      def toInt: Int = age

  def apply(name: Name, age: Age, gender: Gender): Person =
    Person(Id.init, name, age, gender)

  def apply(fn: FirstName, ln: LastName, age: Age, gender: Gender): Person =
    Person(Id.init, Name(fn, ln), age, gender)

end Person
