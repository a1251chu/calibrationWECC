package wecc.Cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ManualFrame extends JFrame {
	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];
	private JPanel contentPane;
	static MainFrame mainFrame;
	int[] highMFCNum = new int[4];
	int[] lowMFCNum = new int[4];
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManualFrame frame = new ManualFrame();
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
	public ManualFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
				// TODO Auto-generated method stub

				
				//mainFrame = new MainFrame();
				//device.setFullScreenWindow(mainFrame);
				//mainFrame.setVisible(true);
				CloseManualFrame();
			}
		});
		
		genBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//mainFrame = new MainFrame(arrayToDouble(highMFCNum),arrayToDouble(lowMFCNum));
				//device.setFullScreenWindow(mainFrame);
				//mainFrame.setVisible(true);
				double airMFCFlow = (arrayToDouble(highMFCNum)* 1000.0);
				double gasMFCFlow= arrayToDouble(lowMFCNum);
				if((airMFCFlow<500 && airMFCFlow != 0) || airMFCFlow>9500 || gasMFCFlow>95.0 || (gasMFCFlow < 10 && gasMFCFlow != 0)){
					MainFrame.statusMessage = "MFC Flow Warning";
					MainFrame.txtStatusMessage.setText(MainFrame.statusMessage);
					MainFrame.txtStatusMessage.setForeground(Color.RED);
				}else{
					MainFrame.cal.manualGen(airMFCFlow, gasMFCFlow);
					//MainFrame.cal.highMFC.targetFlow = arrayToDouble(highMFCNum);
					//System.out.println(MainFrame.cal.airVoltage);
					//System.out.println(MainFrame.cal.gasVoltage);
					//MainFrame.status = 1;
					MainFrame.airVoltage.setText(Double.toString(MainFrame.cal.airVoltage));
					MainFrame.gasVoltage.setText(Double.toString(MainFrame.cal.gasVoltage));
					MainFrame.btnStandBy.setEnabled(true);
					MainFrame.manualBtn.setEnabled(false);
					
					MainFrame.statusMessage = "Manual Generate";
					MainFrame.txtStatusMessage.setText(MainFrame.statusMessage);
					MainFrame.txtStatusMessage.setForeground(Color.WHITE);
					MainFrame.lblAirTargetFlow.setText(String.valueOf(MainFrame.cal.highMFC.targetFlowCC/1000)+"L");
					MainFrame.lblGasTargetFlow.setText(String.valueOf(MainFrame.cal.lowMFC.targetFlowCC)+"cc");
				}
				
				CloseManualFrame();
			}
		});
		
	}
	
	private void initComponent(JPanel panel_1) {
		
		
		JLabel lblFlow = new JLabel("High MFC Flow");
		lblFlow.setForeground(Color.BLUE);
		lblFlow.setFont(new Font("Serif", Font.PLAIN, 30));
		lblFlow.setBounds(26, 138, 250, 44);
		panel_1.add(lblFlow);
		
		JButton[] highMFCUpBtn = new JButton[4];
		JButton[] highMFCDownBtn = new JButton[4];
		JLabel[] highMFCLbl = new JLabel[4];
		int xPosition = 294;
		int numXPosition = 312;
		int i;
		for(i=0;i<4;i++){
			highMFCUpBtn[i] = new JButton("^");
			highMFCLbl[i] = new JLabel("0");
			highMFCDownBtn[i] = new JButton("﹀");
			highMFCUpBtn[i].setBounds(xPosition, 116, 60, 23);
			highMFCLbl[i].setBounds(numXPosition,136,23,44);
			highMFCDownBtn[i].setBounds(xPosition, 177, 60	, 23);
			highMFCLbl[i].setForeground(Color.ORANGE);
			highMFCLbl[i].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
			panel_1.add(highMFCUpBtn[i]);
			panel_1.add(highMFCDownBtn[i]);
			panel_1.add(highMFCLbl[i]);
			if(i==1){
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
		JLabel unitLbl = new JLabel("Liter");
		unitLbl.setForeground(Color.ORANGE);
		unitLbl.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
		unitLbl.setBounds(numXPosition,136,123,84);
		
		panel_1.add(unitLbl);
		
		JLabel lblTotalFlow = new JLabel("Low MFC Flow");
		lblTotalFlow.setForeground(Color.BLUE);
		lblTotalFlow.setFont(new Font("Serif", Font.PLAIN, 30));
		lblTotalFlow.setBounds(26, 251, 250, 44);
		panel_1.add(lblTotalFlow);
		
		JButton[] lowMFCUpBtn = new JButton[4];
		JButton[] lowMFCDownBtn = new JButton[4];
		JLabel[] lowMFCLbl = new JLabel[4];
		xPosition = 294;
		numXPosition = 312;
		for(i=0;i<4;i++){
			lowMFCUpBtn[i] = new JButton("^");
			lowMFCLbl[i] = new JLabel("0");
			lowMFCDownBtn[i] = new JButton("﹀");
			lowMFCUpBtn[i].setBounds(xPosition, 236, 60, 23);
			lowMFCLbl[i].setBounds(numXPosition,256,23,44);
			lowMFCDownBtn[i].setBounds(xPosition, 297, 60	, 23);
			lowMFCLbl[i].setForeground(Color.ORANGE);
			lowMFCLbl[i].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
			panel_1.add(lowMFCUpBtn[i]);
			panel_1.add(lowMFCDownBtn[i]);
			panel_1.add(lowMFCLbl[i]);
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
		JLabel flowUnitLbl = new JLabel("CC");
		flowUnitLbl.setForeground(Color.ORANGE);
		flowUnitLbl.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 31));
		flowUnitLbl.setBounds(numXPosition,256,120,84);
		
		panel_1.add(flowUnitLbl);
		
		setHighMFCBtnListener(highMFCUpBtn,highMFCLbl,highMFCDownBtn);
		setLowMFCBtnListener(lowMFCUpBtn,lowMFCLbl,lowMFCDownBtn);
	}
	
	private void setHighMFCBtnListener(JButton[] highMFCUpBtn, JLabel[] highMFCLbl,JButton[] highMFCDownBtn) {
		highMFCUpBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
						highMFCNum[0]++;
						if(highMFCNum[0]>9)
							highMFCNum[0] = 0;
				       highMFCLbl[0].setText(Integer.toString(highMFCNum[0]));
				   }
			}
		});
		
		highMFCUpBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					highMFCNum[1]++;
					if(highMFCNum[1]>9)
						highMFCNum[1] = 0;
			       highMFCLbl[1].setText(Integer.toString(highMFCNum[1]));
				   }
			}
		});
		
		highMFCUpBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					highMFCNum[2]++;
					if(highMFCNum[2]>9)
						highMFCNum[2] = 0;
			       highMFCLbl[2].setText(Integer.toString(highMFCNum[2]));
				   }
			}
		});
		
		highMFCUpBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					highMFCNum[3]++;
					if(highMFCNum[3]>9)
						highMFCNum[3] = 0;
			       highMFCLbl[3].setText(Integer.toString(highMFCNum[3]));
				   }
			}
		});
		
		
		highMFCDownBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				highMFCNum[0]--;
				if(highMFCNum[0]<0)
					highMFCNum[0] = 9;
		       highMFCLbl[0].setText(Integer.toString(highMFCNum[0]));
			}
		});
		
		highMFCDownBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				highMFCNum[1]--;
				if(highMFCNum[1]<0)
					highMFCNum[1] = 9;
		       highMFCLbl[1].setText(Integer.toString(highMFCNum[1]));
			}
		});
		
		highMFCDownBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				highMFCNum[2]--;
				if(highMFCNum[2]<0)
					highMFCNum[2] = 9;
		       highMFCLbl[2].setText(Integer.toString(highMFCNum[2]));
			}
		});
		
		highMFCDownBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				highMFCNum[3]--;
				if(highMFCNum[3]<0)
					highMFCNum[3] = 9;
		       highMFCLbl[3].setText(Integer.toString(highMFCNum[3]));
			}
		});
	}
	
	private void setLowMFCBtnListener(JButton[] lowMFCUpBtn, JLabel[] lowMFCLbl,JButton[] lowMFCDownBtn) {
		lowMFCUpBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
						lowMFCNum[0]++;
						if(lowMFCNum[0]>9)
							lowMFCNum[0] = 0;
				       lowMFCLbl[0].setText(Integer.toString(lowMFCNum[0]));
				   }
			}
		});
		
		lowMFCUpBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					lowMFCNum[1]++;
					if(lowMFCNum[1]>9)
						lowMFCNum[1] = 0;
			       lowMFCLbl[1].setText(Integer.toString(lowMFCNum[1]));
				   }
			}
		});
		
		lowMFCUpBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					lowMFCNum[2]++;
					if(lowMFCNum[2]>9)
						lowMFCNum[2] = 0;
			       lowMFCLbl[2].setText(Integer.toString(lowMFCNum[2]));
				   }
			}
		});
		
		lowMFCUpBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( e.getSource() instanceof JButton) {
					lowMFCNum[3]++;
					if(lowMFCNum[3]>9)
						lowMFCNum[3] = 0;
			       lowMFCLbl[3].setText(Integer.toString(lowMFCNum[3]));
				   }
			}
		});
		
		
		lowMFCDownBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lowMFCNum[0]--;
				if(lowMFCNum[0]<0)
					lowMFCNum[0] = 9;
		       lowMFCLbl[0].setText(Integer.toString(lowMFCNum[0]));
			}
		});
		
		lowMFCDownBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lowMFCNum[1]--;
				if(lowMFCNum[1]<0)
					lowMFCNum[1] = 9;
		       lowMFCLbl[1].setText(Integer.toString(lowMFCNum[1]));
			}
		});
		
		lowMFCDownBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lowMFCNum[2]--;
				if(lowMFCNum[2]<0)
					lowMFCNum[2] = 9;
		       lowMFCLbl[2].setText(Integer.toString(lowMFCNum[2]));
			}
		});
		
		lowMFCDownBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lowMFCNum[3]--;
				if(lowMFCNum[3]<0)
					lowMFCNum[3] = 9;
		       lowMFCLbl[3].setText(Integer.toString(lowMFCNum[3]));
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
	
	public void CloseManualFrame(){
		MainFrame.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		device.setFullScreenWindow(MainFrame.mainFrame);
	    super.dispose();
	}

}
