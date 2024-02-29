import java.util.concurrent.atomic.*;

/*
@author Bandurin Vladislav
 */
@SuppressWarnings("ClassEscapesDefinedScope")
public class Solution implements Lock<Solution.Node> {
    private final Environment env;
    public final AtomicReference<Node> tail;

    public Solution(Environment env) {
        this.env = env;
        tail = new AtomicReference<>(null);
    }

    @Override
    public Node lock() {
        Node my = new Node(); // сделали узел
        Node pred = tail.getAndSet(my);
        if (pred != null) {
            pred.next.compareAndSet(null, my);
            while (my.locked.get()) {
                env.park();
            }
        }
        return my; // вернули узел
    }

    @Override
    @SuppressWarnings("StatementWithEmptyBody")
    public void unlock(Node my) {
        if (my.next.get() == null) {
            if (tail.compareAndSet(my,null)) {
                return;
            } else {
                while (my.next.get() == null) {
                    // pass
                }
            }
        }
        my.next.get().locked.getAndSet(false);
        env.unpark(my.next.get().thread);
    }

    static class Node {
        final Thread thread = Thread.currentThread(); // запоминаем поток, которые создал узел
        public final AtomicReference<Node> next = new AtomicReference<>(null);
        public final AtomicReference<Boolean> locked = new AtomicReference<>(true);
    }
}
