package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.State;

class CancelController {

    private State state;

    public CancelController(State state) {
        this.state = state;
    }

    void cancel(boolean cancel) {
        if (cancel) {
            this.state.next();
        }
    }
}
