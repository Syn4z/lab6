package model

class CsgoModel {
    var ctRounds = 0
    var tRounds = 0
    var ctStreak = 0
    var tStreak = 0

    fun addRounds(team: String) {
        if (team == "ct") {
            ctRounds++
            tStreak++
        }
        else if (team == "t") {
            tRounds++
            ctStreak++
        }
    }

    fun entities(): Player {
        return Player(
            mutableListOf("John", "Chad", "Brian", "Cory", "Finn"),
            mutableListOf("Arnold", "Kyle", "Ringo", "Rip", "Zach")
        )
    }

    fun weapons(): Buy {
        return Buy()
    }

    fun bomb(entities: Player): Bomb {
        return Bomb(entities.ctNames, entities.tNames)
    }
}