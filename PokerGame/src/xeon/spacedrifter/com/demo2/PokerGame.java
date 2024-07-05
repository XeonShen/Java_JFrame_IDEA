package xeon.spacedrifter.com.demo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class PokerGame {

    //a Dictionary storing the Poker cards and the corresponding sequence number
    //by comparing the sequence, which card is larger can be easily judged
    static HashMap<Integer, String> hm = new HashMap<>();
    //the sequence number players get
    static ArrayList<Integer> list = new ArrayList<>();

    //static code block, prepare the cards
    static {
        String[] color = {"♦", "♣", "♥", "♠" };
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2" };

        int serialNumber = 1;
        for (String n : number) {
            for (String c : color) {
                hm.put(serialNumber, c + n);
                list.add(serialNumber);
                serialNumber++;
            }
        }
        hm.put(serialNumber, "Black_Joker");
        list.add(serialNumber);
        serialNumber++;
        hm.put(serialNumber, "Red_Joker");
        list.add(serialNumber);
    }

    public PokerGame() {
        Collections.shuffle(list);

        TreeSet<Integer> lord = new TreeSet<>();
        TreeSet<Integer> player1 = new TreeSet<>();
        TreeSet<Integer> player2 = new TreeSet<>();
        TreeSet<Integer> player3 = new TreeSet<>();

        for (int i = 0; i < list.size(); i++) {
            int serialNumber = list.get(i);
            if (i <= 2) {
                lord.add(serialNumber);
                continue;
            }
            if (i % 3 == 0) {
                player1.add(serialNumber);
            } else if (i % 3 == 1) {
                player2.add(serialNumber);
            } else {
                player3.add(serialNumber);
            }
        }

        lookPoker("lord", lord);
        lookPoker("player1", player1);
        lookPoker("player2", player2);
        lookPoker("player3", player3);
    }

    public void lookPoker(String name, TreeSet<Integer> ts) {
        System.out.print(name + ": ");
        for (int serialNumber : ts) {
            String poker = hm.get(serialNumber);
            System.out.print(poker + " ");
        }
        System.out.println();
    }
}
