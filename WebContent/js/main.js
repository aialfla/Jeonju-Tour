
	$(document).on('click', "input[name='age']", function(){
	    if(this.checked) {
	        const checkboxes = $("input[name='age']");
	        for(let ind = 0; ind < checkboxes.length; ind++){
	            checkboxes[ind].checked = false;
	        }
	        this.checked = true;
	    } else {
	        this.checked = false;
	    }
	});
	$(document).on('click', "input[name='head']", function(){
	    if(this.checked) {
	        const checkboxes = $("input[name='head']");
	        for(let ind = 0; ind < checkboxes.length; ind++){
	            checkboxes[ind].checked = false;
	        }
	        this.checked = true;
	    } else {
	        this.checked = false;
	    }
	});
	$(document).on('click', "input[name='period']", function(){
	    if(this.checked) {
	        const checkboxes = $("input[name='period']");
	        for(let ind = 0; ind < checkboxes.length; ind++){
	            checkboxes[ind].checked = false;
	        }
	        this.checked = true;
	    } else {
	        this.checked = false;
	    }
	});
	$(document).on('click', "input[name='prps']", function(){
	    if(this.checked) {
	        const checkboxes = $("input[name='prps']");
	        for(let ind = 0; ind < checkboxes.length; ind++){
	            checkboxes[ind].checked = false;
	        }
	        this.checked = true;
	    } else {
	        this.checked = false;
	    }
	});