package xeon.spacedrifter.com.game;

import xeon.spacedrifter.com.domain.User;
import xeon.spacedrifter.com.util.CaptchaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.jar.JarEntry;

public class LoginJFrame extends JFrame implements MouseListener {

    static ArrayList<User> allUsers = new ArrayList<>();

    static {
        allUsers.add(new User("zhangsan", "123"));
        allUsers.add(new User("lisi", "1234"));
    }

    JButton loginJButton = new JButton();
    JButton registerJButton = new JButton();
    JTextField usernameJTextField = new JTextField();
    JPasswordField passwordJPasswordField = new JPasswordField();
    JTextField captchaJTextField = new JTextField();
    JLabel rightCaptchaJLabel = new JLabel();

    public LoginJFrame() {
        initJFrame();
        initView();
        this.setVisible(true);
    }

    public void initJFrame() {
        this.setSize(633, 423);
        this.setTitle("Poker Game Login Page");
        this.setDefaultCloseOperation(3); //when close, close all windows
        this.setLocationRelativeTo(null); //center the window
        this.setAlwaysOnTop(true); //pin the window at top
        this.setLayout(null); //cancel the default layout
    }

    public void initView() {
        //1.add username label
        JLabel usernameJlabel = new JLabel("Username");
        usernameJlabel.setForeground(Color.white);
        Font usernameFont = new Font(null, 1, 16);
        usernameJlabel.setFont(usernameFont);
        usernameJlabel.setBounds(140, 55, 55,22);
        this.getContentPane().add(usernameJlabel);

        //2.add username textfield
        usernameJTextField.setBounds(223, 46, 200, 30);
        this.getContentPane().add(usernameJTextField);

        //3.add password label
        JLabel passwordJLabel = new JLabel("Password");
        passwordJLabel.setForeground(Color.white);
        Font passwordFont = new Font(null, 1, 16);
        passwordJLabel.setFont(passwordFont);
        passwordJLabel.setBounds(197, 95, 40, 22);
        this.getContentPane().add(passwordJLabel);

        //4.add password textfield
        passwordJPasswordField.setBounds(263, 87, 160, 30);
        this.getContentPane().add(passwordJPasswordField);

        //5.add captcha label
        JLabel captchaJLabel = new JLabel("CAPTCHA");
        captchaJLabel.setForeground(Color.white);
        Font captchaFont = new Font(null, 1, 16);
        captchaJLabel.setFont(captchaFont);
        captchaJLabel.setBounds(215, 142, 55, 22);
        this.getContentPane().add(captchaJLabel);

        //6.add captcha textfield
        captchaJTextField.setBounds(291, 133, 100, 30);
        this.getContentPane().add(captchaJTextField);

        //7.add right captcha label
        rightCaptchaJLabel.setText(CaptchaGenerator.getCaptcha());
        rightCaptchaJLabel.setForeground(Color.RED);
        Font rightCaptchaFont = new Font(null, 1, 15);
        rightCaptchaJLabel.setFont(rightCaptchaFont);
        rightCaptchaJLabel.addMouseListener(this);
        rightCaptchaJLabel.setBounds(400, 133, 100, 30);
        this.getContentPane().add(rightCaptchaJLabel);

        //8.add login button
        loginJButton.setBounds(123, 310, 128, 47);
        loginJButton.addMouseListener(this);
        this.getContentPane().add(loginJButton);

        //9.add register button
        registerJButton.setBounds(256, 310, 128, 47);
        registerJButton.addMouseListener(this);
        this.getContentPane().add(registerJButton);

    }

    public void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);//other windows are not available until this dialog is closed

        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        jDialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == loginJButton) {
            if (captchaJTextField.getText().isEmpty()) {
                showJDialog("Captcha can not be empty");
                return;
            }
            if (usernameJTextField.getText().isEmpty() || passwordJPasswordField.getText().isEmpty()) {
                showJDialog("Username or Password is empty");
                return;
            }
            if (!captchaJTextField.getText().equalsIgnoreCase(rightCaptchaJLabel.getText())) {
                showJDialog("Captcha is wrong");
                return;
            }

            User userInfo = new User(usernameJTextField.getText(), passwordJPasswordField.getText());
            if (allUsers.contains(userInfo)) {
                this.setVisible(false); //close this window
                new GameJFrame(); //open game window
            } else {
                showJDialog("Username or Password is wrong");
            }
        } else if (obj == registerJButton) {
            System.out.println("register button is pressed");
        } else if (obj == rightCaptchaJLabel) {
            rightCaptchaJLabel.setText(CaptchaGenerator.getCaptcha());
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
