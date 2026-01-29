package model;

import java.time.LocalDate;

public class Solicitud {
    private String idSolicitud;
    private String tipoSolicitante;
    private String tipoActuacion;
    private int añoFinEjecucion;
    private double totalAhorroSolicitado;
    private String comunidadAutonoma;
    private int numeroActuaciones;
    private double ahorroActuacion;
    private String idActuacion;
    private String codigoTitulo;
    private String descripcion;
    private LocalDate fecha;
    private String estado;
    private String sector;
    
    // Constructor
    public Solicitud(String idSolicitud, String tipoSolicitante, String tipoActuacion, 
                     int añoFinEjecucion, double totalAhorroSolicitado, String comunidadAutonoma,
                     int numeroActuaciones, double ahorroActuacion, String idActuacion,
                     String codigoTitulo, String descripcion, LocalDate fecha, 
                     String estado, String sector) {
        this.idSolicitud = idSolicitud;
        this.tipoSolicitante = tipoSolicitante;
        this.tipoActuacion = tipoActuacion;
        this.añoFinEjecucion = añoFinEjecucion;
        this.totalAhorroSolicitado = totalAhorroSolicitado;
        this.comunidadAutonoma = comunidadAutonoma;
        this.numeroActuaciones = numeroActuaciones;
        this.ahorroActuacion = ahorroActuacion;
        this.idActuacion = idActuacion;
        this.codigoTitulo = codigoTitulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.sector = sector;
    }
    
    // Getters y Setters
    public String getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(String idSolicitud) { this.idSolicitud = idSolicitud; }
    
    public String getTipoSolicitante() { return tipoSolicitante; }
    public void setTipoSolicitante(String tipoSolicitante) { this.tipoSolicitante = tipoSolicitante; }
    
    public String getTipoActuacion() { return tipoActuacion; }
    public void setTipoActuacion(String tipoActuacion) { this.tipoActuacion = tipoActuacion; }
    
    public int getAñoFinEjecucion() { return añoFinEjecucion; }
    public void setAñoFinEjecucion(int añoFinEjecucion) { this.añoFinEjecucion = añoFinEjecucion; }
    
    public double getTotalAhorroSolicitado() { return totalAhorroSolicitado; }
    public void setTotalAhorroSolicitado(double totalAhorroSolicitado) { this.totalAhorroSolicitado = totalAhorroSolicitado; }
    
    public String getComunidadAutonoma() { return comunidadAutonoma; }
    public void setComunidadAutonoma(String comunidadAutonoma) { this.comunidadAutonoma = comunidadAutonoma; }
    
    public int getNumeroActuaciones() { return numeroActuaciones; }
    public void setNumeroActuaciones(int numeroActuaciones) { this.numeroActuaciones = numeroActuaciones; }
    
    public double getAhorroActuacion() { return ahorroActuacion; }
    public void setAhorroActuacion(double ahorroActuacion) { this.ahorroActuacion = ahorroActuacion; }
    
    public String getIdActuacion() { return idActuacion; }
    public void setIdActuacion(String idActuacion) { this.idActuacion = idActuacion; }
    
    public String getCodigoTitulo() { return codigoTitulo; }
    public void setCodigoTitulo(String codigoTitulo) { this.codigoTitulo = codigoTitulo; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }
    
    @Override
    public String toString() {
        return String.format("Solicitud[id=%s, sector=%s, ahorro=%.2f kWh, estado=%s]", 
                idSolicitud, sector, totalAhorroSolicitado, estado);
    }
}