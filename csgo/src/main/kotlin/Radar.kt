interface Radar {
    var map : String
    // function to display the map
    private fun display() {
        map = "1"
    }

    // adjust the map where the player goes
    fun adjust() {
        map = "2"
    }
}