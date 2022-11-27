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

public class EventManager extends JFrame implements ActionListener, MouseListener {

    int role;
    String username;

    JButton create;
    JButton fetch;
    JButton back;
    JButton signOut;
    JButton update;
    JButton reset;

    JLabel heading;
    JLabel eventNameLabel;
    JLabel startDateLabel;
    JLabel endDateLabel;
    JLabel status;

    JTextField eventName;
    JComboBox startDate,startMonth,startYear,endDate,endMonth,endYear;

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollpane;

    EventManager(int role,String username){

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

        eventNameLabel = new JLabel();
        eventNameLabel.setText("Event Name:");
        eventNameLabel.setBounds(10,100,200,50);
        eventNameLabel.setHorizontalTextPosition(JLabel.LEFT);
        eventNameLabel.setVerticalTextPosition(JLabel.CENTER);
        eventNameLabel.setForeground(new Color(0x000000));
        eventNameLabel.setFont(font);
        eventNameLabel.setIconTextGap(10);
        eventNameLabel.setBorder(border);
        eventNameLabel.setVerticalAlignment(JLabel.TOP);
        eventNameLabel.setHorizontalAlignment(JLabel.CENTER);

        eventName = new JTextField();
        eventName.setFont(font);
        eventName.setBounds(250,100,200,50);
        eventName.setBackground(color);
        eventName.setBorder(border);

        startDateLabel = new JLabel();
        startDateLabel.setText("Start Date:");
        startDateLabel.setBounds(10,160,200,50);
        startDateLabel.setHorizontalTextPosition(JLabel.CENTER);
        startDateLabel.setVerticalTextPosition(JLabel.CENTER);
        startDateLabel.setForeground(new Color(0x000000));
        startDateLabel.setFont(font);
        startDateLabel.setIconTextGap(10);
        startDateLabel.setBorder(border);
        startDateLabel.setVerticalAlignment(JLabel.TOP);
        startDateLabel.setHorizontalAlignment(JLabel.CENTER);

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
        startDate.setBounds(250,160,50,50);

        startMonth = new JComboBox(month);
        startMonth.setBounds(310,160,50,50);

        startYear = new JComboBox(year);
        startYear.setBounds(370,160,100,50);

        endDateLabel = new JLabel();
        endDateLabel.setText("End Date:");
        endDateLabel.setBounds(10,220,200,50);
        endDateLabel.setHorizontalTextPosition(JLabel.CENTER);
        endDateLabel.setVerticalTextPosition(JLabel.CENTER);
        endDateLabel.setForeground(new Color(0x000000));
        endDateLabel.setFont(font);
        endDateLabel.setIconTextGap(10);
        endDateLabel.setBorder(border);
        endDateLabel.setVerticalAlignment(JLabel.TOP);
        endDateLabel.setHorizontalAlignment(JLabel.CENTER);


        endDate = new JComboBox(date);
        endDate.setBounds(250,220,50,50);
        endMonth = new JComboBox(month);
        endMonth.setBounds(310,220,50,50);
        endYear = new JComboBox(year);
        endYear.setBounds(370,220,100,50);

        create = new JButton();
        create.setBounds (100,280,100,50);
        create.addActionListener(this);
        create.setText("Create");
        create.setFocusable(false);
        create.setFont(new Font("Comic Sans",Font.BOLD,12));
        create.setForeground(Color.BLACK);

        fetch = new JButton();
        fetch.setBounds (210,280,100,50);
        fetch.addActionListener(this);
        fetch.setText("Fetch");
        fetch.setFocusable(false);
        fetch.setFont(new Font("Comic Sans",Font.BOLD,12));
        fetch.setForeground(Color.BLACK);

        signOut = new JButton();
        signOut.setBounds (320,280,100,50);
        signOut.addActionListener(this);
        signOut.setText("Log out");
        signOut.setFocusable(false);
        signOut.setFont(new Font("Comic Sans",Font.BOLD,12));
        signOut.setForeground(Color.BLACK);

        update = new JButton();
        update.setBounds (100,340,100,50);
        update.addActionListener(this);
        update.setText("Update");
        update.setFocusable(false);
        update.setFont(new Font("Comic Sans",Font.BOLD,12));
        update.setForeground(Color.BLACK);

        reset = new JButton();
        reset.setBounds (210,340,100,50);
        reset.addActionListener(this);
        reset.setText("Reset");
        reset.setFocusable(false);
        reset.setFont(new Font("Comic Sans",Font.BOLD,12));
        reset.setForeground(Color.BLACK);

        back = new JButton();
        back.setBounds (320,340,100,50);
        back.addActionListener(this);
        back.setText("Back");
        back.setFocusable(false);
        back.setFont(new Font("Comic Sans",Font.BOLD,12));
        back.setForeground(Color.BLACK);

        status = new JLabel();
        status.setText("Status");
        status.setBounds(0,400,500,50);
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

        model = new DefaultTableModel(new String[]{"Id","Name","Start Date","Start Month","Start Year","End Date","End Month","End Year"},0);

        table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setMaxWidth(80);
        table.getColumnModel().getColumn(3).setMaxWidth(80);
        table.getColumnModel().getColumn(4).setMaxWidth(80);
        table.getColumnModel().getColumn(5).setMaxWidth(80);
        table.getColumnModel().getColumn(6).setMaxWidth(80);
        table.getColumnModel().getColumn(7).setMaxWidth(80);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(80);
        table.getTableHeader().setReorderingAllowed(false);
        table.setOpaque(false);
        table.setBackground(new Color(0xdfcdcd));
        table.addMouseListener(this);

        scrollpane = new JScrollPane(table);
        scrollpane.setBounds(510,10,730,440);
        scrollpane.setOpaque(false);
        scrollpane.getViewport().setBackground(new Color(0xcddfcd));

        this.setDefaultCloseOperation(EventManager.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1270,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Task Manager");
        this.getContentPane().setBackground(new Color(0xADD8E6));
        this.add(heading);
        this.add(eventNameLabel);
        this.add(eventName);
        this.add(startDateLabel);
        this.add(startDate);
        this.add(startMonth);
        this.add(startYear);
        this.add(endDateLabel);
        this.add(endDate);
        this.add(endMonth);
        this.add(endYear);
        this.add(create);
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
                status.setText("Event Created Successfully");
                fetch();
            }
            else{
                status.setText("Invalid Date Range");
            }
        }
        else if(e.getSource() == update){
            if(validCreate()){
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
        eventName.setText(model.getValueAt(index,1).toString());
        startDate.setSelectedItem(Integer.parseInt(model.getValueAt(index,2).toString()));
        startMonth.setSelectedItem(Integer.parseInt(model.getValueAt(index,3).toString()));
        startYear.setSelectedItem(Integer.parseInt(model.getValueAt(index,4).toString()));
        endDate.setSelectedItem(Integer.parseInt(model.getValueAt(index,5).toString()));
        endMonth.setSelectedItem(Integer.parseInt(model.getValueAt(index,6).toString()));
        endDate.setSelectedItem(Integer.parseInt(model.getValueAt(index,7).toString()));
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
        boolean valid;
        int sd = Integer.parseInt(String.valueOf(startDate.getItemAt(startDate.getSelectedIndex())));
        int sm = Integer.parseInt(String.valueOf(startMonth.getItemAt(startMonth.getSelectedIndex())));
        int sy = Integer.parseInt(String.valueOf(startYear.getItemAt(startYear.getSelectedIndex())));
        int ed = Integer.parseInt(String.valueOf(endDate.getItemAt(endDate.getSelectedIndex())));
        int em = Integer.parseInt(String.valueOf(endMonth.getItemAt(endMonth.getSelectedIndex())));
        int ey = Integer.parseInt(String.valueOf(endYear.getItemAt(endYear.getSelectedIndex())));
        if(sy > ey){
            valid = false;
        }
        else if(sy == ey){
            if(sm > em){
                valid = false;
            }
            else if(sm == em){
                if(sd > ed){
                    valid = false;
                }
                else{
                    valid = true;
                }
            }
            else{
                valid = true;
            }
        }
        else{
            valid = true;
        }

        return valid && !eventName.getText().isEmpty();
    }

    public void create(){
        String name = eventName.getText();
        int sd = Integer.parseInt(String.valueOf(startDate.getItemAt(startDate.getSelectedIndex())));
        int sm = Integer.parseInt(String.valueOf(startMonth.getItemAt(startMonth.getSelectedIndex())));
        int sy = Integer.parseInt(String.valueOf(startYear.getItemAt(startYear.getSelectedIndex())));
        int ed = Integer.parseInt(String.valueOf(endDate.getItemAt(endDate.getSelectedIndex())));
        int em = Integer.parseInt(String.valueOf(endMonth.getItemAt(endMonth.getSelectedIndex())));
        int ey = Integer.parseInt(String.valueOf(endYear.getItemAt(endYear.getSelectedIndex())));

        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;

        try {
            String sql = "insert into event(name,startdate,startmonth,startyear,enddate,endmonth,endyear) values(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,sd);
            ps.setInt(3,sm);
            ps.setInt(4,sy);
            ps.setInt(5,ed);
            ps.setInt(6,em);
            ps.setInt(7,ey);
            ps.execute();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void fetch(){
        model.getDataVector().removeAllElements();
        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;

        try{
            String SQL = "SELECT * FROM event";

            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int sd = rs.getInt("startdate");
                int sm = rs.getInt("startmonth");
                int sy = rs.getInt("startyear");
                int ed = rs.getInt("enddate");
                int em = rs.getInt("endmonth");
                int ey = rs.getInt("endyear");
                model.addRow(new Object[]{id, name, sd, sm, sy, ed, em, ey});
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void update(){
        int index = table.getSelectedRow();
        int i = Integer.parseInt(model.getValueAt(index,0).toString());

        String name = eventName.getText();
        int sd = Integer.parseInt(String.valueOf(startDate.getItemAt(startDate.getSelectedIndex())));
        int sm = Integer.parseInt(String.valueOf(startMonth.getItemAt(startMonth.getSelectedIndex())));
        int sy = Integer.parseInt(String.valueOf(startYear.getItemAt(startYear.getSelectedIndex())));
        int ed = Integer.parseInt(String.valueOf(endDate.getItemAt(endDate.getSelectedIndex())));
        int em = Integer.parseInt(String.valueOf(endMonth.getItemAt(endMonth.getSelectedIndex())));
        int ey = Integer.parseInt(String.valueOf(endYear.getItemAt(endYear.getSelectedIndex())));

        try{
            Connection con;
            con = connection.connectDB();
            PreparedStatement ps;

            String SQL = "UPDATE event SET name = ?, startdate = ?, startmonth = ?,startyear = ?, enddate = ?, endmonth = ?, endyear = ? WHERE id = ?";
            ps = con.prepareStatement(SQL);
            ps.setString(1,name);
            ps.setInt(2,sd);
            ps.setInt(3,sm);
            ps.setInt(4,sy);
            ps.setInt(5,ed);
            ps.setInt(6,em);
            ps.setInt(7,ey);
            ps.setInt(8,i);
            ps.execute();
            status.setText("event updated");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void reset(){
        eventName.setText("");
    }
}
