package com.mycompany.practica2;

import javax.swing.*;
/**
 *
 * @author gonza
 */
public class quickSort extends sortVisualizer {
    
    public quickSort(JPanel panel, int[] arreglo, estadisticas statsPanel) {
        super(panel, arreglo, statsPanel);
    }

    public quickSort(JPanel panel, int[] arreglo, boolean ascendente, int velocidad, estadisticas statsPanel) {
        super(panel, arreglo, ascendente, velocidad, statsPanel);
    }
    
    @Override
    protected String getNombre() { return "Quick Sort"; }

    @Override
    protected void ordenar(int[] arr) {
        quickSort_run(arr, 0, arr.length-1);
    }
    
    private void quickSort_run(int[] arr, int izq, int der){
        
        SwingUtilities.invokeLater(() -> {
            notificarIteracion();
        });
        if (izq<=der){
            int pi = partition(arr,izq,der);
            quickSort_run(arr, izq ,pi-1);
            quickSort_run(arr, pi+1 ,der);
        }
        
    }
    
    private int partition(int[] arr, int izq, int der){
    
        int pivot = der;
        int i =izq-1;
        
        for (int j=izq;j<der;j++){
        
            if((!debeIntercambiar(arr[j],arr[pivot]))&&(i!=j)){
                i++;
                swap(arr,i,j);
                
                final int idx1 = i, idx2 = j;
                final int[] snap = arr.clone();

                SwingUtilities.invokeLater(() -> {
                    actualizarDataset(snap, idx1, idx2);
                    notificarIntercambio();
                    lblEstado.setText("Intercambiando: i" + idx1 + " e i" + idx2);
                    statsPanel.appendLog("Intercambiando: i" + idx1 + " e i" + idx2);
                });
                pausa();
            }
            
        }
        swap(arr, i+1, der);
        final int idx1 = der, idx2 = i+1;
        final int[] snap = arr.clone();
        SwingUtilities.invokeLater(() -> {
            notificarIntercambio();
            actualizarDataset(snap, idx1, idx2);
            lblEstado.setText("Colocando el pivote en su posicion final: i" + idx1 + " -> i" + idx2);
            statsPanel.appendLog("Colocando el pivote en su posicion final: i" + idx1 + " -> i" + idx2);
        });
        pausa();
        return i+1;
        
    }
    
    private void swap(int[] arr, int a, int b){
    
        if (a!=b){
            arr[a]=arr[a]+arr[b];
            arr[b]=arr[a]-arr[b];
            arr[a]=arr[a]-arr[b];
        }
        
    }
    
}
