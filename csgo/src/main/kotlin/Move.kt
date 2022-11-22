interface Move {
    // chickens also can move
    var location: String

    val moveFront: String // "how many meters"
    val moveBack: String
    val moveRight: String
    val moveLeft: String

    val crouch: String  // when holded
    val jump: String
    val sneak: String
}