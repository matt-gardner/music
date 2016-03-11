package com.gardner.music;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gardner.music.desktop.ui.*;
import com.gardner.music.desktop.*;
import com.gardner.music.transcription.*;

class Main {
    public static void main (String[] args) throws InterruptedException {
        String title = "Sound Engine";
        String ui = "basic";
        if (args.length > 0) {
            ui = args[0];
        }
        JPanel panel;

        if (ui.equals("file")) {
            panel = new SoundEngineFilePanel();
        } else {
            System.out.println("Currently broken, sorry...");
            DesktopMicrophone mic = new DesktopMicrophone();
            TranscriptionEngine engine = new TranscriptionEngine(mic);
            panel = null;
            //panel = new SoundEnginePanel(engine, mic);
        }

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
