package com.mycompany.practica2;
import javax.swing.*;
/**
 *
 * @author gonza
 */
public class estadisticas extends javax.swing.JPanel {

    private String historial = "";
    private int numEjecucion = 0;
    private String historialHTML = "";
    /**
     * Creates new form Estadisticas
     */
    public estadisticas() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("Comparaciones");
        jLabel1.setFocusable(false);

        jLabel2.setText("Intercambios");
        jLabel2.setFocusable(false);

        jLabel3.setText("Iteraciones");
        jLabel3.setFocusable(false);

        jLabel4.setText("ESTADISTICAS");
        jLabel4.setFocusable(false);

        jLabel5.setText("0");
        jLabel5.setFocusable(false);

        jLabel6.setText("0");
        jLabel6.setFocusable(false);

        jLabel7.setText("0");
        jLabel7.setFocusable(false);

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setFocusable(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("REPORTES E HISTORIAL");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(64, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame ventana = new JFrame("Reportes");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        // Tab 1 - History
        JTextArea areaHistorial = new JTextArea(historial);
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        tabs.addTab("Historial", new JScrollPane(areaHistorial));

        // Tab 2 - Report HTML
        String htmlCompleto = "<html><body><table border='1' cellpadding='5'>"
                + "<tr><th>Algoritmo</th><th>Orden</th><th>Arreglo Original</th><th>Arreglo Resultado</th><th>Comp</th><th>Inter</th><th>Iter</th><th>Tiempo</th><th>Velocidad</th></tr>"
                + historialHTML
                + "</table></body></html>";
        JEditorPane editorPane = new JEditorPane("text/html", htmlCompleto);
        editorPane.setEditable(false);
        tabs.addTab("Reporte", new JScrollPane(editorPane));

        ventana.add(tabs);
        ventana.setSize(750, 450);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void actualizarComparaciones(int valor) {
        jLabel5.setText(String.valueOf(valor));
    }

    public void actualizarIntercambios(int valor) {
        jLabel6.setText(String.valueOf(valor));
    }

    public void actualizarIteraciones(int valor) {
        jLabel7.setText(String.valueOf(valor));
    }

    public void reset() {
        jLabel5.setText("0");
        jLabel6.setText("0");
        jLabel7.setText("0");
    }

    public void agregarResumen(String resumen) {
        numEjecucion++;
        historial += "#" + numEjecucion + " | " + resumen + "\n\n";
        historialHTML += "<tr><td>" + resumen.replace(" | ", "</td><td>") + "</td></tr>\n";
    }
    
    public void appendLog(String mensaje) {
        jTextArea1.append(mensaje + "\n");
    }
    
    public void resetLog() {
        jTextArea1.setText("");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
