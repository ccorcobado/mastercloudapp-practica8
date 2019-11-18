package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.views.CancelView;

public class CancelController {

    private State state;

    public CancelController(State state) {
        this.state = state;
    }
    
    public void cancelGame() {
        new CancelView().confirm(this);		
    }

    public void cancel(boolean cancel) {
        if (cancel) {
            this.state.next();
        }
    }
}
