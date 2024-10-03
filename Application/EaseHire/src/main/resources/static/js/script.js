$(document).ready(function() {

    $('#other-details').hide();

    // Define the notification function
    $.fn.showNotification = function(message, isSuccess) {
        var $notification = this;
        var $notificationMessage = $notification.find('#notification-message');

        // Set notification styles and message
        if (isSuccess) {
            $notification.removeClass('alert-danger').addClass('alert-success');
        } else {
            $notification.removeClass('alert-success').addClass('alert-danger');
        }

        $notificationMessage.text(message);

        // Show the notification with full opacity
        $notification.stop(true, true).show().css('opacity', 1);

        // Hide the notification after a delay
        setTimeout(function() {
            $notification.fadeTo(2000, 0, function() {
                $notification.hide().css('opacity', 1); // Reset opacity for next use
            });
        }, 4000);
    };

    // Toggle sidebar
    $('#sidebarToggle').on('click', function() {
        var $sidebar = $('#sidebar');
        var $mainContent = $('#mainContent');

        $sidebar.toggleClass('collapsed');
        $mainContent.toggleClass('collapsed');
        $(this).toggleClass('collapsed');

        var icon = $sidebar.hasClass('collapsed') ? '<i class="fas fa-chevron-right"></i>' : '<i class="fas fa-chevron-left"></i>';
        $(this).html(icon);
    });


    // Close notification
    $('#notification .close').on('click', function() {
        $('#notification').hide();
    });

    $('#fileInput').on('change', function() {
        var fileName = $(this).val().split('\\').pop();
        $('#fileName').text(fileName || 'No file chosen');
    });

    $('#uploadModal').on('hidden.bs.modal', function () {
        $('#fileInput').val(''); // Clear file input
        $('#fileName').text('No file chosen'); // Reset file name display
    });

    $(document).on('keydown', function(event) {
        if (event.altKey && event.code === 'KeyM') {
            event.preventDefault();
            $('.add-button')[0].click(); // Trigger click on the button
        }
    });

    $('#file-submit-btn').on('click', function() {
        $('#uploadForm').submit();
    });

    $('#upload-result').on('click', function(){
        var $row = $(this).closest('tr');
        var connectId = $row.find('td:nth-child(1)').text().trim();
        var collegeCode = $row.find('td:nth-child(4)').text().trim();

        var newActionUrl = $('#uploadForm').data('base-url') + '?connectId=' + encodeURIComponent(connectId) + '&collegeCode=' + encodeURIComponent(collegeCode);

        console.log(newActionUrl);

        $('#uploadForm').attr('action', newActionUrl);
    });
});