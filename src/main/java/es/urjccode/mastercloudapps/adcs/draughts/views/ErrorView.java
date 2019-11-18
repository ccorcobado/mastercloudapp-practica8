package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;

class ErrorView {

    static final String[] MESSAGES = {
        "No te entiendo: <d><d>{,<d><d>}[0-2] o -1 para rendirte",
        "No es una coordenada del tablero",
        "No hay ficha que mover",
        "No es una de tus fichas",
        "No vas en diagonal",
        "No respetas la distancia",
        "No está vacío el destino",
        "No avanzas",
        "No comes contrarias",
        "No se puede comer tantas en un movimiento"
    };

    Error error;
    private static Console console = new Console();

    ErrorView(Error error) {
        this.error = error;
    }

    void writeln() {
        ErrorView.console.writeln("Error!!! " + ErrorView.MESSAGES[this.error.ordinal()]);
    }

}
