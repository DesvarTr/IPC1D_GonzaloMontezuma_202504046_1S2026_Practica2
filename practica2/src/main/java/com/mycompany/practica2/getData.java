package com.mycompany.practica2;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author gonza
 */
public class getData extends javax.swing.JPanel {
    
    public getData() {
        initComponents();
        asegurarArchivoExiste();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jButton1.setText("INGRESAR DATOS");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jToggleButton1.setSelected(true);
        jToggleButton1.setText("ASCENDENTE");
        jToggleButton1.addActionListener(this::jToggleButton1ActionPerformed);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "500ms", "100ms", "020ms" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jLabel1.setText("Velocidad:");

        jLabel2.setText("INGRESE EL GRUPO DE DATOS A ORDENAR");

        jLabel3.setText("*Los datos deben estar separados por una coma*");

        jLabel4.setText("*Ingrese números enteros unicamente*");

        jLabel5.setText("Ejemplo input válido: 16,48,233,16,45,89");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String raw_data = jTextField1.getText();
        boolean valido = validate(raw_data);
        if (!valido){
            JOptionPane.showMessageDialog(null, "Ingrese datos validos");
        } else {
            int choice = JOptionPane.showConfirmDialog(null, "¿Está seguro de ingresar el arreglo?"
                    + "["+raw_data+"]",
            "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                clearFile();
                write_file(raw_data);
                JOptionPane.showMessageDialog(null, "Datos actualizados");
                jTextField1.setText("");

            } else if (choice == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Sin cambios realizados");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        if (jToggleButton1.isSelected()) {
            jToggleButton1.setText("ASCENDENTE");
        } else {
            jToggleButton1.setText("DESCENDENTE");
        }
        
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    public void asegurarArchivoExiste() {
        File archivo = new File("DATOS.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }
    
    private boolean is_digit(String arg){
        try{
            Integer.parseInt(arg);
            return true;
        }catch(NumberFormatException e){
            System.out.println("El argumento no es un numero");
            return false;
        }
    }
    
    private boolean validate(String texto){
        String[] separado = texto.split(",");
        for (String elemento : separado){
            if(!is_digit(elemento)) return false;
        }
        return true;
    }

    private void write_file(String texto){
        
        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter(new FileWriter("DATOS.txt", true));
            
            String[] separado = texto.split(",");
            for (String elemento : separado){
                escritor.println(elemento);
            }
            
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            
        } finally {
            if (escritor != null) {
                escritor.close();
            }
        }
    }
    
    private void clearFile(){
        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter(new FileWriter("DATOS.txt", false));
            escritor.print("");

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());

        } finally {
            if (escritor != null) {
                escritor.close();
            }
        }
    }
    
    private int contarLineas() {
        int lineas = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("DATOS.txt"));
            while (lector.readLine() != null) {
                lineas++;
            }
            lector.close();
        } catch (IOException e) {
            return 0;
        }
        return lineas;
    }
    
    public int[] getDataArray(){
        
        int cantidadLineas = contarLineas();
        int[] data = new int[cantidadLineas];

        try {
            BufferedReader lector = new BufferedReader(new FileReader("DATOS.txt"));
            String linea;
            int indice = 0;
            
            while ((linea = lector.readLine()) != null) {
                data[indice]=Integer.parseInt(linea);
                indice++;
            }
            
            lector.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data;
        
    }
    
    public boolean getAscendente(){
        return jToggleButton1.isSelected();
    }
    
    public int getVelocidad(){
        return Integer.parseInt((jComboBox1.getSelectedItem().toString()).substring(0,3));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
