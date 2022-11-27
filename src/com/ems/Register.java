package com.ems;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends JFrame implements ActionListener {
    int role;

    JLabel heading,status,nameLabel,usernameLabel,passwordLabel,emailLabel,userwarn,passwarn;
    JButton submit,back;

    JTextField name, username, password, email;

    public Register(int role){
        this.role  = role;
        Border border = BorderFactory.createLineBorder(new Color(0x000000),5);
        Font font =  new Font("Monospaced",Font.BOLD,25);
        Color color = new Color(0xFFFFFF);

        heading = new JLabel();
        heading.setText("Register");
        heading.setBounds(0,0,500,50);
        heading.setHorizontalTextPosition(JLabel.LEFT);
        heading.setVerticalTextPosition(JLabel.CENTER);
        heading.setForeground(new Color(0x000000));
        heading.setFont(new Font("MV Boli",Font.BOLD,25));
        heading.setIconTextGap(10);
        heading.setBorder(border);
        heading.setVerticalAlignment(JLabel.TOP);
        heading.setHorizontalAlignment(JLabel.CENTER);

        nameLabel = new JLabel();
        nameLabel.setText("Name:");
        nameLabel.setBounds(10,100,200,50);
        nameLabel.setHorizontalTextPosition(JLabel.LEFT);
        nameLabel.setVerticalTextPosition(JLabel.CENTER);
        nameLabel.setForeground(new Color(0x000000));
        nameLabel.setFont(font);
        nameLabel.setIconTextGap(10);
        nameLabel.setBorder(border);
        nameLabel.setVerticalAlignment(JLabel.TOP);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        name = new JTextField();
        name.setFont(font);
        name.setBounds(250,100,200,50);
        name.setBackground(color);
        name.setBorder(border);

        usernameLabel = new JLabel();
        usernameLabel.setText("User Name:");
        usernameLabel.setBounds(10,160,200,50);
        usernameLabel.setHorizontalTextPosition(JLabel.LEFT);
        usernameLabel.setVerticalTextPosition(JLabel.CENTER);
        usernameLabel.setForeground(new Color(0x000000));
        usernameLabel.setFont(font);
        usernameLabel.setIconTextGap(10);
        usernameLabel.setBorder(border);
        usernameLabel.setVerticalAlignment(JLabel.TOP);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);

        userwarn = new JLabel();
        userwarn.setText("<html>UserID:   6-20 characters,  Must start with an alphabet.Can contain only alphabets, numbers and underscore.(No spaces)</html>");
        userwarn.setBounds(10,220,470,40);
        userwarn.setHorizontalTextPosition(JLabel.LEFT);
        userwarn.setVerticalTextPosition(JLabel.CENTER);
        userwarn.setOpaque(true);
        userwarn.setBackground(Color.BLACK);
        userwarn.setForeground(Color.WHITE);
        userwarn.setFont(new Font("Courier", Font.BOLD,12));
        userwarn.setBorder(BorderFactory.createLineBorder(new Color(0x000000),1));
        userwarn.setIconTextGap(10);
        userwarn.setVerticalAlignment(JLabel.TOP);
        userwarn.setHorizontalAlignment(JLabel.CENTER);

        username = new JTextField();
        username.setFont(font);
        username.setBounds(250,160,200,50);
        username.setBackground(color);
        username.setBorder(border);

        passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setBounds(10,270,200,50);
        passwordLabel.setHorizontalTextPosition(JLabel.LEFT);
        passwordLabel.setVerticalTextPosition(JLabel.CENTER);
        passwordLabel.setForeground(new Color(0x000000));
        passwordLabel.setFont(font);
        passwordLabel.setIconTextGap(10);
        passwordLabel.setBorder(border);
        passwordLabel.setVerticalAlignment(JLabel.TOP);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        passwarn = new JLabel();
        passwarn.setText("<html>Password:    8-20 characters, Must Contain a number,special character,upper case & lower case letter.(No spaces)</html>");
        passwarn.setBounds(10,330,470,40);
        passwarn.setHorizontalTextPosition(JLabel.LEFT);
        passwarn.setVerticalTextPosition(JLabel.CENTER);
        passwarn.setOpaque(true);
        passwarn.setBackground(Color.BLACK);
        passwarn.setForeground(Color.WHITE);
        passwarn.setFont(new Font("Courier", Font.BOLD,12));
        passwarn.setBorder(BorderFactory.createLineBorder(new Color(0x000000),1));
        passwarn.setIconTextGap(10);
        passwarn.setVerticalAlignment(JLabel.TOP);
        passwarn.setHorizontalAlignment(JLabel.CENTER);

        password = new JTextField();
        password.setFont(font);
        password.setBounds(250,270,200,50);
        //passwordText.setEchoChar('*');
        password.setBackground(color);
        password.setBorder(border);

        emailLabel = new JLabel();
        emailLabel.setText("Email:");
        emailLabel.setBounds(10,380,200,50);
        emailLabel.setHorizontalTextPosition(JLabel.LEFT);
        emailLabel.setVerticalTextPosition(JLabel.CENTER);
        emailLabel.setForeground(new Color(0x000000));
        emailLabel.setFont(font);
        emailLabel.setIconTextGap(10);
        emailLabel.setBorder(border);
        emailLabel.setVerticalAlignment(JLabel.TOP);
        emailLabel.setHorizontalAlignment(JLabel.CENTER);

        email = new JTextField();
        email.setFont(new Font("MV Boli",Font.BOLD,20));
        email.setBounds(250,380,200,50);
        email.setBackground(color);
        email.setBorder(border);

        submit = new JButton();
        submit.setBounds (100,450,100,50);
        submit.addActionListener(this);
        submit.setText("Submit");
        submit.setFocusable(false);
        submit.setFont(new Font("Comic Sans",Font.BOLD,12));
        submit.setForeground(Color.BLACK);

        back = new JButton();
        back.setBounds (320,450,100,50);
        back.addActionListener(this);
        back.setText("Back");
        back.setFocusable(false);
        back.setFont(new Font("Comic Sans",Font.BOLD,12));
        back.setForeground(Color.BLACK);

        status = new JLabel();
        status.setText("Status");
        status.setBounds(0,510,500,50);
        status.setHorizontalTextPosition(JLabel.LEFT);
        status.setVerticalTextPosition(JLabel.CENTER);
        status.setBackground(color);
        status.setOpaque(true);
        status.setForeground(new Color(0x000000));
        status.setFont(new Font("MV Boli",Font.BOLD,20));
        status.setIconTextGap(10);
        status.setBorder(border);
        status.setVerticalAlignment(JLabel.TOP);
        status.setHorizontalAlignment(JLabel.CENTER);

        this.setDefaultCloseOperation(Register.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(520,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Task Manager");
        this.getContentPane().setBackground(new Color(0xADD8E6));
        this.add(heading);
        this.add(nameLabel);
        this.add(name);
        this.add(usernameLabel);
        this.add(userwarn);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        this.add(passwarn);
        this.add(emailLabel);
        this.add(email);
        this.add(submit);
        this.add(back);
        this.add(status);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            if(validCreate()){
                create();
                status.setText("User created Successfully");
                reset();
            }
            else{
                errorMessage();
            }
        }
        if(e.getSource() == back){
            this.dispose();
            new SignInPage();
        }
    }

    public boolean emailValidation(){
        String Email = email.getText();
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Email);
        return matcher.matches();
    }

    public boolean passwordValidation(){
        String pass = password.getText();
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    public boolean userIDValidation(){
        String user = username.getText();
        String regex = "^[A-Za-z]\\w{5,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user);
        return matcher.matches();
    }

    public boolean uniqueUserName(){
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;

        String user = username.getText();
        boolean unique = true;

        try {

            String sql = "select * from users where BINARY username = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,user);
            ResultSet s = ps.executeQuery();

            if(s.next()){
                unique = false;
            }
            else{
                unique = true;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return unique;
    }

    public boolean uniqueEmail(){
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;

        String Email = email.getText();
        boolean unique = true;

        try {

            String sql = "select * from users where BINARY email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,Email);
            ResultSet s = ps.executeQuery();

            if(s.next()){
                unique = false;
            }
            else{
                unique = true;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return unique;
    }

    public boolean emptyCheck(){
        return name.getText().isEmpty();
    }

    public boolean validCreate(){
        return emailValidation() && passwordValidation() && userIDValidation() && uniqueUserName() && uniqueEmail() && !emptyCheck();
    }

    public void create(){
        String n = name.getText();
        String id = username.getText();
        String pass = password.getText();
        String em = email.getText();

        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;

        try {
            String sql = "insert into users(name,username,email,password,role) values(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,n);
            ps.setString(2,id);
            ps.setString(3,em);
            ps.setString(4,pass);
            ps.setInt(5,role);
            ps.execute();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void errorMessage(){
        if(!userIDValidation()){
            status.setText("Invalid UserID");
        }
        else if(!passwordValidation()){
            status.setText("Invalid Password");
        }
        else if(!emailValidation()){
            status.setText("Invalid Email");
        }
        else if(!uniqueUserName()){
            status.setText("User Already Exist");
        }
        else if(!uniqueEmail()){
            status.setText("Email Already Exist");
        }
        else if(emptyCheck()){
            if(name.getText().isEmpty()){
                status.setText("Name can't be empty");
            }
        }
    }

    public void reset(){
        name.setText("");
        username.setText("");
        password.setText("");
        email.setText("");
    }

}
