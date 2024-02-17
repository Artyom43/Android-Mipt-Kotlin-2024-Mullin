package zoo

import zoo.animal.*
import java.util.*

class Zoo() {
    private val animals = TreeSet<Animal>()
    private val supervisorToAnimal = HashMap<Supervisor, MutableList<Animal>>()

    constructor(list: List<Animal>) : this() {
        animals.addAll(list)
    }

    fun addAnimal(animal: Animal) = animals.add(animal)

    fun removeAnimalById(id: Int) = animals.removeIf { it.id == id }

    fun findAnimalById(id: Int) = animals.find { it.id == id }

    fun addSupervisorToAnimal(animal: Animal, supervisor: Supervisor) = supervisorToAnimal.putOrAdd(supervisor, animal)

    fun findAnimalBySupervisorsId(id: Int) = supervisorToAnimal.filterKeys { it.id == id }.values.toList()[0]

    fun findAnimalBySupervisorsName(name: String) = supervisorToAnimal.filterKeys { it.name == name }.values.toList()

    fun findEnoughHighAnimal (height: Int) = animals.filter { it.height > height }

    fun findSoundMakers() = animals.filterIsInstance<SoundMaker>().map { it as Animal }

    fun findAnimalByType(type: AnimalType) = when(type) {
        AnimalType.CAT -> animals.filterIsInstance<Cat>()
        AnimalType.DOG -> animals.filterIsInstance<Dog>()
        AnimalType.HIPPOPOTAMUS -> animals.filterIsInstance<Hippopotamus>()
        AnimalType.HORSE -> animals.filterIsInstance<Horse>()
        AnimalType.FISH -> animals.filterIsInstance<Fish>()
    }

    enum class AnimalType {
        FISH, CAT, DOG, HIPPOPOTAMUS, HORSE
    }
}