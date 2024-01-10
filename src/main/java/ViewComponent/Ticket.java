/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ViewComponent;

import Model.AllId;
import database.DatabaseConnection;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class Ticket extends javax.swing.JPanel {
    private DefaultTableModel model;

    /**
     * Creates new form Ticket
     */
    public Ticket() {
        initComponents();
        init();
    }
    private void init() {
        System.out.println(AllId.userId);
        DatabaseConnection conn = new DatabaseConnection();
        model = (DefaultTableModel) table.getModel();
        String query = "SELECT et.ID AS TicketID, e.IMAGE,e.NAME,e.DESCRIPTION,e.PLACE,e.date, e.PRICE,et.PAIDDATE FROM events e JOIN eventticket et ON e.ID = et.EVENTID WHERE et.ISPAID = 1 and OWNERID='"+AllId.userId+"'";
        model.setRowCount(0);
        try (ResultSet resultSet = conn.retrive(query)) {
            while (resultSet.next()) {
                int No = resultSet.getInt(1);
                // Assuming that the second column is a BLOB containing an image
                Blob blob = resultSet.getBlob(2);
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                ImageIcon image = new ImageIcon(imageBytes);
                String name = resultSet.getString(3);
                String description = resultSet.getString(4);
                int price = resultSet.getInt(7);
                String venue = resultSet.getString(5);
                Date date = resultSet.getDate(6);
                Timestamp timestamp = resultSet.getTimestamp(8);
                Date bookeddate = new Date(timestamp.getTime());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(bookeddate);

                // Add a custom renderer for the image column
                table.getColumnModel().getColumn(1).setCellRenderer(new ImageTableCellRenderer());

                model.addRow(new Object[]{No, image, name, description, venue, date, price,formattedDate});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Image", "Event Name", "Description", "Venue ", "Date", "Ticket Price", "Booked Date"
            }
        ));
        table.setRowHeight(50);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
