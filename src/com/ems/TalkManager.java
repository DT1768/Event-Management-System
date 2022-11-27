package com.ems;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Objects;

public class TalkManager extends JFrame implements ActionListener, MouseListener {

    int role;
    String username;

    JButton create;
    JButton fetch;
    JButton back;
    JButton signOut;
    JButton update;
    JButton reset;

    JLabel heading;
    JLabel talkNameLabel;
    JLabel descriptionLabel;
    JLabel dateLabel;
    JLabel timeLabel;
    JLabel eventLabel;
    JLabel speakerLabel;
    JLabel locationLabel;
    JLabel status;
    JLabel eventName;
    JLabel speakerName;
    JLabel locationName;

    JTextField talkName;
    JTextArea description;
    JComboBox startDate,startMonth,startYear,startTime,event,speaker,location;

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollpane;

    public TalkManager(int role, String username) {
        this.role = role;
        this.username = username;

        Border border = BorderFactory.createLineBorder(new Color(0x000000),5);
        Font font =  new Font("Monospaced",Font.BOLD,25);
        Color color = new Color(0xFFFFFF);

        heading = new JLabel();
        heading.setText("Talk Manager");
        heading.setBounds(0,0,500,50);
        heading.setHorizontalTextPosition(JLabel.LEFT);
        heading.setVerticalTextPosition(JLabel.CENTER);
        heading.setForeground(new Color(0x000000));
        heading.setFont(new Font("MV Boli",Font.BOLD,25));
        heading.setIconTextGap(10);
        heading.setBorder(border);
        heading.setVerticalAlignment(JLabel.TOP);
        heading.setHorizontalAlignment(JLabel.CENTER);

        talkNameLabel = new JLabel();
        talkNameLabel.setText("Talk Name:");
        talkNameLabel.setBounds(10,100,200,50);
        talkNameLabel.setHorizontalTextPosition(JLabel.LEFT);
        talkNameLabel.setVerticalTextPosition(JLabel.CENTER);
        talkNameLabel.setForeground(new Color(0x000000));
        talkNameLabel.setFont(font);
        talkNameLabel.setIconTextGap(10);
        talkNameLabel.setBorder(border);
        talkNameLabel.setVerticalAlignment(JLabel.TOP);
        talkNameLabel.setHorizontalAlignment(JLabel.CENTER);

        talkName = new JTextField();
        talkName.setFont(font);
        talkName.setBounds(250,100,200,50);
        talkName.setBackground(color);
        talkName.setBorder(border);

        descriptionLabel = new JLabel();
        descriptionLabel.setText("Description:");
        descriptionLabel.setBounds(10,160,200,50);
        descriptionLabel.setHorizontalTextPosition(JLabel.LEFT);
        descriptionLabel.setVerticalTextPosition(JLabel.CENTER);
        descriptionLabel.setForeground(new Color(0x000000));
        descriptionLabel.setFont(font);
        descriptionLabel.setIconTextGap(10);
        descriptionLabel.setBorder(border);
        descriptionLabel.setVerticalAlignment(JLabel.TOP);
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);

        description = new JTextArea();
        description.setBounds(250,160,200,150);
        description.setBackground(color);
        description.setBorder(border);

        dateLabel = new JLabel();
        dateLabel.setText("Date:");
        dateLabel.setBounds(10,320,200,50);
        dateLabel.setHorizontalTextPosition(JLabel.CENTER);
        dateLabel.setVerticalTextPosition(JLabel.CENTER);
        dateLabel.setForeground(new Color(0x000000));
        dateLabel.setFont(font);
        dateLabel.setIconTextGap(10);
        dateLabel.setBorder(border);
        dateLabel.setVerticalAlignment(JLabel.TOP);
        dateLabel.setHorizontalAlignment(JLabel.CENTER);

        Integer[] date = new Integer[31];
        for(int i=1;i<=31;i++){
            date[i-1] = i;
        }
        Integer[] month = new Integer[12];
        for(int i=1;i<=12;i++){
            month[i-1] = i;
        }
        Integer[] year = new Integer[5];
        for(int i=1;i<=5;i++){
            year[i-1] = i + 2021;
        }


        startDate = new JComboBox(date);
        startDate.setBounds(250,320,50,50);

        startMonth = new JComboBox(month);
        startMonth.setBounds(310,320,50,50);

        startYear = new JComboBox(year);
        startYear.setBounds(370,320,100,50);

