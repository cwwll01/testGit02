<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="commons/jquery/jquery-3.2.1.min.js"></script>

<script>
if(typeof jQuery == 'undefined'){
    window.alert("没有jquery");
}
	$(document).ready(function(){
		$("#addRecord").click(function(){
			$("table").append("<tr><td> <input type='checkbox'> </td>"+
					"<td> <input class='txt' type='text' name='isKey' /> </td>"+
					"<td> <input class='txt' type='text' name='type' /> </td>"+
					"<td> <input class='txt' type='text' name='isNull' /> </td>"+
					"<td> <input class='txt' type='text' name='comment' /> </td>"+
				"</tr>");
			
		});
		
		$("#delRecord").click(function(){
			
			$("input[type=checkbox]:checked").each(function(i){
				$(this).parents("tr").remove();  
			});
		});
		
		
		$("#submit").click(function(){
			var dataJson="{\"list\":[";  
			var userId = "";  
			var subjectScore = "";
		
			$("tr[id!=row1]").each(function(i){
				var fild = "{";
				$(this).find("input[type!=checkbox]").each(function(i){
					fild += "\""+$(this).attr("name")+"\":\""+$(this).val()+"\",";
				});
				fild = fild.substring(0, fild.length -1);
				fild += "},";
				dataJson += fild;
			});
			
			dataJson = dataJson.substring(0,dataJson.length -1)+"],";
			
			//表名
			dataJson += "\"tableName\":"+"\""+$("#tableName").val()+"\"}";
			alert(dataJson);
			/*
			$.ajax({
			 	
				type:"POST", 
	            url:"createTable.html", 
	            dataType:"json",      
	            contentType:"application/json",               
	            data: $toJSON(dataJson), 
	            timeout: 1000,
				success: function(result){  
					if(result.success){  
						alert(11111);
						
						$.messager.alert('成功提示',result.msg,"info",function(){  
						//window.opener.doAction("****","../controller/url");//实现页面成功之后的跳转  
						//window.close();
						location.href = "NewList.aspx?pagenum="+pn;//location.href实现客户端页面的跳转   
  
					});  
					}else{  
						alert(22222);
						$.messager.alert('错误提示', result.msg,"error");  
					}  
				}
			
			});
			
			*/
			
			var obj = "{\"name\":\"obj\",\"age\":\"665\",\"list\":[{\"a\":\"1\",\"b\":\"2\",\"c\":\"3\"},{\"a\":\"21\",\"b\":\"31\",\"c\":\"41\"},{\"a\":\"11\",\"b\":\"12\",\"c\":\"13\"}]}";
		    obj['name'] = "obj";
		    obj['age'] = "111";

		    $.ajax({
		        url: '${pageContext.request.contextPath}/createTable.html',
		        method: 'post',
		        contentType: 'application/json', // 这句不加出现415错误:Unsupported Media Type
		        data: dataJson, 
		        success: function(data) {
		        	alert(11);
		            console.log("success...");
		        },
		        /* XMLHttpRequest.readyState
		        0 － （未初始化）还没有调用send()方法
				1 － （载入）已调用send()方法，正在发送请求
				2 － （载入完成）send()方法执行完成，已经接收到全部响应内容
				3 － （交互）正在解析响应内容
				4 － （完成）响应内容解析完成，可以在客户端调用了 
		        */
		        error:function(xhr, textStatus, err) {
		        	alert(xhr.readyState);
		         //   alert(xhr.responseText);
		            alert(xhr.status);
		            alert(textStatus);
		            alert(err);
		        }
		       
		    });
			
		});
		
	});


</script>




<style type="text/css">
	body{
		width:720px;
		text-align: center;
	}
	
	table{
		width:720px;
	}
	tr{
		margin:0px;
		padding:5px;
		text-align:center;
		background:#e5eecc;
		border:solid 1px #c3c3c3;
	}
	label{
		margin:0px;
		padding:5px;
		text-align:left;
		width:250px;
		
	}
	.txt{
		color:#005aa7; 
		border-bottom:1px solid #005aa7; /* 下划线效果 */ 
		border-top:0px; 
		border-left:0px; 
		border-right:0px; 
		background-color:transparent; /* 背景色透明 */ 
	}

	button{
		/*background-color:transparent;  背景色透明 */ 
		border:0px; /*border:0px solid #005aa7;边框取消 */ 
		cursor:pointer; 
	}
	
	.select{
		width: 50px;
	}

</style>


</head>
<body>
	<label>表名：</label>  <input class="txt" type="text" name="tableName" id="tableName" />
	<br>
	<div align="right">
		<button name="addRecord" id="addRecord">添加</button><button name="delRecord" id="delRecord">删除</button><button name="submit" id="submit">提交</button>
	</div>
	<table>
		<tr id="row1">
			<td class="select">select</td>
			<td>isKey</td>
			<td>type</td>
			<td>isNull</td>
			<td>comment</td>
		</tr>
		
	</table>
	
	<p><a href="<%=request.getContextPath() %>/register.html"> 注册 </a></p>
</body>
</html>