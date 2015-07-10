
public class SYSVIEW_Info 
{	
	public static String[] releaseStr = {"",
		    "12.7", 
            "13.0", 
            "13.5",
            "13.7"};

	public static String[] FMID_Str   = {"",
		    "CNM4C70", 
            "CNM4D00", 
            "CNM4D50",
            "CNM4D70"};

	private String FMID;
	private String APAR;
	private String PTF;
	private String Label;
	private String FixDescription;
	private Calendar ReleaseDate;	
	
	SYSVIEW_Info()
	{
		FMID = APAR = PTF = Label = FixDescription = "";
		ReleaseDate = new Calendar(0,0,0);
	}
	
	public void Clear()
	{
		FMID = APAR = PTF = Label = FixDescription = "";
		ReleaseDate.Clear();
	}
	
	public void setFMID(String input)
	{
		FMID = input;
	}
	
	public String GetFMID()
	{
		return FMID;
	}
	
	public void SetAPAR(String input)
	{
		APAR = input;
	}
	
	public String GetAPAR()
	{
		return APAR;
	}
	
	public void SetPTF(String input)
	{
		PTF = input;
	}
	
	public String GetPTF()
	{
		return PTF;
	}
	
	public void SetLabel(String input)
	{
		Label = input;
	}
	
	public String GetLabel()
	{
		return Label;
	}
	
	public void SetFixDescription(String input)
	{
		FixDescription = input;
	}
	
	public String GetFixDescription()
	{
		return FixDescription;
	}
	
	public void SetReleaseDate(int d, int m, int y)
	{
		ReleaseDate.SetValue(d, m, y);
	}
	
	public Calendar GetReleaseDate()
	{
		return ReleaseDate;
	}

}
