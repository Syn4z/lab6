package controller

import model.CsgoModel
import view.CsgoView

class CsgoController {
    fun simulation() {
        var matchCount = 1

        while (true) {
            val printStatement = CsgoView()
            val rounds = CsgoModel()

            printStatement.matchStart(matchCount)
            var j = 1

            while (j < 31) {
                printStatement.roundStart(j)
                val entities = CsgoModel().entities()
                val weapons = CsgoModel().weapons()
                val bomb = CsgoModel().bomb(entities)

                // Eco statement
                if (rounds.ctStreak == 3) {
                    printStatement.ecoRound(rounds.ctRounds, rounds.tRounds,"ct")
                    entities.dead(weapons.ecoRound(), true, 0, true)
                } else if (rounds.tStreak == 3) {
                    printStatement.ecoRound(rounds.ctRounds, rounds.tRounds, "t")
                    entities.dead(weapons.ecoRound(), true, 0, true)
                } else {
                    entities.dead(weapons.read(10), true, 200, false)
                }

                // Win situation
                if (bomb.plant(true) || entities.terrorists > entities.counterTerrorists
                    && entities.counterTerrorists == 0) {
                        printStatement.roundOver("t")
                        rounds.tStreak = 0
                        rounds.addRounds("t")
                } else if (!bomb.plant(true) || entities.counterTerrorists > entities.terrorists) {
                    printStatement.roundOver("ct")
                    rounds.ctStreak = 0
                    rounds.addRounds("ct")
                }

                // Round stats
                printStatement.roundStats(entities.counterTerrorists, entities.terrorists, rounds.ctRounds, rounds.tRounds)

                // Match outcome possibilities
                if (rounds.ctRounds == 16 && rounds.ctRounds > rounds.tRounds) {
                    printStatement.matchOver("ct")
                    matchCount += 1
                    break
                } else if (rounds.tRounds == 16 && rounds.tRounds > rounds.ctRounds) {
                    printStatement.matchOver("t")
                    matchCount += 1
                    break
                } else if (rounds.ctRounds == 15 && rounds.tRounds == 15) {
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