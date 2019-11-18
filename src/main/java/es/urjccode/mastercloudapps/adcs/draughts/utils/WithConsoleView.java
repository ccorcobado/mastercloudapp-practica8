package es.urjccode.mastercloudapps.adcs.draughts.utils;

public abstract class WithConsoleView {

    protected Console console;

    protected WithConsoleView() {
        this.console = new Console();
    }

}
