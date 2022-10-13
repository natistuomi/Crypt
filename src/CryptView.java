import javax.swing.*;
import java.awt.event.ActionListener;

public class CryptView {
    private JPanel view;
    private JTextField messageNameTextField;
    private JTextField keyNameTextField;
    private JButton sendButton;
    private JTextArea encryptionTextArea;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CryptView");
        frame.setContentPane(new CryptView().view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getView() {
        return view;
    }

    public String getMessage() {
        return messageNameTextField.getText();
    }

    public String getKey() {
        return keyNameTextField.getText();
    }

    public void setCrypt(String c) {
        encryptionTextArea.setText(c);
    }

    public void setCryptListener(ActionListener cryptListener) {
        sendButton.addActionListener(cryptListener);
    }
}
