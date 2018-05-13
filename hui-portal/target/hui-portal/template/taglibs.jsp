<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>


<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="system" value="/ecps/console"/>
<c:set var="upload" value="http://127.0.0.1:8088/pic_server/upload/"/>
<c:set var="datePattern"><fmt:message key="date.format"/></c:set>
  