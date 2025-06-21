
import javax.swing.JOptionPane;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

public class gui_quiz extends javax.swing.JFrame {

    private javax.swing.JTextField avg_txt;
    private javax.swing.JTextField grade_txt;

    public gui_quiz() {
        initComponents();
        avg_txt = new javax.swing.JTextField();
        grade_txt = new javax.swing.JTextField();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rupp = new javax.swing.JLabel();
        midterm_txt = new javax.swing.JTextField();
        mideter_label = new javax.swing.JLabel();
        final_label = new javax.swing.JLabel();
        final_txt = new javax.swing.JTextField();
        code_label = new javax.swing.JLabel();
        code_txt = new javax.swing.JTextField();
        name_labe = new javax.swing.JLabel();
        name_txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_tb = new javax.swing.JTable();
        delete_btn = new javax.swing.JButton();
        update_btn = new javax.swing.JButton();
        add_btn2 = new javax.swing.JButton();
        kGradientPanel1 = new keeptoo.KGradientPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1033, 547));
        setMinimumSize(new java.awt.Dimension(1033, 547));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        rupp.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        rupp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rupp.setText("Royal University Of Cambodia");
        getContentPane().add(rupp);
        rupp.setBounds(6, -2, 1020, 60);

        midterm_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                midterm_txtActionPerformed(evt);
            }
        });
        getContentPane().add(midterm_txt);
        midterm_txt.setBounds(170, 250, 220, 40);

        mideter_label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mideter_label.setText("Miderm Score :");
        getContentPane().add(mideter_label);
        mideter_label.setBounds(30, 250, 150, 40);

        final_label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        final_label.setText("Final Score :");
        getContentPane().add(final_label);
        final_label.setBounds(30, 320, 150, 40);

        final_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                final_txtActionPerformed(evt);
            }
        });
        getContentPane().add(final_txt);
        final_txt.setBounds(170, 320, 220, 40);

        code_label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        code_label.setText("Code :");
        getContentPane().add(code_label);
        code_label.setBounds(30, 120, 150, 40);

        code_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                code_txtActionPerformed(evt);
            }
        });
        getContentPane().add(code_txt);
        code_txt.setBounds(170, 120, 220, 40);

        name_labe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        name_labe.setText("Name :");
        getContentPane().add(name_labe);
        name_labe.setBounds(30, 180, 150, 40);

        name_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_txtActionPerformed(evt);
            }
        });
        getContentPane().add(name_txt);
        name_txt.setBounds(170, 180, 220, 40);

        table_tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Midterm", "Final", "Average", "Result"
            }
        ));
        table_tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_tbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_tb);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(460, 120, 520, 370);

        delete_btn.setText("Delete");
        delete_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delete_btnMouseClicked(evt);
            }
        });
        getContentPane().add(delete_btn);
        delete_btn.setBounds(150, 450, 110, 40);

        update_btn.setText("Update");
        update_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                update_btnMouseClicked(evt);
            }
        });
        getContentPane().add(update_btn);
        update_btn.setBounds(280, 450, 110, 40);

        add_btn2.setText("add");
        add_btn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_btn2MouseClicked(evt);
            }
        });
        getContentPane().add(add_btn2);
        add_btn2.setBounds(30, 450, 110, 40);

        kGradientPanel1.setLayout(null);
        getContentPane().add(kGradientPanel1);
        kGradientPanel1.setBounds(0, 0, 1030, 550);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void loadTableData() {
        try {
            DefaultTableModel model = (DefaultTableModel) table_tb.getModel();
            model.setRowCount(0); // Clear old rows

            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/m7_database", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("code"),
                    rs.getString("name"),
                    rs.getDouble("midterm"),
                    rs.getDouble("final_score"),
                    rs.getDouble("average"),
                    rs.getString("grade")
                };
                model.addRow(row);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(gui_quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void midterm_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_midterm_txtActionPerformed

    }//GEN-LAST:event_midterm_txtActionPerformed

    private void final_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_final_txtActionPerformed

    }//GEN-LAST:event_final_txtActionPerformed

    private void code_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_code_txtActionPerformed

    }//GEN-LAST:event_code_txtActionPerformed

    private void name_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_txtActionPerformed

    }//GEN-LAST:event_name_txtActionPerformed

    private void add_btn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_btn2MouseClicked
        int code = Integer.parseInt(code_txt.getText());
        String name = name_txt.getText();
        double midterm = Double.parseDouble(midterm_txt.getText());
        double finals = Double.parseDouble(final_txt.getText());

        double avg = (midterm + finals) / 2;

        String grade;
        if (avg >= 90) {
            grade = "A";
        } else if (avg >= 80) {
            grade = "B";
        } else if (avg >= 70) {
            grade = "C";
        } else if (avg >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/m7_database", "root", "");

            PreparedStatement check = conn.prepareStatement("SELECT * FROM students WHERE code = ?");
            check.setInt(1, code);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "❌ Code already exists! Please enter a different code.");
            } else {

                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO `students`(`code`, `name`, `midterm`, `final_score`, `average`, `grade`) "
                        + "VALUES (?, ?, ?, ?, ?, ?)"
                );
                ps.setInt(1, code);
                ps.setString(2, name);
                ps.setDouble(3, midterm);
                ps.setDouble(4, finals);
                ps.setDouble(5, avg);
                ps.setString(6, grade);

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Inserted Successfully!");

                    DefaultTableModel model = (DefaultTableModel) table_tb.getModel();
                    model.addRow(new Object[]{code, name, midterm, finals, avg, grade});
                }

                ps.close();
            }

            rs.close();
            check.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(gui_quiz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_add_btn2MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        DefaultTableModel model = (DefaultTableModel) table_tb.getModel();
        model.setRowCount(0);
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/m7_database", "root", "");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students");
            ps.execute();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object rowData[] = {rs.getInt("code"), rs.getString("name"), rs.getDouble("midterm"), rs.getDouble("final_score"), rs.getDouble("average"), rs.getString("grade")};
                model.addRow(rowData);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(gui_quiz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formWindowOpened

    private void table_tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_tbMouseClicked

        int selectedRow = table_tb.getSelectedRow(); // Get row clicked
        if (selectedRow >= 0) {

            Object[] rowData = new Object[table_tb.getColumnCount()];
            for (int i = 0; i < table_tb.getColumnCount(); i++) {
                rowData[i] = table_tb.getValueAt(selectedRow, i);
            }
            code_txt.setText(rowData[0].toString());
            name_txt.setText(rowData[1].toString());
            midterm_txt.setText(rowData[2].toString());
            final_txt.setText(rowData[3].toString());
            avg_txt.setText(rowData[4].toString());
            grade_txt.setText(rowData[5].toString());

        }

    }//GEN-LAST:event_table_tbMouseClicked

    private void update_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_btnMouseClicked
        int code = Integer.parseInt(code_txt.getText());
        String name = name_txt.getText();
        double midterm = Double.parseDouble(midterm_txt.getText());
        double finalScore = Double.parseDouble(final_txt.getText());

        DefaultTableModel model = (DefaultTableModel) table_tb.getModel();

        double avg = (midterm + finalScore) / 2;
        String grade = (avg >= 90) ? "A"
                : (avg >= 80) ? "B"
                        : (avg >= 70) ? "C"
                                : (avg >= 60) ? "D" : "F";

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/m7_database", "root", "");
            PreparedStatement ps = conn.prepareStatement("UPDATE `students` SET `name`='" + name + "',`midterm`='" + midterm + "',`final_score`='" + finalScore + "',`average`='" + avg + "',`grade`='" + grade + "' WHERE code=" + code);
            ps.execute();
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Update Succesfully");

                model.setRowCount(0);
                ps = conn.prepareStatement("SELECT * FROM students");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Object[] row = {
                        rs.getInt("code"),
                        rs.getString("name"),
                        rs.getDouble("midterm"),
                        rs.getDouble("final_score"),
                        rs.getDouble("average"),
                        rs.getString("grade")
                    };
                    model.addRow(row);
                }

                rs.close();
                ps.close();

            } else {
                JOptionPane.showMessageDialog(this, "Update Fail");
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(gui_quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_update_btnMouseClicked

    private void delete_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delete_btnMouseClicked
        int code = Integer.parseInt(code_txt.getText());
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/m7_database", "root", "");
                PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE code = ?");
                ps.setInt(1, code);

                int rowsDeleted = ps.executeUpdate();
                ps.close();
                conn.close();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Delete Successfully");

                    code_txt.setText("");
                    name_txt.setText("");
                    midterm_txt.setText("");
                    final_txt.setText("");
                    avg_txt.setText("");
                    grade_txt.setText("");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(this, "Delete Failed");
                }

            } catch (SQLException ex) {
                Logger.getLogger(gui_quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_delete_btnMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui_quiz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_btn2;
    private javax.swing.JLabel code_label;
    private javax.swing.JTextField code_txt;
    private javax.swing.JButton delete_btn;
    private javax.swing.JLabel final_label;
    private javax.swing.JTextField final_txt;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel mideter_label;
    private javax.swing.JTextField midterm_txt;
    private javax.swing.JLabel name_labe;
    private javax.swing.JTextField name_txt;
    private javax.swing.JLabel rupp;
    private javax.swing.JTable table_tb;
    private javax.swing.JButton update_btn;
    // End of variables declaration//GEN-END:variables
}
