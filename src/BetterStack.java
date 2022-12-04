import java.util.EmptyStackException;

/**
 * @implNote Implement a stack using an array with initial capacity 8.
 *
 * Implement BetterStackInterface and add a constructor
 *
 * You are explicitly forbidden from using java.util.Stack and any
 * other java.util.* library EXCEPT java.util.EmptyStackException and java.util.Arrays.
 * Write your own implementation of a Stack.
 *
 *
 * @param <E> the type of object this stack will be holding
 */
public class BetterStack<E> implements BetterStackInterface<E> {

    /**
     * Initial size of stack.  Do not decrease capacity below this value.
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
     */
    private E[] stack;
    int t = 0;

    /**
     * Constructs an empty stack
     */
    @SuppressWarnings("unchecked")
    public BetterStack(){
        //todo
        this.stack = (E[]) new Object[INIT_CAPACITY];
    }


    /**
     * Push an item onto the top of the stack
     *
     * @param item item to push
     * @throws OutOfMemoryError if the underlying data structure cannot hold any more elements
     */
    @Override
    public void push(E item) throws OutOfMemoryError {
        //todo
        E[] temp;
        if (t >= stack.length - 1) {
            if (Integer.toUnsignedLong(stack.length) * INCREASE_FACTOR > Integer.MAX_VALUE) {
                temp = (E[]) new Object[stack.length + CONSTANT_INCREMENT];
            }
            else {
                temp = (E[]) new Object[stack.length * INCREASE_FACTOR];
            }

            for (int i = 0; i < stack.length; i++) {
                temp[i] = stack[i];
            }

            stack = temp;
        }

        stack[t] = item;
        t++;
    }

    /**
     * Remove and return the top item on the stack
     *
     * @return the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    @Override
    public E pop() {
        //todo
        E[] temp;
        if (t == 0) {
            throw new EmptyStackException();
        }

        if (t < stack.length * DECREASE_FACTOR) {
            temp = (E[]) new Object[(int) (stack.length * DECREASE_FACTOR)];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = stack[i];
            }

            stack = temp;
        }

        t = t - 1;
        E top = stack[t];
        stack[t] = null;

        return top;
    }

    /**
     * Returns the top of the stack (does not remove it).
     *
     * @return the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    @Override
    public E peek() {
        //todo
        if (t == 0) {
            throw new EmptyStackException();
        }

        return stack[t - 1];
    }

    /**
     * Returns whether the stack is empty
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        //todo
        return (t == 0);
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return integer representing the number of elements in the stack
     */
    @Override
    public int size() {
        //todo
        return t;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void draw(java.awt.Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the stack how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
