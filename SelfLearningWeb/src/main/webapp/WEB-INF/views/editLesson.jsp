<%@ page import="java.util.ArrayList,
				model.BEAN.User,
				model.BEAN.Lesson,
				model.BEAN.Section,
				model.BEAN.Content" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    <title>Self Study</title>
</head>
<body>
	<% 
   		User user = (User) session.getAttribute("currentUser");
   	%>
    <div class="container--main">
        <div class="sideBar">
            <h1 class="logo--black">SELF STUDY</h1>
            <ul class="navbar--vertical">
                <li> 
                    <img src="../static/img/book-solid.svg" alt="">
                    <a href="${pageContext.request.contextPath}/Lesson/list">Học tập</a>
                </li>
                <li>
                    <img src="../static/img/folder-solid.svg" alt="">
                    <a href="${pageContext.request.contextPath}/Lesson/edit?author_id=<%=user.getID()%>">Bài học của bạn</a>
                </li>
            </ul>
            <div class="action-group">

            </div>
            <ul class="navbar--vertical">
                <li>
                    <img src="../static/img/right-from-bracket-solid.svg" alt="">
                    <a href="${pageContext.request.contextPath}/Auth/logout">Đăng xuất</a>
                </li>
            </ul>
        </div>
        <div class="content-section--main">
            <div class="content-section__header--main">
                <div class="headerbar">
                    <h1>Bài học của bạn</h1>
                </div>
                <div class="account-box">
                    <p><%=user.getFullName()%></p>
                </div>
            </div>
            <div class="content-section__body--main">
                <div class="content-box">
                	<% Lesson lesson = (Lesson) request.getAttribute("lesson");
                	%>
                    <div class="content-box__body">
                        <div class="lesson-box">
                            <img src="${pageContext.request.contextPath}/upload/img/<%=lesson.getBannerFilePath() %>" alt="">
                            <div class="lesson-box__overview">
                                <h2><%=lesson.getTitle() %></h2>
                                <p><%=lesson.getDescription() %></p>
                                <form action="../Lesson/delete" method="POST">
                                	<input type="text" name="id" hidden="true" value="<%=lesson.getID() %>">
                                	<input type="text" name="author_id" hidden="true" value="<%=user.getID() %>">
                                	<button class="btn btn--alert" type="submit">Xóa</button>
                                	<button class="btn" type="button" 
                                    onclick="openModal('edit-lesson-modal',{
                                        id :'<%=lesson.getID() %>',
                                        title : '<%=lesson.getTitle() %>',
                                        description : '<%=lesson.getDescription() %>',
                                        file_path : '<%=lesson.getBannerFilePath() %>'
                                    })">Thay đổi</button>
                                </form>
                            </div>
                        </div>
	                    <% 
	                    	ArrayList<Section> sectionList = (ArrayList<Section>) lesson.getSessions();
	                    	for(int i = 0; i < sectionList.size();i++) {
	                    		Section section = sectionList.get(i);
	                    %>
                        <div class="section-box">
                        	<div class="section-box__header">
                        		<h3><%=section.getHeading() %></h3>
                        		<div class="btn-group">
                                	<button class="btn btn--alert" onclick="deleteSection('<%=section.getID() %>')">Xóa</button>
                                	<button class="btn" 
                                    onclick="openModal('edit-section-modal',
                                    {   id :'<%=section.getID() %>',
                                        heading : '<%=section.getHeading() %>'
                                    })">Thay đổi</button>
                                </div>
                        	</div>
                        	
                            <% 
		                    	ArrayList<Content> contentList = (ArrayList<Content>) section.getContents();
		                    	for(int j = 0; j < contentList.size();j++) {
		                    		Content content = contentList.get(j);
		                    %>
                            <div class="section-box__content">
                            	<%
		                    		if (content.getType().equals("txt")) {
                            	%>
                                	<%=content.getText() %>
                                <%	} else if (content.getType().equals("img")) {	%>
                            		<img src="${pageContext.request.contextPath}/upload/img/<%=content.getFilePath() %>" alt="">
                            	<%	} else if (content.getType().equals("vid")) {	%>
                            		<video width="100%" height="" controls>
									    <source src="${pageContext.request.contextPath}/upload/vid/<%=content.getFilePath() %>" type="video/mp4">
									    Your browser does not support the video tag.
									</video>
	                            <%	}
			                    %>
                                <div class="btn-group">
                                	<button class="btn btn--alert" onclick="deleteContent('<%=content.getID() %>')">Xóa</button>
                                	<button class="btn" 
                                    onclick="openModal('content-modal', {
                                        content_id : '<%=content.getID() %>',
                                        type : '<%=content.getType() %>',
                                        content_text : '<%=content.getText() %>',
                                        file_path : '<%=content.getFilePath() %>'
                                    }, '../Content/update')">Thay đổi</button>
                                </div>
                            </div>
							<% 	} %>
                            <button class="section-box__content-create-btn" onclick="openModal('content-modal',{ section_id : '<%=section.getID()%>'})"><img src="${pageContext.request.contextPath}/static/img/plus-solid.svg" alt="+"></button>
                        </div>
		            <%  }	%>
                        <div class="section-box">
                            <h3>Thêm phần mục</h3>
                            <form action="../Section/create" method="POST">
                                <input type="text" name="lesson_id" hidden="true" value="<%=lesson.getID() %>">
                                <input type="text" name="heading" placeholder="Tên đề mục"/>
                                <div class="section-box__btn-group">
                                    <button type="submit" class="btn">Thêm</button>
                                    <button type="reset" class="btn">Hủy</button>
                                </div>
                            </form>
                        </div>
                        
                    </div>
                </div>
                <div class="info-box">
                    <div class="calendar">
                        <h3>Tháng 6, 2024</h3>
                        <div class="calendar__table">
                            <table>
                                <tr>
                                    <th>CN</th>
                                    <th>T2</th>
                                    <th>T3</th>
                                    <th>T4</th>
                                    <th>T5</th>
                                    <th>T6</th>
                                    <th>T7</th>
                                </tr>
                                <tr>
                                    <td>11</td>
                                    <td>11</td>
                                    <td>11</td>
                                    <td>11</td>
                                    <td>11</td>
                                    <td>11</td>
                                    <td>11</td>
                            </table>
                        </div>
                    </div>
                    <div class="leaderboard">
                        
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="hidden modal" id="content-modal">
        <div class="modal__overlay"></div>
        <div class="modal__content">
            <div class="modal__header">
                <h2>Thêm nội dung mới</h2>
                <img src="${pageContext.request.contextPath}/static/img/x-solid.svg" alt="" onclick="closeModal('content-modal')">
            </div>
            
            <form action="../Content/create" method="POST">
                <div class="modal__form-group">
                    <input type="text" name="id" hidden="true">
                    <input type="text" name="section_id" hidden="true">
                    <input type="text" name="lesson_id" hidden="true" value="<%=lesson.getID() %>">
                    <div class="modal__input-group">
                        <label for="type">LOẠI NỘI DUNG</label>
                        <select name="type" id="type">
                            <option value="txt">Văn bản</option>
                            <option value="img">Hình ảnh</option>
                            <option value="vid">Video</option>
                        </select>
                        <div id="text-group">
                            <label for="content_text">NỘI DUNG VĂN BẢN</label>
                            <textarea name="content_text" id="content_text" cols="30" rows="10" placeholder="Đây là nội dung của văn bản"></textarea>
                        </div>
                        <div id="file-group" hidden="true">
                            <label for="file_path">Hình ảnh / Video</label>
                            <input type="text" name="file_path" hidden>
                            <input type="text" id="file_path" placeholder="Đường dẫn của file" readonly>
                            <input type="file" class="file" id="file" readonly hidden>
                            <label for="file" class="btn">Tải lên</label>
                            <progress class="uploadProgress" value="0" max="100"></progress>
                        </div>
                    </div>
                </div>
                <div class="modal__btn-group">
                    <button type="reset" class="btn" onclick="closeModal('content-modal')">Hủy</button>
                    <button type="submit" class="btn">Tạo</button>
                </div>
                <script>
                    document.getElementById('file').addEventListener('change', function() {
                        document.getElementById('file_path').value = this.value;
                    });

                    document.getElementById('type').addEventListener('change', function() {
                        if (this.value == 'txt') {
                            document.getElementById('text-group').hidden = false;
                            document.getElementById('file_path').value = ''; 
                            document.getElementById('file-group').hidden = true;
                        } else {
                            if (this.value == 'img') {
                                document.getElementById('file').accept = "image/*"; 
                            } else {
                                document.getElementById('file').accept = "video/*";
                            }
                            document.getElementById('file-group').hidden = false;
                            document.getElementById('text-group').hidden = true;
                            document.getElementById('content_text').value = '';

                        }
                    });
                </script>
            </form>
    	</div>
    </div>
    
    <div class="hidden modal" id="edit-section-modal">
        <div class="modal__overlay"></div>
        <div class="modal__content">
            <div class="modal__header">
                <h2>Chỉnh sửa nội dung</h2>
                <img src="${pageContext.request.contextPath}/static/img/x-solid.svg" alt="" onclick="closeModal('edit-section-modal')">
            </div>
            <form action="../Section/update" method="POST">
                <div class="modal__form-group">
                    <input type="text" name="id" hidden="true">
                    <input type="text" name="lesson_id" hidden="true" value="<%=lesson.getID() %>">
                    <div class="modal__input-group">
                    	<label for="heading">ĐỀ MỤC</label>
                        <input type="text" name="heading" id="heading" placeholder="Tên đề mục"/>
                    </div>
                </div>
                <div class="modal__btn-group">
                    <button type="reset" class="btn" onclick="closeModal('edit-section-modal')">Hủy</button>
                    <button type="submit" class="btn">Sửa</button>
                </div>
            </form>
    	</div>
    </div>
    
	<div class="hidden modal" id="edit-lesson-modal">
        <div class="modal__overlay"></div>
        <div class="modal__content">
            <div class="modal__header">
                <h2>Chỉnh sửa nội dung</h2>
                <img src="${pageContext.request.contextPath}/static/img/x-solid.svg" alt="" onclick="closeModal('edit-lesson-modal')">
            </div>
            
            <form action="../Lesson/update" method="POST">
                <div class="modal__form-group">
                    <input type="text" name="id" hidden="true">
                    <div class="modal__input-group--img">
                        <img src="${pageContext.request.contextPath}/upload/img/placeholder_banner.png" alt="" id="banner-img">
                        <input type="text" name="file_path" hidden>
                        <div>
	                       <input type="file" class="file" id="banner-file" readonly hidden>
	                       <label for="banner-file" class="btn">Tải lên</label>
                        </div>
                    </div>
                    <div class="modal__input-group">
                        <label for="title">TIÊU ĐỀ</label>
                        <input type="text" name="title" id="title" placeholder="Tiêu đề của bài học" class="input--lg">
                        <label for="description">MÔ TẢ</label>
                        <textarea name="description" id="description" cols="30" rows="10" placeholder="Trong bài học này, bạn sẽ học được ..."></textarea>
                    </div>
                </div>
                <div class="modal__btn-group">
                    <button type="reset" class="btn" onclick="closeModal('edit-lesson-modal')">Hủy</button>
                    <button type="submit" class="btn">Sửa</button>
                </div>
                <script>
                    document.getElementById('banner-file').addEventListener('change', function() {
                        document.getElementById('file_path').value = this.value;
                        document.getElementById('banner-img').src = URL.createObjectURL(this.files[0]);
                    });
                </script>
            </form>
    	</div>
    </div>
    <form action="" method="POST" id="delete-form">
    	<input type="text" name="id" hidden="true">
		<input type="text" name="lesson_id" hidden="true" value="<%=lesson.getID() %>">
    </form>
