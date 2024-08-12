package datastructure.queue;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class HamburgerSimulation {

    private enum Burger {
        CHE_BUR("Cheese burger", 12),
        BUL_BUR( "Bulgogi burger", 15),
        DUB_BUR( "Double burger", 24);

        private final String name;
        private final int cookingTime;

        Burger(String name, int cookingTime) {
            this.name = name;
            this.cookingTime = cookingTime;
        }

        public String getName() {
            return name;
        }
        public int getCookingTime() {
            return cookingTime;
        }
    }

    private static final int CUSTOMER_COME_TERM = 15;
    private static final List<Burger> BURGERS = Arrays.asList(Burger.values());
    private static final Map<Burger, Integer> BURGER_ORDER_CNT = new EnumMap<>(Burger.class);

    public static void main(String[] args) {
        ArrayBaseQueue<Integer> queue = new ArrayBaseQueue<>();
        int makeProc = 0;

        for (int sec = 0; sec < 3600; sec++) {
            if (sec % CUSTOMER_COME_TERM == 0) {
                switch (BURGERS.get((int) (Math.random() * 3))) {
                    case CHE_BUR:
                        queue.enqueue(Burger.CHE_BUR.getCookingTime());
                        BURGER_ORDER_CNT.put(Burger.CHE_BUR, BURGER_ORDER_CNT.getOrDefault(Burger.CHE_BUR, 0) + 1);
                        break;
                    case BUL_BUR:
                        queue.enqueue(Burger.BUL_BUR.getCookingTime());
                        BURGER_ORDER_CNT.put(Burger.BUL_BUR, BURGER_ORDER_CNT.getOrDefault(Burger.BUL_BUR, 0) + 1);
                        break;
                    case DUB_BUR:
                        queue.enqueue(Burger.DUB_BUR.getCookingTime());
                        BURGER_ORDER_CNT.put(Burger.DUB_BUR, BURGER_ORDER_CNT.getOrDefault(Burger.DUB_BUR, 0) + 1);
                        break;
                }
            }

            if (makeProc <= 0 && !queue.isEmpty()) {
                makeProc = queue.dequeue();
            }
            makeProc--;
        }

        System.out.println("Simulation Report!");
        for (Burger burger : Burger.values()) {
            System.out.printf(" - %s: %d\n", burger.getName(), BURGER_ORDER_CNT.getOrDefault(burger, 0));
        }
        System.out.printf(" - Waiting room size: %d\n", queue.getCapacity());
    }
}
