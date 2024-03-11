package zoo.animal

data class Horse(
    override val id: Int,
    override val height: Int
) : Animal(), SoundMaker {
    override fun makeSound() = "Neigh-neigh!"
}