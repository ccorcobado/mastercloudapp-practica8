package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.CancelController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.utils.YesNoDialog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class CancelViewTest {

    @Mock
    State state;

    @Mock
    Game game;

    @Mock
    CancelController cancelController;

    @Mock
    YesNoDialog yesNoDialog;

    @InjectMocks
    CancelView cancelView;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenCancelViewWhenUserWantsCancelThenNextState() {
        when(yesNoDialog.read("¿Quieres cancelar")).thenReturn(true);
        cancelView.confirm(cancelController);
        verify(cancelController).cancel(true);
    }
}
