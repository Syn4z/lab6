
class Player(override val ctNames: MutableList<String>,
             override val tNames: MutableList<String>
): Game, Team, Move {
    override val ctId: String = "(CT)"
    override val tId: String = "(T)"
    override var timer: Int = 115
    override var armor: Int = 0
    override var health: Int = 100
    override var bomb: Boolean = false
    override var counterTerrorists: Int = 5
    override var terrorists: Int = 5
    override var location: String = ""
    //var playerCrosshair: String = ""
    //private val crosshair = mapOf("default" to 1, "cross" to 2, "square" to 3, "circle" to 4, "dot" to 5)
    //var playerPosition = listOf(0,0)
    override val moveFront: String = ""
    override val moveBack: String = ""
    override val moveRight: String = ""
    override val moveLeft: String = ""
    override val crouch: String = ""
    override val jump: String = ""
    override val sneak: String = ""

    override var ctBalance: Int = 800
    override var tBalance: Int = 800

    private var shuffledCtNames = ctNames
    private var shuffledTNames = tNames
    /*
    fun showHealth() {
        println("The health is : $health")
    }
     */
    /*
    fun showArmor() {
        println("The armor is : $armor")
    }
     */
    /*
    fun showCrosshair() {
        println("Choose your crosshair: ")
        val playerInput = readln()
        when (playerInput.toInt()) {
            1 -> playerCrosshair = crosshair.filterValues { it == 1 }.keys.toString()
            2 -> playerCrosshair = crosshair.filterValues { it == 2 }.keys.toString()
            3 -> playerCrosshair = crosshair.filterValues { it == 3 }.keys.toString()
            4 -> playerCrosshair = crosshair.filterValues { it == 4 }.keys.toString()
        }
    }
     */

    private fun shot(armorInput: Int) {
        armor = armorInput
        val lowDamage = listOf(10, 25, 50, 75)
        val highDamage = listOf(10, 25, 50, 75, 100)
        println("Shot!")
        if (health > 0) {
            when (armor) {
                200 -> {
                    health -= lowDamage.random()
                    armor -= lowDamage.random()
                }
                100 -> {
                    health -= highDamage.random()
                    armor -= highDamage.random()
                }
                else -> {
                    health -= highDamage.random()
                }
            }
        }
    }

    private fun randomShoot(armorInput: Int): Boolean {
        var result = false
        val random = (0..1).random()
        val headshot = (0..9).random()
        if (random == 1) {
            if (headshot == 1 && armor <= 100) {
                println("HeadShot!")
                health -= 100
                armor -= 100
                result = true
            } else if (headshot == 1 && armor == 200) {
                println("HeadShot! Helmet broke!")
                health -= 50
                armor -= 100
                result = true
            } else {
                shot(armorInput)
                result = false
            }
        }
        return result
    }

    fun kill(weapon : MutableList<String>, suicideOn : Boolean, armorInput: Int, eco: Boolean) {
        println("\n")

        while ((counterTerrorists > 0) && (terrorists > 0)) {
            val shuffledWeapon: MutableList<String> = if (eco){
                weapon.slice(0..5).shuffled().toMutableList()
            } else {
                weapon.shuffled().toMutableList()
            }
            val head = randomShoot(armorInput)
            val choice = (0..2).random()
            var suicide = 0
            if (suicideOn) {
                suicide = (0..30).shuffled().last()
            }
            if ((health <= 0) && (choice == 1)) {
                shuffledCtNames = shuffledCtNames.shuffled().toMutableList()
                println("$ctId${shuffledCtNames[0]} was killed by $tId${shuffledTNames[(0 until shuffledTNames.size).random()]} with ${shuffledWeapon[0]}")
                shuffledCtNames.removeAt(0)
                counterTerrorists -= 1
                ctBalance += 100
                resetStats()
            }
            else if ((health <= 0) && (choice == 2)) {
                shuffledTNames = shuffledTNames.shuffled().toMutableList()
                println("$tId${shuffledTNames[0]} was killed by $ctId${shuffledCtNames[(0 until shuffledCtNames.size).random()]} with ${shuffledWeapon[0]}")
                shuffledTNames.removeAt(0)
                terrorists -= 1
                tBalance += 100
                resetStats()
            }
            else if (suicide == 2 && (health <= 0) && !head) {
                shuffledCtNames = shuffledCtNames.shuffled().toMutableList()
                println("$ctId${shuffledCtNames[0]} had committed suicide")
                shuffledCtNames.removeAt(0)
                counterTerrorists -= 1
                tBalance += 50
                resetStats()
            }
            else if (suicide == 4 && (health <= 0) && !head) {
                shuffledTNames = shuffledTNames.shuffled().toMutableList()
                println("$tId${shuffledTNames[0]} had committed suicide")
                shuffledTNames.removeAt(0)
                terrorists -= 1
                ctBalance += 50
                resetStats()
            }
        }
    }

    // function to drop the bomb
    /*
    private fun drop() {
        bomb = false
    }
     */

    // function to plant the bomb
    fun plant(isPlant : Boolean, weapons: MutableList<String>): Boolean {
        var result = false
        if (isPlant) {
            val planted = (0..1).random()
            if (planted == 1) {
                bomb = true
                println("\nBomb has been planted!")
                val explode = (0..1).random()
                if (explode == 0) {
                    println("\nBomb exploded!")
                    result = true
                } else if (explode == 1 && "[Defuse Kit]" in weapons) {
                    println("\nCounterTerrorists defused the bomb")
                } else {
                    println("\nBomb exploded!")
                    result = true
                }
            }
        }
        return result
    }

    // shows how many ct and t are alive or dead
    /*
    fun showTab() {
        println("CounterTerrorists: $counterTerrorists")
        println("Terrorists: $terrorists")
    }
     */

    private fun resetStats() {
        health = 100
        armor = 0
    }
}