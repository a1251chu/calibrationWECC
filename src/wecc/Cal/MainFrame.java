package wecc.Cal;


import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import eu.hansolo.steelseries.extras.AirCompass;
import eu.hansolo.steelseries.gauges.DisplayRectangular;
import eu.hansolo.steelseries.tools.GaugeType;

import java.awt.CardLayout;

public class MainFrame extends JFrame {



	/**
	 * Launch the application.
	 */
	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];
	static MainFrame mainFrame;
	static Calibratrion cal;
	static String statusMessage;
	static String status;
	static JTextField txtStatusMessage;
	static JLabel airVoltage;
	static JLabel gasVoltage;
	static JLabel lblGasTargetFlow;
	static JLabel lblAirTargetFlow;
	static JButton standbyBtn;
	static JButton manualBtn;
	SystemSetting sys = new SystemSetting();
	GasPanel gasPanel;
	AutoPanel autoPanel;
	AddGasPanel addGasPanel;
	TypePanel typePanel;
	CardLayout cl;
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
		getContentPane().setSize(1000,1000);
		getContentPane().setLayout(new CardLayout());
		JPanel panel_1 = new JPanel();
		setCardLayoutComponent(panel_1);
		panel_1.setBackground(sys.sysLightGrayBackground);
		panel_1.setLayout(null);
		


		
		JButton gasBtn = sys.sysGasBtn;
		gasBtn.setLocation(25, 361);
		panel_1.add(gasBtn);
	
		autoBtn = sys.sysAutoBtn;
		autoBtn.setLocation(180, 361);
		panel_1.add(autoBtn);
		
		manualBtn = sys.sysManualBtn;
		manualBtn.setLocation(333, 361);
		panel_1.add(manualBtn);
		
		JButton settingBtn = sys.sysSetBtn;
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
		panel.setBounds(223, 10, 492, 176);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JLabel lblCal = new JLabel("Air");
		lblCal.setForeground(Color.WHITE);
		lblCal.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblCal.setBounds(221, 46, 58, 36);
		panel.add(lblCal);
		
		JLabel lblGas = new JLabel("GAS");
		lblGas.setForeground(Color.WHITE);
		lblGas.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblGas.setBounds(350, 46, 72, 36);
		panel.add(lblGas);
		
		JLabel lblVoltage = new JLabel("Voltage");
		lblVoltage.setForeground(Color.WHITE);
		lblVoltage.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblVoltage.setBounds(10, 133, 122, 36);
		panel.add(lblVoltage);
		
		airVoltage = new JLabel("0.0");
		airVoltage.setForeground(Color.WHITE);
		airVoltage.setFont(new Font("Arial Black", Font.BOLD, 25));
		airVoltage.setBounds(221, 133, 122, 36);
		panel.add(airVoltage);
		
		gasVoltage = new JLabel("0.0");
		gasVoltage.setForeground(Color.WHITE);
		gasVoltage.setFont(new Font("Arial Black", Font.BOLD, 25));
		gasVoltage.setBounds(350, 133, 132, 36);
		panel.add(gasVoltage);
		
		JLabel lblTargetFlow = new JLabel("Target Flow");
		lblTargetFlow.setForeground(Color.WHITE);
		lblTargetFlow.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblTargetFlow.setBounds(10, 87, 182, 36);
		panel.add(lblTargetFlow);
		
		lblAirTargetFlow = new JLabel("0.0");
		lblAirTargetFlow.setForeground(Color.WHITE);
		lblAirTargetFlow.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblAirTargetFlow.setBounds(221, 87, 122, 36);
		panel.add(lblAirTargetFlow);
		
		lblGasTargetFlow = new JLabel("0.0");
		lblGasTargetFlow.setForeground(Color.WHITE);
		lblGasTargetFlow.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblGasTargetFlow.setBounds(350, 87, 132, 36);
		panel.add(lblGasTargetFlow);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 492, 27);
		panel.add(panel_2);
		
		standbyBtn = sys.sysStandbyBtn;
		standbyBtn.setEnabled(false);
		standbyBtn.setBounds(482, 361, 100, 100);
		panel_1.add(standbyBtn);
		
		standbyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cal.standBy();
				lblAirTargetFlow.setText("0.0");
				lblGasTargetFlow.setText("0.0");
				airVoltage.setText(Double.toString(cal.airVoltage));
				gasVoltage.setText(Double.toString(cal.gasVoltage));
				standbyBtn.setEnabled(false);
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
				cl.show(getContentPane(), "auto");
				autoPanel.getGasName();
				
			}
		});
		manualBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

		        cl.show(getContentPane(), "manual");

			}
		});
		gasBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  cl.show(getContentPane(), "gas");
				
			}
		});
		settingBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(getContentPane(), "type");
				typePanel.initParameter();
			}
		});
		
	}
	public void setCardLayoutComponent(JPanel panel_1) {
		ManualPanel manualPanel = new ManualPanel();
		autoPanel = new AutoPanel();
		gasPanel = new GasPanel();
		addGasPanel = new AddGasPanel();
		typePanel = new TypePanel();
		cl = (CardLayout)(getContentPane().getLayout());
		getContentPane().add(panel_1,"main");
		getContentPane().add(manualPanel, "manual");
		getContentPane().add(autoPanel, "auto");
		getContentPane().add(gasPanel, "gas");
		getContentPane().add(addGasPanel, "addGas");
		getContentPane().add(typePanel, "type");
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
