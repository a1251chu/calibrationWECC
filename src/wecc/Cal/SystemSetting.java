package wecc.Cal;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class SystemSetting {
	String password = "12345";
	Font font = new Font("Rockwell Extra Bold", Font.BOLD, 31);
	Font mainBtnFont = new Font("Dialog", Font.BOLD, 16);
	Color titleColor = Color.WHITE;
	Color sysRed = new Color(255,84,85);
	Color sysBlue = new Color(103,195,239);
	Color sysYellow = new Color(250,187,61);
	Color sysPurple = new Color(145,57,219);
	Color sysDeepBlue = new Color(72,117,181);
	Color sysLightGrayBackground = new Color(238,238,238);
	Color sysGenBackground = new Color(0,150,136);
	Color sysMinus = new Color(33,150,243);
	Color sysPlus = new Color(253,126,59);
	Color syslogin = new Color(154,210,103);
	
	Color sysWhite = Color.WHITE;
	Color sysGreen = new Color(121,196,71);
	
	ImageIcon upIcon = new ImageIcon("assets/plus.png");
	ImageIcon minusIcon = new ImageIcon("assets/minus.png");
	ImageIcon cancelIcon = new ImageIcon("assets/cancel.png");
	ImageIcon addIcon = new ImageIcon("assets/plus.png");
	ImageIcon genIcon = new ImageIcon("assets/gen.png");
	ImageIcon cylIcon = new ImageIcon("assets/cyl.png");
	ImageIcon settingIcon = new ImageIcon("assets/setting.png");
	ImageIcon loginIcon = new ImageIcon("assets/login.png");
	ImageIcon manualIcon = new ImageIcon("assets/manual.png");
	ImageIcon autoIcon = new ImageIcon("assets/auto.png");
	ImageIcon standbyIcon = new ImageIcon("assets/standby.png");
	
	JButton sysCancelBtn = new JButton("Cancel");
	JButton sysGenBtn = new JButton("Generate");
	JButton sysAddBtn = new JButton("Add");
	JButton sysGasBtn = new JButton("GAS");
	JButton	sysSetBtn = new JButton("Setting");
	JButton	sysLoginBtn = new JButton("Login");
	JButton sysManualBtn = new JButton("Manual");
	JButton sysAutoBtn = new JButton("Auto");
	JButton sysStandbyBtn = new JButton("StandBy");
	public SystemSetting(){
		sysCancelBtn.setSize(100, 100);
		sysCancelBtn.setIcon(cancelIcon); 
		sysCancelBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
	    sysCancelBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		sysCancelBtn.setBackground(sysRed);
		sysCancelBtn.setForeground(sysWhite);
		sysCancelBtn.setFont(mainBtnFont);
		sysCancelBtn.setBorder(null);
		
		sysGenBtn.setSize(100, 100);
		sysGenBtn.setIcon(genIcon); 
		sysGenBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
	    sysGenBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		sysGenBtn.setBackground(sysGreen);
		sysGenBtn.setForeground(sysWhite);
		sysGenBtn.setFont(mainBtnFont);
		sysGenBtn.setBorder(null);
		
		sysAddBtn.setSize(100, 100);
		sysAddBtn.setIcon(addIcon); 
		sysAddBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
	    sysAddBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		sysAddBtn.setBackground(sysPurple);
		sysAddBtn.setForeground(sysWhite);
		sysAddBtn.setFont(mainBtnFont);
		sysAddBtn.setBorder(null);
		
		sysGasBtn.setSize(100, 100);
		sysGasBtn.setIcon(cylIcon); 
		sysGasBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		sysGasBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		sysGasBtn.setBackground(sysYellow);
		sysGasBtn.setForeground(sysWhite);
		sysGasBtn.setFont(mainBtnFont);
		sysGasBtn.setBorder(null);
		
		sysSetBtn.setSize(100, 100);
		sysSetBtn.setIcon(settingIcon); 
		sysSetBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		sysSetBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		sysSetBtn.setBackground(sysPurple);
		sysSetBtn.setForeground(sysWhite);
		sysSetBtn.setFont(mainBtnFont);
		sysSetBtn.setBorder(null);
		
		sysLoginBtn.setSize(100, 100);
		sysLoginBtn.setIcon(loginIcon); 
		sysLoginBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		sysLoginBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		sysLoginBtn.setBackground(syslogin);
		sysLoginBtn.setForeground(sysWhite);
		sysLoginBtn.setFont(mainBtnFont);
		sysLoginBtn.setBorder(null);
		
		sysManualBtn.setSize(100, 100);
		sysManualBtn.setIcon(manualIcon); 
		sysManualBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		sysManualBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		sysManualBtn.setBackground(sysGreen);
		sysManualBtn.setForeground(sysWhite);
		sysManualBtn.setFont(mainBtnFont);
		sysManualBtn.setBorder(null);
		
		sysAutoBtn.setSize(100, 100);
		sysAutoBtn.setIcon(autoIcon); 
		sysAutoBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		sysAutoBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		sysAutoBtn.setBackground(sysBlue);
		sysAutoBtn.setForeground(sysWhite);
		sysAutoBtn.setFont(mainBtnFont);
		sysAutoBtn.setBorder(null);
		
		sysStandbyBtn.setSize(100, 100);
		sysStandbyBtn.setIcon(standbyIcon); 
		sysStandbyBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		sysStandbyBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		sysStandbyBtn.setBackground(sysRed);
		sysStandbyBtn.setForeground(sysWhite);
		sysStandbyBtn.setFont(mainBtnFont);
		sysStandbyBtn.setBorder(null);
		
	}
	
}
