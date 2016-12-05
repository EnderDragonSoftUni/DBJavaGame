package bg.softunitower.display;

import bg.softunitower.db.models.Profile;
import bg.softunitower.db.services.interfaces.ProfileService;
import bg.softunitower.game.Game;
import bg.softunitower.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;

@Component
public class RegistrationFrame extends JFrame {

    @Autowired
    private ProfileService profileService;
    private final JFrame loginFrame = new JFrame();
    private final JFrame registerFrame = new JFrame();
    public static String playerName = "NoName";

    public RegistrationFrame() {

        JPanel loginPanel = new JPanel();
        JPanel registrationPanel = new JPanel();

        JLabel nameLabel = new JLabel("Name: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");

        JLabel registerUsernameLabel = new JLabel("Username: ");
        JLabel registerPasswordLabel = new JLabel("Password: ");
        JLabel registerConfirmPassLabel = new JLabel("Re-enter Password: ");

        JTextField nameTextField = new JTextField("", 25);
        JTextField usernameTextField = new JTextField(25);
        JTextField passwordTextField = new JPasswordField(25);

        JTextField registerUsernameTextField = new JTextField(25);
        JTextField registerPasswordTextField = new JPasswordField(25);
        JTextField registerConfirmPassTextField = new JPasswordField(25);

        JButton loginButton = new JButton("Log in");
        JButton registrationButton = new JButton("Register");
        JButton confirmRegistrationButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        initFrame(loginFrame);

        Font fnt = new Font("Calibri", Font.BOLD, 20);
        nameLabel.setFont(fnt);
        usernameLabel.setFont(fnt);
        passwordLabel.setFont(fnt);

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordTextField);
        loginPanel.add(loginButton);
        loginPanel.add(registrationButton);

        registrationPanel.add(registerUsernameLabel);
        registrationPanel.add(registerUsernameTextField);
        registrationPanel.add(registerPasswordLabel);
        registrationPanel.add(registerPasswordTextField);
        registrationPanel.add(registerConfirmPassLabel);
        registrationPanel.add(registerConfirmPassTextField);
        registrationPanel.add(confirmRegistrationButton);
        registrationPanel.add(backButton);

        loginFrame.add(loginPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = usernameTextField.getText();
                String pass = passwordTextField.getText();
                List<Profile> profiles = profileService.getAllProfiles();

                for (Profile profile : profiles) {
                    try {
                        if (profile.getUsername().equals(input) && profile.getPassword().equals(PasswordHelper.md5get(pass))) {
                            Game.setProfile(profile);
                            loginFrame.dispose();
                        }
                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initFrame(registerFrame);
                registerFrame.add(registrationPanel);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerFrame.dispose();
            }
        });
        confirmRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateRegistration(registerUsernameTextField.getText(), registerPasswordTextField.getText(), registerConfirmPassTextField.getText());
                    profileService.createProfile(registerUsernameTextField.getText(), PasswordHelper.md5get(registerPasswordTextField.getText()));
                    registerUsernameTextField.setText("");
                    registerPasswordTextField.setText("");
                    registerConfirmPassTextField.setText("");
                    registerFrame.dispose();
                } catch (IllegalArgumentException iae) {
                    System.out.println("something went wrong :(");
                    resetFields(registerUsernameTextField, registerPasswordTextField, registerConfirmPassTextField);
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void resetFields(JTextField usernameTextField, JTextField passwordTextField, JTextField registerConfirmPassTextField) {
        usernameTextField.setText("");
        passwordTextField.setText("");
        registerConfirmPassTextField.setText("");
    }

    private void validateRegistration(String username, String password, String confPassword) {
        if (profileService.checkIfUserExists(username) || username.isEmpty() || password.isEmpty() || confPassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (!password.equals(confPassword)) {
            throw new IllegalArgumentException();
        }

    }

    private void initFrame(JFrame frame) {
        frame.setUndecorated(true);
        frame.setTitle("Enter your data");
        frame.setVisible(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        frame.setLocation(gd.getDisplayMode().getWidth() / 2 - 200, gd.getDisplayMode().getHeight() / 2 - 100);
        frame.requestFocus();
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setPlayerName(String name) {
        System.out.println(name);
        if (name.equals("") || name.contains(" ")) {
            playerName = "NoName";
        } else {
            playerName = name;
        }
    }
}