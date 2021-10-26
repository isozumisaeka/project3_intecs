function calendar(){
	// セレクトボックスのidのyear,month,dayを取得し、yearとmonthのvalue値を格納
	const year = document.getElementById('year');
	const yearValue = parseInt(year.value);
	const month = document.getElementById('month');
	const monthValue = parseInt(month.value);
	const day = document.getElementById('day');
	var dayValue = parseInt(day.value);

	// dayのoptionを削除
	while(day.lastChild){
		day.removeChild(day.lastChild);
	}

	/*
	1,3,5,7,8,10,12月は31日
	4,6,9,11月は30日
	2月は28日
	ただし、4の倍数の年は閏年、100の倍数の年は平年、400の倍数の年は閏年
	*/
	maxday = 31;
	switch (monthValue){
		case 4:
		case 6:
		case 9:
		case 11:
			maxday = 30;
			break;

		case 2:
			maxday = 28;
			if(yearValue%4 == 0){
				maxday = 29;
			}
			if (yearValue%100 == 0){
				maxday = 28;
			}
			if(yearValue%400 == 0){
				maxday = 29;
			}
			break;
	}
	// dayのoptionを追加
	for (i=1;i<=maxday;i++) {
		var option = document.createElement('option');
		option.text = i + "日";
		option.value = i;
		day.appendChild(option);
	}

	if(dayValue >= maxday){
		dayValue = maxday;
	}

	day.selectedIndex = dayValue -1;
}