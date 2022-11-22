class Buy: Weapons() {
    override var bomb: Boolean = false
    override var counterTerrorists: Int = 5
    override var terrorists: Int = 5
    override var health: Int = 100
    override var timer: Int = 115
    //
    private var categoryInput = 0
    private var typeInput = 0
    private var categoryChoice = mapOf("" to 0)
    override var weapons: MutableList<String> = arrayListOf()
    private var i = 0
    override var armor = 0

    // --TO DO--
    override var ctBalance: Int = 800
    override var tBalance: Int = 800

    var utility: MutableList<String> = arrayListOf()

    // Buy menu
    fun read(n: Int): MutableList<String> {
        while (i < n) {
            categoryInput = (1..6).random()
            when (categoryInput) {
                1 -> categoryChoice = ctPistol
                2 -> categoryChoice = ctHeavy
                3 -> categoryChoice = ctSmg
                4 -> categoryChoice = ctRifle
                5 -> categoryChoice = ctGrenade
                6 -> categoryChoice = ctEquipment
            }
            typeInput = when (categoryInput) {
                4 -> {
                    (1..6).random()
                }

                6 -> {
                    (1..4).random()
                }

                else -> {
                    (1..5).random()
                }
            }
            when (typeInput) {
                // the specific model of a category
                1 -> println("Weapon/Utility: ${categoryChoice.filterValues { it == 1 }.keys}")
                2 -> println("Weapon/Utility: ${categoryChoice.filterValues { it == 2 }.keys}")
                3 -> println("Weapon/Utility: ${categoryChoice.filterValues { it == 3 }.keys}")
                4 -> println("Weapon/Utility: ${categoryChoice.filterValues { it == 4 }.keys}")
                5 -> println("Weapon/Utility: ${categoryChoice.filterValues { it == 5 }.keys}")
                6 -> println("Weapon/Utility: ${categoryChoice.filterValues { it == 6 }.keys}")
            }
            if ((categoryInput != 5) && (categoryInput != 6)) {
                 weapons += categoryChoice.filterValues { it == typeInput }.keys.toString()
            }
            else {
                when (categoryInput) {
                    5 -> utility += categoryChoice.filterValues { it == typeInput }.keys.toString()
                    6 -> utility += categoryChoice.filterValues { it == typeInput }.keys.toString()
                }
            }
            if ((categoryChoice == ctEquipment) && (typeInput == 1)) {
                armor = 100
            } else if ((categoryChoice == ctEquipment) && (typeInput == 2)) {
                armor = 200
            }
            when (categoryInput) {
                1 -> when (typeInput) {
                    1 -> println("Ammo: ${ammo.filterValues { it == 1 }.keys}")
                    2 -> println("Ammo: ${ammo.filterValues { it == 2 }.keys}")
                    3 -> println("Ammo: ${ammo.filterValues { it == 3 }.keys}")
                    4 -> println("Ammo: ${ammo.filterValues { it == 4 }.keys}")
                    5 -> println("Ammo: ${ammo.filterValues { it == 5 }.keys}")
                }

                2 -> when (typeInput) {
                    1 -> println("Ammo: ${ammo.filterValues { it == 6 }.keys}")
                    2 -> println("Ammo: ${ammo.filterValues { it == 7 }.keys}")
                    3 -> println("Ammo: ${ammo.filterValues { it == 8 }.keys}")
                    4 -> println("Ammo: ${ammo.filterValues { it == 9 }.keys}")
                    5 -> println("Ammo: ${ammo.filterValues { it == 10 }.keys}")
                }

                3 -> when (typeInput) {
                    1 -> println("Ammo: ${ammo.filterValues { it == 11 }.keys}")
                    2 -> println("Ammo: ${ammo.filterValues { it == 12 }.keys}")
                    3 -> println("Ammo: ${ammo.filterValues { it == 13 }.keys}")
                    4 -> println("Ammo: ${ammo.filterValues { it == 14 }.keys}")
                    5 -> println("Ammo: ${ammo.filterValues { it == 15 }.keys}")
                }

                4 -> when (typeInput) {
                    1 -> println("Ammo: ${ammo.filterValues { it == 16 }.keys}")
                    2 -> println("Ammo: ${ammo.filterValues { it == 17 }.keys}")
                    3 -> println("Ammo: ${ammo.filterValues { it == 18 }.keys}")
                    4 -> println("Ammo: ${ammo.filterValues { it == 19 }.keys}")
                    5 -> println("Ammo: ${ammo.filterValues { it == 20 }.keys}")
                    6 -> println("Ammo: ${ammo.filterValues { it == 21 }.keys}")
                }
            }
            i++
        }
        return weapons
    }

    fun ecoRound(): MutableList<String> {
        var i = 0
        println("\nEco buy:")
        while (i < 5) {
            val random = (1..4).random()
            println("Weapon/Utility: ${ctPistol.filterValues { it == random }.keys}")
            weapons += ctPistol.filterValues { it == random }.keys.toString()
            i++
        }
        println("\nOther team buy:")
        read(5)
        return weapons
    }
}