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
                    <h2>Colleges</h2>

                    <div class="shortcut-container">
                        <a class="btn btn-primary fixbutton add-button" href="${pageContext.request.contextPath}/college/add-college">
                            <i class="fas fa-plus"></i>
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
                                <th>Code</th>
                                <th>Name</th>
                                <th>Short Name</th>
                                <th>Affiliation</th>
                                <th>City</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${collegeDTOS}" var="parameter">
                            <tr>
                                <td>${parameter.collegeCode}</td>
                                <td>${parameter.collegeName}</td>
                                <td>${parameter.collegeShortName}</td>
                                <td>${parameter.city}</td>
                                <td>${parameter.affiliation}</td>
                                <td>
                                    <div class="btn-group" role="group">
                                        <a class="btn btn-primary btn-sm mr-1" href="${pageContext.request.contextPath}/college/update-college?collegeId=${parameter.collegeId}" style="color: white;">Edit</a>
                                        <button
                                            class="btn btn-danger btn-sm delete-btn"
                                            data-end-point="/college/delete-college?collegeId=${parameter.collegeId}"
                                            data-success-msg="College Successfully deleted."
                                            style="color: white;"
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
