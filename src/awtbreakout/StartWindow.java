package awtbreakout;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class StartWindow extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID = -1863196624729165596L;
    private Panel             startPanel       = new Panel();
    private Label             columnsLbl       = new Label("Columns:");
    private JTextField        columnTextBox    = new JTextField(3);
    private Label             rowsLbl          = new Label("Rows:");
    private JTextField        rowTextBox       = new JTextField(3);
    private JButton           enterBtn         = new JButton("Enter");

    public StartWindow()
    {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(500, 350);
        setLocation(d.width / 2 - getWidth() / 2, d.height / 2 -(getHeight() - 50) / 2);
        setDefaultCloseOperation(1);
        setResizable(false);
//        setUndecorated(true);
        setVisible(true);
        startPanel.setVisible(true);
        add(startPanel);
        startPanel.add(columnsLbl);
        columnTextBox.setToolTipText("How many columns to play with");
        columnTextBox.setText("20");
        columnTextBox.setSize(300, 50);
        columnTextBox.setLocation(getWidth() / 2 - columnTextBox.getWidth() / 2,(getHeight() - 50) - 250);
        startPanel.add(columnTextBox);
        startPanel.add(rowsLbl);
        rowTextBox.setToolTipText("How many rows to play with");
        rowTextBox.setText("8");
        rowTextBox.setSize(300, 50);
        rowTextBox.setLocation(getWidth() / 2 - rowTextBox.getWidth() / 2,(getHeight() - 50) - 150);
        rowTextBox.setHorizontalAlignment(JTextField.CENTER);
        startPanel.add(rowTextBox);
        enterBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                AWTBreakout.setSuperCols(Integer.valueOf(columnTextBox.getText()));
                AWTBreakout.setSuperRows(Integer.valueOf(rowTextBox.getText()));
                dispose();
                AWTBreakout.setGame(Integer.valueOf(columnTextBox.getText()), Integer.valueOf(rowTextBox.getText()));
            }
        });
        enterBtn.setEnabled(true);
        enterBtn.setVisible(true);
        enterBtn.setSize(300, 50);
        enterBtn.setLocation(getWidth() / 2 - enterBtn.getWidth() / 2, (getHeight() - 50) - enterBtn.getHeight());
        startPanel.add(enterBtn);
        repaint();
    }
}
