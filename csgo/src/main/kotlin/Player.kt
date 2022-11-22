class Player(override val ctNames: MutableList<String>,
             override val tNames: MutableList<String>
): Game, Team {
    override val ctId: String = "(CT)"
    override val tId: String = "(T)"
    override var armor: Int = 0
    override var health: Int = 100
    override var bomb: Boolean = false
    override var counterTerrorists: Int = 5
    override var terrorists: Int = 5

    override var ctBalance: Int = 800
    override var tBalance: Int = 800

    private var shuffledCtNames = ctNames
    private var shuffledTNames = tNames

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

    private fun killBar(name: MutableList<String>, whoKilledName: MutableList<String>,
                        wasKilled: String, whoKilled: String, weapon: MutableList<String>, suicide: Boolean): MutableList<String> {
        name.shuffled().toMutableList()
        if (suicide) {
            println("$wasKilled${name[0]} had committed suicide")
        }
        else {
            println("$wasKilled${name[0]} was killed by " +
                    "$whoKilled${whoKilledName[(0 until whoKilledName.size).random()]}" +
                    " with ${weapon[0]}")
        }
        name.removeAt(0)
        if (name == shuffledCtNames) {
            counterTerrorists -= 1
            if (!suicide) {
                ctBalance += 500
            }
            else {
                tBalance += 500
            }
        }
        else if (name == shuffledTNames) {
            terrorists -= 1
            if (!suicide) {
                tBalance += 500
            }
            else {
                ctBalance += 500
            }
        }
        resetStats()

        return name
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
                // CT dead
                killBar(shuffledCtNames, shuffledTNames, ctId, tId, shuffledWeapon, false)
            }
            else if ((health <= 0) && (choice == 2)) {
                // T dead
                killBar(shuffledTNames, shuffledCtNames, tId, ctId, shuffledWeapon, false)
            }
            else if (suicide == 2 && (health <= 0) && !head) {
                // CT suicide
                killBar(shuffledCtNames, shuffledTNames, ctId, tId, shuffledWeapon, true)
            }
            else if (suicide == 4 && (health <= 0) && !head) {
                // T suicide
                killBar(shuffledTNames, shuffledCtNames, tId, ctId, shuffledWeapon, true)
            }
        }
    }

    // function to plant the bomb
    fun plant(isPlant : Boolean, weapons: MutableList<String>): Boolean {
        var result = false
        if (isPlant) {
            val planted = (0..1).random()
            if (planted == 1) {
                bomb = true
                println("\nBomb has been planted!")
                tBalance += 100
                val explode = (0..1).random()
                if (explode == 0) {
                    println("\nBomb exploded!")
                    result = true
                } else if (explode == 1 && "[Defuse Kit]" in weapons) {
                    println("\nCounterTerrorists defused the bomb")
                    ctBalance += 500
                } else {
                    println("\nBomb exploded!")
                    tBalance += 500
                    result = true
                }
            }
        }
        return result
    }

    private fun resetStats() {
        health = 100
        armor = 0
    }
}