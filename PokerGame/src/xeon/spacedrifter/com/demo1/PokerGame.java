package xeon.spacedrifter.com.demo1;

import java.util.ArrayList;
import java.util.Collections;

public class PokerGame {

    static ArrayList<String> list = new ArrayList<>();

    //static code block, prepare the cards
    static {
        String[] color = {"♦", "♣", "♥", "♠" };
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2" };
        for (String c : color) {
            for (String n : number) {
                list.add(c + n);
            }
        }
        list.add("Red_Joker");
        list.add("Black_Joker");
    }

    public PokerGame() {
        Collections.shuffle(list);

        ArrayList<String> lord = new ArrayList<>();
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String poker = list.get(i);
            if (i <= 2) {
                lord.add(poker);
                continue;
            }
            if (i % 3 == 0) {
                player1.add(poker);
            } else if (i % 3 == 1) {
                player2.add(poker);
            } else {
                player3.add(poker);
            }
        }

        lookPoker("lord", lord);
        lookPoker("player1", player1);
        lookPoker("player2", player2);
        lookPoker("player3", player3);

    }

    public void lookPoker(String name, ArrayList<String> list) {
        System.out.print(name + ": ");
        for (String poker : list) {
            System.out.print(poker + " ");
        }
        System.out.println();
    }

}
