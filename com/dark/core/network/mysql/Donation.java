package com.dark.core.network.mysql;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.mysql.jdbc.Statement;

public class Donation {


	public static final String checkDonation = null;

	public static void checkDonation(String username, Player player) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://31.220.105.211:3306/darkasyl_store2","darkasyl_store2", "store123");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            stmt = (Statement) connection.createStatement();
            String sql;
            sql = "SELECT productId FROM checkout WHERE playerName='" +username+ "' AND canClaim='1'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("productId");
                switch(id) {
                    case 17:
                    	player.getInventory().add(new Item(13190, 1));
                        break;
                    case 18:
                    	player.getInventory().add(new Item(13191, 1));
                        break;
                    case 19:
                    	player.getInventory().add(new Item(13192, 1));
                        break;
                    case 20:
                    	player.getInventory().add(new Item(13193, 1));
                        break;
                    case 21:
                    	player.getInventory().add(new Item(13195, 1));
                        break;
                    case 22:
                    	player.getInventory().add(new Item(292, 1));
                        break;
                    case 24:
                    	player.getInventory().add(new Item(600, 1));
                        break;
                        
                }
            }
            sql = "UPDATE checkout SET canClaim = '2' WHERE playerName='" +username+ "' AND canClaim='1'";
            stmt.execute(sql);
            rs.close();
            stmt.close();
            connection.close();

        } else {
            System.out.println("Failed to make connection!");
        }
    }
}