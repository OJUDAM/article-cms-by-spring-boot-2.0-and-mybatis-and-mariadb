function Article__sortChanged(el){
	var $el = $(el);
	var newValue = $el.val();
	
	var newUrl = getNoDomainUrl();
	newUrl = replaceUrlParam(newUrl, 'sort', newValue);
	
	location.href = newUrl;
}