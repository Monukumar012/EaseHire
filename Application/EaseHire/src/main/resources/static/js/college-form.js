$(document).ready(function() {
    // Sliding the section
    const $nextButton = $('#nextButton');
    const $backButton = $('#backButton');
    const $collegeDetails = $('#college-details');
    const $otherDetails = $('#other-details');

    $nextButton.on('click', function () {
        // Remove all classes from both sections
        $collegeDetails.removeClass();
        $otherDetails.removeClass();

        // Add classes to handle the animations
        $collegeDetails.addClass('slide-out-left');
        $otherDetails.show(); // Show the section immediately
        $otherDetails.addClass('slide-in-right');

        // Use a timeout to handle the visibility after the animation
        setTimeout(function () {
            $collegeDetails.hide(); // Hide the current section
            $otherDetails.addClass('slide-in-right');
        }, 500); // Delay should match the duration of the slide-out animation
    });

    $backButton.on('click', function () {
        // Remove all classes from both sections
        $collegeDetails.removeClass();
        $otherDetails.removeClass();

        // Add classes to handle the animations
        $otherDetails.addClass('slide-out-right');
        $collegeDetails.show(); // Show the section immediately
        $collegeDetails.addClass('slide-in-left');

        // Use a timeout to handle the visibility after the animation
        setTimeout(function () {
            $otherDetails.hide(); // Hide the current section
            $collegeDetails.addClass('slide-in-left');
        }, 500); // Delay should match the duration of the slide-out animation
    });



    function validateRowData(data) {
        // Check if all required fields are filled
        if (!data.name || !data.email || !data.phoneNumber || !data.year) {
            return { isValid: false, message: 'All fields are required.'};
        }

        // Validate email format
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(data.email)) {
            return { isValid: false, message: 'Please enter a valid email address.' };
        }

        // Validate phone number format (simple check for numeric values)
        var phonePattern = /^\d+$/;
        if (!phonePattern.test(data.phoneNumber)) {
            return { isValid: false, message: 'Phone number should contain only digits.' };
        }

        // Validate year (should be a positive integer)
        var year = parseInt(data.year, 10);
        if (isNaN(year) || year <= 0) {
            return { isValid: false, message: 'Year must be a positive integer.' };
        }

        // If all checks pass
        return { isValid: true, message: '' };
    }


    function createNewInputRow() {
        return $(
            '<tr data-new-row="true">' +
                '<td><input type="text" class="form-control" ></td>' +
                '<td><input type="email" class="form-control" ></td>' +
                '<td><input type="tel" class="form-control" ></td>' +
                '<td><input type="number" class="form-control"></td>' +
                '<td class="text-center">' +
                    '<div class="btn-group" role="group">' +
                        '<button type="button" class="btn btn-success btn-sm save-row">' +
                            '<i class="fas fa-check"></i>' +
                        '</button>' +
                        '<button type="button" class="btn btn-danger btn-sm cancel-row">' +
                            '<i class="fas fa-times"></i>' +
                        '</button>' +
                    '</div>' +
                '</td>' +
            '</tr>'
        );
    }

    function handleAddRow(buttonId, tableBodyId) {
        $(buttonId).click(function() {
            if ($(tableBodyId).find('tr[data-new-row="true"]').length > 0) {
                alert('A row with input fields already exists.');
                return;
            }
            $(tableBodyId).append(createNewInputRow());
        });
    }

    handleAddRow('#addCoordinator', '#coordinatorsTableBody');
    handleAddRow('#addTpo', '#tpoTableBody');

    function createNewTableRow(data, id) {
        console.log("createNewTableRow(data, id): ",data);
        return $(
           '<tr data-index="' + id + '">' +
               '<td>' + data.name + '</td>' +
               '<td>' + data.email + '</td>' +
               '<td>' + data.phoneNumber + '</td>' +
               '<td>' + data.year + '</td>' +
               '<td class="text-center">' +
                   '<div class="btn-group" role="group">' +
                       '<button type="button" class="btn btn-info btn-sm edit-btn">' +
                           '<i class="fas fa-edit"></i>' +
                       '</button>' +
                       '<button type="button" class="btn btn-danger btn-sm delete-btn">' +
                           '<i class="fas fa-trash-alt"></i>' +
                       '</button>' +
                   '</div>' +
               '</td>' +
           '</tr>'
       );
    }

    function appendRow(data, tableId) {
        $(tableId).append(createNewTableRow(data));
    }

    function handleRowActions(tableBodyId, addEndPoint, deleteEndPoint, updateEndPoint) {
        $(tableBodyId).on('click', 'button', function() {
            var $button = $(this);
            var $row = $button.closest('tr');

            if ($button.hasClass('save-row')) {
                var $inputs = $row.find('input');
                var data = {
                    name: $inputs.eq(0).val().trim(),
                    email: $inputs.eq(1).val().trim(),
                    phoneNumber: $inputs.eq(2).val().trim(),
                    year: $inputs.eq(3).val().trim()
                };

                var validation = validateRowData(data);
                if (!validation.isValid) {
                    $('#notification').showNotification(validation.message, false);
                    return;
                }
                sendAjaxRequest(data, addEndPoint, 'New row added.', 'POST').done(function() {
                    appendRow(data, tableBodyId);
                    $row.remove();
                });
            } else if ($button.hasClass('cancel-row')) {
                $row.remove();
            } else if ($button.hasClass('delete-btn')) {
                var id = $row.data('index');
                var data = { id: id-1 };

                sendAjaxRequest(data, deleteEndPoint, 'Deleted row.', 'DELETE').done(function() {
                    $row.remove();
                });
            } else if ($button.hasClass('edit-btn')) {
                var rowData = {
                    name: $row.find('td').eq(0).text(),
                    email: $row.find('td').eq(1).text(),
                    phoneNumber: $row.find('td').eq(2).text(),
                    year: $row.find('td').eq(3).text()
                };
                var $editableRow = createEditableRow(rowData, $row.data('index'));
                $row.replaceWith($editableRow);
            } else if ($button.hasClass('save-edit-row')) {
                var $inputs = $row.find('input');
                var data = {
                    name: $inputs.eq(0).val().trim(),
                    email: $inputs.eq(1).val().trim(),
                    phoneNumber: $inputs.eq(2).val().trim(),
                    year: $inputs.eq(3).val().trim()
                };

                var validation = validateRowData(data);
                if (!validation.isValid) {
                    $('#notification').showNotification(validation.message, false);
                    return;
                }

                var id = $row.data('index');

                var endPoint = updateEndPoint + '?id=' + (id-1);
                sendAjaxRequest(data, endPoint, 'Updated row.', 'PUT').done(function() {
                    $row.replaceWith(createNewTableRow(data, id))
                });
            } else if ($button.hasClass('cancel-edit-row')) {
                var originalData = JSON.parse(decodeURIComponent($row.data('original')));
                $row.replaceWith(createNewTableRow(originalData, $row.data('index')));
            }
        });
    }

    function createEditableRow(data, id) {
        return $(
            '<tr data-index="' + id + '" data-original="' + encodeURIComponent(JSON.stringify(data)) + '">' +
                '<td><input type="text" class="form-control" value="' + data.name + '"></td>' +
                '<td><input type="email" class="form-control" value="' + data.email + '"></td>' +
                '<td><input type="tel" class="form-control" value="' + data.phoneNumber + '"></td>' +
                '<td><input type="number" class="form-control" value="' + data.year + '"></td>' +
                '<td class="text-center">' +
                    '<div class="btn-group" role="group">' +
                        '<button type="button" class="btn btn-success btn-sm save-edit-row">' +
                            '<i class="fas fa-check"></i>' +
                        '</button>' +
                        '<button type="button" class="btn btn-danger btn-sm cancel-edit-row">' +
                            '<i class="fas fa-times"></i>' +
                        '</button>' +
                    '</div>' +
                '</td>' +
            '</tr>'
        );
    }

    handleRowActions('#coordinatorsTableBody', '/coordinator/add-coordinator', '/coordinator/delete-coordinator', '/coordinator/update-coordinator');
    handleRowActions('#tpoTableBody', '/tpo/add-tpo', '/tpo/delete-tpo', '/tpo/update-tpo');


    function initializeTable(tableBodyId,emptyStateId) {
        updateEmptyState(tableBodyId, emptyStateId);
        $(tableBodyId).find('tr').each(function(index) {
            $(this).data('index', index);
        });
    }

    function observeTableChanges(tableBodyId, emptyStateId) {
        var targetNode = document.querySelector(tableBodyId);
        if (!targetNode) {
            console.error('Table body not found:', tableBodyId);
            return;
        }
        var config = { childList: true, subtree: true };
        var callback = function(mutationsList) {
            initializeTable(tableBodyId, emptyStateId);
        };
        var observer = new MutationObserver(callback);
        observer.observe(targetNode, config);
    }

    observeTableChanges('#coordinatorsTableBody', '#coordinatorsEmptyState');
    observeTableChanges('#tpoTableBody', '#tpoEmptyState');

    function updateEmptyState(tableBodyId, emptyStateId) {
        var $tableBody = $(tableBodyId);
        var $emptyState = $(emptyStateId);

        // Check if table has rows (excluding header row)
        if ($tableBody.find('tr').length === 1) { // Only header row present
            $emptyState.show();
        } else {
            $emptyState.hide();
        }
    }

    updateEmptyState('#coordinatorsTableBody', '#coordinatorsEmptyState');
    updateEmptyState('#tpoTableBody', '#tpoEmptyState');

});