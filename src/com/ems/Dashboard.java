package com.ems;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    int role;
    String username;

    JButton eventManager, talkManager, locationManager ,speakerManager,signOut, eventManagerAttendee;

    JLabel welcome;
    public Dashboard(int role, String username) {
        this.role = role;
        this.username = username;

        Border border = BorderFactory.createLineBorder(new Color(0x000000),5);

        welcome = new JLabel();
        welcome.setText( username +"'s Dashboard");
        welcome.setBounds(0,0,500,50);
        welcome.setHorizontalTextPosition(JLabel.LEFT);
        welcome.setVerticalTextPosition(JLabel.CENTER);
        welcome.setForeground(new Color(0x000000));
        welcome.setFont(new Font("MV Boli",Font.BOLD,25));
        welcome.setIconTextGap(10);
        welcome.setBorder(border);
        welcome.setVerticalAlignment(JLabel.TOP);
        welcome.setHorizontalAlignment(JLabel.CENTER);

        eventManager = new JButton();
        eventManager.setBounds (25,60,200,50);
        eventManager.addActionListener(this);
        eventManager.setText("Event Manager");
        eventManager.setFocusable(false);
        eventManager.setFont(new Font("Comic Sans",Font.BOLD,12));
        eventManager.setForeground(Color.BLACK);


        talkManager = new JButton();
        talkManager.setBounds (250,60,200,50);
        talkManager.addActionListener(this);
        talkManager.setText("Talk Manager");
        talkManager.setFocusable(false);
        talkManager.setFont(new Font("Comic Sans",Font.BOLD,12));
        talkManager.setForeground(Color.BLACK);

        eventManagerAttendee = new JButton();
        eventManagerAttendee.setBounds (250,60,200,50);
        eventManagerAttendee.addActionListener(this);
        eventManagerAttendee.setText("Event Manager");
        eventManagerAttendee.setFocusable(false);
        eventManagerAttendee.setFont(new Font("Comic Sans",Font.BOLD,12));
        eventManagerAttendee.setForeground(Color.BLACK);

        locationManager = new JButton();
        locationManager.setBounds (150,120,200,50);
        locationManager.addActionListener(this);
        locationManager.setText("Location Manager");
        locationManager.setFocusable(false);
        locationManager.setFont(new Font("Comic Sans",Font.BOLD,12));
        locationManager.setForeground(Color.BLACK);

        speakerManager = new JButton();
        speakerManager.setBounds (25,180,200,50);
        speakerManager.addActionListener(this);
        speakerManager.setText("Speaker Manager");
        speakerManager.setFocusable(false);
        speakerManager.setFont(new Font("Comic Sans",Font.BOLD,12));
        speakerManager.setForeground(Color.BLACK);

        signOut = new JButton();
        signOut.setBounds (250,180,200,50);
        signOut.addActionListener(this);
        signOut.setText("Logout");
        signOut.setFocusable(false);
        signOut.setFont(new Font("Comic Sans",Font.BOLD,12));
        signOut.setForeground(Color.BLACK);

        this.setDefaultCloseOperation(SignInPage.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Dashboard");
        this.getContentPane().setBackground(new Color(0xADD8E6));
        this.add(welcome);
        if(role == 1){
            this.add(eventManager);
            this.add(locationManager);
            this.add(speakerManager);
            this.add(talkManager);
        }
        else if(role == 2){
            this.add(talkManager);
        }
         else if(role == 3){
            this.add(eventManagerAttendee);
        }
        this.add(signOut);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == eventManager){
            new EventManager(role,username);
        }
        else if(e.getSource() == locationManager){
            new LocationManager(role,username);
        }
        else if(e.getSource() == speakerManager){
            new Register(2);
        }
        else if(e.getSource() == talkManager){
            new TalkManager(role,username);
        }
        else if(e.getSource() == eventManagerAttendee){
            new EventManagerAttendee(role,username);
        }
        else if(e.getSource() == signOut){
            new SignInPage();
        }
        this.dispose();
    }
}
