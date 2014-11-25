package usr.doetsch.jtimer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TimerWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel;
	private JButton btnStart;
	private JButton btnLap;
	private JButton btnReset;
	private Component horizontalGlue;
	private Component horizontalGlue_1;
	private Component horizontalStrut;
	private JPanel panel_1;
	private JButton btnNewButton;
	private Component horizontalGlue_2;
	private Component horizontalGlue_3;
	private JButton btnNewButton_1;
	private Component horizontalGlue_4;
	private JTimer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimerWindow frame = new TimerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TimerWindow() {
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 166);
		this.contentPane = new JPanel();
		//this.contentPane.setMinimumSize(new Dimension(416, 128));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		

		
		this.textField = new JTextField();
		this.textField.setHorizontalAlignment(SwingConstants.CENTER);
		this.textField.setBackground(Color.WHITE);
		this.textField.setEditable(false);
		this.textField.setText("00:00:00.000");
		this.contentPane.add(this.textField, BorderLayout.CENTER);
		this.textField.setColumns(10);
		textField.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent arg0) {
				Dimension d = getContentPane().getSize();
				
				
				textField.setFont(new Font("Arial", Font.PLAIN, textField.getSize().height));
				setTitle(String.valueOf(d.width) + "x" + String.valueOf(d.height));
			}

		});
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.NORTH);
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.X_AXIS));
		
		this.horizontalGlue = Box.createHorizontalGlue();
		this.panel.add(this.horizontalGlue);
		
		this.btnStart = new JButton("Start /Pause");
		this.btnStart.addActionListener(new BtnStartActionListener());
		this.panel.add(this.btnStart);
		
		this.btnLap = new JButton("Lap");
		this.panel.add(this.btnLap);
		
		this.horizontalStrut = Box.createHorizontalStrut(20);
		this.panel.add(this.horizontalStrut);
		
		this.btnReset = new JButton("Reset");
		this.panel.add(this.btnReset);
		
		this.horizontalGlue_1 = Box.createHorizontalGlue();
		this.panel.add(this.horizontalGlue_1);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1, BorderLayout.SOUTH);
		this.panel_1.setLayout(new BoxLayout(this.panel_1, BoxLayout.X_AXIS));
		
		this.horizontalGlue_2 = Box.createHorizontalGlue();
		this.panel_1.add(this.horizontalGlue_2);
		
		this.btnNewButton_1 = new JButton("Lap Log...");
		this.panel_1.add(this.btnNewButton_1);
		
		this.horizontalGlue_4 = Box.createHorizontalGlue();
		this.panel_1.add(this.horizontalGlue_4);
		
		this.btnNewButton = new JButton("Configure...");
		this.panel_1.add(this.btnNewButton);
		
		this.horizontalGlue_3 = Box.createHorizontalGlue();
		this.panel_1.add(this.horizontalGlue_3);
		
		this.timer = new JTimer(10000, textField);
		this.timer.execute();
	}

	private class BtnStartActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (timer.isRunning()) {
				timer.pause();
			} else {
				timer.start();
			}
		}
	}
}
