<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EaseHire</title>

    <style>

    </style>


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
                                <c:when test="${empty connectDTO.connectId}">
                                    /connect/add-connect
                                </c:when>
                                <c:otherwise>
                                    /connect/update-connect?connectId=${connectDTO.connectId}&status=${connectDTO.status}
                                </c:otherwise>
                            </c:choose>
                        </c:set>
                        <form id="connectForm"
                              action="${actionUrl}"
                              method="${empty connectDTO.connectId ? 'POST' : 'PUT'}"
                              data-success-message="${empty connectDTO.connectId ? 'Connect successfully saved. Redirecting...' : 'Connect successfully updated. Redirecting...'}"
                              data-redirect-url="/connect">

                            <div id="college-details">
                                <div class="card">
                                    <div class="card-header bg-dark text-white">
                                        <h4 class="mb-0">Connect Details</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="prePlacementTalkDate">Pre Placement Talk Date</label>
                                                <input type="date" name="prePlacementTalkDate" class="form-control" id="prePlacementTalkDate" value="${connectDTO.prePlacementTalkDate}" required />
                                                <span id="prePlacementTalkDateError" class="text-danger small"></span>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="assessmentDate">Assessment Date</label>
                                                <input type="date" name="assessmentDate" class="form-control" id="assessmentDate" value="${connectDTO.assessmentDate}" required />
                                                <span id="assessmentDateError" class="text-danger small"></span>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-12">
                                                <label for="collegeSelect">College</label>
                                                <select name="collegeId" class="form-control" id="collegeSelect" required>
                                                    <option value="">Select College</option>
                                                    <c:forEach var="college" items="${collegeDTOS}">
                                                        <option value="${college.collegeId}"
                                                            <c:if test="${college.collegeId == connectDTO.collegeDTO.collegeId}">selected</c:if>
                                                        >
                                                            ${college.collegeName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Buttons for actions -->
                                        <div class="form-row mt-4">
                                            <div class="form-group col-md-12 text-right">
                                                <a href="${pageContext.request.contextPath}/connect" class="btn btn-secondary mr-1">Cancel</a>
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

<script>
$(document).ready(function() {
    function validateDates() {
        let valid = true;

        // Clear previous error messages
        $('#prePlacementTalkDateError').text('');
        $('#assessmentDateError').text('');

        // Get the values and convert to Date objects
        const prePlacementTalkDate = new Date($('#prePlacementTalkDate').val());
        const assessmentDate = new Date($('#assessmentDate').val());
        const today = new Date();

        // Check if dates are future dates
        if (prePlacementTalkDate <= today) {
            $('#prePlacementTalkDateError').text('The Pre Placement Talk Date must be a future date.');
            valid = false;
        }
        if (assessmentDate <= today) {
            $('#assessmentDateError').text('The Assessment Date must be a future date.');
            valid = false;
        }

        // Check if assessment date is before the pre-placement talk date
        if (assessmentDate < prePlacementTalkDate) {
            $('#assessmentDateError').text('The Assessment Date cannot be before the Pre Placement Talk Date.');
            valid = false;
        }

        return valid;
    }

    // Event listeners for validation
    $('#prePlacementTalkDate, #assessmentDate').on('change', function() {
        validateDates();
    });

    $('#connectForm').on('submit', function(event) {
        if (!validateDates()) {
            event.preventDefault(); // Prevent form submission if not valid
        }
    });
});
</script>
</html>
