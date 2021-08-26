package be.paulhalleux.event.resources;

import be.paulhalleux.event.EventHandler;
import be.paulhalleux.event.Listener;

public class TestListener implements Listener {

    @EventHandler
    public void onCallReceived(TestEvent event) {
        event.setPassed(true);
    }

}
