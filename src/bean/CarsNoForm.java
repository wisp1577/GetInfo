package bean;

import java.io.Serializable;
import java.util.List;

 /**
 *  Class Name: CarsNoForm.java
 *  Function:订单�?
 *  
 *  Modifications:   
 *  
 *  @author gxl  DateTime 2015-1-9 上午10:08:46    
 *  @version 1.0 
 */
public class CarsNoForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<CarsNo> Result;
	
	private String Total;
	
	private String moneys;

	private String ZPmoneys;
	
	private String XJmoneys;
	
	private String ZPRS;
	
	private String XJRS;

	public List<CarsNo> getResult() {
		return Result;
	}

	public void setResult(List<CarsNo> result) {
		Result = result;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public String getMoneys() {
		return moneys;
	}

	public void setMoneys(String moneys) {
		this.moneys = moneys;
	}

	public String getZPmoneys() {
		return ZPmoneys;
	}

	public void setZPmoneys(String zPmoneys) {
		ZPmoneys = zPmoneys;
	}

	public String getXJmoneys() {
		return XJmoneys;
	}

	public void setXJmoneys(String xJmoneys) {
		XJmoneys = xJmoneys;
	}

	public String getZPRS() {
		return ZPRS;
	}

	public void setZPRS(String zPRS) {
		ZPRS = zPRS;
	}

	public String getXJRS() {
		return XJRS;
	}

	public void setXJRS(String xJRS) {
		XJRS = xJRS;
	}
	
}
