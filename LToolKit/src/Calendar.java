
public class Calendar
{
	private Integer date;
	private Integer month;
	private Integer year;
	
	Calendar(int d, int m, int y)
	{
		date = d;
		month = m;
		year = y;
	}
	
	void Clear()
	{
		date = month = year = 0;
	}
	
	void SetValue(int d, int m, int y)
	{
		date = d;
		month = m;
		year = y;
	}
	
	public Integer GetDate()
	{
		return date;
	}
	
	public Integer GetMonth()
	{
		return month;
	}
	
	public Integer GetYear()
	{
		return year;
	}
	
	public String GetFormatedDate()
	{	
		return  month.toString() + "/" + date.toString() + "/" + year.toString();
	}
}