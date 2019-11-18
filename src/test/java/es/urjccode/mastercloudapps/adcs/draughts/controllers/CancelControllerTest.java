package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class CancelControllerTest {
    @Mock
    Game game;

    @Mock
    State state;

    @InjectMocks
    CancelController cancelController;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenCancelControllerWhenCancelGameThenStateIsFinalState() {
        when(state.getValueState()).thenReturn(StateValue.FINAL);
        cancelController.cancel(true);
        assertEquals(StateValue.FINAL,state.getValueState());
        verify(state).next();
    }
}
