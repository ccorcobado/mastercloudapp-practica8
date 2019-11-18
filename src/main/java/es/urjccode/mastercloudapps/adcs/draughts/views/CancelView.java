package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.CancelController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.YesNoDialog;

public class CancelView {

    private YesNoDialog yesNoDialog;
    
    public CancelView() {
        this.yesNoDialog = new YesNoDialog();
    }
    
    public void confirm(CancelController cancelController) {
        cancelController.cancel(this.yesNoDialog.read("¿Quieres cancelar?"));
    }
}
