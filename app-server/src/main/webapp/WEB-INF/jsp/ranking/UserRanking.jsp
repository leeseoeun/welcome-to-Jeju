<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  h1 {
    text-align: center;
  }
</style>

<br>
<h1>π μ μ  μμ λ³΄κΈ°</h1>

<table class="table table-hover">
<thead>
<tr>
  <th>μμ</th>
  <th>λλ€μ</th>
</tr>
</thead>
  
<tbody>
  <c:forEach items="${userList}" var="user" varStatus="status">
  <tr>
    <td>${status.count}</td>
    <td><a href='../theme/userlist?no=${user.no}'>${user.nickname}</a></td>
  </tr>
  </c:forEach>
</tbody>
</table>

</body>

</html>