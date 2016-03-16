<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

Hello ${user.name}<br/>,

You have following unread items:<br>
<table>
    <tr>
        <th>S.No.</th>
        <th>From Topic</th>
    </tr>

    <g:each var="resource" in="${unreadResources}" status="count">
        <tr>
            <td>${count}</td>
            <td>${resource.topic}</td>
        </tr>
    </g:each>

</table>

</body>
</html>