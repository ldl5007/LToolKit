import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;


public class Main_Window {

	static final String SW_VERSION = ProjectHistory.SW_VERSION;
	
	private JFrame frmLtoolkit;
	private JTextField textSource;
	private JTextField textSolText;
	private JTextField textEndMonth;
	private JTextField textEndDate;
	private JTextField textEndYear;
	private JTextField textFMID;
	
	private JComboBox  comboRelease;
	
	private JFileChooser fc;
	private JCheckBox chckbxAutoFillTexts;
	private JTextArea textArea;
	
	//                                        "CNM4D50 RO46337 TSD5058" 
	static final String STRING_PAD          = "                       ";
	
	static final int    MAX_STRING_LENGTH   = 79;
	static final int    MAX_DATE_VALUE      = 31;
	static final int    MAX_MONTH_VALUE     = 12;
	static final int    MAX_YEAR_VALUE      = 2099;

	static Calendar EndPeriod, StartPeriod;
	static String DEFAULT_TARGET_NAME = "SOLTEXT.txt";	
	
	Integer IputCount = 0;
	Integer PTFCount = 0;
	Integer PECount  = 0;
	Integer OutdatedCount = 0;
	Integer InfoCount = 0;
	Integer AMCount   = 0;
	
	static SYSVIEW_Info SysviewInfo = new SYSVIEW_Info();
	private JTextField textAMFile;
	private JTextField textStartMonth;
	private JTextField textStartDate;
	private JTextField textStartYear;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Window window = new Main_Window();
					window.frmLtoolkit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLtoolkit = new JFrame();
		frmLtoolkit.setTitle("LToolKit " + SW_VERSION);
		frmLtoolkit.setBounds(100, 100, 445, 495);
		frmLtoolkit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLtoolkit.getContentPane().setLayout(null);
		
		JLabel lblSource = new JLabel("Source :");
		lblSource.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSource.setBounds(10, 92, 57, 14);
		frmLtoolkit.getContentPane().add(lblSource);
		
		JLabel lblAmfile = new JLabel("AM File :");
		lblAmfile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmfile.setBounds(10, 125, 56, 14);
		frmLtoolkit.getContentPane().add(lblAmfile);
		
		JLabel lblSolText = new JLabel("SolText :");
		lblSolText.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSolText.setBounds(10, 156, 57, 14);
		frmLtoolkit.getContentPane().add(lblSolText);
		
		JLabel lblStartDate = new JLabel("Start Date :");
		lblStartDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStartDate.setBounds(20, 30, 99, 14);
		frmLtoolkit.getContentPane().add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date :");
		lblEndDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndDate.setBounds(20, 61, 99, 14);
		frmLtoolkit.getContentPane().add(lblEndDate);
		
		JLabel lblMonth = new JLabel("MM");
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setBounds(129, 8, 29, 14);
		frmLtoolkit.getContentPane().add(lblMonth);
		
		JLabel lblDate = new JLabel("DD");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(168, 8, 29, 14);
		frmLtoolkit.getContentPane().add(lblDate);
		
		JLabel lblYear = new JLabel("YYYY");
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYear.setBounds(207, 8, 40, 14);
		frmLtoolkit.getContentPane().add(lblYear);
		
		JLabel lblFmid = new JLabel("FMID");
		lblFmid.setHorizontalAlignment(SwingConstants.CENTER);
		lblFmid.setBounds(257, 8, 70, 14);
		frmLtoolkit.getContentPane().add(lblFmid);
		
		JLabel lblRelease = new JLabel("Release");
		lblRelease.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelease.setBounds(327, 8, 89, 14);
		frmLtoolkit.getContentPane().add(lblRelease);
		
		textSource = new JTextField();
		textSource.setBounds(77, 89, 240, 20);
		frmLtoolkit.getContentPane().add(textSource);
		textSource.setColumns(10);
		
		textAMFile = new JTextField();
		textAMFile.setBounds(77, 122, 240, 20);
		frmLtoolkit.getContentPane().add(textAMFile);
		textAMFile.setColumns(10);
		
		textSolText = new JTextField();
		textSolText.setBounds(77, 153, 240, 20);
		frmLtoolkit.getContentPane().add(textSolText);
		textSolText.setColumns(10);
		
		textStartMonth = new JTextField();
		textStartMonth.setHorizontalAlignment(SwingConstants.CENTER);
		textStartMonth.setColumns(10);
		textStartMonth.setBounds(129, 27, 29, 20);
		frmLtoolkit.getContentPane().add(textStartMonth);
		
