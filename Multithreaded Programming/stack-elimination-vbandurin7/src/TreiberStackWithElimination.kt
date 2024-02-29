import java.util.concurrent.*
import java.util.concurrent.atomic.*

/**
 * @author Bandurin Vladislav
 */
open class TreiberStackWithElimination<E> : Stack<E> {
    private val stack = TreiberStack<E>()

    private val eliminationArray = AtomicReferenceArray<Any?>(ELIMINATION_ARRAY_SIZE)

    override fun push(element: E) {
        if (tryPushElimination(element)) return
        stack.push(element)
    }

    protected open fun tryPushElimination(element: E): Boolean {
        val randomCellIndex = randomCellIndex()
        if (!eliminationArray.compareAndSet(randomCellIndex, CELL_STATE_EMPTY, element)) {
            return false
        }
        for (i in 1..ELIMINATION_WAIT_CYCLES) {
            if (eliminationArray.compareAndSet(randomCellIndex, CELL_STATE_RETRIEVED, CELL_STATE_EMPTY)) {
                return true
            }
        }
        if (eliminationArray.compareAndSet(randomCellIndex, element, CELL_STATE_EMPTY)) {
            return false
        } 
        eliminationArray.compareAndSet(randomCellIndex, CELL_STATE_RETRIEVED, CELL_STATE_EMPTY)
        return true
    }

    override fun pop(): E? = tryPopElimination() ?: stack.pop()

    private fun tryPopElimination(): E? {
        val randomCellIndex = randomCellIndex()
        val element = eliminationArray.get(randomCellIndex)
        if (element != CELL_STATE_EMPTY
            && element != CELL_STATE_RETRIEVED
            && eliminationArray.compareAndSet(randomCellIndex, element, CELL_STATE_RETRIEVED)) {
            return element as E?
        }
        return null
    }

    private fun randomCellIndex(): Int =
        ThreadLocalRandom.current().nextInt(eliminationArray.length())

    companion object {
        private const val ELIMINATION_ARRAY_SIZE = 2 // Do not change!
        private const val ELIMINATION_WAIT_CYCLES = 1 // Do not change!

        // Initially, all cells are in EMPTY state.
        private val CELL_STATE_EMPTY = null

        // `tryPopElimination()` moves the cell state
        // to `RETRIEVED` if the cell contains element.
        private val CELL_STATE_RETRIEVED = Any()
    }
}
