package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    CancelControllerTest.class, 
    PlayControllerTest.class, 
    ResumeControllerTest.class, 
    StartControllerTest.class } )
public final class AllControllerTest {
}