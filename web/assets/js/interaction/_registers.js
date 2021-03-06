/**
 * Register constructor, initializer and translator.
 */

/**
 * Create DOM wrapper that can manipulate registers.
 */
function Register(registerId, input) {
	var div = input.parent();
	var register = {
		id: registerId,
		get isVisible() { return div.is(':visible'); },
		get name() { return div.attr('data-name'); },
		set name(value) { div.attr('data-name', value); },
		get value() { return input.prop('value'); },
		set value(value) { input.prop('value', value); },
		send: function() { input.change(); }
	}; 
	input.change(function() {
		var args, pattern = /^\d+$/;
    	if( !pattern.test(register.value) ) {
    		args = {registerId: registerId, action: "get"};
    		$.get("RegisterAccessor", args, function(actualValue) {
    			register.value = actualValue;
    		});
		}
    	else {
    		args = {registerId: registerId, action: "set", value: register.value};
    		$.get("RegisterAccessor", args, function(correctedValue) {
    			register.value = correctedValue;
    		});
    	}
	});
	return register; 
}

/**
 * Create register wrappers.
 */
function initRegisters() {
	var key,
		names = Mappings.Names.Registers,
		dom = Mappings.Dom.Registers;
	MW.Registers = {};
	for(index in names) {
		key = names[index];
		MW.Registers[key] = Register(key, dom[key]);
	}
}

/**
 * Read registers' state from server.
 */
function restoreRegisters() {
	$.get("RegisterAccessor", {action: "get"}, function(values) {
		var registerId;
		for(registerId in values)
			MW.Registers[registerId].value = values[registerId];
	});
}

/**
 * Change registers' language.
 */
function retranslateRegisters() {
	var key,
		names = Mappings.Names.Registers;
	for(index in names) {
		key = names[index];
		MW.Registers[key].name = MW.Language.Registers[key];
	}
}
