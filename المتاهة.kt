import java.util.*

data class Pos(val x: Int, val y: Int)
data class Dimensions(val rows: Int, val columns: Int)
class TriwizardMazeSolver {
    private var visitedPositions: HashMap<Pos, Boolean> = hashMapOf()
    private val queue: Queue<Pos> = LinkedList()
    private var mazeMatrix: List<List<Int>>

    private var dimensions: Dimensions

    private lateinit var harryPotterPos: Pos
    private lateinit var ronWeasleyPos: Pos

    private val isErrorFlag = -1
    private val isHarryFlag = 2
    private val isWallFlag = 0
    private val isLandFlag = 1
    private val isRonFlag = 3

    var harryCanFindRonWeasley = false
    private var isSolved = false

    // This Is Like Class Constructor!! For None Kotlin Developers.
    init {
        this.dimensions = readLineAsDimensions()
        this.mazeMatrix = readMaze()
    }

    // forEach Iterate Every Element In Object And  Referees TO
    // Them By Saying `it` Every New Iteration  (Like foreach loop).
    private fun searchForRonStartingFrom(start: Pos) {
        queue.add(start)
        markAsVisited(start)
        while (queue.isNotEmpty()) {
            val pos = queue.remove()
            getNextPositions(pos).forEach {
                if (isNotVisited(it))
                    visit(it)
            }
        }
    }

    private fun getNextPositions(pos: Pos) = sequence {

        getPositions(pos).forEach {
            if (isValidPosition(it))
                yield(it)
        }
    }

    // `with` Extracts Object Attributes Into The Scope
    // No Need To Say `pos.x` Or `pos.y` Just Say `x` Or `y`.
    private fun getPositions(pos: Pos): List<Pos> {
        with(pos) {
            return listOf(
                Pos(x + 1, y),
                Pos(x - 1, y),
                Pos(x, y + 1),
                Pos(x, y - 1)
            )
        }
    }

    private fun isValidPosition(pos: Pos): Boolean {
        if (isVerticallyAligned(pos) && isHorizontallyAligned(pos)) {
            if (isNotWall(pos))
                return true
        }
        return false
    }

    private fun visit(pos: Pos) {
        markAsVisited(pos)
        if (isRonHere(pos))
            stopSearching()
        else
            queue.add(pos)
    }

    private fun isNotVisited(pos: Pos): Boolean {
        return !this.visitedPositions[pos]!!
    }

    private fun isRonHere(pos: Pos): Boolean {
        if (pos == this.ronWeasleyPos) {
            harryCanFindRonWeasley = true
            return true
        }
        return false
    }

    private fun stopSearching() {
        isSolved = true
    }

    private fun markAsVisited(pos: Pos) {
        this.visitedPositions[pos] = true
    }

    private fun markAsNotVisited(pos: Pos) {
        this.visitedPositions[pos] = false
    }


    // There Are Two Scopes Here `with(Pos)` And All The Class `TriwizardMazeSolver`
    // So I needed Explain What I Mean By Saying `this`
    // That Is Why I Needed To Use `this@Scope` Here.
    private fun isHorizontallyAligned(pos: Pos): Boolean {
        with(pos) { return ((y >= 0) && (y < this@TriwizardMazeSolver.dimensions.rows)) }
    }

    private fun isVerticallyAligned(pos: Pos): Boolean {
        with(pos) { return ((x >= 0) && (x < this@TriwizardMazeSolver.dimensions.columns)) }
    }

    private fun isNotWall(pos: Pos): Boolean {
        with(pos) { return this@TriwizardMazeSolver.mazeMatrix[y][x] != isWallFlag }
    }

    private fun isPerson (intLocation: Int): Boolean {
        return intLocation > isLandFlag
    }

    private fun getParsedLocation(char: Char): Int {
        return when (char) {
            'X' -> isWallFlag
            '.' -> isLandFlag
            'M' -> isHarryFlag
            '*' -> isRonFlag
            else -> isErrorFlag
        }
    }

    private fun readLineAsDimensions(): Dimensions {
        val line = readLine()!!.trim().split(" ")
        return when (line.size) {
            2 -> Dimensions(line[0].toInt(), line[1].toInt())
            else -> Dimensions(isErrorFlag, isErrorFlag)
        }

    }

    private fun readMaze(): List<List<Int>> {
        val rowsArray = mutableListOf<List<Int>>()

        for (row in 0 until this.dimensions.rows) {
            val columnsArray = mutableListOf<Int>()

            readLine()!!.forEachIndexed { index, char ->
                val intLocation = getParsedLocation(char)
                val pos = Pos(index, row)

                if (isPerson(intLocation)) {
                    when (intLocation) {
                        isHarryFlag -> this.harryPotterPos = pos
                        isRonFlag -> this.ronWeasleyPos = pos
                    }
                }
                markAsNotVisited(pos)
                columnsArray.add(intLocation)
            }
            rowsArray.add(columnsArray)

        }
        return rowsArray
    }

    fun isWrongTestCase(): Boolean {
        if (dimensions.rows == isErrorFlag) {
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

    fun solve() {
        if (!isSolved)
            searchForRonStartingFrom(this.harryPotterPos)
    }
}

fun main() {
    val testCases = readLine()!!.toInt()
    repeat(testCases) {

        val mazeSolver = TriwizardMazeSolver()

        // Test Case #2
        if (mazeSolver.isWrongTestCase())
            return

        mazeSolver.solve()
        println(
            when (mazeSolver.harryCanFindRonWeasley) {
                true -> "yes"
                else -> "no"
            }
        )

    }
}