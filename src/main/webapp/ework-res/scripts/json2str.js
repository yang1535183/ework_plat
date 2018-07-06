/**
 * 将传入的JSON转换成String
 * @param o
 * @return
 */
function json2str(o) {
	var arr = [];
	var fmt = function(s) {
		if (typeof s == 'object' && s != null) return json2str(s);
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}
	for (var i in o){
		if(fmt(o[i])!=undefined)
			arr.push("'" + i + "':" + fmt(o[i]));
	}
	return '{' + arr.join(',') + '}';
}


/**
 * 将传入的JSON转换成String(NULL值也转换)
 * @param o
 * @return
 */
function json2strAll(o) {
	var arr = [];
	var fmt = function(s) {
		if (typeof s == 'object') return json2strAll(s);
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}
	for (var i in o){
		if(fmt(o[i])!=undefined)
			arr.push("'" + i + "':" + (fmt(o[i])=="{}"?"''":fmt(o[i])));
	}
	return '{' + arr.join(',') + '}';
}

/**
 * 将传入的JSON数组转换成String
 * @param jsonArray
 * @return
 */
function jsonArray2Str(jsonArray) {
	var JsonArrayString = "[";
	for(var i=0; i<jsonArray.length; i++) {
		JsonArrayString=JsonArrayString+JsonToStringCfz(jsonArray[i])+",";
	}
	JsonArrayString = JsonArrayString.substring(0,JsonArrayString.length-1)+"]";
	return JsonArrayString;
}

/**
 * 将传入的String转换成JSON
 * @param stringValue
 * @return
 */
function str2json(stringValue)
{
   eval("var theJsonValue = "+stringValue);
   return theJsonValue;
}

/**
 * 数组的冒泡排序
 * @param array
 * @return
 */
function bubbleSort(array){
	var len = array.length; 
	for(var i=0; i<len; i++){ 
	　　for(var j=0; j<len; j++){ 
	　　		if(array[i] < array[j]){ 
	　　			var d = array[j]; array[j] = array[i]; array[i] = d; 
	　　		} 
	　　} 
	}
	return array;
}

/**
 * javascript剔除html标签
 * @param str
 * @param noEnter
 * @return
 */
function html2txt(str,noEnter){
	 var html = str;
	 html = html.replace(/<!--[\s\S]*?-->/img, "");//注释
	 html = html.replace(/<[\/]*table[^>]*>/img, "\n");//table
	 html = html.replace(/<[\/]*tbody[^>]*>/img, "");//tbody
	 html = html.replace(/<[\/]*tr[^>]*>/img, "\n");//tr
	 html = html.replace(/<[\/]*td[^>]*>/img, "\n");//td
	 html = html.replace(/<[\/]*p[^>]*>/img, "\n");//p
	 html = html.replace(/<[\/]*a[^>]*>/img, "\n");//a
	 html = html.replace(/<[\/]*col[^>]*>/img, "\n");//col
	 html = html.replace(/<[\/]*br[^>]*>/img, "\n");//br
	 html = html.replace(/<[\/]*[^>]*>/img, "\n");//
	 html = html.replace(/<[\/]*span[^>]*>/img, "");//span
	 html = html.replace(/<[\/]*center[^>]*>/img, "");//center
	 html = html.replace(/<[\/]*ul[^>]*>/img, "");//ul
	 html = html.replace(/<[\/]*i[^>]*>/img, "");//i
	 html = html.replace(/<[\/]*li[^>]*>/img, "");//li
	 html = html.replace(/<[\/]*b[^>]*>/img, "");//b
	 html = html.replace(/<[\/]*hr[^>]*>/img, "");//hr
	 html = html.replace(/<[\/]*h\d+[^>]*>/img, "");//h1,2,3,4,5,6
	 html = html.replace(/<STYLE[\s\S]*?<\/STYLE>/img, "");//样式
	 html = html.replace(/<script[\s\S]*?<\/script>/img, "");//引用的脚本
	 //html = html.replace(/<[\?!A-Za-z\][^><]*>/img, "");alert("str:"+html)
	 html = html.replace(/\r/img, "");//换行
	 html = html.replace(/\n/img, "\r\n");//回车
	 html = html.replace(/[　|\s]*\r\n[　|\s]*\r\n/img, "\r\n");
	 //html = reg.replace(html,@"(\r\n)[^ 　]/img,"$1");
	 //html = formatHtml(html);
	 if (noEnter) {
	    html = html.replace(/\r\n/img, "");
	    html = html.replace(/\n/img, "");
	    html = html.replace(/\r/img, "");
	 }
	 return (html);
}