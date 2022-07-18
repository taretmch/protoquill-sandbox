import protoquill.sandbox.entity.Person
import protoquill.sandbox.repository.PersonRepository

object Sample:

  val p1 = Person(Person.FirstName("tomoki"), Person.LastName("mizogami"), Person.Age(24), Person.Gender.MALE)

  val repository = new PersonRepository

  @main def getPerson: Unit =
    val persons = repository.findAll
    persons.foreach(println _)

  @main def insertPerson: Unit =
    val insertedId = repository.add(p1)
    println("id of inserted value is " + insertedId)

  @main def updatePerson: Unit =
    val persons = repository.findAll
    val maxId   = Person.Id(persons.map(_.id.toLong).max)
    for
      person <- repository.get(maxId)
    yield
      val data = person.copy(age = person.age.increment)
      repository.update(data)
      println("before: " + person)
      println("after:  " + data)

  @main def deletePerson: Unit =
    val persons = repository.findAll
    val maxId   = Person.Id(persons.map(_.id.toLong).max)
    for
      deleted <- repository.delete(maxId)
    yield
      println("deleted person: " + deleted)
