/* 파일 첨부에 관한 스크립트 */

$(document).on('change', '#attach-file', function() { // 첨부 파일 선택시
	var attached = this.files[0];  	// 현재 태그의 0번지 값을 attached 에 할당 
	if ( attached  ) {	// 첨부된 파일이 있을 경우 파일명을 나타내고 delete 이미지도 표시함.
		$('#file-name').text(attached.name);
		$('#delete-file').css('display', 'inline');
		
		// 이미지 파일인 경우 미리보기 (#preview)에 보여지게 처리
		// 태그 존재 여부는 길이 (length)로 판단함 : 0보다 크면 태그가 있다는 것임
		if( $('#preview').length > 0 ){
			if( isImage( attached.name) ){
				$('#preview').html('<img src="" id="preview-img" /> ');
				
				var reader = new FileReader();	//파일 정보를 읽기 위한 객체(FileReader)를 선언
				reader.onload = function(e){	// 파일 읽기 처리
					$('#preview-img').attr('src',e.target.result);
				}
				reader.readAsDataURL ( attached);				
				
			} else
			$('#preview').html('');		//이미지 파일이 아니면 미리보기 처리하지 않음
			
		}
		
	} else {
		$('#file-name').text('');
		$('#delete-file').css('display', 'none');
	}
}).on('click', '#delete-file', function(){ // 첨부 파일 삭제시
	if($('#preview').length >0 )	$('#preview').html('');	
	$('#file-name').text('');					// 파일명 안 보이기
	$('#delete-file').css('display', 'none');	// 삭제 버튼 안 보이기
	 
	$('#attach-file').val('');		// 파일 태그의 첨부된 파일 정보 없애기
})		

/* 이미지 파일 여부 확인 함수*/
function isImage ( filename ){
	// abc.text , abc.png    확장자를 통해 파일 형태를 확인
	var ext = filename.substring( filename.lastIndexOf('.') + 1 ).toLowerCase();
	// 확장자 jpg, png, tiff, gif, 'webp', 'bmp', 'pcx' 
	var imgs = ['jpg', 'png', 'tiff', 'gif', 'webp', 'bmp', 'pcx'];
	
	if(imgs.indexOf(ext) > -1) return true;
	else return false;
	
	
}













						