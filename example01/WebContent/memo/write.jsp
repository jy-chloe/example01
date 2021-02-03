<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>    
<table border="1" width="100%">
	<tr>
		<td><h2>메모장</h2></td>
	</tr>
	<tr>
		<td>
			<form name="wForm">
				<table border="1" width="100%">
			    	<tr>	
						<td>이름 </td>
						<td><input type="text" name="writer" value=""></td>
					</tr>
					<tr>
						<td>메모  </td>
						<td><input type="text" name="content" value="" style="width:50%">&nbsp;<button type="button" onclick="save();">확인</button></td>
					</tr>
				</table>
			</form>		
		</td>
	</tr>
	<tr>
		<td align="center">
			<table border="1" width="80%">
				<tr align="center">
					<td>ID</td>
					<td>이름</td>
					<td>메모</td>
					<td>날짜</td>
				</tr>
				<c:if test="${list.size() == 0 }">
		    		<tr>
		    			<td colspan="7" height="200">등록된 방명록이 없습니다.</td>
		    		</tr>				
				</c:if>		
				<c:forEach var="dto" items="${list}">
					<tr>
						<td>${dto.no}</td>
						<td>${dto.writer}</td>
						<td>${dto.content}</td>
						<td>${dto.regiDate}</td>
					</tr>				
				</c:forEach>
				<tr>
	  			<c:if test="${totalRecord > 0}">
		    		<td colspan="7" height="50" align="center">
			    		<a href="#" onclick="goPage('1');">[첫페이지]</a>&nbsp;&nbsp;
			    		
			    		<c:if test="${startPage>blockSize}">
			    			<a href="#" onclick="goPage('${startPage - blockSize}');">[이전10개]</a>
			    		</c:if>
			    		<c:if test="${startPage<=blockSize}">
			    			[이전10개]
			    		</c:if>	&nbsp;	  
			    		
			    		<c:forEach var="i" begin="${startPage}" end="${lastPage}" step="1">
			    			<c:if test="${i == pageNumber}">
			    				[${i}]
			    			</c:if>
			    			<c:if test="${i != pageNumber}">
			    				<a href="#" onclick="goPage('${i}');">${i}</a>
			    			</c:if>
			    		</c:forEach>&nbsp;
			    		<c:if test="${lastPage < totalPage}">
			    			<a href="#" onclick="goPage('${startPage + blockSize}');">[다음10개]</a>
			    		</c:if>
			    		<c:if test="${lastPage >= totalPage}">
			    			[다음10개]
			    		</c:if>&nbsp;&nbsp;
			    		<a href="#" onclick="goPage('${totalPage}');">[끝페이지]</a>
		    		</td>
		    	</c:if>
		    	
		    	<c:if test="${totalRecord == 0}">
					<td colspan="7" height="50" align="right">
					
					</td>
				</c:if>
				</tr>
			</table>		
		</td>
	</tr>
	
</table>


	<script>
		function save(){
			if(confirm('등록하시겠습니까?')){
				wForm.method="post";
				wForm.action="${path}/memo_servlet/writeProc.do";
				wForm.submit();
			}
		}
		
    	function goPage(value1){
    		location.href='${path}/memo_servlet/write.do?pageNumber=' + value1;
    	}
	</script>
	
