import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import util.SentClient;
import util.Util;
import bean.CarsNoForm;
import bean.LeftCarCountForm;
import bean.OrderRecordDetailList;
import bean.OrderRecordForm;
import bean.Result;
import bean.StudentInfoForm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class YueChe {

	private SentClient client = null;
	
	private DefaultHttpClient httpClient = null;

	private static Logger log = Logger.getLogger(YueChe.class);
	
//	private static String userId = null;
	
	private static String kemu = null;
	
	private static String folder_count = null;
	private static String del_page = null;
	private static String fpath = null;
	private static String fpath_carNo = null;
	private static String jxname = null;
	
//	private static String check_date = null;
	private static String proxyId = "0";
	private static String dateNum = "";
	
	public YueChe () {
		ThreadSafeClientConnManager tcm = new ThreadSafeClientConnManager();
		tcm.setMaxTotal(10);
		this.httpClient = new DefaultHttpClient(tcm);
		this.httpClient
		.getParams()
		.setParameter(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; TEN)");
		// 设置代理用于Fiddler抓包监控
		if ("1".equals(proxyId)) {
			//设置代理开始。如果代理服务器需要验证的话，可以修改用户名和密码
			//192.168.1.107为代理地址 808为代理端口 UsernamePasswordCredentials后的两个参数为代理的用户名密码
			this.httpClient.getCredentialsProvider().setCredentials(new AuthScope("127.0.0.1",8888), new UsernamePasswordCredentials("", ""));
			HttpHost proxy = new HttpHost("127.0.0.1", 8888);
			this.httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
		this.client = new SentClient(this.httpClient);
	}
	
	public String getStudentInfo(String cardNo, String jxid) {
		String res = this.client.getStudentInfo(cardNo, jxid);
		return res;
	}
	
	public Result<LeftCarCountForm> getLeftCarCount(String cardNo, String jxid) {
		Gson g = new Gson();
		String res = null;
		
		if (cardNo != null && !"".equals(cardNo)) {
			res = this.client.getLeftCarCount(cardNo, jxid);
			if (res != null && !"".equals(res)) {
				Result<LeftCarCountForm> detail = g.fromJson(res,
						new TypeToken<Result<LeftCarCountForm>>() {
						}.getType());
				return detail;
			} else {
				Result<LeftCarCountForm> temp = new Result<LeftCarCountForm>();
				temp.setData(null);
				temp.setCode(101);
				temp.setMessage("interface return null !");
				return temp;
			}
		} else {
			Result<LeftCarCountForm> temp = new Result<LeftCarCountForm>();
			temp.setData(null);
			temp.setCode(100);
			temp.setMessage("get cardno fail !");
			return temp;
		}
	}
	
	public Result<CarsNoForm> getCarsNo(String cardNo, String yyrq, String xnsd, String jxid) {
		Gson g = new Gson();
		String res = null;
		if (cardNo != null && !"".equals(cardNo)) {
			res = this.client.getCarsNo(cardNo, yyrq, xnsd, jxid);
			if (res != null && !"".equals(res)) {
				Result<CarsNoForm> detail = g.fromJson(res,
						new TypeToken<Result<CarsNoForm>>() {
						}.getType());
				return detail;
			} else {
				Result<CarsNoForm> temp = new Result<CarsNoForm>();
				temp.setData(null);
				temp.setCode(101);
				temp.setMessage("interface return null !");
				return temp;
			}
		} else {
			Result<CarsNoForm> temp = new Result<CarsNoForm>();
			temp.setData(null);
			temp.setCode(100);
			temp.setMessage("get cardno fail !");
			return temp;
		}
	}
	
	public Result<String> submitOrder(String cardNo, String cnbh, String yyrq, String xnsd, String jxid) {
		Gson g = new Gson();
		String res = null;
		if (cardNo != null && !"".equals(cardNo)) {
			res = this.client.submitOrder(cardNo, cnbh, yyrq, xnsd, jxid);
			if (res != null && !"".equals(res)) {
				Result<String> detail = g.fromJson(res,
						new TypeToken<Result<String>>() {
						}.getType());
				return detail;
			} else {
				Result<String> temp = new Result<String>();
				temp.setData(null);
				temp.setCode(101);
				temp.setMessage("interface return null !");
				return temp;
			}
		} else {
			Result<String> temp = new Result<String>();
			temp.setData(null);
			temp.setCode(100);
			temp.setMessage("get cardno fail !");
			return temp;
		}
	}
	
	public Result<OrderRecordForm> getOrderRecord(String cardNo, String jxid) {
		Gson g = new Gson();
		String res = null;
		if (cardNo != null && !"".equals(cardNo)) {
			res = this.client.getOrderRecord(cardNo, jxid);
			if (res != null && !"".equals(res)) {
				Result<OrderRecordForm> detail = g.fromJson(res,
						new TypeToken<Result<OrderRecordForm>>() {
						}.getType());
				return detail;
			} else {
				Result<OrderRecordForm> temp = new Result<OrderRecordForm>();
				temp.setData(null);
				temp.setCode(101);
				temp.setMessage("接口返回为空");
				return temp;
			}
		} else {
			Result<OrderRecordForm> temp = new Result<OrderRecordForm>();
			temp.setData(null);
			temp.setCode(100);
			temp.setMessage("获取卡号失败");
			return temp;
		}
	}
	
	private static void readXml() {
		SAXReader reader = new SAXReader();
        try {
            // 读取XML文件
            Document doc = reader.read("beanConfig.xml");
            Element root = doc.getRootElement();
            @SuppressWarnings("unchecked")
			List<Element> param = root.elements();
            for (Element element : param) {
            	String nameStr = element.attributeValue("name");
//                if(nameStr.equals("userId")){
//                	userId = element.getText();
//                } else 
                if (nameStr.equals("kemu")) {
                	kemu = element.getText();
                } else if (nameStr.equals("folder_count")) {
                	folder_count = element.getText();
                } else if (nameStr.equals("del_page")) {
                	del_page = element.getText();
                } else if (nameStr.equals("fpath")) {
                	fpath = element.getText();
                } else if (nameStr.equals("fpath_carNo")) {
                	fpath_carNo = element.getText();
                } else if (nameStr.equals("jxname")) {
                	jxname = element.getText();
                } else if (nameStr.equals("check_date")) {
//                	check_date = element.getText();
                } else if (nameStr.equals("dateNum")) {
                	dateNum = element.getText();
                } else if (nameStr.equals("proxyId")) {
                	proxyId = element.getText();
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
	}
	
//	public static void SetLogFileName(String filename) {
//		DailyRollingFileAppender appender = (DailyRollingFileAppender)Logger.getRootLogger().getAppender("A2");
//		appender.setFile( "./"+filename+".log");//动态地修改这个文件名 
//		appender.activateOptions();
//	}
	
	public static void writeLog(String kmId, String str)
    {
		String today = Util.getCurDate();
        try
        {
	        String path = kemu + kmId + today.substring(4, 10) + ".log";
	        File file = new File(path);
	        if(!file.exists())
	            file.createNewFile();
//	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        FileOutputStream out=new FileOutputStream(file, false); //如果追加方式用true
	        StringBuffer sb=new StringBuffer();
	        sb.append(str + "\n");
//	        sb.append(sdf.format(new Date()) + "\n" + str + "\n");
	        out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
	        out.close();
        }
        catch(IOException ex)
        {
        	log.info(ex.getStackTrace());
        }
    }
	
	public static void writeLogin(String kmId, String str)
    {
        try
        {
	        String path = fpath + "\\aresult\\" +kemu + kmId + ".log";
	        File file = new File(path);
	        if(!file.exists())
	            file.createNewFile();
	        FileOutputStream out=new FileOutputStream(file, false); //如果追加方式用true
	        StringBuffer sb=new StringBuffer();
	        sb.append(str + "\n");
	        out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
	        out.close();
        }
        catch(IOException ex)
        {
        	log.info(ex.getStackTrace());
        }
    }
	
//	public static void main(String[] args) throws Exception {
//		
//        //创建文件对象   
//        java.io.File file=new java.io.File("F:/workSapce/javaXML/src/Dom/test.xml");   
//        //创建一个读取XML文件的对象   
//        SAXReader reader=new SAXReader();   
//        //创建一个文档对象   
//        Document document=reader.read(file);   
//        //获取文件的根节点   
//        Element element=document.getRootElement();
//        for(Iterator i=element.elementIterator("disk");i.hasNext();){
//        	//获取节点元素   
//            element=(Element)i.next();
//        	String name=element.attributeValue("name");
//            String capacity=element.elementText("capacity");//取disk子元素capacity的内容 
//            String directories=element.elementText("directories"); 
//            String files=element.elementText("files"); 
//            System.out.println("磁盘信息:"); 
//            System.out.println("分区盘符:"+name); 
//            System.out.println("分区容量:"+capacity); 
//            System.out.println("目录数:"+directories); 
//            System.out.println("文件数:"+files); 
//            System.out.println("-----------------------------------"); 
//        }   
//    }
	
	private static String getTimeAndToken(String num) {
		File file = new File(fpath + jxname +"-" + num + "\\YC.exe.config");
        BufferedReader reader = null;
        String xxzh_str = "";
        try {
            // System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            //int line = 1;
            // 一次读入一行，直到读入null为文件结束
            StringBuilder xxzh_return = new StringBuilder();
            while ((tempString = reader.readLine()) != null) {
            	if (tempString.indexOf("xxzh1") >= 0) {
            		xxzh_str = tempString.replace("<add key=\"xxzh1\" value=\"", "").replace("\"/>", "").trim();
//                    xxzh_str = tempString.substring(tempString.indexOf("xxzh1")+14, 34);
                    xxzh_return.append(xxzh_str).append("#");
                    //ConsoleHelper.WL(ConsoleColor.Yellow, xxzh_str);
                    //break;
                } else if (tempString.indexOf("pwd23change") >= 0) {
                	xxzh_str = tempString.replace("<add key=\"pwd23change\" value=\"", "").replace("\"/>", "").trim();
//                	System.out.println("xxzh_str      " + xxzh_str);
//                    xxzh_str = tempString.substring(tempString.indexOf("pwd23change")+14, 34);
                    xxzh_return.append(xxzh_str).append("#");
                    //ConsoleHelper.WL(ConsoleColor.Yellow, xxzh_str);
                    //break;
                } else if (tempString.indexOf("iphoneno") >= 0) {
                	xxzh_str = tempString.replace("<add key=\"iphoneno\" value=\"", "").replace("\"/>", "").trim();
//                	System.out.println("xxzh_str      " + xxzh_str);
//                    xxzh_str = tempString.substring(tempString.indexOf("pwd23change")+14, 34);
                    xxzh_return.append(xxzh_str).append("#");
                    //ConsoleHelper.WL(ConsoleColor.Yellow, xxzh_str);
                    //break;
                } else if (tempString.indexOf("orderid") >= 0) {
                	xxzh_str = tempString.replace("<add key=\"orderid\" value=\"", "").replace("\"/>", "").trim();
//                	System.out.println("xxzh_str      " + xxzh_str);
//                    xxzh_str = tempString.substring(tempString.indexOf("pwd23change")+14, 34);
                    xxzh_return.append(xxzh_str).append("#@");
                    //ConsoleHelper.WL(ConsoleColor.Yellow, xxzh_str);
                    //break;
                }
            	//tempString = reader.readLine();
                // 显示行号
                // System.out.println("line " + line + ": " + tempString);
                //line++;
            }
            reader.close();
            return xxzh_return.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
	
	public static String[] getFileName(String path) {
        File file = new File(path);
        String [] fileName = file.list();
        return fileName;
    }
	
	private static ArrayList<String> getCardNoInfo() {
		String filename = "";
		String[] fileName = getFileName(fpath_carNo);
		for (int i=0;i<fileName.length;i++) {
			if (fileName[i].contains("a-lq-zd-web-carNo_res")) {
				filename = fileName[i];
				break;
			}
		}
		File file = new File(fpath_carNo + filename);
        BufferedReader reader = null;
        ArrayList<String> xxzh_str = new ArrayList<String>();
        try {
            // System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8")); 
            String tempString = null;
            //int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	tempString = tempString.replaceAll("\r|\n|\t", "");
            	if (tempString!=null && !"".equals(tempString)) {
            		xxzh_str.add(tempString);
            	}
            	//line++;
            }
            reader.close();
            return xxzh_str;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
	
	private static String getBookingDate()
    {
		Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(dateNum));
	    Date date = calendar.getTime();
	    //System.out.println(sdf.format(date));
	    return sdf.format(date);
    }
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		readXml();
		ArrayList<String> cardNoList = getCardNoInfo();
//        String today = Util.getCurDate();
//        String tomorrow = Util.getNextDateTime();
//		System.out.println(reserve_id_array);
		// System.out.println(kemu + "        " +date3+ "        " +date4+ "        " +date5+ "        " +date6+ "        " +date7+ "        " +date8+ "        " +delTimeId+ "        " +delTimeId2+ "        " + userId);
        // System.out.println(del_page.length());
        String[] del_page_str = del_page.split("#");
        // System.out.println(del_page_str.length);
//		String[] id_array = reserve_id_array.split("#");
//		Scanner sc = new Scanner(System.in);
//        System.out.print("input url_id （lq:1 xy:2 jdf:3 hj:4 xf:5 yd:6 fs:7）:");
//        String jxFlag = sc.nextLine();
//        if (jxFlag == null || "".equals(jxFlag)) {
        String jxFlag = "1";
//        }
//        System.out.print("get cars result. select kemu (二:2 三:3 default:2): ");
//        int kemuId = 2;
//        kemuId = sc.nextInt();
		YueChe yc = new YueChe();
        int count = 0;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb_check = new StringBuffer();
        StringBuffer sb_check_half_match = new StringBuffer();
//        StringBuffer sb_login = new StringBuffer();
        count = Integer.parseInt(folder_count);
        String xxzhArray = null;
        String xxzhDisplay = null;
        int num_no = 1;
        for (int q = 1; q < count + 1; q++) {
        	boolean zhaodao = false;
            for (int m = 0; m < del_page_str.length; m++ )
            {
                int a = Integer.parseInt(del_page_str[m]);
                // ConsoleHelper.WL(ConsoleColor.Yellow, a);
                if (q == a)
                {
                    zhaodao = true;
                    break;
                }
            }
            if (zhaodao)
            {
                continue;
            }
            xxzhArray = getTimeAndToken(String.valueOf(q));
//            System.out.println("xxzhArray     " + xxzhArray);
//            sb_login.append(xxzhArray);
            xxzhDisplay = xxzhArray.split("#")[0];
            System.out.println("******************  " + q + "  start ******************");
            sb.append("******************  " + q + "  start ******************").append("\r\n");
            System.out.println(xxzhDisplay.toString());
            sb.append(xxzhDisplay.toString()).append("\r\n");
            sb.append("-----------------------------------------------").append("\r\n");
            String[] strArray = xxzhArray.split("@")[0].split("#");
            String res = null;
            Gson g = new Gson();
            if (!"********".equals(strArray[0]))
            {
            	boolean flag1 = true;
            	while (flag1) {
	            	res = yc.getStudentInfo(strArray[0], jxFlag);
	    			Result<StudentInfoForm> stu = g.fromJson(res,
	    					new TypeToken<Result<StudentInfoForm>>() {
	    					}.getType());
	    			if (stu != null && stu.getCode() == 0) {
	    				StudentInfoForm data = null;
	                    try
	                    {
	                        data = stu.getData();
	                    }
	                    catch (Exception exception1)
	                    {
	                    	System.out.println(">>> account "+strArray[0]+" error cancelQuery. message="+exception1.getMessage());
	                    }
	                    if (data != null)
	                    {
	                    	System.out.println(data.getST_NAME()+"    "+data.getJXNAME()+"    "+data.getSQCXNAME()+"    "+data.getST_CLASSSName());
	                        sb.append(data.getST_NAME()).append("   ").append(data.getJXNAME()).append("   ").append(data.getSQCXNAME()).append("   ").append(data.getST_CLASSSName()).append("\r\n");
	                        System.out.println(data.getST_IDCARD()+"    "+data.getST_HANDSET());
	                        sb.append(data.getST_IDCARD()).append("   ").append(data.getST_HANDSET()).append("\r\n");
	                        System.out.println(data.getST_ID()+"    "+data.getST_PWD()+"    "+data.getSTAUTSNAME());
	                        sb.append(data.getST_ID()).append("   ").append(data.getST_PWD()).append("   ").append(data.getSTAUTSNAME()).append("\r\n");
	                        int num = (Integer.parseInt(data.getXLXSS()) + Integer.parseInt(data.getYYWLXSS()) + Integer.parseInt(data.getZFXSS()));
	                        System.out.println("---------------------------------------");
	                        sb.append("---------------------------------------").append("\r\n");
	                        System.out.println("共:"+ String.valueOf(num)+"  训:"+data.getXLXSS()+"  约:"+data.getYYWLXSS()+"  废:"+data.getZFXSS()+"  余:"+data.getSYXSS()+"  总:"+data.getGMXSS());
	                        sb.append(String.valueOf(num)).append("   ").append(data.getXLXSS()).append("   ").append(data.getYYWLXSS()).append("   ").append(data.getZFXSS()).append("   ").append(data.getSYXSS()).append("   ").append(data.getGMXSS()).append("\r\n");
	                        System.out.println("---------------------------------------");
	                        sb.append("---------------------------------------").append("\r\n");
	                        if (num < 30)
	                        {
	                        	System.out.println(strArray[0] + "    （ " + String.valueOf(num) + " ） ##可以使用 \r\n");
	                            sb.append(strArray[0] + "    （ " + String.valueOf(num) + " ） ##可以使用 \r\n");
	                        }
	                        else {
	                        	System.out.println("不可以使用 \r\n");
	                            sb.append("不可以使用 \r\n");
	                        }
	                        flag1 = false;
	                    }
	                } else {
	                	flag1 = true;
	                }
    			
            	}
            	Result<OrderRecordForm> resOrderRecord = null;
            	boolean flag2 = true;
            	while (flag2) {
	                resOrderRecord = yc.getOrderRecord(strArray[0], jxFlag);
	                if (resOrderRecord.getCode() == 0) {
	                    List<OrderRecordDetailList> carCancelItems = null;
	                    try
	                    {
	                    	carCancelItems = resOrderRecord.getData().getResult();
	                    }
	                    catch (Exception exception2)
	                    {
	                    	System.out.println(">>> account "+strArray[0]+" error cancelQuery. message="+exception2.getMessage());
	                    }
	                    if ((carCancelItems != null) && (carCancelItems.size() > 0))
	                    {
	                        String str5 = "";
	                        String xNSD = "";
	                        sb.append("---------------------------------------").append("\r\n");
	                        for (int i = 0; i < carCancelItems.size(); i++)
	                        {
	                        	String riqi = carCancelItems.get(i).getYYRQ().toString().substring(0,10).replace("/", "-");
	                            if (!str5.equals(riqi) || !xNSD.equals(carCancelItems.get(i).getXNSD()))
	                            {
	                                String sFXL = null;
	                                if ("8".equals(carCancelItems.get(i).getSFXL()))
	                                {
	                                    sFXL = "未训";
	                                }
	                                else if ("1".equals(carCancelItems.get(i).getSFXL()))
	                                {
	                                    sFXL = "已训";
	                                }
	                                else if ("3".equals(carCancelItems.get(i).getSFXL()))
	                                {
	                                    sFXL = "作废";
	                                }
	                                else
	                                {
	                                    sFXL = carCancelItems.get(i).getSFXL();
	                                }
	                                System.out.println(riqi+"    "+carCancelItems.get(i).getXNSD()+"    "+carCancelItems.get(i).getCNBH()+"    "+carCancelItems.get(i).getJLCBH()+"    "+sFXL);
	                                sb.append(riqi + "    " + carCancelItems.get(i).getXNSD() + "    " + carCancelItems.get(i).getCNBH() + "    " + carCancelItems.get(i).getJLCBH() + "    " + sFXL + "\r\n");
	                                if (getBookingDate().equals(riqi)) {
	                                	num_no++;
	                                	String shiduan = "";
	                                	String change = "";
	                            		if ("3001".equals(carCancelItems.get(i).getXNSD())) {
	                            			shiduan = "上午";
	                            		} else if ("3002".equals(carCancelItems.get(i).getXNSD())) {
	                            			shiduan = "下午";
	                            		} else if ("3003".equals(carCancelItems.get(i).getXNSD())) {
	                            			shiduan = "晚上";
	                            		}
	                                	// 标记账号是否约上
	                                	for (int h=0;h<cardNoList.size();h++) {
	                                		change = cardNoList.get(h);
	                                		String[] info_str = change.split("@")[0].split("#");
	                                		if (strArray[0].equals(info_str[0]) && info_str[3].contains(shiduan)) {
	                                			sb_check.append(strArray[0] + "#" + carCancelItems.get(i).getXNSD() + "#" + carCancelItems.get(i).getCNBH() + "#" + riqi.substring(5, 10) + "#0#" + q + "#" + strArray[3] + "#" + strArray[2] + "#" + strArray[1] + "\n");
	                                			// 完全匹配
	                                			//cardNoList.set(h, change+"】");
	                                			cardNoList.remove(h);
	                                		} else if (strArray[0].equals(info_str[0]) && !info_str[3].contains(shiduan)) {
	                                			sb_check_half_match.append(strArray[0] + "#" + carCancelItems.get(i).getXNSD() + "#" + carCancelItems.get(i).getCNBH() + "#" + riqi.substring(5, 10) + "#0#" + q + "#" + strArray[3] + "#" + strArray[2] + "#" + strArray[1] + "\n");
	                                			// 不完全匹配
	                                			cardNoList.set(h, change+"】问要");
	                                		}
	                                	}
	                                }
	                                str5 = carCancelItems.get(i).getYYRQ().toString().substring(0,10).replace("/", "-");
	                                xNSD = carCancelItems.get(i).getXNSD();
	                            }
	                        }
	                        sb.append("---------------------------------------").append("\r\n");
	                        System.out.println("---------------------------------------");
	                    }
	                    flag2 = false;
	                } else {
	                	flag2 = true;
	                	Thread.sleep(0x3E8);
	                }
	                
            	}
                //Thread.sleep(0xBB8);
            }
            System.out.println("******************  " + q + "  end ******************");
            sb.append("******************  " + q + "  end ******************").append("\r\n");
        }
        num_no = num_no-1;
//        sb_check.append("**********************   total  " + num_no + "  count   **********************" + "\r\n");
        writeLog("-result", sb.toString());
        sb_check.append("\n").append(sb_check_half_match.toString());
        writeLog("-server_res", sb_check.toString());
        String[] temp = null;
        sb = new StringBuffer();
        sb_check = new StringBuffer();
        for (int h=0;h<cardNoList.size();h++) {
        	if (cardNoList.get(h).contains("问要")) {
        		sb_check.append(cardNoList.get(h)).append("\n");
        	} else {
        		temp = cardNoList.get(h).split("#");
        		sb.append(temp[0]).append("#").append(temp[1]).append("#")
        		.append(temp[2]).append("#").append(temp[3]).append("#")
        		.append(temp[4]).append("#").append(temp[5]).append("#")
        		.append(temp[6]).append(temp[8]).append("\n");
        	}
    	}
        sb.append(sb_check.toString());
        writeLog("-cardNo", sb.toString());
//        sb_login.append("##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@##@");
//        writeLogin("-login", sb_login.toString());
        Thread.sleep(0xF4240);
	}
}