</body>
<script>
    const contentModal = document.getElementById('content-modal');
    const editLessonModal = document.getElementById('edit-lesson-modal');
    const editSectionModal = document.getElementById('edit-section-modal');
    const deleteForm = document.getElementById('delete-form');
    const CHUNK_SIZE = 1024 * 1024 * 5; //5MB

    function openModal(id_modal, form_data, form_action = null) {
        const modal = document.getElementById(id_modal);
        if (form_action) {
            modal.querySelector('form').action = form_action;
        }
        modal.classList.toggle('hidden');
        for (const [key, value] of Object.entries(form_data)) {
            const inputElement = modal.querySelector('[name="'+key+'"]');
            if (inputElement) {
                inputElement.value = value;
            }
        }
    }

    function closeModal(id_modal) {
        const modal = document.getElementById(id_modal);
        modal.classList.toggle('hidden');
        const inputs = modal.querySelectorAll('[name]');
        inputs.forEach(input => input.value = '');
    }

    function deleteContent(id) {
    	deleteForm.action="../Content/delete";
    	deleteForm.querySelector('input[name="id"]').value = id;
    	deleteForm.submit()
    }
    
    function deleteSection(id) {
    	deleteForm.action="../Section/delete";
    	deleteForm.querySelector('input[name="id"]').value = id;
    	deleteForm.submit()
    }

    function submitFormWithFile(form,type,category) {
        const fileInput = form.querySelector('.file');
        const filePath = form.querySelector('input[name="file_path"]');
        const file = fileInput.files[0];
        const uploadProgress = form.querySelector('.uploadProgress');
        const submitButton = form.querySelector('button[type="submit"]');
        submitButton.disabled = true;

        if (!file) {
            alert('Vui lòng chọn file.');
            return;
        }
            
        const timestamp = Date.now();
        const fileExtension = file.name.split('.').pop();
        fileName = category + '-' + type + '-' + timestamp + '.' + fileExtension;
        filePath.value = fileName;
        
        let currentChunk = 0;
        const totalChunks = Math.ceil(file.size / CHUNK_SIZE)
        
        const uploadChunk = async (start) => {
            const end = Math.min(start + CHUNK_SIZE, file.size);
            const chunk = file.slice(start, end);

            const chunkFormData = new FormData();
            chunkFormData.append('fileName', fileName);
            chunkFormData.append('type',type);
            chunkFormData.append('file', chunk);
            chunkFormData.append('chunkIndex', currentChunk);
            chunkFormData.append('totalChunks', totalChunks);

            try {
                const response = await fetch('../Upload', { method: 'POST',body: chunkFormData });
                if (response.ok) {
                    currentChunk++;
                    const progress = (currentChunk / totalChunks) * 100;
                    uploadProgress.value = progress;
                    if (currentChunk < totalChunks) {
                        uploadChunk(start + CHUNK_SIZE);  // Upload next chunk
                    } else {
                        alert('Upload hoàn tất');
                        submitButton.disabled = false;
                        form.submit();
                    }
                } else {
                    alert('Lỗi trong quá trình upload.');
                    submitButton.disabled = false;
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Có lỗi xảy ra trong quá trình upload.');
                submitButton.disabled = false;
            }
        };
            
        uploadChunk(0);
    }
    
    async function submitContentForm(event) {
        event.preventDefault();
        const form = event.target;
        const type = form.querySelector('select[name="type"]').value;
        const contentText = form.querySelector('textarea[name="content_text"]').value.trim();
        
        const sectionId = form.querySelector('input[name="section_id"]').value;
        
        if (type == 'txt') {
            if (content_text == '') {
                alert('Vui lòng nhập nội dung văn bản.');
                return;
            }
            form.submit();
        } else {
            submitFormWithFile(form,type,'content');
        }
        
    }
    
    contentModal.addEventListener('submit', submitContentForm);
    editLessonModal.addEventListener('submit', (event) => {
        event.preventDefault();
        const form = event.target;
        submitFormWithFile(form,'img','lesson');
    });
</script>
</html>