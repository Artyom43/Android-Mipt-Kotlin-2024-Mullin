package zoo.animal

data class Fish(
    override val id: Int,
    override val height: Int
) : Animal()