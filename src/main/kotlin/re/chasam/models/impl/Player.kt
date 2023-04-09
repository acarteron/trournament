package re.chasam.models.impl

import kotlinx.serialization.Serializable

@Serializable
class Player (var name: String = "", var score: Int = 0, var rank : Int = 0) : Comparable<Player>{
    override fun compareTo(other: Player): Int {
        if (this.score == other.score)
            return 0
        return this.score - other.score
    }
    override fun toString(): String {
        return "$name $score"
    }
    override fun equals(other: Any?): Boolean {
        if (other is Player)
            return (name == other.name)
        return false
    }
    override fun hashCode(): Int {
        return name.hashCode()
    }
}
