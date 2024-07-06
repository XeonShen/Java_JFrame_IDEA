package xeon.spacedrifter.com.domain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Poker extends JLabel implements MouseListener {

    private String name;
    private boolean isFacingUp;
    private boolean isClickable = false;
    private boolean isClicked = false;



    public Poker(String name, boolean isFacingUp) {
        this.name = name;
        this.isFacingUp = isFacingUp;
        if (this.isFacingUp){
            this.turnFront();
        }else {
            this.turnRear();
        }
        this.setSize(71, 96);
        this.setVisible(true);
        this.addMouseListener(this);
    }



    public void turnFront() {
        this.setIcon(new ImageIcon("image/" + name + ".png"));
        this.isFacingUp = true;
    }

    public void turnRear() {
        this.setIcon(new ImageIcon("image/rear.png"));
        this.isFacingUp = false;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsFacingUp() {
        return isFacingUp;
    }

    public void setIsFacingUp(boolean isFacingUp) {
        this.isFacingUp = isFacingUp;
    }

    public boolean getIsClickable() {
        return isClickable;
    }

    public void setIsClickable(boolean isClickable) {
        this.isClickable = isClickable;
    }

    public boolean getIsClicked() {
        return isClicked;
    }

    public void setIsClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (isClickable) {
            int step = 0;
            if (isClicked) {
                step = 20;
            } else {
                step = -20;
            }
            isClicked = !isClicked;

            Point from = this.getLocation();
            Point to = new Point(from.x, from.y + step);
            this.setLocation(to);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }




}
