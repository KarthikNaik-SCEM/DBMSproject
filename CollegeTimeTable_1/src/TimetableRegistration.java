/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.*;
import javax.security.auth.Subject;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

/**
 *
 * @author USER
 */
public class TimetableRegistration extends javax.swing.JFrame {

    /**
     * Creates new form TimetableRegistration
     */
    PreparedStatement pstmt;
    static int decide=0;
    
    public TimetableRegistration() {
        initComponents();
        consetup.createCon();
//        loadClass();
//        loadSubject();
//        loadFaculty();
        DefaultTableModel m = (DefaultTableModel)tblreg.getModel();
        m.setRowCount(0);
        load_dept();
//        displayTable();
    }
    
    public void displayTable()
    {
        try{
        Connection con= consetup.createCon();
        DefaultTableModel m = (DefaultTableModel)tblreg.getModel();
        
        String q="select * from collegetimetable;";
        Statement stmt= con.createStatement();
        ResultSet re = stmt.executeQuery(q);
        m.setRowCount(0);
        while(re.next())
            {
                String C_name=re.getString(1);
                String class_roomNumber=re.getString(2);
                
                m.insertRow(m.getRowCount(), new Object[] {
                re.getInt(1),
                re.getString(2),
                re.getInt(3),
                re.getString(4),
                re.getString(5),
                re.getString(6),
                re.getString(7)
                });
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
    }
    
    
    public void DisplayTable(String dept)
    {
        try{
        Connection con= consetup.createCon();
        DefaultTableModel m = (DefaultTableModel)tblreg.getModel();
        
        String q="select * from collegetimetable where department=?;";
        pstmt= con.prepareStatement(q);
        pstmt.setString(1, dept);
        ResultSet re = pstmt.executeQuery();
        m.setRowCount(0);
        while(re.next())
            {
                String C_name=re.getString(1);
                String class_roomNumber=re.getString(2);
                
                m.insertRow(m.getRowCount(), new Object[] {
                re.getInt(1),
                re.getString(2),
                re.getInt(3),
                re.getString(4),
                re.getString(5),
                re.getString(6),
                re.getString(7)
                });
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
    }
    
    public boolean faculty_reser(String subcode,String fid, String dept)
    {
        try {
            int cfacul=0;
            Connection con= consetup.createCon();
            String q="select distinct f_id,class_name from collegetimetable where subcode=? and department=?;";
            pstmt = con.prepareStatement(q);
            pstmt.setString(1, subcode);
            pstmt.setString(2, dept);
            ResultSet r= pstmt.executeQuery();
            while(r.next())
            {
                System.out.println(fid.equals(r.getString("f_id")));
                if(fid.equals(r.getString("f_id")))
                    return true;
                else
                    cfacul++;
            }
            
            if(cfacul>=3){
                decide=1;
                return false;
            }
            else
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        decide=1;
        return false;
    }
    
    public boolean insert_trig(String Class,String faculty,String day,String time, String dept)
    {
        try{
        Connection con=consetup.createCon();
        String q="select * from collegetimetable";
        
        pstmt=con.prepareStatement(q);
        
//        pstmt.setString(1, dept);
        
        ResultSet set=pstmt.executeQuery(q);
         
         
               
        while(set.next())
        {
//           System.out.print((Class.equals(set.getString(2))&&day.equals(set.getString(5))&&time.equals(set.getString(6)))||(faculty.equals(set.getString(3))&& time.equals(set.getString(6))&& !Class.equals(set.getString(2))));
            if((Class.equals(set.getString(2))&&day.equals(set.getString(5))&&time.equals(set.getString(6)))||(faculty.equals(set.getString(3))&& time.equals(set.getString(6))&& !Class.equals(set.getString(2))))
            {
                
                return false;
            }
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        return true;
    }
    
    public void loadClass(String dept){
        try
        {
        Connection con=consetup.createCon();
        
        String q="select distinct name from class where dept='"+dept+"';";
        
        Statement st = con.createStatement();
        ResultSet re = st.executeQuery(q);
        cmboClass.removeAllItems();
        while(re.next())
        {
            cmboClass.addItem(re.getString("name"));
        }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }    
    
    public void loadSubject(){
        try
        {
        Connection con=consetup.createCon();
        
        String q="select distinct code from subject;";
        Statement st = con.createStatement();
        ResultSet re = st.executeQuery(q);
        cmboSubject.removeAllItems();
        while(re.next())
        {
            cmboSubject.addItem(re.getString("code"));
        }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    } 
    
    
    public void loadFaculty(String dept){
        try
        {
        Connection con=consetup.createCon();
        
        String q="select distinct fid from faculty where fdept='"+dept+"';";
        Statement st = con.createStatement();
        ResultSet re = st.executeQuery(q);
        cmboFaculty.removeAllItems();
        while(re.next())
        {
            cmboFaculty.addItem(Integer.toString(re.getInt("fid")) );
        }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    } 
    
    
     public void load_dept(){
        try
        {
        Connection con=consetup.createCon();
        
        String q="select * from department;";
        Statement st = con.createStatement();
        ResultSet re = st.executeQuery(q);
        cmbdept.removeAllItems();
        while(re.next())
        {
            cmbdept.addItem(re.getString("dept_name"));
        }
        
        }
        catch(Exception e)
        {
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmboClass = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmboSubject = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmboDays = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmboTime = new javax.swing.JComboBox<>();
        cmboFaculty = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPrefSub = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblFname = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblClassno = new javax.swing.JLabel();
        btnSaveTimeReg = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cmbdept = new javax.swing.JComboBox<>();
        btnFilter = new javax.swing.JButton();
        scrollpane = new javax.swing.JScrollPane();
        tblreg = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-schedule-50.png"))); // NOI18N
        jLabel1.setText("Timetable Registration");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 5, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Class");

        cmboClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmboClassItemStateChanged(evt);
            }
        });
        cmboClass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmboClassMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Subject");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Days");

        cmboDays.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Time");

        cmboTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:30AM-9:30AM", "9:30AM-10:30AM", "10:45AM-11:45AM", "11:45AM-12:45PM", "1:30PM-2:30PM", "2:30PM-3:30PM", "3:30PM-4:30PM", "4:30PM-5:30PM" }));

        cmboFaculty.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmboFacultyItemStateChanged(evt);
            }
        });
        cmboFaculty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboFacultyActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Faculty");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Prefered subject");

        lblPrefSub.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPrefSub.setText("<NA>");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("faculty name");

        lblFname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFname.setText("<NA>");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Classroom no.");

        lblClassno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblClassno.setText("<NA>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(47, 47, 47)
                        .addComponent(cmboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(47, 47, 47)
                        .addComponent(cmboDays, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(47, 47, 47)
                        .addComponent(cmboSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(47, 47, 47)
                        .addComponent(cmboClass, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lblPrefSub, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFname, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(lblClassno, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(47, 47, 47)
                                .addComponent(cmboFaculty, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(lblClassno))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cmboClass, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmboSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmboFaculty, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmboDays, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(65, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblFname))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblPrefSub))
                        .addGap(97, 97, 97))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 71, -1, -1));

        btnSaveTimeReg.setText("save");
        btnSaveTimeReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveTimeRegActionPerformed(evt);
            }
        });
        getContentPane().add(btnSaveTimeReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 705, 150, 52));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("Dept");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 566, -1, -1));

        getContentPane().add(cmbdept, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 564, 250, 42));

        btnFilter.setText("filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        getContentPane().add(btnFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 571, 184, -1));

        tblreg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "class", "fid", "sub code", "day", "time", "dept"
            }
        ));
        tblreg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblregMouseClicked(evt);
            }
        });
        scrollpane.setViewportView(tblreg);

        getContentPane().add(scrollpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 820, 780));

        btnEdit.setText("edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 700, 140, 50));

        btnDelete.setText("delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 700, 150, 50));

        setSize(new java.awt.Dimension(1753, 820));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveTimeRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTimeRegActionPerformed
        // TODO add your handling code here:
        
            Connection con=consetup.createCon();
            String Class=cmboClass.getSelectedItem().toString();
            String faculty=cmboFaculty.getSelectedItem().toString();
            String subject=cmboSubject.getSelectedItem().toString();
            String day=cmboDays.getSelectedItem().toString();
            String time=cmboTime.getSelectedItem().toString();
            
            System.out.println(insert_trig(Class,faculty, day, time,cmbdept.getSelectedItem().toString()) );
            
            if(insert_trig(Class,faculty, day, time,cmbdept.getSelectedItem().toString()) && faculty_reser(subject,faculty,cmbdept.getSelectedItem().toString()))
            {
                
                try
                {
                String q="insert into collegetimetable(class_name,f_id,subcode,days,time,department) values(?,?,?,?,?,?);";
                pstmt=con.prepareStatement(q);
                pstmt.setString(1, Class);
                pstmt.setString(2, faculty);
                pstmt.setString(3, subject);
                pstmt.setString(4, day);
                pstmt.setString(5, time);
                pstmt.setString(6, cmbdept.getSelectedItem().toString());
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "added to timetable database");
                cmboClass.setSelectedIndex(-1);
                cmboFaculty.setSelectedIndex(-1);
                cmboSubject.setSelectedIndex(-1);
                cmboDays.setSelectedIndex(-1);
                cmboTime.setSelectedIndex(-1);  
                DisplayTable(cmbdept.getSelectedItem().toString());
            }

            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "error");
                e.printStackTrace();
            }

            }
            
            else
            {
                if(decide==0)
                    JOptionPane.showMessageDialog(this, "interception of either classes of two different faculties in same class\nOR\ninterception of classes of two different classes being taken by same faculty");
                
                else
                     JOptionPane.showMessageDialog(this, "already three faculties have been allocated");
            }
        
        
    }//GEN-LAST:event_btnSaveTimeRegActionPerformed

    private void cmboFacultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboFacultyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmboFacultyActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        loadClass(cmbdept.getSelectedItem().toString());
        loadSubject();
        loadFaculty(cmbdept.getSelectedItem().toString());
        DisplayTable(cmbdept.getSelectedItem().toString());
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
         try {
            Connection con = consetup.createCon();
             DefaultTableModel m=(DefaultTableModel) tblreg.getModel();
             int selectedIndex = tblreg.getSelectedRow();
            int id= Integer.parseInt(m.getValueAt(selectedIndex,0).toString());
            
            String q="update collegetimetable set class_name=?,f_id=?,subcode=?,days=?,time=?,department=? where id=?;";
            pstmt=con.prepareStatement(q);
            pstmt.setString(1, cmboClass.getSelectedItem().toString());
            pstmt.setString(2, cmboFaculty.getSelectedItem().toString());
            pstmt.setString(3, cmboSubject.getSelectedItem().toString());
            pstmt.setString(4, cmboDays.getSelectedItem().toString());
            pstmt.setString(5, cmboTime.getSelectedItem().toString());
            pstmt.setString(6, cmbdept.getSelectedItem().toString());
            pstmt.setInt(7, id);
            
           
            pstmt.executeUpdate();
            DisplayTable(cmbdept.getSelectedItem().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void tblregMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblregMouseClicked
        // TODO add your handling code here:
        DefaultTableModel m=(DefaultTableModel) tblreg.getModel();
        int selectedIndex = tblreg.getSelectedRow();
        cmboClass.setSelectedItem(m.getValueAt(selectedIndex,1).toString());
        cmboFaculty.setSelectedItem(m.getValueAt(selectedIndex,2).toString());
        cmboSubject.setSelectedItem(m.getValueAt(selectedIndex,3).toString());
        cmboDays.setSelectedItem(m.getValueAt(selectedIndex,4).toString());
        cmboTime.setSelectedItem(m.getValueAt(selectedIndex,5).toString());
        cmbdept.setSelectedItem(m.getValueAt(selectedIndex,6).toString());
        

    }//GEN-LAST:event_tblregMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try{
        Connection con = consetup.createCon();
        String q="delete from collegetimetable where id=?;";
        DefaultTableModel m= (DefaultTableModel) tblreg.getModel();
        int selectedIndex = tblreg.getSelectedRow();
        int id= Integer.parseInt(m.getValueAt(selectedIndex,0).toString());
        pstmt =con.prepareStatement(q);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        DisplayTable(cmbdept.getSelectedItem().toString());
        JOptionPane.showMessageDialog(this, "record deleted");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cmboClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmboClassMouseClicked
        // TODO add your handling code here:
        try {
            Connection con=consetup.createCon();
            String q="select roomno from class where name=? and dept=?;";
            pstmt = con.prepareStatement(q);
            pstmt.setString(1, cmboClass.getSelectedItem().toString());
            pstmt.setString(2, cmbdept.getSelectedItem().toString());
            
            ResultSet r=pstmt.executeQuery();
            
            while(r.next())
                lblClassno.setText(r.getString("roomno"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_cmboClassMouseClicked

    private void cmboClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmboClassItemStateChanged
        // TODO add your handling code here:
        try {
            Connection con=consetup.createCon();
            String q="select roomno from class where name=? and dept=?;";
            pstmt = con.prepareStatement(q);
            pstmt.setString(1, cmboClass.getSelectedItem().toString());
            pstmt.setString(2, cmbdept.getSelectedItem().toString());
            
            ResultSet r=pstmt.executeQuery();
            
            while(r.next())
                lblClassno.setText(r.getString("roomno"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmboClassItemStateChanged

    private void cmboFacultyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmboFacultyItemStateChanged
        // TODO add your handling code here:
        try {
            Connection con=consetup.createCon();
            String q="select fname,fpreferedsub from faculty where fid=?";
            pstmt = con.prepareStatement(q);
            pstmt.setString(1, cmboFaculty.getSelectedItem().toString());
           
            
            ResultSet r=pstmt.executeQuery();
            
            while(r.next())
            {
                lblFname.setText(r.getString("fname"));
                lblPrefSub.setText(r.getString("fpreferedsub"));         
            }   
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmboFacultyItemStateChanged

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
            java.util.logging.Logger.getLogger(TimetableRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimetableRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimetableRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimetableRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimetableRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnSaveTimeReg;
    private javax.swing.JComboBox<String> cmbdept;
    private javax.swing.JComboBox<String> cmboClass;
    private javax.swing.JComboBox<String> cmboDays;
    private javax.swing.JComboBox<String> cmboFaculty;
    private javax.swing.JComboBox<String> cmboSubject;
    private javax.swing.JComboBox<String> cmboTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblClassno;
    private javax.swing.JLabel lblFname;
    private javax.swing.JLabel lblPrefSub;
    private javax.swing.JScrollPane scrollpane;
    private javax.swing.JTable tblreg;
    // End of variables declaration//GEN-END:variables
}
