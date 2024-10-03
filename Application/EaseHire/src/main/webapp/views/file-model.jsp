<!-- Modal -->
<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content shadow-lg">
            <div class="modal-header bg-dark text-light">
                <h5 class="modal-title" id="uploadModalLabel">Upload Result</h5>
                <button type="button" class="close text-light" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <form id="uploadForm"
                    action="/student/upload-result"
                    method="post" enctype="multipart/form-data"
                    data-success-message="File uploaded successfully!"
                    data-redirect-url="/"
                    data-base-url="/student/upload-result"
                >
                    <div class="download-template-container mb-4">
                        <span class="text-muted">Need a template?</span>
                        <a href="${pageContext.request.contextPath}/student/download-template" class="btn btn-outline-info btn-sm ml-2" download>
                            <i class="fas fa-download"></i> Download Template
                        </a>
                    </div>
                    <div class="file-upload-container">
                        <label for="fileInput" class="d-block mb-2 text-dark">
                            <i class="fas fa-upload"></i> Choose File
                        </label>
                        <input type="file" class="form-control-file mb-2 border-dark" id="fileInput" name="studentFile" accept=".xlsx" required>
                        <div class="file-info mb-3 text-dark">
                            <small class="text-muted">Supported formats:</small>
                            <strong>.xlsx, .csv</strong>
                        </div>
                        <div class="selected-file-info">
                            <strong id="fileName">No file chosen</strong>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" id="file-submit-btn">Submit</button>
            </div>
        </div>
    </div>
</div>
