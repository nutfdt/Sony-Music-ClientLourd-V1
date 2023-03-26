package outils;

public class Date {
	private int year;
	private int month;
	private int day;
	
	public Date(int year, int month, int day) {
		this.year=year;
		this.month=month;
		this.day=day;
	}
	
	public boolean verifDate(int year, int month, int day) {
		int nbF=0;
		if(day<1 || day>31 || month<0 || month>12 || year<0) {
			return false;
		}
		else {
			if(year%4==0 && year%100!=0 || year%400==0) {
				nbF=29;
			}
			else {
				nbF=28;
			}
			switch(month) {
				case 4: case 6: case 9: case 11:{
					if(day>30) {
						return false;
					}
					else {
						return true;
					}
				}
				case 1: case 3: case 5: case 7: case 8: case 10: case 12:{
					if(day>31) {
						return false;
					}
					else {
						return true;
					}
				}
					
				case 2:{
					if(day>nbF) {
						return false;
					}
					else {
						return true;
					}
				}
					
			}
		}
		return false;
	}
}



