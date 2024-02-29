package dijkstra

import kotlinx.atomicfu.atomic
import java.util.*
import java.util.concurrent.Phaser
import java.util.concurrent.locks.ReentrantLock
import kotlin.Comparator
import kotlin.concurrent.thread

private val NODE_DISTANCE_COMPARATOR = Comparator<Node> { o1, o2 -> Integer.compare(o1!!.distance, o2!!.distance) }

/*
@author Bandurin Vladislav
 */
fun shortestPathParallel(start: Node) {
    val workers = Runtime.getRuntime().availableProcessors()
    start.distance = 0
    val q = MultiQueue(workers)
    q.insert(start)
    val onFinish = Phaser(workers + 1)
    repeat(workers) {
        thread {
            while (true) {
                val cur: Node = synchronized(q) { q.delete() } ?: if (q.activeNodes.value == 0) break else continue
                for (e in cur.outgoingEdges) {
                    do {
                        val curDist = e.to.distance
                        val newDist = cur.distance + e.weight
                        if (curDist > newDist && e.to.casDistance(curDist, newDist)) {
                            q.insert(e.to)
                            break
                        }
                    } while (curDist > newDist)
                }
                q.activeNodes.getAndDecrement()
            }
            onFinish.arrive()
        }
    }
    onFinish.arriveAndAwaitAdvance()
}

class MultiQueue(private val workers: Int) {
    private val random: Random = Random()
    private val queueCount = 2 * workers
    private val queues: Array<PriorityQueue<Node>> =
        Array(queueCount) { PriorityQueue(workers, NODE_DISTANCE_COMPARATOR) }
    private val locks: Array<ReentrantLock> = Array(queueCount) { ReentrantLock() }
    val activeNodes = atomic(0)

    fun insert(node: Node) {
        while (true) {
            val queueIndex = random.nextInt(queueCount)
            val q = queues[queueIndex]
            if (!locks[queueIndex].tryLock()) {
                continue
            }
            try {
                q.add(node)
            } finally {
                locks[queueIndex].unlock()
            }
            activeNodes.getAndIncrement()
            return
        }
    }

    fun delete(): Node? {
        while (true) {
            val i1 = random.nextInt(queueCount)
            val i2 = random.nextInt(queueCount)
            if (i1 == i2) {
                continue
            }
            if (!locks[i1].tryLock()) {
                continue
            }
            try {
                if (!locks[i2].tryLock()) {
                    continue
                }
                try {
                    val n1 = queues[i1].peek()
                    val n2 = queues[i2].peek()

                    when {
                        n1 == null && n2 == null -> return null

                        n1 != null && n2 != null -> return when {
                            NODE_DISTANCE_COMPARATOR.compare(n1, n2) < 0 -> {
                                queues[i1].poll()
                                n1
                            }

                            else -> {
                                queues[i2].poll()
                                n2
                            }
                        }

                        n1 != null -> {
                            queues[i1].poll()
                            return n1
                        }

                        else -> {
                            queues[i2].poll()
                            return n2
                        }
                    }
                } finally {
                    locks[i2].unlock()
                }
            } finally {
                locks[i1].unlock()
            }
        }
    }
}