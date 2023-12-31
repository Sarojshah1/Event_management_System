package DAO;

import Model.EventsModel;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Event extends DatabaseConnection {

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
     public void setConnection(Connection connection) {
        this.connection = connection;
    }
private int userid;
    public void CreateEvent(EventsModel event) {
//        String query = "INSERT INTO events(IMAGE,NAME,PRICE,DESCRIPTION,PLACE,CAPACITY,ISPUBLIC,USERID,VENUEID,date) VALUES('" + Arrays.toString(event.getImage()) + "','" + event.getEvent_name() + "','" + event.getPrice() + "','" + event.getDescription() + "','" + event.getVanue() + "','" + event.getCapacity() + "','" + 0 + "'," + userid + "','" + 1 + "','" + event.getDate() + "')";
//        manipulate(query);
System.out.println(userid);
String query = "INSERT INTO events (IMAGE, NAME, PRICE, DESCRIPTION, PLACE, CAPACITY, ISPUBLIC, USERID, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    preparedStatement.setBytes(1, event.getImage());
    preparedStatement.setString(2, event.getEvent_name());
    preparedStatement.setInt(3, event.getPrice());
    preparedStatement.setString(4, event.getDescription());
    preparedStatement.setString(5, event.getVanue());
    preparedStatement.setInt(6, event.getCapacity());
    preparedStatement.setInt(7, 0);  // Assuming ISPUBLIC is an integer
    preparedStatement.setInt(8, event.getUserid());
 // Assuming VENUEID is an integer
    preparedStatement.setDate(9, java.sql.Date.valueOf(event.getDate()));

    preparedStatement.executeUpdate();
} catch (SQLException e) {
    e.printStackTrace();
}


    }

}
