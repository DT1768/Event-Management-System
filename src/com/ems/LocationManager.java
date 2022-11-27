package com.ems;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LocationManager extends JFrame implements ActionListener, MouseListener {
    int role;
    String username;

    JLabel heading, locationLabel, status;

    JButton create;
    JButton fetch;
    JButton back;
    JButton signOut;
    JButton update;
    JButton reset;

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollpane;

    JTextField location;

    public LocationManager(int role, String username) {
        this.role = role;
        this.username = username;

        Border border = BorderFactory.createLineBorder(new Color(0x000000),5);
        Font font =  new Font("Monospaced",Font.BOLD,25);
        Color color = new Color(0xFFFFFF);

        heading = new JLabel();
        heading.setText("Location Manager");
        heading.setBounds(0,0,500,50);
        heading.setHorizontalTextPosition(JLabel.LEFT);
        heading.setVerticalTextPosition(JLabel.CENTER);
        heading.setForeground(new Color(0x000000));
        heading.setFont(new Font("MV Boli",Font.BOLD,25));
        heading.setIconTextGap(10);
        heading.setBorder(border);
        heading.setVerticalAlignment(JLabel.TOP);
        heading.setHorizontalAlignment(JLabel.CENTER);

        locationLabel = new JLabel();
        locationLabel.setText("Location:");
        locationLabel.setBounds(10,100,200,50);
        locationLabel.setHorizontalTextPosition(JLabel.LEFT);
        locationLabel.setVerticalTextPosition(JLabel.CENTER);
        locationLabel.setForeground(new Color(0x000000));
        locationLabel.setFont(font);
        locationLabel.setIconTextGap(10);
        locationLabel.setBorder(border);
        locationLabel.setVerticalAlignment(JLabel.TOP);
        locationLabel.setHorizontalAlignment(JLabel.CENTER);

        location = new JTextField();
        location.setFont(font);
        location.setBounds(250,100,200,50);
        location.setBackground(color);
        location.setBorder(border);

        create = new JButton();
        create.setBounds (100,160,100,50);
        create.addActionListener(this);
        create.setText("Create");
        create.setFocusable(false);
        create.setFont(new Font("Comic Sans",Font.BOLD,12));
        create.setForeground(Color.BLACK);

        fetch = new JButton();
        fetch.setBounds (210,160,100,50);
        fetch.addActionListener(this);
        fetch.setText("Fetch");
        fetch.setFocusable(false);
        fetch.setFont(new Font("Comic Sans",Font.BOLD,12));
        fetch.setForeground(Color.BLACK);

        signOut = new JButton();
        signOut.setBounds (320,160,100,50);
        signOut.addActionListener(this);
        signOut.setText("Log out");
        signOut.setFocusable(false);
        signOut.setFont(new Font("Comic Sans",Font.BOLD,12));
        signOut.setForeground(Color.BLACK);

        update = new JButton();
        update.setBounds (100,220,100,50);
        update.addActionListener(this);
        update.setText("Update");
        update.setFocusable(false);
        update.setFont(new Font("Comic Sans",Font.BOLD,12));
        update.setForeground(Color.BLACK);

        reset = new JButton();
        reset.setBounds (210,220,100,50);
        reset.addActionListener(this);
        reset.setText("Reset");
        reset.setFocusable(false);
        reset.setFont(new Font("Comic Sans",Font.BOLD,12));
        reset.setForeground(Color.BLACK);

        back = new JButton();
        back.setBounds (320,220,100,50);
        back.addActionListener(this);
        back.setText("Back");
        back.setFocusable(false);
        back.setFont(new Font("Comic Sans",Font.BOLD,12));
        back.setForeground(Color.BLACK);

        status = new JLabel();
        status.setText("Status");
        status.setBounds(0,280,500,50);
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

        model = new DefaultTableModel(new String[]{"Id","Name"},0);

        table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getTableHeader().setReorderingAllowed(false);
        table.setOpaque(false);
        table.setBackground(new Color(0xdfcdcd));
        table.addMouseListener(this);

        scrollpane = new JScrollPane(table);
        scrollpane.setBounds(510,10,250,320);
        scrollpane.setOpaque(false);
        scrollpane.getViewport().setBackground(new Color(0xcddfcd));
        this.setDefaultCloseOperation(EventManager.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(790,370);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Task Manager");
        this.getContentPane().setBackground(new Color(0xADD8E6));
        this.add(heading);
        this.add(locationLabel);
        this.add(location);
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
            if(validCreate()){
                create();
                status.setText("Location Created Successfully");
                fetch();
            }
            else{
                status.setText("Error");
            }
        }
        else if(e.getSource() == update){
            if(validCreate()){
                update();
                fetch();
            } else{
                status.setText("Error.");
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
        location.setText(model.getValueAt(index,1).toString());
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
        return !location.getText().isEmpty();
    }

    public void create(){
        String name = location.getText();


        Connection con;
        con = connection.connectDB();
        PreparedStatement ps;

        try {
            String sql = "insert into location(name) values(?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
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
            String SQL = "SELECT * FROM location";

            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                model.addRow(new Object[]{id, name});
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void update(){
        int index = table.getSelectedRow();
        int i = Integer.parseInt(model.getValueAt(index,0).toString());

        String name = location.getText();


        try{
            Connection con;
            con = connection.connectDB();
            PreparedStatement ps;

            String SQL = "UPDATE location SET name = ? WHERE id = ?";
            ps = con.prepareStatement(SQL);
            ps.setString(1,name);
            ps.setInt(2,i);
            ps.execute();
            status.setText("event updated");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void reset(){
        location.setText("");
    }
}
