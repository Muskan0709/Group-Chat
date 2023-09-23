package UI;

import Auth.User;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.Socket;
import java.sql.*;
import javax.swing.*;
import utility.Group;

public class Groups extends JFrame {

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private Group[] groups;
    private static User user;

    public Groups(User us) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        user = new User();
        user.email = us.email;
        user.id = us.id;
        user.name = us.name;

        userLBL.setText(user.name);

        groups = new Group().getGroups(user.id);

        String[] names = new String[groups.length];
        for (int i = 0; i < groups.length; i++) {
            names[i] = groups[i].name;
        }
        groupLST.setListData(names);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        userLBL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupLST = new javax.swing.JList<>();
        createGroupBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ChitChat - Groups");
        setMinimumSize(new java.awt.Dimension(800, 400));

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));

        userLBL.setText("jLabel1");
        userLBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userLBLMouseClicked(evt);
            }
        });

        groupLST.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                groupLSTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(groupLST);

        createGroupBTN.setBackground(new java.awt.Color(255, 255, 255));
        createGroupBTN.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        createGroupBTN.setForeground(new java.awt.Color(0, 0, 0));
        createGroupBTN.setText("Create Group");
        createGroupBTN.setMaximumSize(new java.awt.Dimension(1920, 1080));
        createGroupBTN.setMinimumSize(new java.awt.Dimension(800, 400));
        createGroupBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGroupBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(createGroupBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createGroupBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createGroupBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGroupBTNActionPerformed
        String name = JOptionPane.showInputDialog("Enter Group Name ");

        if (name != null) {
            Group group = new Group().createGroup(name, user.id);

            if (!"".equals(group.id)) {
                new Groups(user).setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Error Group Cannot Be Created ");
            }
        }
    }//GEN-LAST:event_createGroupBTNActionPerformed

    private void groupLSTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_groupLSTMouseClicked
        try {
            Group selGroup = groups[groupLST.getSelectedIndex()];
            Socket socket = new Socket("localhost", 6600);

            new Messages(user, selGroup, socket).setVisible(true);
            this.setVisible(false);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_groupLSTMouseClicked

    private void userLBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userLBLMouseClicked
        new Login().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_userLBLMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createGroupBTN;
    private javax.swing.JList<String> groupLST;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel userLBL;
    // End of variables declaration//GEN-END:variables
}