        timeLabel = new JLabel();
        timeLabel.setText("Time:");
        timeLabel.setBounds(10,380,200,50);
        timeLabel.setHorizontalTextPosition(JLabel.CENTER);
        timeLabel.setVerticalTextPosition(JLabel.CENTER);
        timeLabel.setForeground(new Color(0x000000));
        timeLabel.setFont(font);
        timeLabel.setIconTextGap(10);
        timeLabel.setBorder(border);
        timeLabel.setVerticalAlignment(JLabel.TOP);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        String[] time = {"9 AM", "10 AM","11 AM", "12 PM", "1 PM", "2 PM", "3 PM", "4 PM", "5 PM"};
        startTime = new JComboBox(time);
        startTime.setBounds(250,380,200,50);

        eventLabel = new JLabel();
        eventLabel.setText("Event:");
        eventLabel.setBounds(10,440,200,50);
        eventLabel.setHorizontalTextPosition(JLabel.CENTER);
        eventLabel.setVerticalTextPosition(JLabel.CENTER);
        eventLabel.setForeground(new Color(0x000000));
        eventLabel.setFont(font);
        eventLabel.setIconTextGap(10);
        eventLabel.setBorder(border);
        eventLabel.setVerticalAlignment(JLabel.TOP);
        eventLabel.setHorizontalAlignment(JLabel.CENTER);

