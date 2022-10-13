import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CryptController {
    CryptModel model;
    CryptView view;

    public CryptController(CryptModel m, CryptView v){
        this.model = m;
        this.view = v;
        this.setContentPane(view.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        view.setCryptListener(new cryptListener());
    }

    private class cryptListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setMessage(view.getMessage());
            model.setKey(view.getKey());
            model.makeEncryption();
            view.setCrypt(model.getEncryption());
        }
    }

    public static void main(String[] args) {
        CryptModel m = new CryptModel();
        CryptView v = new CryptView();
        CryptController thisIsTheProgram = new CryptController(m,v);
        thisIsTheProgram.setVisible(true);

    }
}
