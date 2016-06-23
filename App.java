package stock.stock;

import com.lzj.util.HttpUtil;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		String stockCode = "sh601006";
		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < 600; i++) {
			if(i == 99){
				buffer = buffer.append(stockCode);
			}else{
				buffer = buffer.append(stockCode).append(",");
			}
		}
		stockCode = buffer.toString();
		String str = HttpUtil.getJsonContent("http://hq.sinajs.cn/list=" + stockCode );
		//String str = HttpUtil.getJsonContent("http://hq.sinajs.cn/list=" + stockCode )
				//.replace("var hq_str_" + stockCode + "=", "").replace("\"", "").replace(";", "");
		System.out.println(str.split("\\n")[2]);
	}
}