        if(role == 1){
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
            String[] eventList = new String[i+1];
            eventList[0] = "select";
            try{
                String SQL = "SELECT name FROM event";
                ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery(SQL);
                int j = 1;
                while(rs.next() && j<i+1){
                    eventList[j] = rs.getString("name");
                    j++;
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            event = new JComboBox(eventList);
            event.setBounds(250,440,200,50);

        }

        eventName = new JLabel();
        eventName.setText("Please Select talk");
        eventName.setBounds(250,440,200,50);
        eventName.setHorizontalTextPosition(JLabel.LEFT);
        eventName.setVerticalTextPosition(JLabel.CENTER);
        eventName.setForeground(new Color(0x000000));
        eventName.setFont(font);
        eventName.setIconTextGap(10);
        eventName.setBorder(border);
        eventName.setVerticalAlignment(JLabel.TOP);
        eventName.setHorizontalAlignment(JLabel.CENTER);

        speakerLabel = new JLabel();
        speakerLabel.setText("Speaker:");
        speakerLabel.setBounds(10,500,200,50);
        speakerLabel.setHorizontalTextPosition(JLabel.CENTER);
        speakerLabel.setVerticalTextPosition(JLabel.CENTER);
        speakerLabel.setForeground(new Color(0x000000));
        speakerLabel.setFont(font);
        speakerLabel.setIconTextGap(10);
        speakerLabel.setBorder(border);
        speakerLabel.setVerticalAlignment(JLabel.TOP);
        speakerLabel.setHorizontalAlignment(JLabel.CENTER);

        if(role == 1){
            Connection con;
            con = connection.connectDB();
            PreparedStatement ps;
            int i = 0;
            try{
                String SQL = "SELECT COUNT(DISTINCT(username)) AS speakertotal FROM users WHERE role = 2";
                ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery(SQL);

                while(rs.next()){
                    i = rs.getInt("speakertotal");
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            String[] speakerList = new String[i+1];
            speakerList[0] = "select";
            try{
                String SQL = "SELECT username FROM users WHERE role = 2";
                ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery(SQL);
                int j = 1;
                while(rs.next() && j<i+1){
                    speakerList[j] = rs.getString("username");
                    j++;
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            speaker = new JComboBox(speakerList);
            speaker.setBounds(250,500,200,50);
        }


        speakerName = new JLabel();
        speakerName.setText("Please Select talk");
        speakerName.setBounds(250,500,200,50);
        speakerName.setHorizontalTextPosition(JLabel.LEFT);
        speakerName.setVerticalTextPosition(JLabel.CENTER);
        speakerName.setForeground(new Color(0x000000));
        speakerName.setFont(font);
        speakerName.setIconTextGap(10);
        speakerName.setBorder(border);
        speakerName.setVerticalAlignment(JLabel.TOP);
        speakerName.setHorizontalAlignment(JLabel.CENTER);

        locationLabel = new JLabel();
        locationLabel.setText("Location:");
        locationLabel.setBounds(10,560,200,50);
        locationLabel.setHorizontalTextPosition(JLabel.CENTER);
        locationLabel.setVerticalTextPosition(JLabel.CENTER);
        locationLabel.setForeground(new Color(0x000000));
        locationLabel.setFont(font);
        locationLabel.setIconTextGap(10);
        locationLabel.setBorder(border);
        locationLabel.setVerticalAlignment(JLabel.TOP);
        locationLabel.setHorizontalAlignment(JLabel.CENTER);

        if(role == 1){
            Connection con;
            con = connection.connectDB();
            PreparedStatement ps;
            int i = 0;
            try{
                String SQL = "SELECT COUNT(DISTINCT(name)) AS locationtotal FROM location";
                ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery(SQL);

                while(rs.next()){
                    i = rs.getInt("locationtotal");
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            String[] locationList = new String[i+1];
            locationList[0] = "select";
            try{
                String SQL = "SELECT name FROM location";
                ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery(SQL);
                int j = 1;
                while(rs.next() && j<i+1){
                    locationList[j] = rs.getString("name");
                    j++;
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            location = new JComboBox(locationList);
            location.setBounds(250,560,200,50);
        }


        locationName = new JLabel();
        locationName.setText("Please Select talk");
        locationName.setBounds(250,560,200,50);
        locationName.setHorizontalTextPosition(JLabel.LEFT);
        locationName.setVerticalTextPosition(JLabel.CENTER);
        locationName.setForeground(new Color(0x000000));
        locationName.setFont(font);
        locationName.setIconTextGap(10);
        locationName.setBorder(border);
        locationName.setVerticalAlignment(JLabel.TOP);
        locationName.setHorizontalAlignment(JLabel.CENTER);

        create = new JButton();
        create.setBounds (100,620,100,50);
        create.addActionListener(this);
        create.setText("Create");
        create.setFocusable(false);
        create.setFont(new Font("Comic Sans",Font.BOLD,12));
        create.setForeground(Color.BLACK);

        fetch = new JButton();
        fetch.setBounds (210,620,100,50);
        fetch.addActionListener(this);
        fetch.setText("Fetch");
        fetch.setFocusable(false);
        fetch.setFont(new Font("Comic Sans",Font.BOLD,12));
        fetch.setForeground(Color.BLACK);

        signOut = new JButton();
        signOut.setBounds (320,620,100,50);
        signOut.addActionListener(this);
        signOut.setText("Log out");
        signOut.setFocusable(false);
        signOut.setFont(new Font("Comic Sans",Font.BOLD,12));
        signOut.setForeground(Color.BLACK);

        update = new JButton();
        update.setBounds (100,680,100,50);
        update.addActionListener(this);
        update.setText("Update");
        update.setFocusable(false);
        update.setFont(new Font("Comic Sans",Font.BOLD,12));
        update.setForeground(Color.BLACK);

        reset = new JButton();
        reset.setBounds (210,680,100,50);
        reset.addActionListener(this);
        reset.setText("Reset");
        reset.setFocusable(false);
        reset.setFont(new Font("Comic Sans",Font.BOLD,12));
        reset.setForeground(Color.BLACK);

        back = new JButton();
        back.setBounds (320,680,100,50);
        back.addActionListener(this);
        back.setText("Back");
        back.setFocusable(false);
        back.setFont(new Font("Comic Sans",Font.BOLD,12));
        back.setForeground(Color.BLACK);

        status = new JLabel();
        status.setText("Status");
        status.setBounds(0,740,500,50);
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
        table.addMouseListener(this);

        scrollpane = new JScrollPane(table);
        scrollpane.setBounds(510,10,880,780);
        scrollpane.setOpaque(false);
        scrollpane.getViewport().setBackground(new Color(0xcddfcd));

        this.setDefaultCloseOperation(EventManager.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1400,840);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Talk Manager");
        this.getContentPane().setBackground(new Color(0xADD8E6));
        this.add(heading);
        this.add(talkNameLabel);
        this.add(talkName);
        this.add(descriptionLabel);
        this.add(description);
        this.add(dateLabel);
        this.add(startDate);
        this.add(startMonth);
        this.add(startYear);
        this.add(timeLabel);
        this.add(startTime);
        this.add(eventLabel);
        this.add(speakerLabel);
        this.add(locationLabel);
        if(role == 1){
            this.add(event);
            this.add(speaker);
            this.add(location);
            this.add(create);
        }
        else if(role == 2){
            this.add(eventName);
            this.add(speakerName);
            this.add(locationName);
        }

        this.add(status);
        this.add(scrollpane);
        this.add(fetch);
        this.add(signOut);
        this.add(back);
        this.add(update);
        this.add(reset);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create){
            System.out.println();
            if(validCreate()){
                create();
                status.setText("Talk Created Successfully");
                fetch();
            }
            else{
                status.setText("Invalid Date Range");
            }
        }
        else if(e.getSource() == update){
            if(role == 2 ||validCreate()){
                update();
                fetch();
            }
        }
        else if(e.getSource() == fetch){
            fetch();
        }
        else if(e.getSource() == reset){
            reset();
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

    @Override
    public void mouseClicked(MouseEvent e) {
        int index = table.getSelectedRow();
        talkName.setText(model.getValueAt(index,1).toString());
        description.setText(model.getValueAt(index,2).toString());
        startDate.setSelectedItem(Integer.parseInt(model.getValueAt(index,3).toString()));
        startMonth.setSelectedItem(Integer.parseInt(model.getValueAt(index,4).toString()));
        startYear.setSelectedItem(Integer.parseInt(model.getValueAt(index,5).toString()));
        startTime.setSelectedItem(model.getValueAt(index,6).toString());
        if(role == 1){
            event.setSelectedItem(model.getValueAt(index, 7).toString());
            speaker.setSelectedItem(model.getValueAt(index, 8).toString());
            location.setSelectedItem(model.getValueAt(index, 9).toString());
        }
        else {
            eventName.setText(model.getValueAt(index,7).toString());
            speakerName.setText(model.getValueAt(index,8).toString());
            locationName.setText(model.getValueAt(index,9).toString());
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

    public boolean validCreate(){
        return !talkName.getText().isEmpty() && !description.getText().isEmpty() && !Objects.equals(String.valueOf(event.getItemAt(event.getSelectedIndex())), "select") && !Objects.equals(String.valueOf(speaker.getItemAt(speaker.getSelectedIndex())), "select") && !Objects.equals(String.valueOf(location.getItemAt(location.getSelectedIndex())), "select");
    }

    public void create(){
        String name = talkName.getText();
        String desc = description.getText();
        int date = Integer.parseInt(String.valueOf(startDate.getItemAt(startDate.getSelectedIndex())));
        int month = Integer.parseInt(String.valueOf(startMonth.getItemAt(startMonth.getSelectedIndex())));
        int year = Integer.parseInt(String.valueOf(startYear.getItemAt(startYear.getSelectedIndex())));
        String time = String.valueOf(startTime.getItemAt(startTime.getSelectedIndex()));
        int e = getEventId(String.valueOf(event.getItemAt(event.getSelectedIndex())));
        int s = getUserID(String.valueOf(speaker.getItemAt(speaker.getSelectedIndex())));
        int l = getLocationId(String.valueOf(location.getItemAt(location.getSelectedIndex())));
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;

        try {
            String sql = "insert into talk(name,description,date,month,year,time,event,speaker,location) values(?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,desc);
            ps.setInt(3,date);
            ps.setInt(4,month);
            ps.setInt(5,year);
            ps.setString(6,time);
            if(e != -1){
                ps.setInt(7,e);
            }
            else{
                ps.setNull(7, Types.NULL);
            }
            if(s != -1){
                ps.setInt(8,s);
            }
            else{
                ps.setNull(8, Types.NULL);
            }
            if(l != -1){
                ps.setInt(9,l);
            }
            else{
                ps.setNull(9, Types.NULL);
            }
            ps.execute();
        }
        catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void update(){
        int index = table.getSelectedRow();
        int i = Integer.parseInt(model.getValueAt(index, 0).toString());

        String name = talkName.getText();
        String desc = description.getText();
        int date = Integer.parseInt(String.valueOf(startDate.getItemAt(startDate.getSelectedIndex())));
        int month = Integer.parseInt(String.valueOf(startMonth.getItemAt(startMonth.getSelectedIndex())));
        int year = Integer.parseInt(String.valueOf(startYear.getItemAt(startYear.getSelectedIndex())));
        String time = String.valueOf(startTime.getItemAt(startTime.getSelectedIndex()));

        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;
        try {
            String SQL = "UPDATE talk SET name = ?,description = ?, date = ?,month = ?, year = ?, time = ? WHERE id = ?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setInt(3, date);
            ps.setInt(4, month);
            ps.setInt(5, year);
            ps.setString(6, time);
            ps.setInt(7, i);
            ps.execute();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void fetch(){
        model.getDataVector().removeAllElements();
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;

        try{
            String sqltable = null;
            if(role == 1){
                sqltable = "SELECT * from talk";
            }
            else if(role == 2){
                sqltable = "SELECT * from talk WHERE speaker = " + getUserID(username);
            }
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
                String i = getSpeaker(rs.getInt("speaker"));
                String j = getLocation(rs.getInt("location"));
                model.addRow(new Object[]{a, b, c, d,e,f,g,h,i,j});
            }
        }
        catch (Exception e){
            System.out.println(e);
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

    public int getLocationId(String s){
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;
        int location = -1;
        try{
            String SQL = "SELECT id from location WHERE name="+"\""+s+"\"";
            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while(rs.next()){
                location = rs.getInt("id");
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return location;
    }

    public void reset(){
        talkName.setText("");
    }

}
