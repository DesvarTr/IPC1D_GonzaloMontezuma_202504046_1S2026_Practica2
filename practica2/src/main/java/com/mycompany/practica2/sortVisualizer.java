package com.mycompany.practica2;

import java.util.Arrays;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

public abstract class sortVisualizer {

    protected DefaultCategoryDataset dataset;
    protected JLabel lblEstado;
    protected JLabel lblTipo;
    protected JButton btnOrdenar;
    protected JPanel panel;
    protected int[] arreglo;
    protected int velocidad;
    protected boolean ascendente;
    protected int[] ordenados;
    protected int[] movimientos;
    protected estadisticas statsPanel;

    public sortVisualizer(JPanel panel, int[] arreglo, boolean ascendente, int velocidad, estadisticas statsPanel) {
        this.panel      = panel;
        this.arreglo    = arreglo;
        this.ascendente = ascendente;
        this.velocidad  = velocidad;
        this.ordenados = Arrays.copyOf(arreglo,arreglo.length);
        Arrays.sort(ordenados);
        this.movimientos = new int[3];
        this.statsPanel = statsPanel;
        inicializarUI();
    }
    
    public sortVisualizer(JPanel panel, int[] arreglo, estadisticas statsPanel) {
        this(panel, arreglo, true, 300, statsPanel);
    }
   
    private void inicializarUI() {
        dataset = new DefaultCategoryDataset();
        actualizarDataset(arreglo.clone(), -1, -1);
        String titulo = getNombre() + " (" + (ascendente ? "ASC" : "DESC") + ") ";
        JFreeChart chart = ChartFactory.createBarChart(
            titulo, "Indice", "Valor", dataset
        );
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(220, 50, 50));  // normal
        renderer.setSeriesPaint(1, new Color(70, 130, 180));   // comparando
        renderer.setSeriesPaint(2, new Color(50, 180, 50));   // ordenado
        renderer.setShadowVisible(false);
        renderer.setBarPainter(new StandardBarPainter());

        ChartPanel chartPanel = new ChartPanel(chart);

        lblTipo = new JLabel("");
        lblEstado = new JLabel("Presiona para Iniciar", SwingConstants.CENTER);

        btnOrdenar = new JButton("Iniciar Ordenamiento");
        btnOrdenar.addActionListener(e -> {
            btnOrdenar.setEnabled(false);
            lblTipo.setText("Ordenando " + (ascendente ? "ascendente..." : "descendente..."));
            iniciarOrdenamiento(arreglo.clone());
        });

        JPanel panelSur = new JPanel(new BorderLayout(5, 5));
        panelSur.add(lblTipo,  BorderLayout.NORTH);
        panelSur.add(lblEstado,  BorderLayout.CENTER);
        panelSur.add(btnOrdenar, BorderLayout.SOUTH);

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.add(panelSur,   BorderLayout.SOUTH);
        panel.setPreferredSize(new Dimension(600, 400));
        panel.revalidate();
        panel.repaint();
    }


    private void iniciarOrdenamiento(int[] copia) {
        
        movimientos[0] = 0; // reset comparaciones
        movimientos[1] = 0; // reset intercambios
        movimientos[2] = 0; // reset iteraciones
        if (statsPanel != null) statsPanel.reset();
        
        Thread hilo = new Thread(() -> {
            ordenar(copia);

            SwingUtilities.invokeLater(() -> {
                actualizarDataset(copia, -1, -1);
                btnOrdenar.setEnabled(true);
                btnOrdenar.setText("Ordenar de nuevo");
                lblTipo.setText("");
                lblEstado.setText("Ordenado con " + getNombre());
            });
        });

        hilo.setDaemon(true);
        hilo.start();
    }

    // Actualizar grafico
    protected void actualizarDataset(int[] arr, int idx1, int idx2) {
        dataset.clear();
        for (int i = 0; i < arr.length; i++) {
            String etiqueta = "i" + i;
            if (i == idx1 || i == idx2) {
                dataset.addValue(arr[i], "Comparando", etiqueta);
                dataset.addValue(null, "Valores", etiqueta);
                dataset.addValue(null, "Ordenado", etiqueta);
            } else if (ordenados[i]==arr[i]) {
                dataset.addValue(null, "Comparando", etiqueta);
                dataset.addValue(null, "Valores", etiqueta);
                dataset.addValue(arr[i], "Ordenado", etiqueta);
            } else {
                dataset.addValue(null, "Comparando", etiqueta);
                dataset.addValue(arr[i], "Valores", etiqueta);
                dataset.addValue(null, "Ordenado", etiqueta);
            }
        }
    }

    // Comparacion de intercambio
    protected boolean debeIntercambiar(int a, int b) {
            
        SwingUtilities.invokeLater(() -> {
            notificarComparacion();
        });
        return ascendente ? a > b : a < b;
    }

    protected void notificarComparacion() {
        movimientos[0]++;
        if (statsPanel != null) statsPanel.actualizarComparaciones(movimientos[0]);
    }

    protected void notificarIntercambio() {
        movimientos[1]++;
        if (statsPanel != null) statsPanel.actualizarIntercambios(movimientos[1]);
    }

    protected void notificarIteracion() {
        movimientos[2]++;
        if (statsPanel != null) statsPanel.actualizarIteraciones(movimientos[2]);
    }
    
    protected void pausa() {
        try {
            Thread.sleep(velocidad);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected abstract String getNombre();
    protected abstract void ordenar(int[] arr);
}