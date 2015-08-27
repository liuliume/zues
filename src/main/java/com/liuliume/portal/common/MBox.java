package com.liuliume.portal.common;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.mybatis.Parameter;



public class MBox {

	public static final Set<String> DATAGRID_PARAM_PRIFIX = new HashSet<String>();
	static{
		DATAGRID_PARAM_PRIFIX.add("iSortingCols");
		DATAGRID_PARAM_PRIFIX.add("iColumns");
		DATAGRID_PARAM_PRIFIX.add("sColumns");
		DATAGRID_PARAM_PRIFIX.add("sEcho"); 
		DATAGRID_PARAM_PRIFIX.add("bSearchable");
		DATAGRID_PARAM_PRIFIX.add("bRegex");
		DATAGRID_PARAM_PRIFIX.add("bRegex"); 
		DATAGRID_PARAM_PRIFIX.add("bSortable");
		DATAGRID_PARAM_PRIFIX.add("sSort");
	}
	
	public static final String DATAGRID_SEARCH_PARAM_NAME = "sSearch";
	
	private static final Logger logger = LoggerFactory.getLogger(MBox.class);
	
	public static String getCurrentUserName(){
		return "Demo用户";
	}
	
	public static String underLineToCamelize(String value) {
		return underLineToCamelize(value,true);
	}
    /**
     * 将字符串转换为驼峰形式,例如 is_boy转换为isBoy
     * @param value 要转换的字符串
     * @param startWithLowerCase 转换后的字符串是否以小写开始
     * @return
     */
    public static String underLineToCamelize(String value, boolean startWithLowerCase) {
        String[] strings = value.toLowerCase().split("_");
        for (int i = startWithLowerCase ? 1 : 0; i < strings.length; i++) {
            strings[i] = StringUtils.capitalize(strings[i]);
        }
        return StringUtils.join(strings);
    }
    /**
     * 将驼峰命名转换为下划线。
     * @param value
     * @return
     */
    public static String camelizeToUnderLine(String value) {
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<value.length();i++){
    		if(Character.isUpperCase(value.charAt(i)) && i>0){
    			sb.append("_");
    		}
    		sb.append(Character.toLowerCase(value.charAt(i)));
    	}
        return sb.toString();
    }
	
	/**
	 * 只能有数字和小数点，且小数点只能有一个
	 * @param cs
	 * @return
	 */
    public static boolean isFloat(CharSequence cs) {
        if (cs == null) {
            return false;
        }
        boolean findDot=false;
        boolean findNumrice=false;
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(cs.charAt(i)) == false) {
            	if(cs.charAt(i) != '.'){
                    return false;	
            	}else{
            		if( findDot ){
            			return false;
            		}else{
            			findDot=true;
            			continue;
            		}
            	}
            	
            }
            findNumrice=true;
        }
        return findNumrice && findDot;
    }

	public static String getHttpFullpath(HttpServletRequest request , String resourceName){
		return getHttpPrefix(request) + resourceName;
//		logger.info("request para: {} - {} - {} - {}" ,new Object[]{servername,serverPort,contextpath,request.getServletPath()});
	}

	public static String getHttpPrefix(HttpServletRequest request){
		String servername = request.getServerName();
		int serverPort = request.getServerPort();
		String contextpath = request.getContextPath();
		return "http://" + servername + ":" + serverPort+"" + contextpath ;
//		logger.info("request para: {} - {} - {} - {}" ,new Object[]{servername,serverPort,contextpath,request.getServletPath()});
	}


	private static boolean matchGridQueryParam(Map<String,String> destParam , String paramPrefix,String httpParamKey ,String httpParamVal){
		if(httpParamKey.startsWith(paramPrefix)){
			destParam.put(httpParamKey, httpParamVal);
			return true;
		}else{
			return false;
		}

	}

	public static Map<String,Object> dataGridQueryParam(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		Map<String , Object> paramap = (Map<String , Object>)request.getParameterMap();
		Map<String,String> picked_field_names = new HashMap<String,String>();
		Map<String,String> picked_field_values = new HashMap<String,String>();
		Map<String,String> picked_sort_col_idx = new HashMap<String,String>();
		Map<String,String> picked_sort_col_dir = new HashMap<String,String>();
		Map<String,String> finalParams = new HashMap<String,String>();
		OrderBy ob = new OrderBy();
		for(String key : paramap.keySet()){
			String val = null;
			Object valObj = paramap.get(key);
			if(valObj instanceof String){
				val = (String)valObj;
			}else
			if(valObj instanceof Object[]){
				//logger.error("参数[{}]的值是一个数组！！：{} sizeis:" +((Object[])valObj).length ,key,StringUtils.join((Object[])valObj,","));
				Object[] objs = (Object[])valObj;
				if( objs.length==0){
					continue;
				}
				if( objs.length==1 ){
					val = objs[0].toString();
				}else{
					val = StringUtils.join(objs);
				}
			}
			if(val==null || val.trim().length()==0){
				continue;
			}
			val = val.trim();
			if(matchGridQueryParam(picked_field_names ,"mDataProp_" , key , val )){
			}else
			if(matchGridQueryParam(picked_field_values ,"sSearch_" , key , val )){
			}else
			if(key.equals("sSearch")){
				finalParams.put(DATAGRID_SEARCH_PARAM_NAME, val);
				logger.debug("find http query parameter key:[sSearch] -- value:[{}]",val);
			}else
			if(key.equals("iDisplayStart")){
				finalParams.put("start", val);
				continue;
			}else
			if(key.equals("iDisplayLength")){
				finalParams.put("limit", val);
			}else
			if(matchGridQueryParam(picked_sort_col_idx ,"iSortCol_" , key , val )){
				ob.setField(val);
			}else
			if(matchGridQueryParam(picked_sort_col_dir ,"sSortDir_" , key , val )){
				ob.setOrder(val);
			}else{
				finalParams.put(key, val);
			}
		}
//		iDisplayStart:0
//		iDisplayLength:10
//		iSortCol_0:2
//		sSortDir_0:asc
//		iSortingCols:1
		//处理查询值的对应关系
		for(String key : picked_field_names.keySet()){
			String key_idx = StringUtils.substringAfter(key, "mDataProp_");
			String paramName = picked_field_names.get(key);
			String paramVal = picked_field_values.get("sSearch_" + key_idx);
			if(StringUtils.isNotBlank(paramVal)){
				logger.debug("find http query parameter key:[{}] -- value:[{}]",paramName,paramVal);
				finalParams.put(paramName, paramVal);
			}
		}
		//处理排序值的对应关系

		for(String key : picked_sort_col_idx.keySet()){
			String key_idx = StringUtils.substringAfter(key, "iSortCol_");
			String paramFieldName = "mDataProp_"+picked_sort_col_idx.get(key);
			String paramName = picked_field_names.get(paramFieldName);
			String paramVal = picked_sort_col_dir.get("sSortDir_" + key_idx);
			if(StringUtils.isNotBlank(paramVal)){
				logger.debug("find http query parameter orderby key:[{}] -- value:[{}]",paramName,paramVal);
				finalParams.put("sSort_"+paramName, paramVal);
			}
		}
		Map<String,Object> rsParams = new HashMap<String,Object>();

		for(String key : finalParams.keySet()){
			String[] skeys = key.split("_");
			String key_1 = skeys[0];
			if(DATAGRID_PARAM_PRIFIX.contains(key_1)){
				continue;
			}
			rsParams.put(key, finalParams.get(key));
		}
		if(ob.getField()!=null){
			rsParams.put("orderBy", ob);
		}

		logger.info("本次解析Request:{} 查询字符串：{}",request.getRequestURI(),rsParams);
		return rsParams;
	}