		textStartDate = new JTextField();
		textStartDate.setHorizontalAlignment(SwingConstants.CENTER);
		textStartDate.setColumns(10);
		textStartDate.setBounds(168, 27, 29, 20);
		frmLtoolkit.getContentPane().add(textStartDate);
		
		textStartYear = new JTextField();
		textStartYear.setHorizontalAlignment(SwingConstants.CENTER);
		textStartYear.setColumns(10);
		textStartYear.setBounds(207, 27, 40, 20);
		frmLtoolkit.getContentPane().add(textStartYear);
		
		textEndMonth = new JTextField();
		textEndMonth.setHorizontalAlignment(SwingConstants.CENTER);
		textEndMonth.setBounds(129, 58, 29, 20);
		frmLtoolkit.getContentPane().add(textEndMonth);
		textEndMonth.setColumns(10);
		
		textEndDate = new JTextField();
		textEndDate.setHorizontalAlignment(SwingConstants.CENTER);
		textEndDate.setBounds(168, 58, 29, 20);
		frmLtoolkit.getContentPane().add(textEndDate);
		textEndDate.setColumns(10);
		
		textEndYear = new JTextField();
		textEndYear.setHorizontalAlignment(SwingConstants.CENTER);
		textEndYear.setBounds(207, 58, 40, 20);
		frmLtoolkit.getContentPane().add(textEndYear);
		textEndYear.setColumns(10);
		
		fc = new JFileChooser();
		
