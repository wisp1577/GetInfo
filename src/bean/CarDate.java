package bean;

import java.io.Serializable;

 /**
 *  Class Name: CarDate.java
 *  Function:订单�?
 *  
 *  Modifications:   
 *  
 *  @author gxl  DateTime 2015-1-9 上午10:08:46    
 *  @version 1.0 
 */
public class CarDate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Yyrq;
	
	private String DisplayYyrq;
	
	private String DisplayWeek;

	public String getYyrq() {
		return Yyrq;
	}

	public void setYyrq(String yyrq) {
		Yyrq = yyrq;
	}

	public String getDisplayYyrq() {
		return DisplayYyrq;
	}

	public void setDisplayYyrq(String displayYyrq) {
		DisplayYyrq = displayYyrq;
	}

	public String getDisplayWeek() {
		return DisplayWeek;
	}

	public void setDisplayWeek(String displayWeek) {
		DisplayWeek = displayWeek;
	}

	
}
