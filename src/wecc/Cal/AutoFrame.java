package wecc.Cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class AutoFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];
	//static MainFrame mainFrame;
//	static AutoFrame autoFrame;
	int[] concNum = new int[6];
	int[] flowNum = new int[4];
	String[] gasTable;
	String[] unitTable;
	HashMap<String,Double> gasTablePPM;
	static int gasTableNum;
	static int showGasIndex;
	static int unitTableNum;
	static int showUnitIndex;
	
	JLabel unitLbl;
	JLabel gasNameLbl;
	Unit gasUnit = new Unit();
	public AutoFrame() {
		setBackground(Color.GRAY);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //�̤j��
		this.setAlwaysOnTop(true); //�`�b�̫e��
		this.setResizable(false); //������ܤj�p
		this.setUndecorated(true); //���n���
		setBounds(0, 0, 1050, 1500);
		
		getContentPane().setLayout(new BorderLayout() );
		getContentPane().setSize(1000,1000);


		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
	
		JButton genBtn = new JButton("Generate");
		genBtn.setSize(100, 100);
		genBtn.setLocation(225, 361);
		panel_1.add(genBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setSize(100, 100);
		cancelBtn.setLocation(425, 361);
		panel_1.add(cancelBtn);
		
		initComponent(panel_1);
		

		
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
//				mainFrame = new MainFrame();

				//mainFrame.setVisible(true);
				CloseAutoFrame();
			}
		});
		
		genBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				//mainFrame = new MainFrame();
				//device.setFullScreenWindow(mainFrame);
				//mainFrame.setVisible(true);
				

				Double targetConcPPB;
				Double targetFlowCC;
				Double targetGasConcPPB;
				String targetGas = gasNameLbl.getText();
				targetGasConcPPB = gasTablePPM.get(targetGas) * 1000;
				String targetUnit = unitLbl.getText();
				double originFlow = arrayToDouble(flowNum);
				double originConc = arrayToDouble(concNum);
				targetFlowCC = originFlow * 1000;
				targetConcPPB = originConc*(gasUnit.unitMap.get(targetUnit));
