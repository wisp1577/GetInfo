package bean;

import java.io.Serializable;

 /**
 *  Class Name: XnsdList.java
 *  Function:订单�?
 *  
 *  Modifications:   
 *  
 *  @author gxl  DateTime 2015-1-9 上午10:08:46    
 *  @version 1.0 
 */
public class XnsdList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Xnsd;
	
	private String XnsdName;

	public String getXnsd() {
		return Xnsd;
	}

	public void setXnsd(String xnsd) {
		Xnsd = xnsd;
	}

	public String getXnsdName() {
		return XnsdName;
	}

	public void setXnsdName(String xnsdName) {
		XnsdName = xnsdName;
	}

	
}
