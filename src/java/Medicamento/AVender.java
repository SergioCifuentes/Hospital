
package Medicamento;

/**
 *
 * @author sergio
 */
public class AVender {
private Tipo tipo;
private int cantidad;

    public AVender(Tipo tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }


}
