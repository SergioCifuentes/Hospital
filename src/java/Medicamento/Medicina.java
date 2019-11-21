/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medicamento;

/**
 *
 * @author sergio
 */
public class Medicina {
    private String codigo;
    private Tipo tipo;
    private Compra compra;

    public Medicina(String codigo, Tipo tipo, Compra compra) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.compra = compra;
    }

    public String getCodigo() {
        return codigo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Compra getCompra() {
        return compra;
    }
    
    
}
