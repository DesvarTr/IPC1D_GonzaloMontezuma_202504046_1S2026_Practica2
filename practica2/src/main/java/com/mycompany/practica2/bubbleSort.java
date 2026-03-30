package com.mycompany.practica2;

import javax.swing.*;
/**
 *
 * @author gonza
 */
public class bubbleSort extends sortVisualizer {
    
    public bubbleSort(JPanel panel, int[] arreglo, estadisticas statsPanel) {
        super(panel, arreglo, statsPanel);
    }

    public bubbleSort(JPanel panel, int[] arreglo, boolean ascendente, int velocidad, estadisticas statsPanel) {
        super(panel, arreglo, ascendente, velocidad, statsPanel);
    }
    
    @Override
    protected String getNombre() { return "Bubble Sort"; }

    @Override
    protected void ordenar(int[] arr) {
        bubbleSort_run(arr);
    }
    
    private void bubbleSort_run(int[] datos){
    
        int changes = 0;
        for (int x=0; x<datos.length; x++){
        
            changes=0;
            for (int y=0; y<datos.length-1; y++){
        
                SwingUtilities.invokeLater(() -> {
                        notificarIteracion();
                });
                
                    pausa();
                if (debeIntercambiar(datos[y],datos[y+1])){
                    swap(datos,y,y+1);
                    changes++;
                    
                    final int idx1 = y, idx2 = y+1;
                    final int[] snap = datos.clone();

                    SwingUtilities.invokeLater(() -> {
                        actualizarDataset(snap, idx1, idx2);
                        lblEstado.setText("Intercambiando: i" + idx1 + " e i" + idx2);
                        notificarIntercambio();
                    });
                    pausa();
                    
                } else {
                
                    final int idx1 = y, idx2 = y+1;
                    final int[] snap = datos.clone();

                    SwingUtilities.invokeLater(() -> {
                        actualizarDataset(snap, idx1, idx2);
                        lblEstado.setText("No se hace nada con: i" + idx1 + " e i" + idx2);
                    });
                    pausa();
                
                }
            }
            if (changes==0){
                break;
            }
        }
        
    }
    
    private void swap(int[] arr, int a, int b){
    
        if (a!=b){
            arr[a]=arr[a]+arr[b];
            arr[b]=arr[a]-arr[b];
            arr[a]=arr[a]-arr[b];
        }
        
    }
    
}
