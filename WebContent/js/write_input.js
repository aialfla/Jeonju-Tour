$(function() {
	
})
function day1_del( obj )
{	
	var t_ul = $(obj).parents(".tour_add_here");	
	if(confirm("이 일정을 삭제하시겠습니까?")==1) {
		
		$(obj).parent().parent().parent().parent().parent().remove();
	}
	// 번호들 다시 넘버링
	$(t_ul).find("div.tnum").each(function(index,item){
		$(this).find("span").text(++index);
		$(this).parents(".tour_box").find("#sorder").val(index);
	});
	return;
}
