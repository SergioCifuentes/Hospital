/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medicamento;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class AComprar {
    ArrayList<Medicina> medicinas;

    public AComprar(ArrayList<Medicina> medicinas) {
        this.medicinas = medicinas;
    }

    public ArrayList<Medicina> getMedicinas() {
        return medicinas;
    }

    
}
