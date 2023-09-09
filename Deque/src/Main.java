public class Main {
    public static void main(String[] args) {
        CompletedDeque<Integer> deque = new CompletedDeque<>();

        //standard queue behavior
        deque.enqueueBack(3); // 3
        deque.enqueueBack(7); // 3 7
        deque.enqueueBack(4); // 3 7 4
        deque.dequeueFront(); // 7 4
        deque.enqueueBack(9); // 7 4 9
        deque.enqueueBack(8); // 7 4 9 8
        deque.dequeueFront(); // 4 9 8
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());

        //deque features
        System.out.println(deque.dequeueFront()); // 9 8
        deque.enqueueFront(1); // 1 9 8
        deque.enqueueFront(11);// 11 1 9 8
        deque.enqueueFront(3);// 3 11 1 9 8
        deque.enqueueFront(5);// 5 3 11 1 9 8
        System.out.println(deque.dequeueBack()); // 5 3 11 1 9
        System.out.println(deque.dequeueBack());// 5 3 11 1
        System.out.println(deque.last()); // 5 3 11 1
        deque.dequeueFront();
        deque.dequeueFront();
        System.out.println(deque.first());
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());
    }
}