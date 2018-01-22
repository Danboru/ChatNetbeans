package netbeanschat;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author danbo
 */
public class FrameChat extends javax.swing.JFrame implements Runnable {

    Socket client;
    ServerSocket server;
    BufferedReader serverReader, clietnReader;
    BufferedWriter serverWriter, clientWriter;
    DefaultListModel dlm = new DefaultListModel();
    DefaultListModel dlmEmoticon = new DefaultListModel();
    String currentUser = "";
    JFrame frameEmoticon = new JFrame();

    private final Map<String, ImageIcon> imageMap;

    public FrameChat() {
        initComponents();

        String[] nameList = {"Grinning", "Beaming", "Tears of Joy", "ROFL", "Grinning Face With Big Eyes"};
        //String[] nameList = new String[10];
        imageMap = createImageMap(nameList);
        JList list = new JList(nameList);
        list.setCellRenderer(new MarioListRenderer());

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
                 
                    try {
                        serverWriter.write(jTusername.getText() + " : " + nameList[index]);
                        serverWriter.newLine();
                        serverWriter.flush();
                    } catch (Exception e) {
                        Logger.getLogger(FrameChat.class.getName()).log(Level.SEVERE, null, e);
                    }

                    dlm.addElement("Me : " + nameList[index]);
                    Lchat.setModel(dlm);
                    
                } else if (evt.getClickCount() == 3) {

                    // Triple-click detected
                    int index = list.locationToIndex(evt.getPoint());
                }
            }
        });

        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(180, 500));

        
        frameEmoticon.add(scroll);
        frameEmoticon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameEmoticon.pack();
        frameEmoticon.setLocationRelativeTo(null);
        frameEmoticon.setVisible(false);

    }

    public class MarioListRenderer extends DefaultListCellRenderer {

        Font font = new Font("helvitica", Font.BOLD, 0);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            label.setIcon(imageMap.get((String) value));
            //label.setHorizontalTextPosition(JLabel.BOTTOM);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setFont(font);
            return label;
        }
    }

    private Map<String, ImageIcon> createImageMap(String[] list) {
        Map<String, ImageIcon> map = new HashMap<>();
        try {

            map.put("Grinning", new ImageIcon(new URL("https://emojipedia-us.s3.amazonaws.com/thumbs/144/apple/118/grinning-face_1f600.png")));
            map.put("Beaming", new ImageIcon(new URL("https://emojipedia-us.s3.amazonaws.com/thumbs/144/apple/118/grinning-face-with-smiling-eyes_1f601.png")));
            map.put("Tears of Joy", new ImageIcon(new URL("https://emojipedia-us.s3.amazonaws.com/thumbs/144/apple/118/face-with-tears-of-joy_1f602.png")));
            map.put("ROFL", new ImageIcon(new URL("https://emojipedia-us.s3.amazonaws.com/thumbs/144/apple/118/rolling-on-the-floor-laughing_1f923.png")));
            map.put("Grinning Face With Big Eyes", new ImageIcon(new URL("https://emojipedia-us.s3.amazonaws.com/thumbs/144/apple/118/smiling-face-with-open-mouth_1f603.png")));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jCBServer = new javax.swing.JComboBox<>();
        jBabout = new javax.swing.JButton();
        jTusername = new javax.swing.JTextField();
        jTchat = new javax.swing.JTextField();
        jBsend = new javax.swing.JButton();
        jBon = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Lchat = new javax.swing.JList<>();
        jBshowemoticon = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jCBServer.setMaximumRowCount(2);
        jCBServer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SERVER", "CLIENT" }));
        jCBServer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBServerItemStateChanged(evt);
            }
        });
        jCBServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBServerActionPerformed(evt);
            }
        });

        jBabout.setText("ABOUT");

        jBsend.setText("SEND");
        jBsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsendActionPerformed(evt);
            }
        });

        jBon.setText("ON");
        jBon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBonActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(Lchat);

        jBshowemoticon.setText("SHOW EMOTICON");
        jBshowemoticon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBshowemoticonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTusername, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTchat, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBsend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addComponent(jBshowemoticon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBon, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBabout)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBabout)
                    .addComponent(jBon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTusername, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBshowemoticon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTchat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBsend, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBonActionPerformed
        // TODO add your handling code here:

        if (jBon.getText().equalsIgnoreCase("CONNECT")) {
            jBon.setText("DISCONNECT");
            clientConnection();
            Thread thread = new Thread(this);
            thread.start();
        } else if (jCBServer.getSelectedItem().equals("SERVER")) {
            jBon.setText("OFF");
            readConnection();
            Thread thread = new Thread(this);
            thread.start();
        }

    }//GEN-LAST:event_jBonActionPerformed

    private void jBsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsendActionPerformed

        editPositionText(Lchat, SwingConstants.RIGHT);
        try {
            serverWriter.write(jTusername.getText() + " : " + jTchat.getText());
            serverWriter.newLine();
            serverWriter.flush();
        } catch (Exception e) {
            Logger.getLogger(FrameChat.class.getName()).log(Level.SEVERE, null, e);
        }

        dlm.addElement("Me : " + jTchat.getText());
        Lchat.setModel(dlm);

        jTchat.setText("");

    }//GEN-LAST:event_jBsendActionPerformed

    private void jCBServerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBServerItemStateChanged
        // TODO add your handling code here:

        if (jCBServer.getSelectedItem().equals("SERVER")) {
            jBon.setText("ON");
            jTusername.setText("SERVER");
        } else {
            jBon.setText("CONNECT");
            jTusername.setText("CLIENT");
        }


    }//GEN-LAST:event_jCBServerItemStateChanged

    private void jCBServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBServerActionPerformed

    private void jBshowemoticonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBshowemoticonActionPerformed

      frameEmoticon.setVisible(true);

    }//GEN-LAST:event_jBshowemoticonActionPerformed

    private void clientConnection() {

        try {

            String ip = JOptionPane.showInputDialog("Masukkan Ip");
            client = new Socket(ip, 2000);
            jCBServer.setEnabled(false);
            serverReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            serverWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            jBon.setText("DISCONNECT");

            currentUser = "Client";

        } catch (UnknownHostException e) {
            System.out.println("Accept Failed");
            System.exit(-1);
        } catch (IOException e) {
            Logger.getLogger(FrameChat.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void readConnection() {
        try {

            try {
                try {

                    server = new ServerSocket(2000);
                    this.setTitle("Please wait ... ");

                } catch (Exception e) {
                    System.out.println("Could not listen");
                    System.exit(-1);
                }

                jTusername.setText("SERVER");
                jCBServer.setEnabled(false);
                client = server.accept();
                this.setTitle("CONNECTED " + client.getInetAddress());

                currentUser = "Server";

            } catch (Exception e) {
                System.out.println("Accept Failed");
                System.exit(-1);
            }

            serverReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            serverWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        } catch (Exception e) {

            System.out.println("Read Failed");
            System.exit(-1);
        }
    }

    private void disconnectedByClient() {

        try {
            client.close();
            serverReader.close();
            serverWriter.close();
            jCBServer.setEnabled(true);
            jBon.setText("CONNECT");

        } catch (Exception e) {
            Logger.getLogger(FrameChat.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void stoppedByServer() {

        try {
            serverReader.close();
            serverWriter.close();
            jCBServer.setEnabled(true);
            jBon.setText("DISCONNECT");

        } catch (Exception e) {
            Logger.getLogger(FrameChat.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    //Taken from http://forums.whirlpool.net.au/archive/1686652
    public static void editPositionText(JList jList, int constant) {

        jList.setCellRenderer(new DefaultListCellRenderer() {
            public int getHorizontalAlignment() {
                return constant;
            }
        });

    }

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
            java.util.logging.Logger.getLogger(FrameChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameChat().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Lchat;
    private javax.swing.JButton jBabout;
    private javax.swing.JButton jBon;
    private javax.swing.JButton jBsend;
    private javax.swing.JButton jBshowemoticon;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBServer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTchat;
    private javax.swing.JTextField jTusername;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {

        while (true) {
            try {

                dlm.addElement(serverReader.readLine());
                Lchat.setModel(dlm);

            } catch (IOException ex) {
                Logger.getLogger(FrameChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
