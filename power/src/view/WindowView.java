package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.EnergyOption;
import controller.EnergyController;

public class WindowView extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel radioPanel;
	private ButtonGroup group;
	private EnergyController ec;

	public void genererBoutons(TreeSet<EnergyOption> liste) {
		radioPanel.removeAll();
		group = new ButtonGroup();
		for (EnergyOption eo : liste) {
			JRadioButton bouton = new JRadioButton(eo.getDesc());
			bouton.setFocusPainted(false);
			bouton.setSelected(eo.isSelected());
			bouton.setActionCommand(eo.getGuid());
			bouton.addActionListener(this);
			group.add(bouton);
			radioPanel.add(bouton);
		}
		this.pack();
	}

	public void setEnergyController(EnergyController ec) {
		this.ec = ec;
	}

	public WindowView() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setTitle("Alimentation");

		URL url = this.getClass().getResource("/res/icon.png");
		ImageIcon icon = new ImageIcon(url);
		this.setIconImage(icon.getImage());

		this.setMinimumSize(new Dimension(220, (int) this.getMinimumSize().getHeight()));
		this.setResizable(false);
		Container container = this.getContentPane();

		JPanel containerRadio = new JPanel();
		radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
		containerRadio.add(this.radioPanel);
		container.add(containerRadio, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.ec.radioChoosed(e);
	}
}
