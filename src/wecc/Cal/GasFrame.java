package wecc.Cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class GasFrame extends JFrame {

	private JPanel contentPane;
	
	String[] gasTableName;
	Double[] gasTableConc;
	Double[] unitTable;
	HashMap<String,Double> gasTablePPM;
	Unit gasUnit = new Unit();
	JButton addBtn;
	static int gasTableNum;
	/**
	 * Launch the application.
	 */
	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];
	static GasFrame frame;
	static JPanel panel_1 = new JPanel();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GasFrame();
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
	public GasFrame() {
		setBackground(Color.GRAY);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //�̤j��
		this.setAlwaysOnTop(true); //�`�b�̫e��
		this.setResizable(false); //������ܤj�p
		this.setUndecorated(true); //���n���
		setBounds(0, 0, 1050, 1500);
		
		getContentPane().setLayout(new BorderLayout() );
		getContentPane().setSize(1000,1000);


		
		
		panel_1.setBackground(Color.getHSBColor(0.51f, 0.56f,0.55f));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
	
		addBtn = new JButton("Add");
		addBtn.setSize(100, 100);
		addBtn.setLocation(225, 361);
		panel_1.add(addBtn);
		
		JButton cancelBtn = new JButton("Back");
		cancelBtn.setSize(100, 100);
		cancelBtn.setLocation(425, 361);
		panel_1.add(cancelBtn);
		refreshGasList(panel_1);
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
				//mainFrame = new MainFrame();
				//device.setFullScreenWindow(mainFrame);
				//mainFrame.setVisible(true);
				MainFrame.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				device.setFullScreenWindow(MainFrame.mainFrame);
				CloseGasFrame();
			}
		});
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				EditGasFrame editGasFrame = new EditGasFrame();
//				device.setFullScreenWindow(editGasFrame);
//				editGasFrame.setVisible(true);

				CloseGasFrame();
			}
		});

		
	}

	public void refreshGasList(JPanel panel_1) {
		getGasName();
		JPanel[] gasPanel = new JPanel[gasTableNum];
		JLabel[] gasName = new JLabel[gasTableNum];
		JLabel[] gasConc = new JLabel[gasTableNum];
		JLabel[] gasUnit = new JLabel[gasTableNum];
		JButton[] editBtn = new JButton[gasTableNum];
		JButton[] delBtn = new JButton[gasTableNum];
		
		int i;
		int yPosition = 10;
		String showUnit;
		double showConc;
		for(i=0;i<gasTableNum;i++){
			gasPanel[i] = new JPanel();
			gasPanel[i].setBackground(Color.getHSBColor(0.51f, 0.56f,0.55f));
			gasPanel[i].setBounds(10, yPosition, 1030, 50);
			gasPanel[i].setLayout(null);
			panel_1.add(gasPanel[i]);
			
			gasName[i] = new JLabel(gasTableName[i]);
			gasName[i].setForeground(Color.WHITE);
			gasName[i].setHorizontalAlignment(SwingConstants.CENTER);
			gasName[i].setFont(new Font("Myanmar Text", Font.BOLD, 23));
			gasName[i].setBounds(85, 10, 105, 30);
			gasPanel[i].add(gasName[i]);
			
			if((gasTableConc[i]*1000)>this.gasUnit.unitMap.get("PCT")){
				showConc = (gasTableConc[i]*1000)/(this.gasUnit.unitMap.get("PCT"));
				showUnit = "PCT";
			}else{
				showConc = gasTableConc[i];
				showUnit = "PPM";
			}
			
			gasConc[i] = new JLabel(String.valueOf(showConc));
			gasConc[i].setForeground(Color.WHITE);
			gasConc[i].setHorizontalAlignment(SwingConstants.CENTER);
			gasConc[i].setFont(new Font("Myanmar Text", Font.BOLD, 23));
			gasConc[i].setBounds(239, 10, 105, 30);
			gasPanel[i].add(gasConc[i]);
			
			
			gasUnit[i] = new JLabel(showUnit);
			gasUnit[i].setHorizontalAlignment(SwingConstants.CENTER);
			gasUnit[i].setForeground(Color.WHITE);
			gasUnit[i].setFont(new Font("Myanmar Text", Font.BOLD, 23));
			gasUnit[i].setBounds(383, 10, 105, 30);
			gasPanel[i].add(gasUnit[i]);
			
			editBtn[i] = new JButton("Edit");
			editBtn[i].setBounds(515, 10, 59, 23);
			gasPanel[i].add(editBtn[i]);
			
			delBtn[i] = new JButton("Del");
			delBtn[i].setBackground(Color.LIGHT_GRAY);
			delBtn[i].setForeground(Color.RED);
			delBtn[i].setBounds(593, 10, 59, 23);
			gasPanel[i].add(delBtn[i]);
			
			delBtn[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					/*final JOptionPane optionPane = new JOptionPane(
						    "The only way to close this dialog is by\n"
						    + "pressing one of the following buttons.\n"
						    + "Do you understand?",
						    JOptionPane.QUESTION_MESSAGE,
						    JOptionPane.YES_NO_OPTION);*/
					final JFrame jframe = new JFrame();
					int n = JOptionPane.showConfirmDialog(
						    jframe,
						    "Are you sure DELETE the gas?",
						    "Delete Gas",
						    JOptionPane.YES_NO_OPTION);
					if(n==0){
//						System.out.println("delete gas");
					}
				}
			});
			yPosition +=50;
		}
	}
	private void getGasName(){
		gasTablePPM = new HashMap(MainFrame.cal.gasTablePPM);
		gasTableNum = MainFrame.cal.gasTablePPM.size();
		gasTableName = new String[gasTableNum];
		gasTableConc = new Double[gasTableNum];
		int i = 0;
		 for (Object key : MainFrame.cal.gasTablePPM.keySet()) {
	            gasTableName[i] = (String) key;
	            gasTableConc[i] = MainFrame.cal.gasTablePPM.get(key);
	            i++;
	        }
	}
	
	public void CloseGasFrame(){
		//this.setVisible(false);
	    this.dispose();
	}

}