//	public static void setPageInfoOfGridQuery(Map<String,String> map , IdEntity entity){
//		PageRequest p = new PageRequest(map);
//		for(String key : map.keySet()){
//			String val = map.get(key);
//			if(key.startsWith("sSort_")){
//				String fieldName = StringUtils.substringAfter(key, "sSort_");
//				entity.addOrderBy(new OrderBy(fieldName,val));
//			}
//			if(key.equals("sSearch")){
//				entity.setLikeSearch(val);
//			}
//		}
//		entity.setPage(p);
//	}
//
//	public static void setPageInfo(HttpServletRequest request , IdEntity entity){
//		PageRequest p = new PageRequest(request);
//		entity.setPage(p);
//	}

	public static  Integer parseInteger(String str){
		try{
			if(str==null || str.trim().length()==0){
				return null;
			}
			return Integer.parseInt(str);
		}catch(Exception e){
			logger.warn("将字符" + str + " 转换成整形异常!,返回空!",e);
			return null;
		}
	}

	public static boolean checkDir(String path){
		File newfile = new File(path);
		if(!newfile.exists()){
			return newfile.mkdirs();
		}
		return true;
	}



	public static String trimXml(Document doc){
		String str = doc.asXML();
		int pos = str.indexOf("\n");
		if(pos!=-1){
			str = str.substring(pos+1);
		}
		return str;
	}

	//soffice --invisible --headless --nologo --nofirststartwizard  "--accept=socket,host=localhost,port=8100;urp;StarOffice.ServiceManager"
