<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<button onclick="loadList()">멤버 리스트 가져오기</button>
	<div id="list"></div>
	<script>
		function loadList(){
			$.ajax({
				url : "member",	// 요청 경로 : localhost:8089/myapp/member
				type : "get",	// 요청 방식
				dataType : "json",	// 응답받는 데이터의 형식 (view(html,jsp) X, model만 받겠다는 의미)
				success : function(res){	// 요청-응답 성공했을 때
					console.log(res)
					let t="<table border=1>"
						for(let i=0; i<res.length; i++){
							t+="<tr>"
							t+="<td>"+res[i].id+"</td>"
							t+="<td>"+res[i].pw+"</td>"
							t+="<td>"+res[i].nick+"</td>"
							t+="</tr>"
						}
					t+="</table>"
					$("#list").append(t)
					
					
				},
				error : function(){
					alert("통신 실패!")
				}
			})
		}
	</script>
</body>
</html>