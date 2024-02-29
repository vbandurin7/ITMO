import java.util.concurrent.atomic.*

/**
 * @author Bandurin Vladislav
 *
 * TODO: Copy the code from `FAABasedQueueSimplified`
 * TODO: and implement the infinite array on a linked list
 * TODO: of fixed-size `Segment`s.
 */
class FAABasedQueue<E> : Queue<E> {
    private val head: AtomicReference<Segment>
    private val tail: AtomicReference<Segment>
    private val enqIdx = AtomicLong(0)
    private val deqIdx = AtomicLong(0)

    init {
        val first = Segment(0)
        head = AtomicReference(first)
        tail = AtomicReference(first)
    }

    override fun enqueue(element: E) {
        while (true) {
            val curTail = tail.get()
            val i = enqIdx.getAndIncrement()
            val segId = i / SEGMENT_SIZE
            val s = findSegment(curTail, segId)
            if (s.id
                > curTail.id) {
                tail.compareAndSet(curTail, s)
            }
            if (s.cells.compareAndSet((i % SEGMENT_SIZE).toInt(), null, element)) {
                return
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun dequeue(): E? {
        while (true) {
            if (deqIdx.get() >= enqIdx.get()) return null
            val curHead = head.get()
            val i = deqIdx.getAndIncrement()
            val s = findSegment(curHead, i / SEGMENT_SIZE)
            if (s.id > curHead.id) {
                head.compareAndSet(curHead, s)
            }
            if (s.cells.compareAndSet((i % SEGMENT_SIZE).toInt(), null, POISONED)) {
                continue
            }
            return s.cells.compareAndExchange((i % SEGMENT_SIZE).toInt(), s.cells.get((i % SEGMENT_SIZE).toInt()), POISONED) as E
        }
    }

    private fun findSegment(from: Segment, segId: Long): Segment {
        var res = from
        while (res.next.get() != null) {
            if (res.id == segId) {
                return res
            }
            res = res.next.get()!!
        }
        if (res.id == segId) {
            return res
        }
        val newNode = Segment(res.id + 1)
        if (res.next.compareAndSet(null, newNode)) {
            return newNode
        }
        return res.next.get()!!
    }
}

private class Segment(val id: Long) {
    val next = AtomicReference<Segment?>(null)
    val cells = AtomicReferenceArray<Any?>(SEGMENT_SIZE)
    override fun toString(): String {
        return "Segment[id=$id]"
    }
}

// DO NOT CHANGE THIS CONSTANT
private const val SEGMENT_SIZE = 2
private val POISONED = Any()

fun main() {
    val q = FAABasedQueue<Int>()
//    q.dequeue()
    q.enqueue(1)
//    q.enqueue(1)
}