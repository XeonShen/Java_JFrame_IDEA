package xeon.spacedrifter.com.game;
import xeon.spacedrifter.com.util.CaptchaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterJFrame extends JFrame implements MouseListener {


    JTextField usernameJTextField = new JTextField();
    JPasswordField passwordJPasswordField = new JPasswordField();
    JTextField repeatPasswordJTextField = new JTextField();
    JButton registerJButton = new JButton("Register");
    JButton resetJButton = new JButton("Reset");

    public RegisterJFrame() {
        initJFrame();
        initView();
        this.setVisible(true);
    }

    public void initJFrame() {
        this.setSize(400, 300);
        this.setTitle("Poker Game Register Page");
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
    }

    public void initView() {
        //1.add username label
        JLabel usernameJlabel = new JLabel("Username");
        usernameJlabel.setForeground(Color.black);
        Font usernameFont = new Font(null, 1, 16);
        usernameJlabel.setFont(usernameFont);
        usernameJlabel.setBounds(50, 30, 100, 30);
        this.getContentPane().add(usernameJlabel);

        //2.add username textfield
        usernameJTextField.setBounds(140, 30, 200, 30);
        this.getContentPane().add(usernameJTextField);

        //3.add password label
        JLabel passwordJLabel = new JLabel("Password");
        passwordJLabel.setForeground(Color.black);
        Font passwordFont = new Font(null, 1, 16);
        passwordJLabel.setFont(passwordFont);
        passwordJLabel.setBounds(50, 80, 100, 30);
        this.getContentPane().add(passwordJLabel);

        //4.add password textfield
        passwordJPasswordField.setBounds(140, 80, 200, 30);
        this.getContentPane().add(passwordJPasswordField);

        //5.add repeat password label
        JLabel repeatPasswordJLabel = new JLabel("Repeat");
        repeatPasswordJLabel.setForeground(Color.black);
        Font captchaFont = new Font(null, 1, 16);
        repeatPasswordJLabel.setFont(captchaFont);
        repeatPasswordJLabel.setBounds(50, 130, 100, 30);
        this.getContentPane().add(repeatPasswordJLabel);

        //6.add repeat password textfield
        repeatPasswordJTextField.setBounds(140, 130, 200, 30);
        this.getContentPane().add(repeatPasswordJTextField);

        //7.add register button
        registerJButton.setBounds(50, 190, 100, 40);
        registerJButton.setContentAreaFilled(false);
        registerJButton.addMouseListener(this);
        this.getContentPane().add(registerJButton);

        //8.add reset button
        resetJButton.setBounds(240, 190, 100, 40);
        resetJButton.setContentAreaFilled(false);
        resetJButton.addMouseListener(this);
        this.getContentPane().add(resetJButton);
    }

    public void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);

        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        jDialog.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
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
