<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EaseHire</title>
</head>
<body>
<div class="main">
    <div class="top">
        <%@ include file="navbar.jsp"%>
    </div>
    <div class="bottom">
        <div class="left">
            <%@ include file="sidebar.jsp"%>
        </div>
        <div class="right">
            <div class="main-content" id="mainContent">
                <div class="container mt-5 data-table-name">
                    <h2>Connects</h2>
                    <div class="shortcut-container">
                        <a class="btn btn-primary fixbutton add-button" href="${pageContext.request.contextPath}/connect/add-connect">
                            <i class="fas fa-plus shortcut-icon"></i>
                            <span class="shortcut-text">
                                <span class="key">Alt + M</span>
                            </span>
                        </a>
                    </div>
                </div>

                <div class="table-container">
                    <table id="college-table" class="data-table table table-striped table-bordered mt-4">
                        <thead class="thead-dark">
                            <tr>
                                <th>No.</th>
                                <th>Pre PlacementTalk Date</th>
                                <th>Assessment Date</th>
                                <th>College Code</th>
                                <th>College Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${connectDTOS}" var="parameter">
                            <tr>
                                <td>${parameter.connectId}</td>
                                <td>${parameter.prePlacementTalkDate}</td>
                                <td>${parameter.assessmentDate}</td>
                                <td>${parameter.collegeDTO.collegeCode}</td>
                                <td>${parameter.collegeDTO.collegeName}</td>
                                <td>
                                    <div class="btn-group" role="group">
                                        <a class="btn btn-primary btn-sm mr-1"
                                            href="${pageContext.request.contextPath}/connect/update-connect?connectId=${parameter.connectId}&status=${parameter.status}"
                                            style="color: white;"
                                            <c:if test="${parameter.status == 'INACTIVE'}">disabled</c:if>
                                            <c:if test="${parameter.status == 'INACTIVE'}">onclick="return false;"</c:if>
                                        >Edit</a>
                                        <button
                                            class="btn btn-danger btn-sm delete-btn"
                                            data-end-point="/connect/delete-connect?connectId=${parameter.connectId}&status=${parameter.status}"
                                            data-success-msg="Connect Successfully deleted."
                                            style="color: white;"
                                            <c:if test="${parameter.status == 'INACTIVE'}">disabled</c:if>
                                        >Delete</button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
