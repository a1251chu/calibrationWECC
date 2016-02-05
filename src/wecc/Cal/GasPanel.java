package wecc.Cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GasPanel extends JPanel {

	String[] gasTableName;
	Double[] gasTableConc;
	Double[] unitTable;
	String showGasName;
	HashMap<String,Double> gasTablePPM;
	Unit gasUnit = new Unit();
	JButton addBtn;
	static int gasTableNum;
	SystemSetting sys = new SystemSetting();
	public GasPanel() {
		refreshGasList();
		addBtn();			
	}

	private void addBtn() {
		JButton addBtn;
			setBackground(Color.BLACK);
			setBounds(0, 0, 1050, 1500);
			setLayout(null);
		
			addBtn = sys.sysAddBtn;
			addBtn.setLocation(225, 361);
			add(addBtn);
			
			JButton cancelBtn = sys.sysCancelBtn;
			cancelBtn.setLocation(425, 361);
			add(cancelBtn);


			cancelBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

				

					CloseGasPanel();
				}
			});
			addBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					MainFrame.mainFrame.cl.show(MainFrame.mainFrame.getContentPane(), "addGas");
				}
			});
	}

		public void refreshGasList() {
			removeAll();
			getGasName();
			int initR=20;
			int initG=126;
			int initB=240;
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
				gasPanel[i].setBackground(new Color(initR,initG,initB));
				gasPanel[i].setBounds(10, yPosition, 1030, 50);
				gasPanel[i].setLayout(null);
				add(gasPanel[i]);
				
				gasName[i] = new JLabel(gasTableName[i]);
				gasName[i].setForeground(Color.WHITE);
				gasName[i].setHorizontalAlignment(SwingConstants.CENTER);
				gasName[i].setFont(new Font("Myanmar Text", Font.BOLD, 23));
				gasName[i].setBounds(85, 10, 105, 30);
				showGasName = gasTableName[i];
				gasName[i].setText(gasTableName[i]);
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
				gasConc[i].setText(String.valueOf(showConc));
				gasPanel[i].add(gasConc[i]);
				
				
				gasUnit[i] = new JLabel(showUnit);
				gasUnit[i].setHorizontalAlignment(SwingConstants.CENTER);
				gasUnit[i].setForeground(Color.WHITE);
				gasUnit[i].setFont(new Font("Myanmar Text", Font.BOLD, 23));
				gasUnit[i].setBounds(383, 10, 105, 30);
				gasUnit[i].setText(showUnit);
				gasPanel[i].add(gasUnit[i]);
				
				editBtn[i] = new JButton("Edit");
				editBtn[i].setBounds(515, 10, 59, 23);
				editBtn[i].setActionCommand(showGasName);
				gasPanel[i].add(editBtn[i]);
				
				delBtn[i] = new JButton("Del");
				delBtn[i].setBackground(Color.LIGHT_GRAY);
				delBtn[i].setForeground(Color.RED);
				delBtn[i].setBounds(593, 10, 59, 23);
				gasPanel[i].add(delBtn[i]);
				
				editBtn[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						MainFrame.mainFrame.cl.show(MainFrame.mainFrame.getContentPane(), "addGas");
						MainFrame.mainFrame.addGasPanel.setEditPanel(e.getActionCommand());					
					}
				});
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
						jframe.setBounds(0, 0, 500, 300);
						int n = JOptionPane.showConfirmDialog(
							    jframe,
							    "Are you sure DELETE ?",
							    "Delete Gas",
							    JOptionPane.YES_NO_OPTION);
						if(n==0){
							MainFrame.mainFrame.cal.deleteGas(showGasName); 
							refreshGasList();
						}
					}
				});
				yPosition +=50;
				initR-=1;
				initG+=10;
				initB+=1;
			}
			addBtn();
		}
		public void getGasName(){
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
		
		public void CloseGasPanel(){
			MainFrame.mainFrame.cl.show(MainFrame.mainFrame.getContentPane(), "main");

		}
	}


