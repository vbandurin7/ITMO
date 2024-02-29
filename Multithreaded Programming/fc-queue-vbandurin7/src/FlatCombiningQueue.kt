import java.util.concurrent.*
import java.util.concurrent.atomic.*

/**
 * @author Bandurin Vladislav
 */
class FlatCombiningQueue<E> : Queue<E> {
    private val queue = ArrayDeque<E>() // sequential queue
    private val combinerLock = AtomicBoolean(false) // unlocked initially
    private val tasksForCombiner = AtomicReferenceArray<Any?>(TASKS_FOR_COMBINER_SIZE)

    override fun enqueue(element: E) {
        while (true) {
            if (combinerLock.compareAndSet(false, true)) {
                queue.addLast(element)
                processTasks()
                combinerLock.compareAndSet(true, false)
                return
            } else {
                val randIdx = randomCellIndex()
                if (!tasksForCombiner.compareAndSet(randIdx, null, element)) {
                    continue
                }
                while (true) {
                    if (combinerLock.compareAndSet(false, true)) {
                        val res = tasksForCombiner.get(randIdx)
                        if (res is Result<*>) {
                            tasksForCombiner.compareAndSet(randIdx, res, null)
                            processTasks()
                            combinerLock.compareAndSet(true, false)
                            return
                        }
                        tasksForCombiner.compareAndSet(randIdx, res, null)
                        queue.addLast(element)
                        processTasks()
                        combinerLock.compareAndSet(true, false)
                        return
                    } else if (tasksForCombiner.get(randIdx) is Result<*>) {
                        tasksForCombiner.compareAndExchange(
                            randIdx,
                            tasksForCombiner.get(randIdx),
                            null
                        )
                        return
                    }
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun dequeue(): E? {
        while (true) {
            if (combinerLock.compareAndSet(false, true)) {
                val res = queue.removeFirstOrNull()
                processTasks()
                combinerLock.compareAndSet(true, false)
                return res
            } else {
                val randIdx = randomCellIndex()
                if (!tasksForCombiner.compareAndSet(randIdx, null, Dequeue)) {
                    continue
                }
                while (true) {
                    if (combinerLock.compareAndSet(false, true)) {
                        val res = tasksForCombiner.get(randIdx)
                        if (res is Result<*>) {
                            tasksForCombiner.compareAndSet(randIdx, res, null)
                            processTasks()
                            combinerLock.compareAndSet(true, false)
                            return (res as Result<E?>).value
                        }
                        tasksForCombiner.compareAndSet(randIdx, res, null)
                        val result = queue.removeFirstOrNull()
                        processTasks()
                        combinerLock.compareAndSet(true, false)
                        return result
                    } else if (tasksForCombiner.get(randIdx) is Result<*>) {
                        val result = tasksForCombiner.compareAndExchange(
                            randIdx,
                            tasksForCombiner.get(randIdx),
                            null
                        ) as Result<E?>
                        return result.value
                    }
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun processTasks() {
        for (i in 0..<TASKS_FOR_COMBINER_SIZE) {
            val task = tasksForCombiner.get(i)
            if (task == Dequeue) {
                tasksForCombiner.compareAndSet(i, Dequeue, Result(queue.removeFirstOrNull()))
            } else if (task != null && task !is Result<*>) {
                queue.addLast(task as E)
                tasksForCombiner.compareAndSet(i, task, Result(task))
            }
        }
    }

    private fun randomCellIndex(): Int =
        ThreadLocalRandom.current().nextInt(tasksForCombiner.length())
}

private const val TASKS_FOR_COMBINER_SIZE = 3 // Do not change this constant!

private object Dequeue

private class Result<V>(
    val value: V
)
