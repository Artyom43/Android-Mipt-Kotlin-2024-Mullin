package zoo.animal

class Horse(
    override val id: Int,
    override val height: Int
) : Animal(), SoundMaker