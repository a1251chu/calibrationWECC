package wecc.Cal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

public class MainFrame extends JFrame {



	/**
	 * Launch the application.
	 */
	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];
	static MainFrame mainFrame;
	static AutoFrame autoFrame;
	static ManualFrame manualFrame;
	static Calibratrion cal;
	static String statusMessage;
	static String status;
	static JTextField txtStatusMessage;
	static JLabel airVoltage;
	static JLabel gasVoltage;
	static JLabel lblGasTargetFlow;
	static JLabel lblAirTargetFlow;
	static JButton btnStandBy;
	static JButton manualBtn;
	JButton autoBtn;
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cal = new Calibratrion(5.0, 10000.0, 5.0, 100.0);
					mainFrame = new MainFrame();
					device.setFullScreenWindow(mainFrame);
					mainFrame.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	/**
	 * Create the frame.
	 */

	
	
	public MainFrame() {
		initialComponent();

	}

	private void initialComponent() {
		setFullScreen();

		setBounds(0, 0, 1050, 1500);
		
		getContentPane().setLayout(new BorderLayout() );
		getContentPane().setSize(1000,1000);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton gasBtn = new JButton("Gas");
		gasBtn.setSize(100, 100);
		gasBtn.setLocation(25, 361);
		panel_1.add(gasBtn);
	
		autoBtn = new JButton("Auto Gen");
		autoBtn.setSize(100, 100);
		autoBtn.setLocation(180, 361);
		panel_1.add(autoBtn);
		
		manualBtn = new JButton("Manual Gen");
		manualBtn.setSize(100, 100);
		manualBtn.setLocation(333, 361);
		panel_1.add(manualBtn);
		
		JButton settingBtn = new JButton("Setting");
		settingBtn.setSize(100, 100);
		settingBtn.setLocation(625, 361);
		panel_1.add(settingBtn);
		
		txtStatusMessage = new JTextField();
		txtStatusMessage.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatusMessage.setEditable(false);
		txtStatusMessage.setText("Stand By");
		txtStatusMessage.setFont(new Font("Arial", Font.BOLD, 20));
		txtStatusMessage.setForeground(Color.WHITE);
		txtStatusMessage.setBackground(Color.BLUE);
		txtStatusMessage.setBounds(25, 319, 700, 31);
		panel_1.add(txtStatusMessage);
		txtStatusMessage.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(225, 10, 492, 299);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JLabel lblCal = new JLabel("Air");
		lblCal.setForeground(Color.WHITE);
		lblCal.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblCal.setBounds(221, 10, 58, 36);
		panel.add(lblCal);
		
		JLabel lblGas = new JLabel("GAS");
		lblGas.setForeground(Color.WHITE);
		lblGas.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblGas.setBounds(350, 10, 72, 36);
		panel.add(lblGas);
		
		JLabel lblVoltage = new JLabel("Voltage");
		lblVoltage.setForeground(Color.WHITE);
		lblVoltage.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblVoltage.setBounds(10, 97, 122, 36);
		panel.add(lblVoltage);
		
		airVoltage = new JLabel("0.0");
		airVoltage.setForeground(Color.WHITE);
		airVoltage.setFont(new Font("Arial Black", Font.BOLD, 25));
		airVoltage.setBounds(221, 97, 122, 36);
		panel.add(airVoltage);
		
		gasVoltage = new JLabel("0.0");
		gasVoltage.setForeground(Color.WHITE);
		gasVoltage.setFont(new Font("Arial Black", Font.BOLD, 25));
		gasVoltage.setBounds(350, 97, 132, 36);
		panel.add(gasVoltage);
		
		JLabel lblTargetFlow = new JLabel("Target Flow");
		lblTargetFlow.setForeground(Color.WHITE);
		lblTargetFlow.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblTargetFlow.setBounds(10, 51, 182, 36);
		panel.add(lblTargetFlow);
		
		lblAirTargetFlow = new JLabel("0.0");
		lblAirTargetFlow.setForeground(Color.WHITE);
		lblAirTargetFlow.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblAirTargetFlow.setBounds(221, 51, 122, 36);
		panel.add(lblAirTargetFlow);
		
		lblGasTargetFlow = new JLabel("0.0");
		lblGasTargetFlow.setForeground(Color.WHITE);
		lblGasTargetFlow.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblGasTargetFlow.setBounds(350, 51, 132, 36);
		panel.add(lblGasTargetFlow);
		
		btnStandBy = new JButton("Stand by");
		btnStandBy.setEnabled(false);
		btnStandBy.setBounds(482, 361, 100, 100);
		panel_1.add(btnStandBy);
		
		btnStandBy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cal.standBy();
				lblAirTargetFlow.setText("0.0");
				lblGasTargetFlow.setText("0.0");
				airVoltage.setText(Double.toString(cal.airVoltage));
				gasVoltage.setText(Double.toString(cal.gasVoltage));
				btnStandBy.setEnabled(false);
				manualBtn.setEnabled(true);
				autoBtn.setEnabled(true);
				statusMessage = "Stand By";
				txtStatusMessage.setText(statusMessage);
				txtStatusMessage.setForeground(Color.WHITE);
			}
		});
		
		autoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				autoFrame = new AutoFrame();
				device.setFullScreenWindow(autoFrame);
				autoFrame.setVisible(true);
				
			}
		});
		manualBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TestPanel testPanel = new TestPanel();
				getContentPane().removeAll();

				getContentPane().repaint();
				getContentPane().add(testPanel);
				getContentPane().repaint();
				getContentPane().validate();
				JButton btn = new JButton("AAA");
				testPanel.add(btn);
				//getContentPane().add(testPanel);
				//testPanel.setLayout(null);
				/*manualFrame = new ManualFrame();
				device.setFullScreenWindow(manualFrame);
				manualFrame.setVisible(true);*/
			}
		});
		gasBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GasFrame gasFrame = new GasFrame();
				device.setFullScreenWindow(gasFrame);
				gasFrame.setVisible(true);
				
			}
		});
		
	}
	private void setFullScreen() {
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setAlwaysOnTop(true); 
		this.setResizable(false); 
		this.setUndecorated(true);
	}
	

	public void CloseMainFrame(){
	    super.dispose();
		super.setVisible(false);
	}
	
	public class getTargetFlowRunnable implements Runnable{
		boolean doing = true;
		@Override
		public void run() {
			while(doing){
				//System.out.println(cal.highMFC.targetFlowCC);
			}
			
		}
	}
}
