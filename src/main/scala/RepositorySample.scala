import entity.Person
import repository.{ Database, PersonRepository }

object RepositorySample:

  val db               = new Database
  val personRepository = new PersonRepository(using db)

  @main def getPerson: Unit =
    val persons = personRepository.findAll
    persons.foreach(println _)

  @main def insertPerson: Unit =
    val data = Person(Person.FirstName("tomoki"), Person.LastName("mizogami"), Person.Age(25), Person.Gender.MALE)
    val insertedId = personRepository.add(data)
    println("id of inserted value is " + insertedId)

  @main def updatePerson: Unit =
    val persons = personRepository.findAll
    val maxId   = Person.Id(persons.map(_.id.toLong).max)
    for
      person <- personRepository.get(maxId)
    yield
      val data = person.copy(age = person.age.increment)
      personRepository.update(data)
      println("before: " + person)
      println("after:  " + data)

  @main def deletePerson: Unit =
    val persons = personRepository.findAll
    val maxId   = Person.Id(persons.map(_.id.toLong).max)
    for
      deleted <- personRepository.delete(maxId)
    yield
      println("deleted person: " + deleted)
