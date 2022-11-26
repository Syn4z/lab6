package controller

import model.Buy
import model.Player
import view.CsgoView

class CsgoController {
    fun simulation() {
        var matchCount = 1

        while (true) {
            val printStatement = CsgoView()
            printStatement.printMatchStart(matchCount)
            var j = 1
            var ctRounds = 0; var tRounds = 0; var ctStreak = 0; var tStreak = 0

            while (j < 31) {
                printStatement.printRoundStart(j)
                val entities = Player(
                    mutableListOf("John", "Chad", "Brian", "Cory", "Finn"),
                    mutableListOf("Arnold", "Kyle", "Ringo", "Rip", "Zach")
                )
                val weapons = Buy()

                // Eco statement
                if (ctStreak == 3) {
                    printStatement.ecoRound(ctRounds, tRounds,"ct")
                    entities.dead(weapons.ecoRound(), true, 0, true)
                } else if (tStreak == 3) {
                    printStatement.ecoRound(ctRounds, tRounds, "t")
                    entities.dead(weapons.ecoRound(), true, 0, true)
                } else {
                    entities.dead(weapons.read(10), true, 200, false)
                }

                // Win situation
                if (entities.plant(true) || entities.terrorists > entities.counterTerrorists
                    && entities.counterTerrorists == 0) {
                        printStatement.roundOver("t")
                        tStreak = 0
                        tRounds += 1
                        ctStreak += 1
                } else if (!entities.plant(true) || entities.counterTerrorists > entities.terrorists) {
                    printStatement.roundOver("ct")
                    ctStreak = 0
                    ctRounds += 1
                    tStreak += 1
                }

                // Round stats
                val ct = entities.counterTerrorists
                val t = entities.terrorists
                printStatement.roundStats(ct, t, ctRounds, tRounds)

                // Match outcome possibilities
                if (ctRounds == 16 && ctRounds > tRounds) {
                    printStatement.matchOver("ct")
                    matchCount += 1
                    break
                } else if (tRounds == 16 && tRounds > ctRounds) {
                    printStatement.matchOver("t")
                    matchCount += 1
                    break
                } else if (ctRounds == 15 && tRounds == 15) {
                    printStatement.matchOver("draw")
                    matchCount += 1
                    break
                }
                j++
            }
            // Continue simulation
            printStatement.matchOver("repeat")
            when (readln().lowercase()) {
                "y" -> {
                    continue
                }
                "n" -> {
                    break
                }
                else -> break
            }
        }
    }
}