//				System.out.println("targetGasConcPPB:" + targetGasConcPPB);
//				System.out.println("targetConcPPB:" + targetConcPPB);
//				System.out.println("targetFlowCC:" + targetFlowCC);
				MainFrame.cal.autoGen(targetGasConcPPB, targetConcPPB, targetFlowCC);
				MainFrame.airVoltage.setText(Double.toString(MainFrame.cal.airVoltage));
				MainFrame.gasVoltage.setText(Double.toString(MainFrame.cal.gasVoltage));
				MainFrame.btnStandBy.setEnabled(true);
				MainFrame.manualBtn.setEnabled(false);
				MainFrame.statusMessage = String.valueOf(originConc) + targetUnit + " " + targetGas;
				MainFrame.txtStatusMessage.setText(MainFrame.statusMessage);
				MainFrame.txtStatusMessage.setForeground(Color.WHITE);;
				MainFrame.lblAirTargetFlow.setText(String.valueOf(MainFrame.cal.highMFC.targetFlowCC/1000)+"L");
				MainFrame.lblGasTargetFlow.setText(String.valueOf(MainFrame.cal.lowMFC.targetFlowCC)+"cc");
				
				CloseAutoFrame();
			}
		});		
	}
	private void initComponent(JPanel panel_1) {
		JLabel lblGas = new JLabel("GAS");
		lblGas.setForeground(Color.BLUE);
		lblGas.setBounds(26, 48, 86, 44);
//		lblGas.setLocation(200,50);
		lblGas.setFont(new Font("Serif", Font.PLAIN, 30));
		panel_1.add(lblGas);
		int xPosition = 226;
		int numXPosition = 244;
		JButton gasUpBtn = new JButton("^");
		gasNameLbl = new JLabel("SO2");
		JButton gasDownBtn = new JButton("﹀");
		gasUpBtn.setBounds(xPosition, 22, 60, 23);
		gasNameLbl.setBounds(xPosition,46,100,44);
		gasDownBtn.setBounds(xPosition, 84, 60	, 23);
		gasNameLbl.setForeground(Color.ORANGE);
		gasNameLbl.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
		panel_1.add(gasUpBtn);
		panel_1.add(gasNameLbl);
		panel_1.add(gasDownBtn);
		
		getGasName();
		showGasIndex = 0;
		gasNameLbl.setText(gasTable[showGasIndex]);
		setGasBtnListener(gasUpBtn, gasNameLbl, gasDownBtn);
		JLabel lblFlow = new JLabel("Concontration");
		lblFlow.setForeground(Color.BLUE);
		lblFlow.setFont(new Font("Serif", Font.PLAIN, 30));
		lblFlow.setBounds(26, 138, 179, 44);
		panel_1.add(lblFlow);
		
		JButton[] concUpBtn = new JButton[6];
		JButton[] concDownBtn = new JButton[6];
		JLabel[] concLbl = new JLabel[6];

		int i;
		for(i=0;i<6;i++){
			concUpBtn[i] = new JButton("^");
			concLbl[i] = new JLabel("0");
			concDownBtn[i] = new JButton("﹀");
			concUpBtn[i].setBounds(xPosition, 116, 60, 23);
			concLbl[i].setBounds(numXPosition,136,23,44);
			concDownBtn[i].setBounds(xPosition, 177, 60	, 23);
			concLbl[i].setForeground(Color.ORANGE);
			concLbl[i].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
			panel_1.add(concUpBtn[i]);
			panel_1.add(concDownBtn[i]);
			panel_1.add(concLbl[i]);
			if(i==3){
				xPosition += 68;
				numXPosition += 68;
				JLabel label = new JLabel(".");
				label.setForeground(Color.ORANGE);
				label.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
				label.setBounds(numXPosition,136,23,44);
				panel_1.add(label);
			}
			xPosition += 68;
			numXPosition += 68;
		}
		JButton unitUpBtn = new JButton("^");
		JButton unitDownBtn = new JButton("﹀");
		unitUpBtn.setBounds(xPosition, 116, 60, 23);
		unitDownBtn.setBounds(xPosition, 177, 60, 23);
		unitLbl = new JLabel("PPB");
		unitLbl.setForeground(Color.ORANGE);
		unitLbl.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
		unitLbl.setBounds(xPosition,136,123,44);
		panel_1.add(unitUpBtn);
		panel_1.add(unitLbl);
		panel_1.add(unitDownBtn);
		
		getUnit();
		showUnitIndex = 0;
		unitLbl.setText(unitTable[showUnitIndex]);
		setUnitBtnListener(unitUpBtn, unitDownBtn, unitLbl);
		JLabel lblTotalFlow = new JLabel("Total FLow");
		lblTotalFlow.setForeground(Color.BLUE);
		lblTotalFlow.setFont(new Font("Serif", Font.PLAIN, 30));
		lblTotalFlow.setBounds(26, 251, 179, 44);
		panel_1.add(lblTotalFlow);
		
		JButton[] flowUpBtn = new JButton[4];
		JButton[] flowDownBtn = new JButton[4];
		JLabel[] flowLbl = new JLabel[4];
		xPosition = 226;
		numXPosition = 244;
		for(i=0;i<4;i++){
			flowUpBtn[i] = new JButton("^");
			flowLbl[i] = new JLabel("0");
			flowDownBtn[i] = new JButton("﹀");
			flowUpBtn[i].setBounds(xPosition, 236, 60, 23);
			flowLbl[i].setBounds(numXPosition,256,23,44);
			flowDownBtn[i].setBounds(xPosition, 297, 60	, 23);
			flowLbl[i].setForeground(Color.ORANGE);
			flowLbl[i].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
			panel_1.add(flowUpBtn[i]);
			panel_1.add(flowDownBtn[i]);
			panel_1.add(flowLbl[i]);
			if(i==1){
				xPosition += 68;
				numXPosition += 68;
				JLabel label = new JLabel(".");
				label.setForeground(Color.ORANGE);
				label.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
				label.setBounds(numXPosition,256,23,44);
				panel_1.add(label);
			}
			xPosition += 68;
			numXPosition += 68;
		}
		JLabel flowUnitLbl = new JLabel("Liter");
		flowUnitLbl.setForeground(Color.ORANGE);
		flowUnitLbl.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
		flowUnitLbl.setBounds(numXPosition,256,120,84);
		
		panel_1.add(flowUnitLbl);
		
		setConcBtnListener(concUpBtn, concLbl,concDownBtn);
		setFlowBtnListener(flowUpBtn,flowLbl,flowDownBtn);
	}
	private void setUnitBtnListener(JButton unitUpBtn, JButton unitDownBtn, JLabel unitLbl) {
		unitUpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showUnitIndex++;
				if(showUnitIndex>(unitTableNum-1))
					showUnitIndex = 0;
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
	private void setGasBtnListener(JButton gasUpBtn, JLabel gasNameLbl, JButton gasDownBtn) {
		gasUpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showGasIndex++;
				if(showGasIndex>(gasTableNum-1))
					showGasIndex = 0;
				gasNameLbl.setText(gasTable[showGasIndex]);
			}
		});
		
		gasDownBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showGasIndex--;
				if(showGasIndex<0)
					showGasIndex = gasTableNum-1;
				gasNameLbl.setText(gasTable[showGasIndex]);
				
			}
		});
	}
	
	private void getGasName(){
		gasTablePPM = new HashMap(MainFrame.cal.gasTablePPM);
		gasTableNum = MainFrame.cal.gasTablePPM.size();
		gasTable = new String[gasTableNum];
		 int i = 0;
		 for (Object key : MainFrame.cal.gasTablePPM.keySet()) {
	            gasTable[i] = (String) key;
	            i++;
	        }
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
	private void setConcBtnListener(JButton[] concUpBtn, JLabel[] concLbl,JButton[] concDownBtn) {
		concUpBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
						concNum[0]++;
						if(concNum[0]>9)
							concNum[0] = 0;
				       concLbl[0].setText(Integer.toString(concNum[0]));
				   }
			}
		});
		
		concUpBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					concNum[1]++;
					if(concNum[1]>9)
						concNum[1] = 0;
			       concLbl[1].setText(Integer.toString(concNum[1]));
				   }
			}
		});
		
		concUpBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					concNum[2]++;
					if(concNum[2]>9)
						concNum[2] = 0;
			       concLbl[2].setText(Integer.toString(concNum[2]));
				   }
			}
		});
		
		concUpBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					concNum[3]++;
					if(concNum[3]>9)
						concNum[3] = 0;
			       concLbl[3].setText(Integer.toString(concNum[3]));
				   }
			}
		});
		
		concUpBtn[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					concNum[4]++;
					if(concNum[4]>9)
						concNum[4] = 0;
			       concLbl[4].setText(Integer.toString(concNum[4]));
				   }
			}
		});
		
		concUpBtn[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					concNum[5]++;
					if(concNum[5]>9)
						concNum[5] = 0;
			       concLbl[5].setText(Integer.toString(concNum[5]));
				   }
			}
		});
		
		concDownBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				concNum[0]--;
				if(concNum[0]<0)
					concNum[0] = 9;
		       concLbl[0].setText(Integer.toString(concNum[0]));
			}
		});
		
		concDownBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				concNum[1]--;
				if(concNum[1]<0)
					concNum[1] = 9;
		       concLbl[1].setText(Integer.toString(concNum[1]));
			}
		});
		
		concDownBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				concNum[2]--;
				if(concNum[2]<0)
					concNum[2] = 9;
		       concLbl[2].setText(Integer.toString(concNum[2]));
			}
		});
		
		concDownBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				concNum[3]--;
				if(concNum[3]<0)
					concNum[3] = 9;
		       concLbl[3].setText(Integer.toString(concNum[3]));
			}
		});
		
		concDownBtn[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				concNum[4]--;
				if(concNum[4]<0)
					concNum[4] = 9;
		       concLbl[4].setText(Integer.toString(concNum[4]));
			}
		});
		
		concDownBtn[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				concNum[5]--;
				if(concNum[5]<0)
					concNum[5] = 9;
		       concLbl[5].setText(Integer.toString(concNum[5]));
			}
		});

	}
	
	private void setFlowBtnListener(JButton[] flowUpBtn, JLabel[] flowLbl,JButton[] flowDownBtn) {
		flowUpBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
						flowNum[0]++;
						if(flowNum[0]>9)
							flowNum[0] = 0;
				       flowLbl[0].setText(Integer.toString(flowNum[0]));
				   }
			}
		});
		
		flowUpBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					flowNum[1]++;
					if(flowNum[1]>9)
						flowNum[1] = 0;
			       flowLbl[1].setText(Integer.toString(flowNum[1]));
				   }
			}
		});
		
		flowUpBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					flowNum[2]++;
					if(flowNum[2]>9)
						flowNum[2] = 0;
			       flowLbl[2].setText(Integer.toString(flowNum[2]));
				   }
			}
		});
		
		flowUpBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					flowNum[3]++;
					if(flowNum[3]>9)
						flowNum[3] = 0;
			       flowLbl[3].setText(Integer.toString(flowNum[3]));
				   }
			}
		});
		
		
		flowDownBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flowNum[0]--;
				if(flowNum[0]<0)
					flowNum[0] = 9;
		       flowLbl[0].setText(Integer.toString(flowNum[0]));
			}
		});
		
		flowDownBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flowNum[1]--;
				if(flowNum[1]<0)
					flowNum[1] = 9;
		       flowLbl[1].setText(Integer.toString(flowNum[1]));
			}
		});
		
		flowDownBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flowNum[2]--;
				if(flowNum[2]<0)
					flowNum[2] = 9;
		       flowLbl[2].setText(Integer.toString(flowNum[2]));
			}
		});
		
		flowDownBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flowNum[3]--;
				if(flowNum[3]<0)
					flowNum[3] = 9;
		       flowLbl[3].setText(Integer.toString(flowNum[3]));
			}
		});
		

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
	public void CloseAutoFrame(){
		MainFrame.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		device.setFullScreenWindow(MainFrame.mainFrame);
		MainFrame.mainFrame.setAlwaysOnTop(true);
		MainFrame.mainFrame.setVisible(true);
		//MainFrame.mainFrame.setBounds(0, 0, 1050, 1500);
		//MainFrame.mainFrame.setResizable(false); 
		//MainFrame.mainFrame.setUndecorated(true);
	    super.dispose();
	}
}
