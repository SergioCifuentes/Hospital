/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DummiesReportes;

/**
 *
 * @author sergio
 */
public class MedicamentosDTO {
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private String existentes;
    private String cantidadMinima;
    private String precio;
    private String costo;

    public MedicamentosDTO(String codigo, String nombre, String descripcion, String existentes, String cantidadMinima, String precio, String costo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existentes = existentes;
        this.cantidadMinima = cantidadMinima;
        this.precio = precio;
        this.costo = costo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getExistentes() {
        return existentes;
    }

    public String getCantidadMinima() {
        return cantidadMinima;
    }

    public String getPrecio() {
        return precio;
    }

    public String getCosto() {
        return costo;
    }
    
    
            
    
}
