package zoo

import zoo.animal.Animal
import zoo.animal.SoundMaker
import java.util.*

class Zoo() {

    val animals = TreeSet<Animal> { lhsAnimal, rhsAnimal ->
        when {
            lhsAnimal.height < rhsAnimal.height -> -1
            lhsAnimal.height == rhsAnimal.height -> 0
            lhsAnimal.height > rhsAnimal.height -> 1
            else -> throw RuntimeException("stub")
        }
    }

    private val supervisorToAnimal = HashMap<Supervisor, MutableList<Animal>>()

    constructor(list: List<Animal>) : this() {
        animals.addAll(list)
    }

    fun addAnimal(animal: Animal) = animals.add(animal)

    fun removeAnimalById(id: Int) {
        var result = false
        result = result or animals.removeIf { it.id == id }
        result = result or (supervisorToAnimal[findSupervisorByAnimalId(id)]?.removeIf { it.id == id } == true)
        if (!result) throw IllegalArgumentException("No animal with id: $id")
    }

    fun findAnimalById(id: Int) =
        animals.find { it.id == id } ?: throw IllegalArgumentException("No animal with id: $id")

    fun addSupervisorToAnimal(animal: Animal, supervisor: Supervisor) = supervisorToAnimal.putOrAdd(supervisor, animal)

    fun findAnimalBySupervisorsId(id: Int): List<Animal> {
        for (entry in supervisorToAnimal) {
            if (entry.key.id == id) {
                return entry.value
            }
        }
        throw IllegalArgumentException("No supervisor with id: $id")
    }

    fun findAnimalBySupervisorsName(name: String): List<Animal> {
        for (entry in supervisorToAnimal) {
            if (entry.key.name == name) {
                return entry.value
            }
        }
        throw IllegalArgumentException("No supervisor with name: $name")
    }

    fun findEnoughHighAnimals(height: Int): Set<Animal> {
        val resultSet = emptySet<Animal>().toMutableSet()
        animals.descendingSet().forEach {
            if (it.height >= height) {
                resultSet.add(it)
            } else {
                return resultSet
            }
        }
        return resultSet
    }

    fun findSoundMakers() = animals.filterIsInstance<SoundMaker>().map { it as Animal }

    inline fun <reified T : Animal> findAnimalByType() = animals.filterIsInstance(T::class.java)
    private fun findSupervisorByAnimalId(id: Int) = supervisorToAnimal.filterValues { list ->
        list.any { it.id == id }
    }.keys.first()
}