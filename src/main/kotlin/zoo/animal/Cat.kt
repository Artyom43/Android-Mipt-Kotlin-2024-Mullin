package zoo.animal

data class Cat(
    override val id: Int,
    override val height: Int
) : Animal(), SoundMaker