package zoo.animal

data class Hippopotamus(
    override val id: Int,
    override val height: Int
) : Animal(), SoundMaker {
    override fun makeSound() = "Hippo_noise.mp3"
}