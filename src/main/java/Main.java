import javax.swing.*;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showErrorPopup("This is an error message", 3000));
    }

    private static void showErrorPopup(String errorMessage, int delayMillis) {
        JDialog dialog = new JDialog(); // Create a specific JDialog instance
        dialog.setModalityType(Dialog.ModalityType.MODELESS); // Make dialog non-modal
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Ensure proper disposal

        JOptionPane.showMessageDialog(dialog, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);

        // Ensure timer fires on EDT for thread safety
        Timer timer = new Timer(delayMillis, (ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                if (dialog.isVisible()) { // Check visibility before disposal
                    dialog.dispose(); // Dispose on EDT
                }
            });
        });
        timer.setRepeats(false);
        timer.start();
    }
}
