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
                <div class="container mt-5">
                    <div class="form-container">

                        <c:set var="actionUrl">
                            <c:choose>
                                <c:when test="${empty collegeDTO.collegeId}">
                                    /college/add-college
                                </c:when>
                                <c:otherwise>
                                    /college/update-college?collegeId=${collegeDTO.collegeId}
                                </c:otherwise>
                            </c:choose>
                        </c:set>

                        <form id="collegeForm"
                            action="${actionUrl}"
                            method="${empty collegeDTO.collegeId ? 'POST' : 'PUT'}"
                            data-success-message="${empty collegeDTO.collegeId ? 'College successfully saved. Redirecting...' : 'College successfully updated. Redirecting...'}"
                            data-redirect-url="/college"
                        >
                            <div id="college-details">
                                <div class="card">
                                    <div class="card-header bg-dark text-white">
                                        <h4 class="mb-0">College Details</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="collegeCode">Code</label>
                                                <input name="collegeCode" class="form-control" id="collegeCode" value="${collegeDTO.collegeCode}" <c:if test="${not empty collegeDTO.collegeId}">readonly</c:if> required />
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="city">City</label>
                                                <input name="city" class="form-control" id="city" value="${collegeDTO.city}" required />
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-12">
                                                <label for="collegeName">College Name</label>
                                                <input name="collegeName" class="form-control" id="collegeName" value="${collegeDTO.collegeName}" required />
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="collegeShortName">Short Name</label>
                                                <input name="collegeShortName" class="form-control" id="collegeShortName" value="${collegeDTO.collegeShortName}" required />
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="affiliation">Affiliation</label>
                                                <input name="affiliation" class="form-control" id="affiliation" value="${collegeDTO.affiliation}" required />
                                            </div>
                                        </div>

                                        <!-- Buttons for actions -->
                                        <div class="form-row mt-4">
                                            <div class="form-group col-md-12 text-right">
                                                <a href="${pageContext.request.contextPath}/college" class="btn btn-secondary mr-1">Cancel</a>
                                                <button type="button" id="nextButton" class="btn btn-primary">Next</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="other-details">
                                <div class="card mb-3">
                                    <div class="card-header bg-dark text-white">
                                        <h4 class="mb-0">Coordinator and TPO Details</h4>
                                    </div>
                                    <div class="card-body">
                                        <!-- Coordinator Section -->
                                        <div class="container">
                                            <!-- Coordinators Table -->
                                            <div class="table-wrapper coordinators-table">
                                                <!-- Heading and Add Coordinator Button -->
                                                <div class="d-flex justify-content-between align-items-center mb-3">
                                                    <h4 class="mb-0">Coordinators</h4>
                                                    <button type="button" class="btn btn-primary btn-sm" id="addCoordinator">
                                                        <i class="fas fa-plus"></i>
                                                    </button>
                                                </div>

                                                <div class="table-container normal-table">
                                                    <table class="table table-striped table-bordered mb-0">
                                                        <thead class="thead-dark">
                                                            <tr>
                                                                <th>Name</th>
                                                                <th>Email</th>
                                                                <th>Phone No.</th>
                                                                <th>Year</th>
                                                                <th>Actions</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="coordinatorsTableBody">
                                                            <!-- Example Empty State -->
                                                            <tr id="coordinatorsEmptyState" style="display: none;">
                                                                <td colspan="5" class="no-data">No coordinators available</td>
                                                            </tr>

                                                            <c:forEach var="coordinator" items="${collegeDTO.coordinators}">
                                                                <tr>
                                                                    <td>${coordinator.name}</td>
                                                                    <td>${coordinator.email}</td>
                                                                    <td>${coordinator.phoneNumber}</td>
                                                                    <td>${coordinator.year}</td>

                                                                    <td class="text-center">
                                                                       <div class="btn-group" role="group">
                                                                           <button type="button" class="btn btn-info btn-sm edit-btn">
                                                                               <i class="fas fa-edit"></i>
                                                                           </button>
                                                                           <button type="button" class="btn btn-danger btn-sm delete-btn">
                                                                               <i class="fas fa-trash-alt"></i>
                                                                           </button>
                                                                       </div>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- TPO Section -->
                                        <div class="container">
                                            <!-- TPO Table -->
                                            <div class="table-wrapper tpo-table">
                                                <!-- Heading and Add TPO Button -->
                                                <div class="d-flex justify-content-between align-items-center mb-3">
                                                    <h4 class="mb-0">TPOs</h4>
                                                    <button type="button" class="btn btn-primary btn-sm" id="addTpo">
                                                        <i class="fas fa-plus"></i>
                                                    </button>
                                                </div>


                                                <div class="table-container normal-table">
                                                    <table class="table table-striped table-bordered mb-0">
                                                        <thead class="thead-dark">
                                                            <tr>
                                                                <th>Name</th>
                                                                <th>Email</th>
                                                                <th>Phone No.</th>
                                                                <th>Year</th>
                                                                <th>Actions</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="tpoTableBody">
                                                            <!-- Example Empty State -->
                                                            <tr id="tpoEmptyState" style="display: none;">
                                                                <td colspan="5" class="no-data">No TPOs available</td>
                                                            </tr>

                                                            <c:forEach var="tpo" items="${collegeDTO.tpos}">
                                                                <tr>
                                                                    <td>${tpo.name}</td>
                                                                    <td>${tpo.email}</td>
                                                                    <td>${tpo.phoneNumber}</td>
                                                                    <td>${tpo.year}</td>

                                                                    <td class="text-center">
                                                                       <div class="btn-group" role="group">
                                                                           <button type="button" class="btn btn-info btn-sm edit-btn">
                                                                               <i class="fas fa-edit"></i>
                                                                           </button>
                                                                           <button type="button" class="btn btn-danger btn-sm delete-btn">
                                                                               <i class="fas fa-trash-alt"></i>
                                                                           </button>
                                                                       </div>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Buttons for actions -->
                                        <div class="form-row mt-2">
                                            <div class="form-group col-md-12 text-right mb-0">
                                                <button type="button" id="backButton" class="btn btn-primary mr-2">Back</button>
                                                <a href="${pageContext.request.contextPath}/college" class="btn btn-secondary mr-2 ml-0">Cancel</a>
                                                <button type="submit" class="btn btn-primary">Submit</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
