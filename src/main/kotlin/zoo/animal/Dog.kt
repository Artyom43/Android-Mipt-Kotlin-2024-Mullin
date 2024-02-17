package zoo.animal

data class Dog(
    override val id: Int,
    override val height: Int
) : Animal(), SoundMaker