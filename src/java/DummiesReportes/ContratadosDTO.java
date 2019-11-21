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
public class ContratadosDTO {
private int cui;
private String nombre;
private float Salario;
private String area;
private String fecha;

    public ContratadosDTO(int cui, String nombre, float Salario, String area, String fecha) {
        this.cui = cui;
        this.nombre = nombre;
        this.Salario = Salario;
        this.area = area;
        this.fecha = fecha;
    }

    public int getCui() {
        return cui;
    }

    public String getNombre() {
        return nombre;
    }

    public float getSalario() {
        return Salario;
    }

    public String getArea() {
        return area;
    }

    public String getFecha() {
        return fecha;
    }




    
}
