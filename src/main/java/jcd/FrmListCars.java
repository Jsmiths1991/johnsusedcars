package jcd;

import bo.Car;
import dao.CarHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Swing form for the car list
 */
public class FrmListCars extends javax.swing.JInternalFrame {

    // The CarHandler we'll be using a lot
    CarHandler ch = new CarHandler();
    
    /** Creates new form FrmListCars */
    public FrmListCars() {
        initComponents();
        populateCars();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCars = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        btnReload = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        btnDelete = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        btnEdit = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cars");

        tblCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "VIN", "Make", "Model", "Year", "MSRP", "Status"
            }
        ));
        tblCars.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblCars);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(576, 34));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));
        jPanel5.add(filler4);

        btnReload.setText("Reload");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });
        jPanel5.add(btnReload);
        jPanel5.add(filler1);

        btnDelete.setText("Delete...");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel5.add(btnDelete);
        jPanel5.add(filler2);

        btnEdit.setText("Edit...");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel5.add(btnEdit);
        jPanel5.add(filler3);

        getContentPane().add(jPanel5, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        populateCars();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // Get the index of the selected row
        int selectedRow = tblCars.getSelectedRow();

        // If a row is actually selected (that is, not -1)
        if (selectedRow != -1) {

            // Get the VIN number from the first column (0) of the selected row
            int vin = (int)tblCars.getValueAt(selectedRow, 0);

            Car car = ch.findCar(vin);

            if (car != null) {
                DlgUpdateCar dlg = new DlgUpdateCar(null, true);
                dlg.setCar(car);
                dlg.setVisible(true);
                if (dlg.getReturnStatus() == DlgUpdateCar.RET_OK) {
                    this.populateCars();
                }
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Get the index of the selected row
        int selectedRow = tblCars.getSelectedRow();

        // If a row is actually selected (that is, not -1)
        if (selectedRow != -1) {

            // Get the VIN number from the first column (0) of the selected row
            int vin = (int)tblCars.getValueAt(selectedRow, 0);

            // Show a dialog and put the result in res
            int res = JOptionPane.showConfirmDialog(this,
                String.format("Are you sure you want to delete the car with VIN %d?", vin));

            // If the user clicked OK
            if (res == JOptionPane.OK_OPTION) {
                // Delete the selected car
                ch.deleteCar(vin);

                // Reload the list
                populateCars();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Please select a car to delete.");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * Fill the car table (tblCars) with all the Car relations in the database
     */
    public void populateCars() {

        // Use the CarHandler to return a list of cars from the database as
        // Car objects
        List<Car> cars = ch.getCars();

        // Magic DefaultTableModel code for working with Swing JTable objects
        // (no need to understand; just copy and paste)
        String[] columns = new String[] { "VIN", "Make", "Model", "Year", "MSRP" };
        DefaultTableModel tblModel = new DefaultTableModel(columns, 0);
        cars.forEach((car)->{
            // For each row from the database, add it to the car table
            tblModel.addRow(car.getRow());
        });
        tblCars.setModel(tblModel);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReload;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCars;
    // End of variables declaration//GEN-END:variables

}
