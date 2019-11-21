const  NuevaM = document.getElementById("Nuevo");
const  ViejaM = document.getElementById("Viejo");
const  EspacioR = document.getElementById("EspacioNuevo");
const  EspacioE = document.getElementById("EspacioViejo");




function realizarNuevaMedicina() {
    if (NuevaM.checked === true) {
        ViejaM.checked = false;
        EspacioR.hidden = false;
        EspacioE.hidden=true;

    } else {
        EspacioR.hidden = true;
    }

}
function realizarViejaMedicina() {
    if (ViejaM.checked === true) {
        NuevaM.checked = false;
        EspacioR.hidden = true;
        EspacioE.hidden=false;

    } else {
        EspacioE.hidden=true;
    }
}
