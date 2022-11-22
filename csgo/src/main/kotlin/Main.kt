fun main() {
    fun simulation() {
        var matchCount = 1

        while (true) {
            println("Match nr: $matchCount")
            var j = 1
            var ctRounds = 0; var tRounds = 0; var ctStreak = 0; var tStreak = 0

            while (j < 31) {
                println("New Round started, round nr: $j\n")
                val obj1 = Player(
                    mutableListOf("John", "Chad", "Brian", "Cory", "Finn"),
                    mutableListOf("Arnold", "Kyle", "Ringo", "Rip", "Zach")
                )
                val weapons = Buy()

                // Eco statement
                if (ctStreak == 3) {
                    println("CounterTerrorists lost 3 round in a row: $ctRounds VS $tRounds \nThey have an Economic round")
                    obj1.dead(weapons.ecoRound(), true, 0, true)
                } else if (tStreak == 3) {
                    println("Terrorists lost 3 round in a row: $tRounds VS $ctRounds \nThey have an Economic round")
                    obj1.dead(weapons.ecoRound(), true, 0, true)
                } else {
                    obj1.dead(weapons.read(10), true, 200, false)
                }

                // Win situation
                if (obj1.plant(true) || obj1.terrorists > obj1.counterTerrorists
                    && obj1.counterTerrorists == 0) {
                        println("\n\tROUND IS OVER")
                        println("\n\tTerrorists Win!")
                        tStreak = 0
                        tRounds += 1
                        ctStreak += 1
                } else if (!obj1.plant(true) || obj1.counterTerrorists > obj1.terrorists) {
                    println("\n\tROUND IS OVER")
                    println("\n\tCounterTerrorists Win!")
                    ctStreak = 0
                    ctRounds += 1
                    tStreak += 1
                }

                // Round stats
                println("\nRemaining CounterTerrorists: ${obj1.counterTerrorists}")
                println("Remaining Terrorists: ${obj1.terrorists}")
                println("\n\t CT won: $ctRounds rounds; T won: $tRounds rounds\n")

                // Match outcome possibilities
                if (ctRounds == 16 && ctRounds > tRounds) {
                    println("\nCounterTerrorists won the match")
                    matchCount += 1
                    break
                } else if (tRounds == 16 && tRounds > ctRounds) {
                    println("\nTerrorists won the match")
                    matchCount += 1
                    break
                } else if (ctRounds == 15 && tRounds == 15) {
                    println("\nDraw")
                    matchCount += 1
                    break
                }
                j++
            }
            // Continue simulation
            println("Next match?(y/n)")
            when (readln().lowercase()) {
                "y" -> {
                    continue
                }
                "n" -> {
                    break
                }
            }
        }
    }

    simulation()
}