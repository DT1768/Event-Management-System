package com.ems;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class EventManagerAttendee extends JFrame implements ActionListener {

    int role;
    String username;

    JButton register;
    JButton fetch,back,signOut;

    JLabel heading;
    JLabel eventListLabel;
    JLabel status;

    JComboBox eventList;

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollpane;

    public EventManagerAttendee(int role, String username) {
        this.role = role;
        this.username = username;

        Border border = BorderFactory.createLineBorder(new Color(0x000000),5);
        Font font =  new Font("Monospaced",Font.BOLD,25);
        Color color = new Color(0xFFFFFF);

        heading = new JLabel();
        heading.setText("Event Manager");
        heading.setBounds(0,0,500,50);
        heading.setHorizontalTextPosition(JLabel.LEFT);
        heading.setVerticalTextPosition(JLabel.CENTER);
        heading.setForeground(new Color(0x000000));
        heading.setFont(new Font("MV Boli",Font.BOLD,25));
        heading.setIconTextGap(10);
        heading.setBorder(border);
        heading.setVerticalAlignment(JLabel.TOP);
        heading.setHorizontalAlignment(JLabel.CENTER);

        eventListLabel = new JLabel();
        eventListLabel.setText("Select Event:");
        eventListLabel.setBounds(10,100,400,50);
        eventListLabel.setHorizontalTextPosition(JLabel.LEFT);
        eventListLabel.setVerticalTextPosition(JLabel.CENTER);
        eventListLabel.setForeground(new Color(0x000000));
        eventListLabel.setFont(font);
        eventListLabel.setIconTextGap(10);
        eventListLabel.setBorder(border);
        eventListLabel.setVerticalAlignment(JLabel.TOP);
        eventListLabel.setHorizontalAlignment(JLabel.CENTER);

            Connection con;
            con = connection.connectDB();
            PreparedStatement ps;
            int i = 0;
            try{
                String SQL = "SELECT COUNT(DISTINCT(name)) AS eventtotal FROM event";
                ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery(SQL);

                while(rs.next()){
                    i = rs.getInt("eventtotal");
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            String[] eventList1 = new String[i+1];
            eventList1[0] = "select";
            try{
                String SQL = "SELECT name FROM event";
                ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery(SQL);
                int j = 1;
                while(rs.next() && j<i+1){
                    eventList1[j] = rs.getString("name");
                    j++;
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            eventList = new JComboBox(eventList1);
        eventList.setBounds(10,160,400,50);

        register = new JButton();
        register.setBounds (100,220,100,50);
        register.addActionListener(this);
        register.setText("Register");
        register.setFocusable(false);
        register.setFont(new Font("Comic Sans",Font.BOLD,12));
        register.setForeground(Color.BLACK);

        fetch = new JButton();
        fetch.setBounds (210,220,100,50);
        fetch.addActionListener(this);
        fetch.setText("Fetch");
        fetch.setFocusable(false);
        fetch.setFont(new Font("Comic Sans",Font.BOLD,12));
        fetch.setForeground(Color.BLACK);

        back = new JButton();
        back.setBounds (100,280,100,50);
        back.addActionListener(this);
        back.setText("Back");
        back.setFocusable(false);
        back.setFont(new Font("Comic Sans",Font.BOLD,12));
        back.setForeground(Color.BLACK);

        signOut = new JButton();
        signOut.setBounds (210,280,100,50);
        signOut.addActionListener(this);
        signOut.setText("Log out");
        signOut.setFocusable(false);
        signOut.setFont(new Font("Comic Sans",Font.BOLD,12));
        signOut.setForeground(Color.BLACK);

        status = new JLabel();
        status.setText("Status");
        status.setBounds(0,410,500,50);
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

        model = new DefaultTableModel(new String[]{"Id","Name","Description","Date","Month","Year","Time","Event","Speaker","Location"},0);

        table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setMaxWidth(100);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(3).setMaxWidth(50);
        table.getColumnModel().getColumn(4).setMaxWidth(50);
        table.getColumnModel().getColumn(5).setMaxWidth(50);
        table.getColumnModel().getColumn(6).setMaxWidth(80);
        table.getColumnModel().getColumn(7).setMaxWidth(100);
        table.getColumnModel().getColumn(8).setMaxWidth(100);
        table.getColumnModel().getColumn(9).setMaxWidth(100);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
        table.getColumnModel().getColumn(9).setPreferredWidth(100);
        table.getTableHeader().setReorderingAllowed(false);
        table.setOpaque(false);
        table.setBackground(new Color(0xdfcdcd));

        scrollpane = new JScrollPane(table);
        scrollpane.setBounds(510,10,880,460);
        scrollpane.setOpaque(false);
        scrollpane.getViewport().setBackground(new Color(0xcddfcd));

        this.setDefaultCloseOperation(EventManager.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1400,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Event Manager");
        this.getContentPane().setBackground(new Color(0xADD8E6));
        this.add(heading);
        this.add(eventListLabel);
        this.add(eventList);
        this.add(register);
        this.add(fetch);
        this.add(status);
        this.add(scrollpane);
        this.add(signOut);
        this.add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register){
            if(!Objects.equals(String.valueOf(eventList.getItemAt(eventList.getSelectedIndex())), "select")){
                register();
                fetch();
                status.setText("Registered");
            }
            else{
                status.setText("Please select event");
            }
        }
        else if(e.getSource() == fetch){
            fetch();
        }
        else if(e.getSource() == back){
            new Dashboard(role,username);
            this.dispose();
        }
        else if(e.getSource() == signOut){
            new SignInPage();
            this.dispose();
        }
    }

    public void register(){
        int user = getUserID(username);
        int event = getEventId(String.valueOf(eventList.getItemAt(eventList.getSelectedIndex())));

        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;

        try{
            String SQL = "insert into registration(user,event) values (?,?)";
            ps = con.prepareStatement(SQL);
            ps.setInt(1,user);
            ps.setInt(2,event);
            ps.execute();
        }
        catch (Exception e){

        }
    }

    public void fetch(){
        model.getDataVector().removeAllElements();
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;
        int i = 0;
        try{
            String SQL = "SELECT COUNT(DISTINCT(event)) AS events FROM registration WHERE user = " + getUserID(username);
            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while(rs.next()) {
                i = rs.getInt("events");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        int events[];
        events = new int[i];
        try{
            int j = 0;
            String SQL = "SELECT DISTINCT(event) AS events FROM registration WHERE user = " + getUserID(username);
            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while(rs.next() && j<=i) {
                events[j] = rs.getInt("events");
                j++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        for(int j = 0; j<events.length; j++){
            try{
                String sqltable = "SELECT * FROM talk WHERE event = "+ events[j];
                ps = con.prepareStatement(sqltable);
                ResultSet rs = ps.executeQuery(sqltable);
                while(rs.next()){
                    int a = rs.getInt("id");
                    String b = rs.getString("name");
                    String c = rs.getString("description");
                    int d = rs.getInt("date");
                    int e = rs.getInt("month");
                    int f = rs.getInt("year");
                    String g = rs.getString("time");
                    String h = getEvent(rs.getInt("event"));
                    String k = getSpeaker(rs.getInt("speaker"));
                    String l = getLocation(rs.getInt("location"));
                    model.addRow(new Object[]{a, b, c, d,e,f,g,h,k,l});
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public int getUserID(String s){
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;
        int userId= -1;
        try{
            String SQL = "SELECT userId from users WHERE username="+"\""+s+"\"";
            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while(rs.next()){
                userId = rs.getInt("userId");
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return userId;
    }

    public int getEventId(String s){
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;
        int event = -1;
        try{
            String SQL = "SELECT id from event WHERE name="+"\""+s+"\"";
            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while(rs.next()){
                event = rs.getInt("id");
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return event;
    }

    public String getEvent(int i){
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;
        String event = null;
        try{
            String SQL = "SELECT name from event WHERE id="+"\""+i+"\"";
            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while(rs.next()){
                event = rs.getString("name");
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return event;
    }

    public String getSpeaker(int i){
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;
        String speaker = null;
        try{
            String SQL = "SELECT username from users WHERE userId="+"\""+i+"\"";
            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while(rs.next()){
                speaker = rs.getString("username");
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return speaker;
    }

    public String getLocation(int i){
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;
        String location = null;
        try{
            String SQL = "SELECT name from location WHERE id="+"\""+i+"\"";
            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while(rs.next()){
                location = rs.getString("name");
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return location;
    }
}
