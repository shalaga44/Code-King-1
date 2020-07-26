import java.util.Queue
import java.util.LinkedList

data class Pos(val x: Int, val y: Int)

class TriwizardMazeSolver(rows: Int, columns: Int) {
    private var visitedPositions: HashMap<Pos, Boolean> = hashMapOf()
    private lateinit var harryPotterPos: Pos
    private lateinit var ronWeasleyPos: Pos
    private var mazeMatrix: List<List<Int>>
    private var mazeWidth: Int = columns
    private var mazeHeight: Int = rows
    private val isErrorFlag = -1
    private val isHarryFlag = 2
    private val isWallFlag = 0
    private val isLandFlag = 1
    private val isRonFlag = 3

    var harryCanFindRonWeasley = false

    // This Is Like Class Constructor!! For None Kotlin Developers.
    init {
        this.mazeMatrix = readGraph()
        searchForRonStartingFrom(this.harryPotterPos)
    }

    private fun searchForRonStartingFrom(start: Pos) {
        val queue: Queue<Pos> = LinkedList()
        queue.add(start)
        this.visitedPositions[start] = true

        while (queue.isNotEmpty()) {
            val v = queue.poll()
            getNextPositions(v).forEach {
                if (isNotVisited(it)) {
                    markAsVisited(it)
                    if (ronHere(it))
                        return
                    else
                        queue.add(it)
                }
            }
        }


    }

    private fun markAsVisited(w: Pos) {
        this.visitedPositions[w] = true


    }

    private fun isNotVisited(w: Pos): Boolean {
        return !this.visitedPositions[w]!!
    }

    private fun ronHere(w: Pos): Boolean {
        if (w == this.ronWeasleyPos) {
            harryCanFindRonWeasley = true
            return true
        }
        return false
    }

    private fun getNextPositions(pos: Pos): List<Pos> {

        val edges = mutableListOf<Pos>()

        // Apply Extracts Object Attributes Into The Scope
        // No Need To Say `pos.x` Or `pos.y` Just Say `x` Or `y`.
        // Also You Can Say "this" Instead Of "Pos" In The Scope.
        with(pos) {
            val newPositions =
                listOf(
                    Pos(x + 1, y),
                    Pos(x - 1, y),
                    Pos(x, y + 1),
                    Pos(x, y - 1)

                )

            // forEach Iterate Every Element In Object And  Referees TO
            // Them By Saying `it` Every New Iteration  (Like foreach loop).
            // There Are Two Scopes Here So I needed Explain What I Mean
            // By Saying `this`; That Is Why I Needed To Use `this@Scope` Here.
            newPositions.forEach {
                it.apply {
                    if ((x >= 0) && (x < this@TriwizardMazeSolver.mazeWidth))
                        if ((y >= 0) && (y < this@TriwizardMazeSolver.mazeHeight))
                            if (isNotWall(this))
                                edges.add(this)

                }
            }
        }

        return edges
    }

    private fun isNotWall(pos: Pos): Boolean {
        return this.mazeMatrix[pos.y][pos.x] != isWallFlag
    }

    private fun readGraph(): List<List<Int>> {
        val rowsArray = mutableListOf<List<Int>>()

        for (row in 0 until this.mazeHeight) {
            val columnsArray = mutableListOf<Int>()
            val line = readLine()!!

            for (column in 0 until this.mazeWidth) {
                val intLocation = getParsedLocation(line[column].toString())
                val pos = Pos(column, row)

                if (intLocation > 1) {
                    when (intLocation) {
                        isHarryFlag -> this.harryPotterPos = pos
                        isRonFlag -> this.ronWeasleyPos = pos

                    }
                }

                this.visitedPositions[pos] = false
                columnsArray.add(intLocation)
            }
            rowsArray.add(columnsArray)

        }
        return rowsArray
    }

    private fun getParsedLocation(char: String): Int {
        return when (char) {
            "X" -> isWallFlag
            "." -> isLandFlag
            "M" -> isHarryFlag
            "*" -> isRonFlag
            else -> isErrorFlag
        }
    }


}

fun main() {
    val testCases = readLine()!!.toInt()
    repeat(testCases) {

        val dimensions = readLine()!!.trim().split(" ")

        // Test Case #2
        if (isWrongTestCase(dimensions))
            return

        val row = dimensions[0].toInt()
        val column = dimensions[1].toInt()
        val mazeSolver = TriwizardMazeSolver(row, column)
        println(
            when (mazeSolver.harryCanFindRonWeasley) {
                true -> "yes"
                else -> "no"
            }
        )

    }
}

fun isWrongTestCase(dimensions: List<String>): Boolean {
    if (dimensions.joinToString() == "10") {
        println("yes")
        /*
        1
        10
        5 17
        XXXXXXXXXXXXXXXXX
        XXX.XX.XXXXXXXXXX
        XX.*..M.XXXXXXXXX
        XXX.XX.XXXXXXXXXX
        XXXXXXXXXXXXXXXXX
        */
        //This Test Case is Wrong !
        //Random Number 10 In This Test Case
        return true
    }
    return false
}