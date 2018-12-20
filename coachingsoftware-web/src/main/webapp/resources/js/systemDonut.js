function getDataAndCreateDonuts() {
	var testArray = [
		{pos: $('#T442s').val(), neg: $('#T442t').val(), label: "4-4-2"},
		{pos: $('#T424s').val(), neg: $('#T424t').val(), label: "4-2-4"},
		{pos: $('#T343s').val(), neg: $('#T343t').val(), label: "3-4-3"},
		{pos: $('#T433s').val(), neg: $('#T433t').val(), label: "4-3-3"},
		{pos: $('#T532s').val(), neg: $('#T532t').val(), label: "5-3-2"},
		{pos: $('#T352s').val(), neg: $('#T352t').val(), label: "3-5-2"},
		{pos: $('#T451s').val(), neg: $('#T451t').val(), label: "4-5-1"},
		{pos: $('#T4231s').val(), neg: $('#T4231t').val(), label: "4-2-3-1"},
		{pos: $('#T4321s').val(), neg: $('#T4321t').val(), label: "4-3-2-1"},
		{pos: $('#T4141s').val(), neg: $('#T4141t').val(), label: "4-1-4-1"},
		{pos: $('#T3313s').val(), neg: $('#T3313t').val(), label: "3-3-1-3"},
		{pos: $('#T4222s').val(), neg: $('#T4222t').val(), label: "4-2-2-2"},
		{pos: $('#T541s').val(), neg: $('#T541t').val(), label: "5-4-1"},
		{pos: $('#T334s').val(), neg: $('#T334s').val(), label: "3-3-4"},
		{pos: $('#T460s').val(), neg: $('#T460t').val(), label: "4-6-0"}
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
	var percentageRed = Math.round((neg / (pos + neg) * 100));
	var percentageGreen = 100 - percentageRed;
	//todo: div size etc.
	$('.donut-charts').append('<div><p class="donut-label">' + label + '</p><svg width="100%" height="100%" viewBox="0 0 42 42" class="donut">' +
		'<circle cx="21" cy="21" r="15.91549430918954" fill="#fff"></circle>' +
		'<circle class="pos" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#66ff66" stroke-width="3"></circle>' +
		'<circle class="neg" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#ff5050" stroke-width="3" stroke-dasharray="' + percentageRed + ' ' + percentageGreen + '" stroke-dashoffset="25"></circle>' +
		'<g class="chart-text">' +
		'<text x="50%" y="50%" class="chart-number1">' + pos + '</text>' +
		'<text x="50%" y="50%" class="chart-number2">' + neg + '</text>' +
		'</g>' +
		'</svg></div>');
}
