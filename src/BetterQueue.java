import java.awt.*;

/**
 * @implNote implement a queue using a circular array with initial capacity 8.
 *
 * Implement BetterQueueInterface and add a constructor
 *
 * You are explicitly forbidden from using java.util.Queue and any subclass
 * (including LinkedList, for example) and any other java.util.* library EXCEPT java.util.Objects.
 * Write your own implementation of a Queue.
 *
 * Another great example of why we are implementing our own queue here is that
 * our queue is actually FASTER than Java's LinkedList (our solution is 2x faster!). This is due
 * to a few reasons, the biggest of which are 1. the overhead associated with standard library
 * classes, 2. the fact that Java's LinkedList doesn't store elements next to each other, which
 * increases memory overhead for the system, and 3. LinkedList stores 2 pointers with each element,
 * which matters when you store classes that aren't massive (because it increases the size of each
 * element, making more work for the system).
 *
 * @param <E> the type of object this queue will be holding
 */
public class BetterQueue<E> implements BetterQueueInterface<E> {

    /**
     * Initial size of queue.  Do not decrease capacity below this value.
     */
    private final int INIT_CAPACITY = 8;


    /**
     * If the array needs to increase in size, it should be increased to
     * old capacity * INCREASE_FACTOR.
     *
     * If it cannot increase by that much (old capacity * INCREASE_FACTOR > max int),
     * it should increase by CONSTANT_INCREMENT.
     *
     * If that can't be done either throw OutOfMemoryError()
     *
     */
    private final int INCREASE_FACTOR = 2;
    private final int CONSTANT_INCREMENT = 1 << 5; // 32



    /**
     * If the number of elements stored is < capacity * DECREASE_FACTOR, it should decrease
     * the capacity of the UDS to max(capacity * DECREASE_FACTOR, initial capacity).
     *
     */
    private final double DECREASE_FACTOR = 0.5;


    /**
     * Array to store elements in (according to the implementation
     * note in the class header comment).
     *
     * Circular arrays work as follows:
     *
     *   1. Removing an element increments the "first" index
     *   2. Adding an element inserts it into the next available slot. Incrementing
     *      the "last" index WRAPS to the front of the array, if there are spots available
     *      there (if we have removed some elements, for example).
     *   3. The only way to know if the array is full is if the "last" index
     *      is right in front of the "first" index.
     *   4. If you need to increase the size of the array, put the elements back into
     *      the array starting with "first" (i.e. "first" is at index 0 in the new array).
     *   5. No other implementation details will be given, but a good piece of advice
     *      is to draw out what should be happening in each operation before you code it.
     *
     *   hint: modulus might be helpful
     */
    private E[] queue;
    private int f = 0;
    private int b = 0;
    private int size = 0;

    /**
     * Constructs an empty queue
     */
    @SuppressWarnings("unchecked")
    public BetterQueue(){
        //todo
        this.queue = (E[]) new Object[INIT_CAPACITY];
    }

    /**
     * Add an item to the back of the queue
     *
     * @param item item to push
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public void add(E item) {
        //todo
        E[] temp;

        if (item == null) {
            throw new NullPointerException();
        }

        if (size() == queue.length -1) {
            if (Integer.toUnsignedLong(queue.length) * INCREASE_FACTOR > Integer.MAX_VALUE) {
                temp = (E[]) new Object[queue.length + CONSTANT_INCREMENT];
            }
            else {
                temp = (E[]) new Object[queue.length * INCREASE_FACTOR];
            }

            int count = 0;
            if (b < f) {
                for (int i = f; i < queue.length; i++) {
                    temp[count] = queue[i];
                    count++;
                }

                for (int i = 0; i < b; i++) {
                    temp[count] = queue[i];
                    count++;
                }
            }
            else if (b > f) {
                for (int i = f; i < b; i++) {
                    temp[count] = queue[i];
                    count++;
                }
            }

            f = 0;
            b = count;
            queue = temp;
        }

        queue[b] = item;
        b = ((b + 1) % queue.length);
        size++;
    }

    /**
     * Returns the front of the queue (does not remove it) or <code>null</code> if the queue is empty
     *
     * @return front of the queue or <code>null</code> if the queue is empty
     */
    @Override
    public E peek() {
        //todo
        if (size != 0) {
            return queue[f];
        }

        return null;
    }

    /**
     * Returns and removes the front of the queue
     *
     * @return the head of the queue, or <code>null</code> if this queue is empty
     */
    @Override
    public E remove() {
        //todo
        E[] temp;

        if (size == 0) {
            return null;
        }

        E front = queue[f];
        queue[f] = null;
        f = ((f + 1) % queue.length);
        size--;

        if (size < queue.length * DECREASE_FACTOR) {
            temp = (E[]) new Object[(int) (queue.length * DECREASE_FACTOR)];

            int count = 0;
            if (b < f) {
                for (int i = f; i < queue.length; i++) {
                    temp[count] = queue[i];
                    count++;
                }

                for (int i = 0; i < b; i++) {
                    temp[count] = queue[i];
                    count++;
                }
            } else if (b > f) {
                for (int i = f; i < b; i++) {
                    temp[count] = queue[i];
                    count++;
                }
            }

            f = 0;
            b = count;
            queue = temp;
        }

        return front;
    }

    /**
     * Returns the number of elements in the queue
     *
     * @return integer representing the number of elements in the queue
     */
    @Override
    public int size() {
        //todo
        return size;
    }

    /**
     * Returns whether the queue is empty
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        //todo
        return size == 0;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the queue how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
