$(document).ready(function() {
	$('#강아지').addClass('clicked');
	$('#사료').addClass('clicked');
	$('#전체').addClass('clicked');


	$('.filter1').click(function() {
		// 선택된 버튼의 클래스에 clicked 추가
		$(this).addClass('clicked');
		// 다른 filter1 클래스 버튼들은 clicked 해제
		$('.filter1').not(this).removeClass('clicked');
		updateSubtypeButtons();
		updateFilter();

	});

	$('.filter2').click(function() {
		// 선택된 버튼의 클래스에 clicked 추가
		$(this).addClass('clicked');
		// 다른 filter2 클래스 버튼들은 clicked 해제
		$('.filter2').not(this).removeClass('clicked');
		updateSubtypeButtons();
		updateFilter();

	});

	// 동적으로 생성된 filter3 버튼에 이벤트 바인딩
	$(document).on('click', '.filter3', function() {
		$(this).addClass('clicked');
		$('.filter3').not(this).removeClass('clicked');
		updateFilter();
	});


	const subtypeButtons = {
		dog: {
			food: ['전체', '습식사료', '소프트사료', '건식사료'],
			snack: ['전체', '수제간식', '껌', '사시미-육포'],
			item: ['전체', '배변용품', '장난감']
		},
		cat: {
			food: ['전체', '주식캔', '파우치', '건식사료'],
			snack: ['전체', '간식캔', '동결건조', '스낵'],
			item: ['전체', '낚시대-레이져', '스크래쳐-박스']
		}
	};

	function updateSubtypeButtons() {
		// 현재 선택된 버튼 확인
		const isDogActive = $('#강아지').hasClass('clicked');
		const isCatActive = $('#고양이').hasClass('clicked');
		const isFoodActive = $('#사료').hasClass('clicked');
		const isSnackActive = $('#간식').hasClass('clicked');
		const isItemActive = $('#용품').hasClass('clicked');

		// 세부 버튼 초기화
		$('#category2').empty();

		// 조건에 따라 세부 버튼 추가
		if (isDogActive) {
			if (isFoodActive) {
				addSubtypeButtons(subtypeButtons.dog.food);
			} else if (isSnackActive) {
				addSubtypeButtons(subtypeButtons.dog.snack);
			} else if (isItemActive) {
				addSubtypeButtons(subtypeButtons.dog.item);
			}
		} else if (isCatActive) {
			if (isFoodActive) {
				addSubtypeButtons(subtypeButtons.cat.food);
			} else if (isSnackActive) {
				addSubtypeButtons(subtypeButtons.cat.snack);
			} else if (isItemActive) {
				addSubtypeButtons(subtypeButtons.cat.item);
			}
		}

		// 첫 번째 버튼 ('전체')을 기본으로 활성화
		$('#category2 button').first().addClass('clicked');
	}

	function addSubtypeButtons(buttons) {
		buttons.forEach(buttonText => {
			$('#category2').append(`<button type="button" class="filter3" id="${buttonText}">${buttonText}</button>`);
		});
	}


	// 필터 버튼 클릭 시 체크박스 또는 라디오 버튼 토글
	$('.filter-button').click(function() {
		$(this).next('.filter-options').toggle(); // 다음 요소(체크박스 또는 라디오 버튼)를 토글
	});

	$('.filter-category').hide();

	function updateFilter() {
		$('input[type="checkbox"], input[type="radio"]').prop('checked', false); // 체크 해제
		$('.filter-category').hide();

		const isDogActive = $('#강아지').hasClass('clicked');
		const isCatActive = $('#고양이').hasClass('clicked');
		const isFoodActive = $('#사료').hasClass('clicked');
		const isSnackActive = $('#간식').hasClass('clicked');
		const isItemActive = $('#용품').hasClass('clicked');
		const isDPotty = $('#배변용품').hasClass('clicked');
		const isDToy = $('#장난감').hasClass('clicked');
		const isCToy = $('#낚시대-레이져').hasClass('clicked');
		const isCBox = $('#스크래쳐-박스').hasClass('clicked');

		if (isDogActive) {
			if (isFoodActive) {
				$('#dogoption1-filter').show();
				$('#dogoption2-filter').show();
			} else if (isSnackActive) {
				$('#dogoption1-filter').show();
				$('#dogoption2-filter').show();
			} else if (isItemActive) {
				if (isDPotty) {
					$('#dogitem-filter').show();
				} else if (isDToy) {
					$('#dogsound-filter').show();
				}
			}
		} else if (isCatActive) {
			if (isFoodActive) {
				$('#catoption1-filter').show();
				$('#catoption2-filter').show();
			} else if (isSnackActive) {
				$('#catoption1-filter').show();
				$('#catoption2-filter').show();
			} else if (isItemActive) {
				if (isCToy) {
					$('#cattoy-filter').show();
				} else if (isCBox) {
					$('#catbox-filter').show();
				}
			}
		}
		$('#price-filter').show();
		$('#ranking-filter').show();
	}


	$('#resetbtn').click(function() {
	      // 모든 체크박스 해제
	      $('input[type="checkbox"]').prop('checked', false);
	      
	      // 모든 라디오 버튼 해제
	      $('input[type="radio"]').prop('checked', false);
	      
	      
	      // 추가로 필요하다면 여기에 필터 옵션을 초기화하는 코드를 추가
	  });
	  
	  $('#showbtn').click(function() {
	         $('#filteroption').slideToggle(); // #filteroption을 슬라이드로 토글
	     });

});


