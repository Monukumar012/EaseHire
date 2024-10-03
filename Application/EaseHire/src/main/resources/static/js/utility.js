$(document).ready(function() {

    // Initialize All DataTable
    var table = $('.data-table').DataTable({
        paging: true,
        searching: true,
        ordering: true,
        info: true
    });

    // Generic AJAX request function
    window.sendAjaxRequest = function(data, url, msg, method, processData = true, contentType = 'application/x-www-form-urlencoded; charset=UTF-8') {
        console.log('data: ', data);
        $('#loading').show();
        return $.ajax({
            url: contextPath + url,
            method: method,
            data: data,
            processData: processData,
            contentType: contentType,
            success: function(response) {
                console.log("response: ", response);
                $('#loading').hide();
                $('#notification').showNotification(msg, true);
            },
            error: function(xhr, status, error) {
                var res=JSON.parse(xhr.responseText);
                console.log('XHR response : ', res);
                $('#loading').hide();
                $('#notification').showNotification('Error : '+res.error, false);
            }
        });
    }

    // Generic Form submission Handler
    $('form').on('submit', function(event) {
        $('#loading').show();
        event.preventDefault(); // Prevent default form submission

        var form = $(this);
        var action = form.attr('action'); // Get form action URL
        var method = form.attr('method'); // Get form method (POST or PUT)
        var successMessage = form.data('success-message'); // Get success message
        var redirectUrl = form.data('redirect-url'); // Get redirect URL
        var enctype = form.attr('enctype'); // Get enctype attribute

        // Determine if the form is set up for file uploads
        var isFileUpload = enctype === 'multipart/form-data';

        // Set processData and contentType based on whether it's a file upload
        var processData = !isFileUpload; // Serialize data if not a file upload
        var contentType = isFileUpload ? false : 'application/x-www-form-urlencoded; charset=UTF-8'; // Handle content type
        // Use FormData for file uploads, otherwise serialize the form data
        var formData = isFileUpload ? new FormData(this) : form.serialize();

        console.log("Form Data: ", formData);
        console.log(action, method, successMessage, redirectUrl);
        sendAjaxRequest(formData, action, successMessage, method, processData, contentType).done(function() {
            setTimeout(function() {
                window.location.href = contextPath + redirectUrl;
            }, 2000);
        });
    });

    // Generic Delete button Handler
    $('.data-table').on('click', '.delete-btn', function() {
        var $button = $(this);
        var endPoint = $button.data('end-point');
        var successMsg = $button.data('success-msg');

        sendAjaxRequest({}, endPoint, successMsg, 'DELETE').done(function() {
            table.row($button.closest('tr')).remove().draw();
        });
    });

});