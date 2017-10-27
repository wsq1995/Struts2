<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/10/27
  Time: 上午9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>游戏</title>
    <script>
        function onChange(value) {
//            输出value的值
            console.log(value);
//            根据value的值发送请求,获取二级列表的json的数据
            var data = new FormData();
            data.append("index", value);

            var xhr = new XMLHttpRequest();
            xhr.withCredentials = true;

            xhr.addEventListener("readystatechange", function () {
                if (this.readyState === 4) {
                    console.log(this.responseText);
//                    解析json文件  eval
                    JSON = eval('(' + this.responseText + ')');
                    var serverSelect = document.getElementById("servers");
                    var optionEle = serverSelect.getElementsByTagName("option");
                    var length = optionEle.length;
//                    使用循环清空所有的option标签
                    for (var i = 0; i < length; i++) {
                        serverSelect.removeChild(optionEle[0])
                    }
                        serverSelect.innerHTML = "<option value = '-1'>--选择服务器--</option>";
//                    将json数据车入到option中
                    for (var i = 0; i < JSON.length; i++) {
//                        创建一个option标签
                        option = document.createElement("option");

                        option.setAttribute("value", JSON[i].id);
//                        设置文本信息
                        text = document.createTextNode(JSON[i].sname);
//                        把文本信息添加到option中
                        option.appendChild(text);
                        serverSelect.appendChild(option);
                    }

                }
            });

            xhr.open("POST", "http://localhost:8080/getServerJson.action");

            xhr.send(data);


        }
    </script>
</head>
<body>
<h1>选择游戏大区</h1>
<%--显示CitySelectAction中的区数据--%>
<s:select list="areaList" label="区" headerKey="-1" headerValue="选组大区"
          listKey="id" listValue="aname" onchange="onChange(value)"/>
<hr>
区:<select onchange="onChange(value)">
    <option value="-1">选择大区</option>
    <s:iterator value="areaList" var="area">
        <option value="${area.id}">${area.aname}</option>
    </s:iterator>
</select>
服务器:
<select id="servers">
    <option value="-1">选择服务器</option>

</select>


</body>
</html>
