import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class URLShortenerGUI extends JFrame {
    private final URLShortener urlShortener;

    private JTextField longUrlField;
    private JTextField shortUrlField;
    private JTextArea resultArea;

    public URLShortenerGUI() {
        urlShortener = new URLShortener();

        setTitle("URL Shortener");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JPanel longUrlPanel = new JPanel(new FlowLayout());
        longUrlPanel.add(new JLabel("Long URL:"));
        longUrlField = new JTextField(20);
        longUrlPanel.add(longUrlField);
        JButton shortenButton = new JButton("Shorten");
        longUrlPanel.add(shortenButton);

        JPanel shortUrlPanel = new JPanel(new FlowLayout());
        shortUrlPanel.add(new JLabel("Short URL:"));
        shortUrlField = new JTextField(20);
        shortUrlPanel.add(shortUrlField);
        JButton decodeButton = new JButton("Decode");
        shortUrlPanel.add(decodeButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(longUrlPanel);
        add(shortUrlPanel);
        add(new JScrollPane(resultArea));

        shortenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String longUrl = longUrlField.getText();
                String shortUrl = urlShortener.encode(longUrl);
                resultArea.append("Short URL: " + shortUrl + "\n");
            }
        });

        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String shortUrl = shortUrlField.getText();
                String longUrl = urlShortener.decode(shortUrl);
                if (longUrl != null) {
                    resultArea.append("Long URL: " + longUrl + "\n");
                } else {
                    resultArea.append("Short URL not found.\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                URLShortenerGUI gui = new URLShortenerGUI();
                gui.setVisible(true);
            }
        });
    }
}