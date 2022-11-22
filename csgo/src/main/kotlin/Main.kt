
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
                val utility = weapons.utility

                var ctEco = false; var tEco = false
                if (ctStreak == 3) {
                    //result = "CounterTerrorists"
                    ctEco = true
                } else if (tStreak == 3) {
                    //result = "CounterTerrorists"
                    tEco = true
                }

                if (ctEco) {
                    println("CounterTerrorists lost 3 round in a row: $ctRounds VS $tRounds \nThey have an Economic round")
                    obj1.kill(weapons.ecoRound(), true, 0, ctEco)
                } else if (tEco) {
                    println("Terrorists lost 3 round in a row: $tRounds VS $ctRounds \nThey have an Economic round")
                    obj1.kill(weapons.ecoRound(), true, 0, tEco)
                } else {
                    obj1.kill(weapons.read(10), true, 200, ctEco)
                }
                if (obj1.plant(true, utility)) {
                    println("\n\tROUND IS OVER")
                    println("\n\tTerrorists Win!")
                    tStreak = 0
                    tRounds += 1
                    ctStreak += 1
                } else {
                    println("\n\tROUND IS OVER")
                    println("\n\tCounterTerrorists Win!")
                    ctStreak = 0
                    ctRounds += 1
                    tStreak += 1
                }

                println("\nRemaining CounterTerrorists: ${obj1.counterTerrorists}")
                println("Remaining Terrorists: ${obj1.terrorists}")
                println("\n\t CT won: $ctRounds rounds; T won: $tRounds rounds\n")

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