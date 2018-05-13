package com.tainy.common.util.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int showCount = 20; // 每页显示记录数
	private boolean formActionFirst = false;// 是否考虑优先使用form表单action作为分页url
	private int pageshowTag = 10;
	
	private int pageSize; // 每页记录数
	private int totalPage; // 总页数
	private int totalResult; // 总记录数
	private int currentPage; // 当前页, 从1开始
	private int currentResult; // 当前记录起始索引, 从0开始
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private int formNum = 0; // 执行分页跳转的form索引	

	private String webPageStr; // 官网分页样式
	private String newWebPageStr;// 新官网样式
	private String newPageStr; // 页面底部导航，详细见get方法，解决一个页面有多个导航条时使用	
	
	private List<T> list = new ArrayList<T>();

	public Page() {
	}

	public Page(int pageSize, int currentPage) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.showCount = pageSize;
	}

	public int getTotalPage() {
		int tempCount = pageSize > 0 ? pageSize : showCount;
		totalPage = totalResult % tempCount == 0 ? totalResult / tempCount : totalResult / tempCount + 1;

		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setNewPageStr(String newPageStr) {
		this.newPageStr = newPageStr;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if ( currentPage <= 0 ){
			currentPage = 1;
		}			
		
		if ( getTotalPage() > 0 && currentPage > getTotalPage() ){
			currentPage = getTotalPage() + 1;
		}
		
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageshowTag() {
		return pageshowTag;
	}

	public void setPageshowTag(int pageshowTag) {
		this.pageshowTag = pageshowTag;
	}

	public String getNewPageStr() {
		this.newPageStr = this.getPageStr().replaceAll("nextPage", "nextNewPage");

		return newPageStr;
	}

	public String getPageStr() {
		if(1 > 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {
			/* sb.append("	<ul>\n"); */
			if (currentPage == 1) {
				sb.append(" <li class=\"disabled\"><a>共" + totalResult + "条</a></li>\n");
				// sb.append("	<li><a href=\"disabled\" style=\"height: 31px;\" >每页<input name=\"showCount\" value=\""
				// + showCount
				// +"\" type=\"text\" style=\"text-align: center;width: 40px;\"/>条</a></li>\n");
				sb.append("	<li class=\"disabled\"><a>首页</a></li>\n");
				sb.append("	<li class=\"disabled\"><a>上页</a></li>\n");
			} else {
				sb.append(" <li class=\"disabled\"><a>共" + totalResult + "条</a></li>\n");
				// sb.append("	<li><a href=\"disabled\" style=\"height: 31px;\" >每页<input name=\"showCount\" value=\""
				// + showCount
				// +"\" type=\"text\" style=\"text-align: center;width: 40px;\"/>条</a></li>\n");
				sb.append("	<li><a href=javascript:void(0); onclick=\"nextPage(1)\">首页</a></li>\n");
				sb.append("	<li><a href=javascript:void(0); onclick=\"nextPage(" + (currentPage - 1) + ")\">上页</a></li>\n");
			}
			int showTag = pageshowTag; // 分页标签显示数量10
			int startTag = 1;
			if (currentPage > showTag) {
				startTag = currentPage - 1;
			}
			int endTag = startTag + showTag - 1;
			for (int i = startTag; i <= totalPage && i <= endTag; i++) {
				if (currentPage == i)
					sb.append("<li class=\"active\"><a>" + i + "</a></li>\n");
				else
					sb.append("	<li><a href=javascript:void(0); onclick=\"nextPage(" + i + ")\">" + i + "</a></li>\n");
			}
			if (currentPage == totalPage) {
				sb.append("	<li class=\"disabled\"><a>下页</a></li>\n");
				sb.append("	<li class=\"disabled\"><a>尾页</a></li>\n");
			} else {
				sb.append("	<li><a href=javascript:void(0); onclick=\"nextPage(" + (currentPage + 1) + ")\">下页</a></li>\n");
				sb.append("	<li><a href=javascript:void(0); onclick=\"nextPage(" + totalPage + ")\">尾页</a></li>\n");
			}
			sb.append("	<li class=\"disabled\"><a>第" + currentPage + "页</a></li>\n");

			/* sb.append("</ul>\n"); */
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			sb.append("	if(true && document.forms[" + formNum + "]){\n");
			sb.append("		var url = document.forms[" + formNum + "].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&currentPage=\";}\n");
			sb.append("		else{url += \"?currentPage=\";}\n");
			sb.append("		document.forms[" + formNum + "].action = url+page;\n");
			sb.append("		document.forms[" + formNum + "].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&currentPage=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?currentPage=\";}\n");
			sb.append("		document.location = url + page;\n");
			sb.append("	}\n");
			sb.append("}\n");
			sb.append("</script>\n");

			sb.append("	<li class=\"disabled\"><a>共" + totalPage + "页</a></li>\n");
		}
		pageStr = sb.toString();

		return pageStr;
	}

	// 官网的分页样式
	public String getWebPageStr() {
		if(1 > 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {
			if (currentPage == 1) {
				/*
				 * sb.append("	<li class=\"disabled\"><a>首页</a></li>\n");
				 * sb.append("	<li class=\"disabled\"><a><<</a></li>\n");
				 */
				sb.append("	<span style=\"width:60px;margin-right:10px;\">上一页</span>\n");
			} else {
				/*
				 * sb.append(
				 * "	<li><a href=javascript:void(0); onclick=\"nextPage(1)\">首页</a></li>\n"
				 * );
				 * sb.append("	<li><a href=javascript:void(0); onclick=\"nextPage("
				 * +(currentPage-1)+")\"><<</a></li>\n");
				 */
				sb.append("	<a href=javascript:void(0); onclick=\"nextPage(" + (currentPage - 1)
						+ ")\" style=\"width:60px;margin-right:10px;\" id=\"prevPage\">上一页</a>\n");
			}
			int showTag = 10; // 分页标签显示数量
			int startTag = 1;
			if (currentPage > showTag) {
				startTag = currentPage - 1;
			}
			int endTag = startTag + showTag - 1;
			for (int i = startTag; i <= totalPage && i <= endTag; i++) {
				if (currentPage == i) {
					// sb.append("<li class=\"active\"><a>"+i+"</a></li>\n");
					sb.append("<span class=\"pageOn\">" + i + "</span>\n");
				} else {
					// sb.append("	<li><a href=javascript:void(0); onclick=\"nextPage("+i+")\">"+i+"</a></li>\n");
					sb.append("	<a href=javascript:void(0); onclick=\"nextPage(" + i + ")\">" + i + "</a>\n");
				}
			}

			if (currentPage == totalPage) {
				/*
				 * sb.append("	<li class=\"disabled\"><a>>></a></li>\n");
				 * sb.append("	<li class=\"disabled\"><a>尾页</a></li>\n");
				 */

				sb.append("	<span style=\"width:60px;\">下一页</span>\n");
			} else {
				/*
				 * sb.append("	<li><a href=javascript:void(0); onclick=\"nextPage("
				 * +(currentPage+1)+")\">>></a></li>\n");
				 * sb.append("	<li><a href=javascript:void(0); onclick=\"nextPage("
				 * +totalPage+")\">尾页</a></li>\n");
				 */

				sb.append(" <span>...</span>");
				sb.append(" <a href=javascript:void(0); onclick=\"nextPage(" + (currentPage + 1)
						+ ")\" style=\"width:60px;\" id=\"nextPage\" >下一页</a>\n");

			}

			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			sb.append("	if(true && document.forms[" + formNum + "]){\n");
			sb.append("		var url = document.forms[" + formNum + "].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&currentPage=\";}\n");
			sb.append("		else{url += \"?currentPage=\";}\n");
			sb.append("		document.forms[" + formNum + "].action = url+page;\n");
			sb.append("		document.forms[" + formNum + "].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&currentPage=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?currentPage=\";}\n");
			sb.append("		document.location = url + page;\n");
			sb.append("	}\n");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		webPageStr = sb.toString();

		return webPageStr;
	}

	// 新官网分页
	public String getNewWebPageStr() {
		if(1 > 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {
			sb.append("<div class=\"pageTotal\">共").append(getTotalPage()).append("页</div>");
			sb.append("<ul class=\"yiiPager\">");
			if (currentPage == 1) {
				// sb.append("<li class=\"first\"><a href=\"javascript:void(0);\"><i class=\"icon-double-angle-left\"></i></a></li>");
				// sb.append("<li class=\"previous\"><a href=\"javascript:void(0);\"><i class=\"icon-angle-left\"></i></a></li>");
			} else {
				sb.append("<li class=\"first\"><a href=\"javascript:void(0);\" onclick=\"nextPage(" + (1)
						+ ")\"><i class=\"icon-double-angle-left\"></i></a></li>");
				sb.append("<li class=\"previous\"><a href=\"javascript:void(0);\" onclick=\"nextPage(" + (currentPage - 1)
						+ ")\"><i class=\"icon-angle-left\"></i></a></li>");
			}
			int showTag = 5; // 分页标签显示数量
			int startTag = 1;
			if (currentPage >= showTag) {
				startTag = currentPage - 2;// 当前页数字前面显示两个数字
			}
			int endTag = startTag + showTag - 1;
			for (int i = startTag; i <= totalPage && i <= endTag; i++) {
				if (currentPage == i) {
					sb.append("<li class=\"page selected\"><a href=\"javascript:void(0);\">" + i + "</a></li>");
				} else {
					sb.append("<li class=\"page\"><a href=\"javascript:void(0);\" onclick=\"nextPage(" + i + ")\">" + i
							+ "</a></li>");
				}
			}
			if (currentPage == totalPage) {
				// sb.append("<li class=\"next\"><a href=\"javascript:void(0);\"><i class=\"icon-angle-right\"></i></a></li>");
				// sb.append("<li class=\"last\"><a href=\"javascript:void(0);\"><i class=\"icon-double-angle-right\"></i></a></li>");
			} else {
				sb.append("<li class=\"next\"><a href=\"javascript:void(0);\" onclick=\"nextPage(" + (currentPage + 1)
						+ ")\"><i class=\"icon-angle-right\"></i></a></li>");
				sb.append("<li class=\"last\"><a href=\"javascript:void(0);\" onclick=\"nextPage(" + totalPage
						+ ")\"><i class=\"icon-double-angle-right\"></i></a></li>");
			}

			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			sb.append("	if(" + formActionFirst + "&& document.forms[" + formNum + "]){\n");
			sb.append("		var url = document.forms[" + formNum + "].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&currentPage=\";}\n");
			sb.append("		else{url += \"/p\";}\n");
			sb.append("		document.forms[" + formNum + "].action = url+page;\n");
			sb.append("		document.forms[" + formNum + "].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&currentPage=\";\n");
			sb.append("			}\n");
			sb.append("		}else if(url.indexOf('/p')>-1){\n");
			sb.append("         var reg = /\\/p\\d+[\\/d*]*/g;\n");
			sb.append("			url = url.replace(reg,'/p');\n");
			sb.append("     } else { url += \"/p\";}\n");
			sb.append("		document.location = url+page;\n");
			sb.append("	}\n");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		newWebPageStr = sb.toString();

		return newWebPageStr;
	}

	public void setNewWebPageStr(String newWebPageStr) {
		this.newWebPageStr = newWebPageStr;
	}

	public void setWebPageStr(String webPageStr) {
		this.webPageStr = webPageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * (getPageSize()>0 ? getPageSize() : getShowCount());
		if (currentResult < 0){
			currentResult = 0;
		}			
		
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public int getFormNum() {
		return formNum;
	}

	public void setFormNum(int formNum) {
		this.formNum = formNum;
	}

	public boolean isFormActionFirst() {
		return formActionFirst;
	}

	public void setFormActionFirst(boolean formActionFirst) {
		this.formActionFirst = formActionFirst;
	}	

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}