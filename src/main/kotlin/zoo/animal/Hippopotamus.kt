package zoo.animal

data class Hippopotamus(
    override val id: Int,
    override val height: Int
) : Animal(), SoundMaker