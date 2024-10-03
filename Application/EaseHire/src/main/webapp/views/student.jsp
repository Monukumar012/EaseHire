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
                    <h2>Students</h2>
                </div>

                <div class="table-container">
                    <table id="college-table" class="data-table table table-striped table-bordered mt-4">
                        <thead class="thead-dark">
                            <tr>
                                <th>Name</th>
                                <th>Gender</th>
                                <th>Contact Number</th>
                                <th>Graduation</th>
                                <th>Graduation Year</th>
                                <th>College Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${studentDTOS}" var="parameter">
                            <tr>
                                <td>${parameter.firstName} ${parameter.lastName}</td>
                                <td>${parameter.gender}</td>
                                <td>${parameter.contactNumber}</td>
                                <td>${parameter.educationInfo.graduation}</td>
                                <td>${parameter.educationInfo.graduationYear}</td>
                                <td>${parameter.educationInfo.collegeDTO.collegeName}</td>
                                <td>
                                    <div class="btn-group" role="group">
                                        <a class="btn btn-primary btn-sm mr-1"
                                            href="${pageContext.request.contextPath}/student?studentId=${parameter.studentId}"
                                            style="color: white;"
                                        >View All Details</a>
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
