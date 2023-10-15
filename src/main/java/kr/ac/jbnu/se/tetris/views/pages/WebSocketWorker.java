package kr.ac.jbnu.se.tetris.views.pages;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.service.WebSocketService;

import javax.swing.*;

public class WebSocketWorker extends SwingWorker<Void, Void> {
    int returning=0;


    public WebSocketWorker() {

    }

    @Override
    protected Void doInBackground() {
        WebSocketService.getInstance().startMatching();
        while (true) {
            if (WebSocketService.getInstance().getClient().getSessionId() != null) {
                SwingUtilities.invokeLater(() -> {
                    returning=1;
                });
                break;
            } else {

            }
        }

        return null;
    }
}
