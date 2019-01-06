function getDataAndCreateDonuts() {
	var testArray = [
		{pos: parseInt($('#T442s').val()), neg: parseInt($('#T442t').val()), label: "4-4-2"},
		{pos: parseInt($('#T424s').val()), neg: parseInt($('#T424t').val()), label: "4-2-4"},
		{pos: parseInt($('#T343s').val()), neg: parseInt($('#T343t').val()), label: "3-4-3"},
		{pos: parseInt($('#T433s').val()), neg: parseInt($('#T433t').val()), label: "4-3-3"},
		{pos: parseInt($('#T532s').val()), neg: parseInt($('#T532t').val()), label: "5-3-2"},
		{pos: parseInt($('#T352s').val()), neg: parseInt($('#T352t').val()), label: "3-5-2"},
		{pos: parseInt($('#T451s').val()), neg: parseInt($('#T451t').val()), label: "4-5-1"},
		{pos: parseInt($('#T4231s').val()), neg: parseInt($('#T4231t').val()), label: "4-2-3-1"},
		{pos: parseInt($('#T4321s').val()), neg: parseInt($('#T4321t').val()), label: "4-3-2-1"},
		{pos: parseInt($('#T4141s').val()), neg: parseInt($('#T4141t').val()), label: "4-1-4-1"},
		{pos: parseInt($('#T3313s').val()), neg: parseInt($('#T3313t').val()), label: "3-3-1-3"},
		{pos: parseInt($('#T4222s').val()), neg: parseInt($('#T4222t').val()), label: "4-2-2-2"},
		{pos: parseInt($('#T541s').val()), neg: parseInt($('#T541t').val()), label: "5-4-1"},
		{pos: parseInt($('#T334s').val()), neg: parseInt($('#T334t').val()), label: "3-3-4"},
		{pos:  parseInt($('#T460s').val()), neg: parseInt($('#T460t').val()), label: "4-6-0"}
	];

	testArray.forEach(function (array) {
		if(array.pos > 0 || array.neg > 0){
			createDonuts(array.pos, array.neg, array.label);
		}
	});
}

//use this method with a foreach loop for an array of a class that contains (pos value, neg, value and system label
//pos and neg values are either scored/received goals or win/lose
/*
|pos|neg|label|
---------------
|pos|neg|label|
---------------
|pos|neg|label|
 */
function createDonuts(pos, neg, label) {
	var percentageRed = (neg / (pos + neg) * 100);
	var percentageGreen = 100 - percentageRed;
	//todo: div size etc.
	$('.donut-charts').append('<div class="small-12 medium-6"><p class="donut-label">' + label + '</p><svg width="100%" height="100%" viewBox="0 0 42 42" class="donut">' +
		'<circle cx="21" cy="21" r="15.91549430918954" fill="#fff"></circle>' +
		'<circle class="pos" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#66ff66" stroke-width="3"></circle>' +
		'<circle class="neg" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#ff5050" stroke-width="3" stroke-dasharray="' + percentageRed + ' ' + percentageGreen + '" stroke-dashoffset="25"></circle>' +
		'<g class="chart-text">' +
		'<text x="50%" y="50%" class="chart-number1">' + pos + '</text>' +
		'<text x="50%" y="50%" class="chart-number2">' + neg + '</text>' +
		'</g>' +
		'</svg></div>');
}