//	public static void fileToSwf(String infile , String outfile){
//
//	}

	public static void fileWriteThumbnail(String pdffile , String thumbnail) throws IOException, InterruptedException{
		String cmd = "convert -thumbnail 93x " + pdffile + "[0] " + thumbnail;
		logger.info("开始提取缩略图，命令：{}" ,cmd );
		int waitFor = Runtime.getRuntime().exec(cmd).waitFor();
		logger.info("结束提取缩略图，结果:{} 命令:{}" ,waitFor , cmd );
	}

	public static void fileToPdf(String infile  , String outdir) throws IOException, InterruptedException{
		logger.info("开始转换文件为pdf，infile{} outfile:{}",infile,outdir);
		String cmd = "soffice --invisible --headless --nologo --nofirststartwizard --convert-to pdf " + infile + " --outdir " + outdir;
		logger.info("开始执行命令：{}" ,cmd);
		int waitFor = Runtime.getRuntime().exec(cmd).waitFor();
		logger.info("转换结束 :{} cmd:{}" ,waitFor , cmd );
//		OpenOfficeConnection conn = new SocketOpenOfficeConnection(8100);
//		conn.connect();
//
//		DocumentConverter converter =  new OpenOfficeDocumentConverter(conn);
//		converter.convert(new File(infile), new File(outfile));
//		logger.info("结束转换文件为pdf，infile{} outfile:{}",infile,outfile);
//		conn.disconnect();
//		 DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
//
//		    String officeHome = getOfficeHome();
//		    config.setOfficeHome(officeHome);
//
//		    OfficeManager officeManager = config.buildOfficeManager();
//		    officeManager.start();
//
//		    OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
//		    String outputFilePath = getOutputFilePath(inputFilePath);
//		    converter.convert(new File(inputFilePath), new File(outputFilePath));
//
//		    officeManager.stop();
	}
	public static String[] getFileNameComposed(String oriName){
		int pos = oriName.lastIndexOf(".");
		if (pos == -1) {
			throw new IllegalArgumentException("上传文件没有扩展名!");
		}
		String[] str = {
				oriName.substring(0,pos),
				oriName.substring(pos + 1).toLowerCase()
		};
		return str;
	}
	// pdf2swf /home/shiqiangguo/downloads/supplierAbility_1346398211931.pdf -o /home/shiqiangguo/downloads/test.swf -T 9  -f -t -s storeallcharacters
	public static void pdfToSwf(String infile,String outfile) throws InterruptedException, IOException{
//		int pos = infile.lastIndexOf('/');
//		String path = infile.substring(0,pos+1);
//		String fn = infile.substring(pos+1);
//		String[] fnComposed = getFileNameComposed(fn);
//		String outfile = path + fnComposed[0]+".swf"
		String cmd = "pdf2swf " + infile + " -o " + outfile + " -T 9  -f -t -s storeallcharacters";
		logger.info("执行PDF2SWF 命令：" + cmd);
		int waitFor = Runtime.getRuntime().exec(cmd).waitFor();
		logger.info("转换结束 :{} cmd:{}" ,waitFor , cmd );
	}
	
	public static String convertCsvColToDBCol(String csvcol){
		//\p{Punct} 表示标点符号
		return csvcol.trim().replaceAll("[\\s|\\p{Punct}]+", "_").toLowerCase();
	}
		
	/**
	 * 将集合中的对象的多个属性值连接成字符串"<prop1,prop2,prop3>,<prop1,prop2,prop3>"
	 * @param coll
	 * @param separator
	 * @param props
	 * @return
	 */
	public static String join( Collection coll , String separator , final String ... props){
		return StringUtils.join(CollectionUtils.collect(coll, new Transformer() {
			
			public Object transform(Object input) {
					StringBuilder sb = new StringBuilder("<");
					for( String prop : props){
						try {
							String propValueStr = BeanUtils.getProperty(input, prop);
							sb.append(propValueStr).append(",");
						} catch (Exception e) {
							logger.error(String.format("设置[%s]类的[%s]属性出错", input.getClass().getName() , prop) ,e);
						}
					}
					sb.replace(sb.lastIndexOf(","),sb.length(),">");
				return sb.toString();
			}
		}), separator);
	}
	
	public static BigDecimal getShowDecimal(Object input){
		if(input==null){
			return null;
		}
		BigDecimal result = new BigDecimal(input.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
		return result;
	}

	public static Parameter convertParameter(Seed<?> seed){
		Parameter parameter = new Parameter();
		parameter.setStart(seed.getStartPosition());
		parameter.setLimit(seed.getPagesize());
		parameter.setOrderby(parameter.getOrderby());
		return parameter;
	}
	
}
