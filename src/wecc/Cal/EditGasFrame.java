package wecc.Cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;

public class EditGasFrame extends JFrame {
	String[] unitTable;
	int[] concNum = new int[6];
	static int unitTableNum;
	static int showUnitIndex;

	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];
	JLabel unitLbl;
	JLabel gasNameLbl;
	Unit gasUnit = new Unit();
	ArrayList<String> initGasName = new ArrayList<String>();
	JButton[] concUpBtn = new JButton[6];
	JButton[] concDownBtn = new JButton[6];
	JLabel[] concLbl = new JLabel[6];
	String setGasName;
	Double setUnit;
	int initGasIndex;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditGasFrame frame = new EditGasFrame();
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
	public EditGasFrame() {
		initGasIndex = 0;
		getInitGas();
		setBackground(Color.GRAY);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //�̤j��
		this.setAlwaysOnTop(true); //�`�b�̫e��
		this.setResizable(false); //������ܤj�p
		this.setUndecorated(true); //���n���
		setBounds(0, 0, 1050, 1500);
		
		getContentPane().setLayout(new BorderLayout() );
		getContentPane().setSize(1000,1000);
		
		

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.getHSBColor(0.51f, 0.56f,0.55f));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
	
		JButton addBtn = new JButton("Add");
		addBtn.setSize(100, 100);
		addBtn.setLocation(225, 361);
		panel_1.add(addBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setSize(100, 100);
		cancelBtn.setLocation(425, 361);
		panel_1.add(cancelBtn);
		
		
		int xPosition = 36;
		int numXPosition = 54;
		JButton gasUpBtn = new JButton("^");
		JLabel gasNameLbl = new JLabel(initGasName.get(initGasIndex));
		JButton gasDownBtn = new JButton("﹀");
		gasUpBtn.setBounds(xPosition, 96, 70, 43);
		gasNameLbl.setBounds(xPosition,146,100,44);
		gasDownBtn.setBounds(xPosition, 197, 70	, 43);
		gasNameLbl.setForeground(Color.ORANGE);
		gasNameLbl.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
		panel_1.add(gasUpBtn);
		panel_1.add(gasNameLbl);
		panel_1.add(gasDownBtn);
		setGasBtn(gasUpBtn, gasNameLbl, gasDownBtn);
		xPosition += 100;
		numXPosition += 100;
		for(int i=0;i<6;i++){
			concUpBtn[i] = new JButton("^");
			concLbl[i] = new JLabel("0");
			concDownBtn[i] = new JButton("﹀");
			concUpBtn[i].setBounds(xPosition, 96, 70, 43);
			concLbl[i].setBounds(numXPosition,146,23,44);
			concDownBtn[i].setBounds(xPosition, 197, 70	, 43);
			concLbl[i].setForeground(Color.ORANGE);
			concLbl[i].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
			panel_1.add(concUpBtn[i]);
			panel_1.add(concDownBtn[i]);
			panel_1.add(concLbl[i]);
			
			if(i==3){
				xPosition += 78;
				numXPosition += 78;
				JLabel label = new JLabel(".");
				label.setForeground(Color.ORANGE);
				label.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
				label.setBounds(numXPosition,136,23,44);
				panel_1.add(label);
			}
			xPosition += 78;
			numXPosition += 78;
		}
		
		JButton unitUpBtn = new JButton("^");
		JButton unitDownBtn = new JButton("﹀");
		unitUpBtn.setBounds(xPosition, 96, 70, 43);
		unitDownBtn.setBounds(xPosition, 197, 70, 43);
		unitLbl = new JLabel("PPB");
		unitLbl.setForeground(Color.ORANGE);
		unitLbl.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
		unitLbl.setBounds(numXPosition-10,146,123,44);
		panel_1.add(unitUpBtn);
		panel_1.add(unitLbl);
		panel_1.add(unitDownBtn);
		getUnit();
		showUnitIndex = 0;
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double gasConcPPM = (arrayToDouble(concNum)*setUnit)/1000;
				MainFrame.cal.addGas(setGasName, gasConcPPM);
				GasFrame gasFrame = new GasFrame();
				device.setFullScreenWindow(gasFrame);
				gasFrame.setVisible(true);
				CloseEditGasFrame();
			}
		});
		
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GasFrame gasFrame = new GasFrame();
				device.setFullScreenWindow(gasFrame);
				gasFrame.setVisible(true);
				CloseEditGasFrame();
				
			}
		});
		
		
		setUnitBtn(unitUpBtn, unitDownBtn);
		setConcBtnListener();
		
	}

	private void setUnitBtn(JButton unitUpBtn, JButton unitDownBtn) {
		unitUpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showUnitIndex++;
				if(showUnitIndex>(unitTableNum-1))
					showUnitIndex = 0;
				setUnit = gasUnit.unitMap.get(unitTable[showUnitIndex]);
				unitLbl.setText(unitTable[showUnitIndex]);
			}
		});
		
		unitDownBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showUnitIndex--;
				if(showUnitIndex<0)
					showUnitIndex = unitTableNum-1;
				unitLbl.setText(unitTable[showUnitIndex]);
				
			}
		});
	}

	private void setGasBtn(JButton gasUpBtn, JLabel gasNameLbl, JButton gasDownBtn) {
		gasUpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				initGasIndex++;
				if(initGasIndex>=initGasName.size()){
					initGasIndex=0;
				}
				setGasName = initGasName.get(initGasIndex);
				gasNameLbl.setText(initGasName.get(initGasIndex));
			}
		});
		gasDownBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				initGasIndex--;
				if(initGasIndex<0){
					initGasIndex=(initGasName.size()-1);
				}
				setGasName = initGasName.get(initGasIndex);
				gasNameLbl.setText(initGasName.get(initGasIndex));
			}
		});
	}

	private void setConcBtnListener() {
		concUpBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[0]++;
				if(concNum[0]>9)
					concNum[0] = 0;
				concLbl[0].setText(String.valueOf(concNum[0]));
			}
		});
		concUpBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[1]++;
				if(concNum[1]>9)
					concNum[1] = 0;
				concLbl[1].setText(String.valueOf(concNum[1]));
			}
		});
		
		concUpBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[2]++;
				if(concNum[2]>9)
					concNum[2] = 0;
				concLbl[2].setText(String.valueOf(concNum[2]));
			}
		});
		
		concUpBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[3]++;
				if(concNum[3]>9)
					concNum[3] = 0;
				concLbl[3].setText(String.valueOf(concNum[3]));
			}
		});
		
		concUpBtn[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[4]++;
				if(concNum[4]>9)
					concNum[4] = 0;
				concLbl[4].setText(String.valueOf(concNum[4]));
			}
		});
		
		concUpBtn[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[5]++;
				if(concNum[5]>9)
					concNum[5] = 0;
				concLbl[5].setText(String.valueOf(concNum[5]));
			}
		});
		
		concDownBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[0]--;
				if(concNum[0]<0)
					concNum[0] = 9;
				concLbl[0].setText(String.valueOf(concNum[0]));
			}
		});
		concDownBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[1]--;
				if(concNum[1]<0)
					concNum[1] = 9;
				concLbl[1].setText(String.valueOf(concNum[1]));
			}
		});
		concDownBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[2]--;
				if(concNum[2]<0)
					concNum[2] = 9;
				concLbl[2].setText(String.valueOf(concNum[2]));
			}
		});
		concDownBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[3]--;
				if(concNum[3]<0)
					concNum[3] = 9;
				concLbl[3].setText(String.valueOf(concNum[3]));
			}
		});
		concDownBtn[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[4]--;
				if(concNum[4]<0)
					concNum[4] = 9;
				concLbl[4].setText(String.valueOf(concNum[4]));
			}
		});

		concDownBtn[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				concNum[5]--;
				if(concNum[5]<0)
					concNum[5] = 9;
				concLbl[5].setText(String.valueOf(concNum[5]));
			}
		});
	}
	
	public void CloseEditGasFrame(){
		/*GasFrame.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		device.setFullScreenWindow(GasFrame.frame);*/
	    super.dispose();
	}
	
	private void getUnit(){
		unitTableNum = gasUnit.unitMap.size();
		unitTable = new String[unitTableNum];
		 int i = 0;
		 for (Object key : gasUnit.unitMap.keySet()) {
	            unitTable[i] = (String) key;
	            i++;
	        }
	}
	private double arrayToDouble(int[] num){
		double result = 0;
		double dec=0.01;
		for(int i=(num.length-1);i>=0;i--){
			result += num[i] * dec;
			dec = dec * 10;
		}
		return result;
		
	}
	private void getInitGas(){
		initGasName.add("SO2");
		initGasName.add("NO");
		initGasName.add("NO2");
		initGasName.add("CO");
		initGasName.add("O3");
		initGasName.add("CO2");
		initGasName.add("H2S");
	}
}
