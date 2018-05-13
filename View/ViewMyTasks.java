/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CaseController;
import Controller.DatabaseController;
import Controller.TaskController;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author sebastian
 */
public class ViewMyTasks extends javax.swing.JFrame {
    
    TableModel model;
    DatabaseController dbc;
    /**
     * Creates new form ViewMyTasks
     */
    public ViewMyTasks() throws SQLException {
        
        initComponents();
        fillTaskManagerComboBox();
        initShowTaskTable();
        initShowDutyTable();
       
        this.dbc = new DatabaseController();
    }
    
    public void initShowTaskTable(){
        ArrayList<Model.Task> taskList = new ArrayList<>();
        try {
            TaskController tc = new TaskController();
            taskList = tc.getAllTasks();
            int row = 0;
            int rows = taskList.size();
            
            Object[][] data = new Object[rows][4];
            
            for(Model.Task t : taskList){
              data[row][0] = t.getTaskID();
              data[row][1] = t.getCategory();
              data[row][2] = t.getDescription();
              data[row][3] = t.getTaskManagerName();
              row++;
            }
            model = new DefaultTableModel(data, Constants.MyTask_TABLE_HEADER);
            tbl_tasks.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Misslyckades att visa ärendetabellen");
            System.out.println(ex.getMessage());
        }
    }
    
    public void initShowDutyTable(){
        ArrayList<Model.Case> caseList = new ArrayList<>();
        try {
            CaseController dc = new CaseController();
            caseList = dc.getSelectedDutys();
            int row = 0;
            int rows = caseList.size();
            
            Object[][] data = new Object[rows][5];
            
            for(Model.Case d : caseList){
              data[row][0] = d.getDutyCase();
              data[row][1] = d.getBudgetTime();
              data[row][2] = d.getTimePerformed();
              data[row][3] = d.getComment();
              data[row][4] = d.getStatus();
              row++;
            }
            model = new DefaultTableModel(data, Constants.MyTaskDuty_TABLE_HEADER);
            tbl_Cases.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kunde inte hämta data från arbetsuppgifter");
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void fillTaskManagerComboBox() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javauppgift?zeroDateTimeBehavior=convertToNull&useSSL=false","root","hejsan");
        
            Statement st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery("Select taskManagerName from taskmanagertbl");
            while(rs.next()){
                combo_box_taskmanager.addItem(rs.getString("taskManagerName"));
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
        tbl_Cases = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_tasks = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        button_Task_update = new javax.swing.JButton();
        button_Task_delete = new javax.swing.JButton();
        button_Case_update = new javax.swing.JButton();
        button_Case_attest = new javax.swing.JButton();
        combo_box_taskmanager = new javax.swing.JComboBox<>();
        label_taskManager = new javax.swing.JLabel();
        button_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_Cases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbl_Cases);

        tbl_tasks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_tasks.setColumnSelectionAllowed(true);
        tbl_tasks.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbl_tasks);
        tbl_tasks.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setText("Ärenden:");

        jLabel2.setText("Arbetsuppgifter:");

        button_Task_update.setText("Ändra");

        button_Task_delete.setText("Ta bort");

        button_Case_update.setText("Ändra");

        button_Case_attest.setText("Attestera");

        combo_box_taskmanager.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        combo_box_taskmanager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_box_taskmanagerActionPerformed(evt);
            }
        });

        label_taskManager.setText("Skapad av:");

        button_cancel.setText("Tillbaka");
        button_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button_Task_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_Task_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(139, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(105, 105, 105)
                                .addComponent(label_taskManager)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combo_box_taskmanager, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(button_Case_attest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button_Case_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(button_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_box_taskmanager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_taskManager))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(button_Task_update)
                        .addGap(18, 18, 18)
                        .addComponent(button_Task_delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_Case_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_Case_attest)))
                .addGap(44, 44, 44)
                .addComponent(button_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cancelActionPerformed
        ViewMain vm = new ViewMain();
        vm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_button_cancelActionPerformed

    private void combo_box_taskmanagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_box_taskmanagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_box_taskmanagerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewMyTasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewMyTasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewMyTasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewMyTasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewMyTasks().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewMyTasks.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Case_attest;
    private javax.swing.JButton button_Case_update;
    private javax.swing.JButton button_Task_delete;
    private javax.swing.JButton button_Task_update;
    private javax.swing.JButton button_cancel;
    private javax.swing.JComboBox<String> combo_box_taskmanager;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_taskManager;
    private javax.swing.JTable tbl_Cases;
    private javax.swing.JTable tbl_tasks;
    // End of variables declaration//GEN-END:variables
}