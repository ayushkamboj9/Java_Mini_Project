import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

class MusicPlayer extends JFrame implements ActionListener {
    JButton openBtn, playBtn, pauseBtn, stopBtn, prevBtn, nextBtn; // Added prevBtn and nextBtn
    Clip clip;
    JComponent disk;

    public MusicPlayer() {
        setTitle("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Create a Box to hold the buttons
        Box buttonsBox = Box.createHorizontalBox();
        openBtn = new JButton("Open");
        playBtn = new JButton("Play");
        pauseBtn = new JButton("Pause");
        stopBtn = new JButton("Stop");
        prevBtn = new JButton("Previous"); // Added previous button
        nextBtn = new JButton("Next"); // Added next button

        openBtn.addActionListener(this);
        playBtn.addActionListener(this);
        pauseBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        prevBtn.addActionListener(this); // Added action listener for previous button
        nextBtn.addActionListener(this); // Added action listener for next button

        buttonsBox.add(openBtn);
        buttonsBox.add(playBtn);
        buttonsBox.add(pauseBtn);
        buttonsBox.add(stopBtn);
        buttonsBox.add(prevBtn); // Added previous button to the layout
        buttonsBox.add(nextBtn); // Added next button to the layout

        add(buttonsBox, BorderLayout.NORTH);

        // Create a custom JComponent for the rotating disk
        disk = new JComponent() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int size = Math.min(getWidth(), getHeight()) - 20;
                int x = (getWidth() - size) / 2;
                int y = (getHeight() - size) / 2;
                g2d.translate(getWidth() / 2, getHeight() / 2);
                g2d.rotate(Math.toRadians(System.currentTimeMillis() % 3600.0 / 10.0));
                g2d.translate(-size / 2, -size / 2);
                g2d.setColor(Color.BLACK); // Set color to red
                g2d.fillOval(x, y, size, size);
                g2d.setColor(Color.RED); // Set color to blue
                g2d.fillOval(x + size / 4, y + size / 4, size / 2, size / 2);
            }
        };

        add(disk, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openBtn) {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == playBtn) {
            clip.start();
        } else if (e.getSource() == pauseBtn) {
            clip.stop();
        } else if (e.getSource() == stopBtn) {
            clip.stop();
        } else if (e.getSource() == stopBtn) {
            clip.stop();
        } else if (e.getSource() == nextBtn) {
            clip.start();
            clip.setFramePosition(0);
        }
    }

    public static void main(String[] args) {
        new MusicPlayer();
    }
}