		JButton btnBrowse = new JButton("Browse ...");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int retVal = fc.showOpenDialog(null);
				if (retVal == JFileChooser.APPROVE_OPTION)
				{
					String tempString = fc.getSelectedFile().getAbsolutePath();
					textSource.setText(tempString);
					
					if (chckbxAutoFillTexts.isSelected())
					{
						int index = tempString.lastIndexOf("\\");
						if (index != -1)
						{
							tempString = tempString.substring(0, index);
						
							textSolText.setText(tempString + "\\" + DEFAULT_TARGET_NAME);
							
							// Populate AMFile Name
							Integer y = Integer.parseInt(textEndYear.getText());
							y = y - 2000;
							
							textAMFile.setText(tempString + "\\" + "AM" + y.toString() + textEndMonth.getText()+ ".txt");
						}
					}
				}
				else
				{
					textSource.setText("No File Is Selected");
				}
			}
		});
		
		textFMID = new JTextField();
		textFMID.setEditable(false);
		textFMID.setHorizontalAlignment(SwingConstants.CENTER);
		textFMID.setBounds(257, 27, 70, 20);
		frmLtoolkit.getContentPane().add(textFMID);
		textFMID.setColumns(10);
		
		comboRelease = new JComboBox(SYSVIEW_Info.releaseStr);
		comboRelease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int index = comboRelease.getSelectedIndex();
				textFMID.setText(SYSVIEW_Info.FMID_Str[index]);
			}
		});
		comboRelease.setBounds(337, 27, 79, 20);
		frmLtoolkit.getContentPane().add(comboRelease);
		btnBrowse.setBounds(327, 88, 89, 23);
		frmLtoolkit.getContentPane().add(btnBrowse);	
		
		
		JButton btnSelectAMFile = new JButton("Select ...");
		btnSelectAMFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int retVal = fc.showSaveDialog(null);
				if (retVal == JFileChooser.APPROVE_OPTION)
				{
					textSolText.setText(fc.getSelectedFile().getAbsolutePath());
				}				
			}
		});
		btnSelectAMFile.setBounds(327, 122, 89, 23);
		frmLtoolkit.getContentPane().add(btnSelectAMFile);		
		
		JButton btnSelectSolText = new JButton("Select ...");
		btnSelectSolText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int retVal = fc.showSaveDialog(null);
				if (retVal == JFileChooser.APPROVE_OPTION)
				{
					textSolText.setText(fc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnSelectSolText.setBounds(327, 152, 89, 23);
		frmLtoolkit.getContentPane().add(btnSelectSolText);
		
		JButton btnRun = new JButton("Run ...");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				textArea.setText("");    // Clear Text Area
				SysviewInfo.Clear();
				
				if (CheckPeriod() == true && CheckSourceTarget() == true)
				{
					SysviewInfo.setFMID(textFMID.getText());
					RunConvertTask(textSource.getText(), textSolText.getText(), textAMFile.getText());
				}
			}
		});
		btnRun.setBounds(327, 186, 89, 23);
		frmLtoolkit.getContentPane().add(btnRun);
		
		chckbxAutoFillTexts = new JCheckBox("Auto Fill Texts");
		chckbxAutoFillTexts.setSelected(true);
		chckbxAutoFillTexts.setBounds(20, 186, 155, 23);
		frmLtoolkit.getContentPane().add(chckbxAutoFillTexts);
		JScrollPane scrollPanePlain = new JScrollPane();  
		scrollPanePlain.setBounds(10, 219, 406, 227);  
		scrollPanePlain.setVisible(true);
		frmLtoolkit.getContentPane().add(scrollPanePlain);
		
		textArea = new JTextArea();
		scrollPanePlain.setViewportView(textArea);
		
	}
	
	private void RunConvertTask(String SourceStr, String SolTextStr, String AMFileStr)
	{
		String line1, line2, line3, line4;
		String temStr;
		
		textArea.append("Parsing : " + SourceStr + "\n");
		textArea.append("AM File : " + AMFileStr + "\n");
		textArea.append("SOLTEXT : " + SolTextStr + "\n");
		textArea.append("Data parsing started.\n");
		
		// Reset all count
		IputCount = 0;
		PTFCount = 0;
		PECount  = 0;
		OutdatedCount = 0;
		InfoCount = 0;
		AMCount = 0;
		
		try
		{
			FileInputStream fInputStream = new FileInputStream(SourceStr);
			DataInputStream dInputStream = new DataInputStream(fInputStream);
			BufferedReader  BuffReader   = new BufferedReader(new InputStreamReader(dInputStream));
			
			FileOutputStream fOutputStream = new FileOutputStream(SolTextStr);
			DataOutputStream dOutputStream = new DataOutputStream(fOutputStream);
			BufferedWriter   BuffWriter    = new BufferedWriter(new OutputStreamWriter(dOutputStream));
			
			FileOutputStream fAMFileStream       = new FileOutputStream(AMFileStr);
			DataOutputStream dAMFileOutputStream = new DataOutputStream(fAMFileStream);
			BufferedWriter   AMFileBuffWriter    = new BufferedWriter(new OutputStreamWriter(dAMFileOutputStream));
			 
			while ((line1 = BuffReader.readLine()) != null)
			{
				// Parse Line 1
				if (ParseLine1(line1) == true)
				{
					// Read and Parse Line 2
					line2 = BuffReader.readLine();   // Read line2
					if (ParseLine2(line2) == true)
					{	
						// Read and Parse Line 3
						line3 = BuffReader.readLine();   // Read line3
						if (ParseLine3(line3) == true)
						{
							// Read and Parse Line 4
							line4 = BuffReader.readLine(); // Read line4
							if (ParseLine4(line4) == true)
							{
								PTFCount ++;                                           // Update Count
							}	
							
							temStr  = SysviewInfo.GetFMID() + " ";
							temStr += SysviewInfo.GetPTF() + " "; 
							temStr += SysviewInfo.GetAPAR() + " ";
							temStr += SysviewInfo.GetLabel();   
								
							// Check if line is greater than 80 chars 
							if (temStr.length() > MAX_STRING_LENGTH)
							{
								temStr = temStr.substring(0, MAX_STRING_LENGTH);    // If it is then truncate
							}
								
							BuffWriter.write(temStr + "\r\n");  // Write String to file
							
							// Write all 4 String to AMFiles
							AMFileBuffWriter.write(line1 + "\r\n");
							AMFileBuffWriter.write(line2 + "\r\n");
							AMFileBuffWriter.write(line3 + "\r\n");
							AMFileBuffWriter.write(line4 + "\r\n\r\n");
						}
					}
				}		
			}	
			
			// Display Summary information
			PrintSummary();
			
			AMFileBuffWriter.write(textArea.getText());
			
			BuffReader.close();
			BuffWriter.close();
			AMFileBuffWriter.close();
			
			dInputStream.close();
			dOutputStream.close();
			dAMFileOutputStream.close();
			
	
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			// textArea.append(e.getMessage() + "\n");
		}
		
		textArea.append("Data parsing completed.\n");
	}
	
	public boolean CheckPeriod()
	{
		boolean returnStatus = false;
		int m, d, y;
		
		try
		{
			// Check if start period fields is empty
			if (textStartMonth.getText().isEmpty() ||
				textStartDate.getText().isEmpty()  ||
				textStartYear.getText().isEmpty())
			{
				throw new Throwable("Invalid Start Period Date");
			}
			
			// Check if end period fields is empty
			if (textEndMonth.getText().isEmpty() ||
				textEndDate.getText().isEmpty()  ||
				textEndYear.getText().isEmpty())
			{
				throw new Throwable("Invalid End Period Date");
			}
			
			// Check if start period fields contain non number value
			if (CheckIfNumber(textStartMonth.getText()) == false ||
				CheckIfNumber(textStartDate.getText())  == false ||
				CheckIfNumber(textStartYear.getText())  == false)
			{
				throw new Throwable("Invalid Start Period Format");
			}
			
			// Check if fields is contain a non number char
			if (CheckIfNumber(textEndMonth.getText()) == false ||
				CheckIfNumber(textEndDate.getText())  == false ||
				CheckIfNumber(textEndYear.getText())  == false)
			{
				throw new Throwable("Invalid End Period Format");
			}
			
			// Receiving Start Period Date
			m = Integer.parseInt(textStartMonth.getText());
			d = Integer.parseInt(textStartDate.getText());
			y = Integer.parseInt(textStartYear.getText());
						
			// Check invalid date
			if (d < 1 || d > MAX_DATE_VALUE)
			{
				throw new Throwable ("Invalid Date Value");
			}
						
			// Check invalid month
			if (m < 1 || m > MAX_MONTH_VALUE)
			{
				throw new Throwable ("Invalid Month Value");
			}
						
			// Check invalid year
			if (y < 1 || y > MAX_YEAR_VALUE)
			{
				throw new Throwable ("Invalid Year Value");
			}
						
			// Get here mean passed all test
			// Set Start Period Calendar Date
			StartPeriod = new Calendar(d, m, y);
			
			// Receive End Period Date
			m = Integer.parseInt(textEndMonth.getText());
			d = Integer.parseInt(textEndDate.getText());
			y = Integer.parseInt(textEndYear.getText());
			
			// Check invalid date
			if (d < 1 || d > MAX_DATE_VALUE)
			{
				throw new Throwable ("Invalid Date Value");
			}
			
			// Check invalid month
			if (m < 1 || m > MAX_MONTH_VALUE)
			{
				throw new Throwable ("Invalid Month Value");
			}
			
			// Check invalid year
			if (y < 1 || y > MAX_YEAR_VALUE)
			{
				throw new Throwable ("Invalid Year Value");
			}
			
			// Get here mean passed all tests
			// Set End Period Calendar Date
			EndPeriod = new Calendar(d, m, y);
			
			returnStatus = true;
		}
		catch (Throwable message)
		{
			JOptionPane.showMessageDialog(null, message.getMessage());
		}
		
		return returnStatus;
	}

	public boolean CheckSourceTarget()
	{
		boolean returnStatus = false;
		
		try
		{	
			if (textFMID.getText().isEmpty())
			{
				throw new Throwable("FMID field is empty, please select a release");
			}
			
			if (textSource.getText().isEmpty())
			{
				throw new Throwable("Source Field is Empty");
			}
			if (textAMFile.getText().isEmpty())
			{
				throw new Throwable("AM File Field is Empty");
			}
			if (textSolText.getText().isEmpty())
			{
				throw new Throwable("SOLTEXT Field is Empty");
			}
			if (textSource.getText().compareTo(textAMFile.getText()) == 0)
			{
				throw new Throwable("Source and AM File is the same");
			}
			if (textSource.getText().compareTo(textSolText.getText()) == 0)
			{
				throw new Throwable("Source and SOLTEXT is the same");
			}
			
			returnStatus = true;
		}
		catch (Throwable message)
		{
		   JOptionPane.showMessageDialog(null, message.getMessage());
		}
		
		return returnStatus;
	}
	
	public boolean CheckIfNumber(String in)
	{
		try
		{
			Integer.parseInt(in);
		}
		catch (NumberFormatException ex)
		{
			return false;
		}
		
		return true;
	}

	public boolean ParseLine1 (String inputStr)
	{
		boolean returnStatus = false;
		String AparStr, LabelStr, tempString;
		
		// Removing spaces at the begin and end of the line
		inputStr = inputStr.trim();
		// Check and make sure the string length > 8 chars
		if (inputStr.length() > 8)
		{
			AparStr = inputStr.substring(0, 7);
			LabelStr  = inputStr.substring(8, inputStr.length());
			
			tempString = AparStr.substring(4,7);        //tempString = number section in APAR 
	
			if ((AparStr.indexOf("TS") == 0) && CheckIfNumber(tempString) == true)
			{								
				SysviewInfo.SetAPAR(AparStr);
				SysviewInfo.SetLabel(LabelStr);
				
				IputCount ++;
				
				returnStatus = true;
				
			}
			if (inputStr.indexOf("INFORMA") == 0)
			{
				IputCount ++;
				InfoCount ++;
			}
			else if (inputStr.indexOf("AGGREGATE MAINTENANCE") == 0)
			{
				IputCount ++;
				AMCount ++;
			}
				
		}
		
		return returnStatus;
	}
	
	public boolean ParseLine2 (String inputStr)
	{
		boolean returnStatus = false;
		String PtfStr, tempString;
		
		if (inputStr != null && inputStr.length() > 4)
		{
			// Removing spaces at the begin and end of the line
			inputStr = inputStr.trim();
			
			tempString = inputStr.substring(0, 5);
			PtfStr     = inputStr.substring(5, inputStr.length());
			
			if (tempString.contentEquals("Fix #"))
			{		
				SysviewInfo.SetPTF(PtfStr);
				
				returnStatus = true;
			}
		}
		return returnStatus;
	}
	
	public boolean ParseLine3 (String inputStr)
	{
		boolean returnStatus = false;
		int     startIndex, endIndex = 0;
		String  tempStr;
		String  monthStr, dateStr, yearStr;
		int     startDate, endDate, currentDate;
		int     testVal;
		
		if (inputStr != null && !inputStr.isEmpty())
		{
			// Removing spaces at the begin and end of the line
			inputStr = inputStr.trim();
			
			startIndex = inputStr.indexOf("OS ");
			endIndex   = inputStr.indexOf("Download");
			if (startIndex >= 0  && endIndex > 0)
			{
				tempStr = inputStr.substring(startIndex + 2, endIndex);
				tempStr = tempStr.trim();
				
				monthStr = tempStr.substring(0, 2);
				dateStr  = tempStr.substring(3, 5);
				yearStr  = tempStr.substring(6, tempStr.length());
				
				testVal = Integer.parseInt(yearStr);
				if (testVal < 2000)
				{
					testVal = testVal + 2000;
				}
				
				SysviewInfo.SetReleaseDate(Integer.parseInt(dateStr), 
										   Integer.parseInt(monthStr), 
						                   testVal);			
				
				// Check Release date vs Start and End Period
				startDate  = StartPeriod.GetDate();
				startDate += StartPeriod.GetMonth() * MAX_DATE_VALUE;
				startDate += StartPeriod.GetYear() * MAX_MONTH_VALUE * MAX_DATE_VALUE; 
				
				endDate  = EndPeriod.GetDate();
				endDate += EndPeriod.GetMonth() * MAX_DATE_VALUE;
				endDate += EndPeriod.GetYear() * MAX_MONTH_VALUE * MAX_DATE_VALUE;
				
				currentDate  = SysviewInfo.GetReleaseDate().GetDate();
				currentDate += SysviewInfo.GetReleaseDate().GetMonth() * MAX_DATE_VALUE;
				currentDate += SysviewInfo.GetReleaseDate().GetYear() * MAX_MONTH_VALUE * MAX_DATE_VALUE;
				
				if (currentDate >= startDate && currentDate <= endDate)
				{
					returnStatus = true;
				}
				else
				{
					OutdatedCount ++;
				}
			}
		}

		return returnStatus;
	}
	
	public boolean ParseLine4(String inputStr)
	{
		boolean returnStatus = false;
		
		if (inputStr != null && inputStr.length() > 0)
		{
			// make sure string dont contain the MARKEDINERROR text
			if (inputStr.indexOf("M A R K E D I N E R R O R") == -1)
			{
				returnStatus = true;
			}
			else
			{
				PECount ++;
			}
		}
		
		return returnStatus;
	}
	
	public void PrintSummary()
	{
		int msgCount       = 7;
		String[] SumStrArr = new String[msgCount];
		
		
		SumStrArr[0] = "***************** SUMMARY *****************\n";
		SumStrArr[1] = "Total Fixes: " + IputCount.toString()     + "\n";
		SumStrArr[2] = "PTF: "         + PTFCount.toString()      + "\n";
		SumStrArr[3] = "PE: "          + PECount.toString()       + "\n";
		SumStrArr[4] = "Info: "        + InfoCount.toString()     + "\n";
		SumStrArr[5] = "Outdated: "    + OutdatedCount.toString() + "\n";
		SumStrArr[6] = "AGGREGATE MAINTENANCE: " + AMCount.toString() + "\n";
		
		for (int i = 0; i < msgCount; i ++)
		{
			textArea.append(SumStrArr[i]);
		}
	}
	
	public void AppendAreaText(String inputStr)
	{
		textArea.append(inputStr + "\n");
	}
}
