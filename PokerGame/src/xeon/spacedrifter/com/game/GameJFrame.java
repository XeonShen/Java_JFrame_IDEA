package xeon.spacedrifter.com.game;

import xeon.spacedrifter.com.domain.Poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameJFrame extends JFrame implements ActionListener {

    public static Container container = null;

    JButton[] landlord = new JButton[2];
    JButton[] publishCard = new JButton[2];
    JLabel dizhu;
    ArrayList<ArrayList<Poker>> currentList = new ArrayList<>();
    ArrayList<ArrayList<Poker>> playerList = new ArrayList<>();
    ArrayList<Poker> lordList = new ArrayList<>();
    ArrayList<Poker> allPokerList = new ArrayList();
    JTextField[] time = new JTextField[3];

    public GameJFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("image/dizhu.png"));
        initJframe();
        initView();
        this.setVisible(true);
        initCard();
        initGame();
    }

    //1.set the JFrame window
    public void initJframe() {
        this.setTitle("Poker Game");
        this.setSize(830, 620);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        container = this.getContentPane();
        container.setLayout(null);
        container.setBackground(Color.LIGHT_GRAY);
    }

    //2.add components to window
    public void initView() {
        JButton robJButton = new JButton("Rob");
        robJButton.setBounds(320, 400, 75, 20);
        robJButton.setFont(new Font(null, 1, 10));
        robJButton.addActionListener(this);
        robJButton.setVisible(false);
        landlord[0] = robJButton;
        container.add(robJButton);

        JButton noRobJButton = new JButton("No Rob");
        noRobJButton.setBounds(420, 400, 75, 20);
        noRobJButton.setFont(new Font(null, 1, 10));
        noRobJButton.addActionListener(this);
        noRobJButton.setVisible(false);
        landlord[1] = noRobJButton;
        container.add(noRobJButton);

        JButton dealCardJButton = new JButton("Deal");
        dealCardJButton.setBounds(320, 400, 60, 20);
        dealCardJButton.setFont(new Font(null, 1, 10));
        dealCardJButton.addActionListener(this);
        dealCardJButton.setVisible(false);
        publishCard[0] = dealCardJButton;
        container.add(dealCardJButton);

        JButton noDealCardJButton = new JButton("No Deal");
        noDealCardJButton.setBounds(420, 400, 60, 20);
        noDealCardJButton.setFont(new Font(null, 1, 10));
        noDealCardJButton.addActionListener(this);
        noDealCardJButton.setVisible(false);
        publishCard[1] = noDealCardJButton;
        container.add(noDealCardJButton);

        for (int i = 0; i < 3; i++) {
            time[i] = new JTextField("Counting Down: ");
            time[i].setEditable(false);
            time[i].setVisible(false);
            container.add(time[i]);
        }
        time[0].setBounds(140, 230, 60, 20);
        time[1].setBounds(374, 360, 60, 20);
        time[2].setBounds(620, 230, 60, 20);

        dizhu = new JLabel(new ImageIcon("image/dizhu.png"));
        dizhu.setVisible(false);
        dizhu.setSize(40, 40);
        container.add(dizhu);
    }

    //3.shuffle and deal cards
    public void initCard() {
        //create all cards then add to the list
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 13; j++) {
                if ((i == 5) && (j > 2)) {
                    break;
                } else {
                    Poker poker = new Poker(i + "-" + j, false);
                    poker.setLocation(350, 150);
                    allPokerList.add(poker);
                    container.add(poker);
                }
            }
        }

        //shuffle cards
        Collections.shuffle(allPokerList);

        ArrayList<Poker> player0 = new ArrayList<>();
        ArrayList<Poker> player1 = new ArrayList<>();
        ArrayList<Poker> player2 = new ArrayList<>();

        //deal cards
        for (int i = 0; i < allPokerList.size(); i++) {
            Poker poker = allPokerList.get(i);

            if (i <= 2) {
                lordList.add(poker);
                move(poker, poker.getLocation(), new Point(270 + (75 * i), 10));
                continue;
            }
            if (i % 3 == 0) {
                move(poker, poker.getLocation(), new Point(50, 60 + i * 5));
                player0.add(poker);
            } else if (i % 3 == 1) {
                move(poker, poker.getLocation(), new Point(180 + i * 7, 450));
                player1.add(poker);
                //show the cards to player
                poker.turnFront();
            } else if (i % 3 == 2) {
                move(poker, poker.getLocation(), new Point(700, 60 + i * 5));
                player2.add(poker);
            }

            playerList.add(player0);
            playerList.add(player1);
            playerList.add(player2);

            //put the card on the top of Z axis
            container.setComponentZOrder(poker, 0);
        }

        //sort the cards, refresh the cards UI
        for (int i = 0; i < 3; i++) {
            order(playerList.get(i));
            rePosition(playerList.get(i), i);
        }
    }

    //4.some preparing works
    private void initGame() {
        //lists for the cards that players are going to deal
        for (int i = 0; i < 3; i++) {
            ArrayList<Poker> list = new ArrayList<>();
            currentList.add(list);
        }

        landlord[0].setVisible(true);
        landlord[1].setVisible(true);

        for (JTextField field : time) {
            field.setText("Time Left 30s");
            field.setFont(new Font(null, 1, 8));
            field.setVisible(true);
        }
    }



    //rank the cards
    public void order(ArrayList<Poker> list) {
        Collections.sort(list, new Comparator<Poker>() {
            @Override
            public int compare(Poker o1, Poker o2) {
                int color1 = Integer.parseInt(o1.getName().substring(0, 1));
                int value1 = getValue(o1);
                int color2 = Integer.parseInt(o2.getName().substring(0, 1));
                int value2 = getValue(o2);

                int flag = value2 - value1;
                if (flag == 0) {
                    return color2 - color1;
                } else {
                    return flag;
                }
            }
        });
    }

    //get the value of each card
    public int getValue(Poker poker) {
        String name = poker.getName();
        int color = Integer.parseInt(poker.getName().substring(0, 1));
        int value = Integer.parseInt(name.substring(2));

        if (color == 5) {
            return value += 100;
        }
        if (value == 1) {
            return value += 20;
        }
        if (value == 2) {
            return value += 30;
        }

        return value;
    }

    //moving card animation
    public static void move(Poker poker, Point from, Point to) {
        if (to.x != from.x) {
            double k = (1.0) * (to.y - from.y) / (to.x - from.x);
            double b = to.y - to.x * k;
            int flag = 0;
            if (from.x < to.x)
                flag = 20;
            else {
                flag = -20;
            }
            for (int i = from.x; Math.abs(i - to.x) > 20; i += flag) {
                double y = k * i + b;
                poker.setLocation(i, (int) y);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        poker.setLocation(to);
    }

    //reposition the cards
    public static void rePosition(ArrayList<Poker> list, int flag) {
        Point p = new Point();
        if (flag == 0) {
            p.x = 50;
            p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
        }
        if (flag == 1) {
            p.x = (800 / 2) - (list.size() + 1) * 21 / 2;
            p.y = 450;
        }
        if (flag == 2) {
            p.x = 700;
            p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
        }
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Poker poker = list.get(i);
            move(poker, poker.getLocation(), p);
            container.setComponentZOrder(poker, 0);
            if (flag == 1)
                p.x += 21;
            else
                p.y += 15;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == landlord[0]) {
            time[1].setText("Rob");
            ArrayList<Poker> player1 = playerList.get(1);
            player1.addAll(lordList);
            lordList.clear();
            order(playerList.get(1));
            for (Poker poker : player1) {
                poker.turnFront();
            }
            rePosition(playerList.get(1), 1);
        } else if (e.getSource() == landlord[1]) {
            time[1].setText("No Rob");
        } else if (e.getSource() == publishCard[1]) {
            time[1].setText("Give Up");
        } else if (e.getSource() == publishCard[0]) {
            time[1].setText("Deal");
        }
    }
}
