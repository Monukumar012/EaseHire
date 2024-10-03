<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - EaseHire</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Include Font Awesome CSS for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
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
                <h2 class="my-4">Dashboard</h2>
                <div class="row">
                    <div class="col-md-4 mb-4">
                        <div class="card dashboard-card">
                            <div class="card-body dashboard-card-body">
                                <i class="fas fa-user-graduate card-icon"></i>
                                <h5 class="card-title">Total Students</h5>
                                <p class="card-text">${totalStudents}</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card dashboard-card">
                            <div class="card-body dashboard-card-body">
                                <i class="fas fa-university card-icon"></i>
                                <h5 class="card-title">Total Colleges</h5>
                                <p class="card-text">${totalColleges}</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card dashboard-card">
                            <div class="card-body dashboard-card-body"">
                                <i class="fas fa-calendar-check card-icon"></i>
                                <h5 class="card-title">Total Connects</h5>
                                <p class="card-text">${totalConnects}</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container mt-5 heading">
                    <h2>Active Connects</h2>
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
                                <th>Result</th>
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
                                        <button id="upload-result" class="btn btn-primary btn-sm mr-1" style="color: white;" data-toggle="modal" data-target="#uploadModal">Upload</button>
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

<%@ include file="file-model.jsp"%>
</body>
</html>
