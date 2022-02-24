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

		cal.set(Calendar.YEAR, Integer.parseInt(split[0]));	 					 //입력받은 년도로 세팅
		cal.set(Calendar.MONTH, Integer.parseInt(split[1]));					 //입력받은 월로 세팅
		cal.set(Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,1);	 	 //입력받은 월의 1일로 세팅
																				 //month는 0이 1월이므로 -1을 해준다		
	}

}


