package model;

import java.util.Map;
import java.util.List; 

public class AnalisisResultado {
    private Map<String, Double> ahorroPorSector;
    private Map<String, Double> ahorroPorComunidad;
    private Map<String, Long> conteoPorEstado;
    private double ahorroTotal; 
    private double ahorroPromedio;
    private List<String> topComunidades;


    public AnalisisResultado(Map<String, Double> ahorroPorSector, Map<String, Double> ahorroPorComunidad, Map<String, Long> conteoPorEstado, double ahorroTotal, double ahorroPromedio, List<String> topComunidades) {
        this.ahorroPorSector = ahorroPorSector; 
        this.ahorroPorComunidad = ahorroPorComunidad;
        this.conteoPorEstado = conteoPorEstado;
        this.ahorroTotal = ahorroTotal;
        this.ahorroPromedio = ahorroPromedio;
        this.topComunidades = topComunidades;
    }

    // Getters

    public Map<String, Double> getAhorroPorSector() {
        return ahorroPorSector;
    }

    public Map<String, Double> getAhorroPorComunidad() {
        return ahorroPorComunidad;
    }

    public Map<String, Long> getConteoPorEstado() {
        return conteoPorEstado;
    }

    public double getAhorroTotal() {
        return ahorroTotal;
    }

    public double getAhorroPromedio() {
        return ahorroPromedio;
    }

    public List<String> getTopComunidades() {
        return topComunidades;
    }
    
}

