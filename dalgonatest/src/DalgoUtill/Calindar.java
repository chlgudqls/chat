package DalgoUtill;

import java.text.SimpleDateFormat;
import java.util.*;


public class Calindar 
{
	private int end;
	private int dayOfWeek;
		
	public int getEnd() 		{return end;}
	public int getDayOfWeek()	{return dayOfWeek;}

	public void setEnd(int end)				{this.end = end;}
	public void setDayOfWeek(int dayOfWeek) {this.dayOfWeek = dayOfWeek;}


	public void findCalindar() 
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
		String strToday = sdf.format(cal.getTime()); 
		String[] split = strToday.split("/");

		System.out.println("Today=" + strToday);
		
		this.setEnd(cal.getActualMaximum(Calendar.DATE));
		this.setDayOfWeek(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(Calendar.YEAR, Integer.parseInt(split[0]));	 					 //�Է¹��� �⵵�� ����
		cal.set(Calendar.MONTH, Integer.parseInt(split[1]));					 //�Է¹��� ���� ����
		cal.set(Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,1);	 	 //�Է¹��� ���� 1�Ϸ� ����
																				 //month�� 0�� 1���̹Ƿ� -1�� ���ش�		
	}

}


