package util;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.htmlparser.util.ParserException;

/*  22:    */ public class Util
/*  23:    */ {
/*  24: 54 */   @SuppressWarnings({ "unchecked", "rawtypes" })
				private static Map<String, String> cityName2Code = new HashMap();
/*  25:    */   public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
/*  26:    */   public static final String DATE_PART_FORMAT = "yyyy-MM-dd";
/*  27:    */   public static final String TIME_PART_FORMAT = "HH:mm:ss.SSS";
/*  28:    */   
/*  29:    */   static {

/*  39:    */   }
/*  40:    */   
/*  41: 68 */   public static final DateFormat default_date_format = new SimpleDateFormat("yyyy-MM-dd");
/*  42:    */   
/*  43:    */   public static String getCurDateTime()
/*  44:    */   {
/*  45: 77 */     return getCurDateTime("yyyy-MM-dd HH:mm:ss");
/*  46:    */   }
/*  47:    */   
/*  48:    */   public static String getCurDate()
/*  49:    */   {
/*  50: 86 */     return getCurDateTime("yyyy-MM-dd");
/*  51:    */   }
/*  52:    */   
/*  53:    */   public static String getCurDateTime(String formatStr)
/*  54:    */   {
/*  55: 96 */     SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
/*  56: 97 */     String now = sdf.format(new Date());
/*  57: 98 */     return now;
/*  58:    */   }
/*  59:    */   

				public static String getNextDateTime() throws ParseException
/*  54:    */   {
				  Date date = new Date();
				  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		          String nowDate = sf.format(date);
/*  55: 96 */     //通过日历获取下一天日期  
		          Calendar cal = Calendar.getInstance();
		          cal.setTime(sf.parse(nowDate));
		          cal.add(Calendar.DAY_OF_YEAR, +1);
		          String nextDate_1 = sf.format(cal.getTime());
/*  57: 98 */     return nextDate_1;
/*  58:    */   }

				public static String formatDateString(Date date, String formatStr)
/*  54:    */   {
/*  55: 96 */     SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
/*  56: 97 */     String now = sdf.format(date);
/*  57: 98 */     return now;
/*  58:    */   }

/*  60:    */   public static String getCityCode(String cityName)
/*  61:    */   {
/*  62:102 */     return (String)cityName2Code.get(cityName);
/*  63:    */   }

/* 190:    */   public static String removeTagFromHtml(String content)
/* 191:    */   {
/* 192:244 */     String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
/* 193:    */     
/* 194:246 */     String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
/* 195:    */     
/* 196:248 */     String regEx_html = "<[^>]+>";
/* 197:    */     
/* 198:250 */     String temp = content;
/* 199:251 */     if ((content == null) || (content.isEmpty())) {
/* 200:252 */       return "ERROR";
/* 201:    */     }
/* 202:255 */     temp = temp.replaceAll(regEx_script, "");
/* 203:    */     
/* 204:257 */     temp = temp.replaceAll(regEx_style, "");
/* 205:    */     
/* 206:259 */     temp = temp.replaceAll(regEx_html, "");
/* 207:    */     
/* 208:261 */     temp = temp.replaceAll("\\s+", " ");
/* 209:    */     
/* 210:263 */     return temp.trim();
/* 211:    */   }
/* 212:    */   
/* 213:    */   public static String getMessageFromHtml(String content)
/* 214:    */   {
/* 215:268 */     String regEx_msg = "var\\s+message\\s+=\\s+\"([\\S|\\s]*?)\"";
/* 216:269 */     if (content == null) {
/* 217:270 */       return "ERROR";
/* 218:    */     }
/* 219:272 */     String temp = content.trim();
/* 220:273 */     Pattern p = null;
/* 221:274 */     Matcher m = null;
/* 222:275 */     p = Pattern.compile(regEx_msg);
/* 223:276 */     m = p.matcher(temp);
/* 224:    */     try
/* 225:    */     {
/* 226:278 */       if (m.find()) {
/* 227:279 */         temp = m.group(1);
/* 228:    */       }
/* 229:    */     }
/* 230:    */     catch (Exception e)
/* 231:    */     {
/* 232:283 */       e.printStackTrace();
/* 233:    */     }
/* 234:285 */     return temp;
/* 235:    */   }
/* 264:    */   
/* 265:    */   public static String formatInfo(String info)
/* 266:    */   {
/* 267:312 */     return getCurDateTime() + " : " + info + "\n";
/* 268:    */   }
/* 269:    */   
/* 270:    */   public static String StrFormat(String pattern, Object... arguments)
/* 271:    */   {
/* 272:316 */     Object[] argumentStr = new String[arguments.length];
/* 273:317 */     for (int i = 0; i < argumentStr.length; i++) {
/* 274:318 */       argumentStr[i] = arguments[i].toString();
/* 275:    */     }
/* 276:320 */     return MessageFormat.format(pattern, argumentStr);
/* 277:    */   }






/* 278:    */ }


/* Location:           C:\Users\Administrator\Desktop\GoHome_2013版\original-ticketRobot-0.0.1-SNAPSHOT.jar
 * Qualified Name:     com.ywh.train.Util
 * JD-Core Version:    0.7.0.1
 */