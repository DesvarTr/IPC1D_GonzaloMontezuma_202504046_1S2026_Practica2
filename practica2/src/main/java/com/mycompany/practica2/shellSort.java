package com.mycompany.practica2;

import javax.swing.*;
/**
 *
 * @author gonza
 */
public class shellSort extends sortVisualizer {
    
    public shellSort(JPanel panel, int[] arreglo) {
        super(panel, arreglo);
    }

    public shellSort(JPanel panel, int[] arreglo, boolean ascendente, int velocidad) {
        super(panel, arreglo, ascendente, velocidad);
    }
    
    @Override
    protected String getNombre() { return "Shell Sort"; }

    @Override
    protected void ordenar(int[] arr) {
        shellSort_run(arr, 2);
    }
    
    private void shellSort_run(int[] arr, int denominator){
        
        for (int i = arr.length / denominator; i >= 1; i = Math.max(i / denominator, i == 1 ? 0 : 1)) {

            for (int j = i; j < arr.length; j++) {
                //Guardar lo que esta originalmente en j
                int temp = arr[j];
                int k = j;
                
                while (k >= i && (debeIntercambiar(arr[k - i], temp))) {
                    arr[k] = arr[k - i];
                    k -= i;
                    final int idx1 = k-i, idx2 = k;
                    final int[] snap = arr.clone();

                    SwingUtilities.invokeLater(() -> {
                        actualizarDataset(snap, idx1, idx2);
                        lblEstado.setText("Intercambiando: i" + idx1 + " e i" + idx2);
                    });
                    pausa();
                }
                //Colocar j en la posicion donde debia ser insertado
                arr[k] = temp;
                
            }

        }
        
    }

}