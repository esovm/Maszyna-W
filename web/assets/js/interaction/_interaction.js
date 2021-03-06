/**
 * Restore W Machine state. Read state from server
 * and fill controls.
 */
function restoreState(wmachine) {
	var restore = function(wmachine) {
		for(registerId in wmachine.registers)
			MW.Registers[registerId].value = wmachine.registers[registerId];
		for(signalId in wmachine.signals)
			MW.Signals[signalId].state = wmachine.signals[signalId];
		var i, cell;
		for(i = 0; i < wmachine.memory.length; ++i) {
			cell = MW.Memory.Cells[i]; 
			cell.value = wmachine.memory[i].value;
			cell.text = wmachine.memory[i].text;
			cell.isVisible = true;
		}
	};
	if(typeof wmachine === "undefined" || wmachine == "") {
		$.get("WMachineState", {action: "restore"}, restore);
	}
	else {
		restore(wmachine);
	}
}

/**
 * Init controls.
 */
function initInteractions() {
	/**
	 * Edit input value if are changes.
	 */
	var editInputValues = function() {
		var input = $('.js-quick-edit'),
	        onlyNumber = $('.js-only-numbers'),
			actualValue;

	    //Clearing text input
		input.focusin(function() {
			var $this = $(this);

			actualValue = $this.val();
	        $this.val('');
	    });

	    //Saving changes if new value
	    input.focusout(function() {
				var $this = $(this);
				
				if($this.val() == '') {
					$this.val(actualValue);
				}
				
				var pattern = /^\d+$/;
				
		    	if( !pattern.test($this.val()) ) {
					$this.val('0');
				}
			});
	    
	    input.change(function() {
	    	var $this = $(this);
			var pattern = /^\d+$/;
			
	    	if( !pattern.test($this.val()) ) {
				$this.val(actualValue);
			}
	    });

	    //Saving changes on click enter button
		input.keypress(function(e) {
	        if (e.keyCode == 13) {
				$(this).blur();
	        }
	    });

	    //accepting only numbers (addtitional keys: '-'' and 'backspace')
	    onlyNumber.keypress(function(e) {
	        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which != 45) {
	            return false;
	        }
	    });
	}

    var shortcut = function () {
        jwerty.key('f7', function () { 
        	$('#run-tact').click();
        });
        
        jwerty.key('f8', function () { 
        	$('#run-command').click();
        });
        
        jwerty.key('f2', function () { 
        	window.location = document.getElementById('SIMULATOR_TAB').href;
        });
        
        jwerty.key('f3', function () {
        	window.location = document.getElementById('FORUM_TAB').href;
        });
        
        jwerty.key('ctrl+shift+1', function () { 
        	window.location = document.getElementById('USER_LOGIN_TAB').href;
        });
        
        jwerty.key('ctrl+shift+2',function () { 
        	window.location = document.getElementById('USER_REGISTER_TAB').href;
        });
    };
    
    editInputValues();
    shortcut();
};