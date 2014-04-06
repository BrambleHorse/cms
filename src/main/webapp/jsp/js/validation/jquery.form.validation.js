var validatorUtilities = {
    defaults: {
        test: 'none',
        invalid: function () {},
        valid: function () {}
    },
    tests: {
        init: function (data, v, p) {
            try {
                return this[data[0]](v, p, data[1]);
            } catch (e) {
				console.log('"'+data[0]+'" test\'s err')
                return this['blank'](v, p, data[1]);
            }
        },
		none: function(){return true},
        email: function (v) {
            return /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(v);
        },
        blank: function (v, p) {
            return (v !== '' && v !== p);
        },
        minlength: function (v, p, m) {
            return v.length >= m;
        },
        maxlength: function (v, p, m) {
            return v.length <= m || v == p;
        },
        lengthrange: function (v, p, m) {
            r = m.split('-');
            return (v !== p) && (v.length >= r[0] && v.length <= r[1]);
        },
        digits: function (v) {
			 return !(/\D/g.test(v));
        },
        letters: function (v) {
            return !(/\d/g.test(v));
        }
    },
    placeholderAction: function (obj, e) {
        var v = obj.val();
        var p = obj.data('placeholder');
        if (p) {
            if (v == '' && (e && e.type == 'blur')) {
                obj.val(p);
                obj.addClass('placeholded');
            } else {
                if (v == p && (e && e.type == 'focus')) {
                    obj.val('');
                    obj.removeClass('placeholded')
                }
            }
        }
    },
    status: {
        check: function (obj, test, action) {
            if (test) {
                this.valid(obj, action);
            } else {
                this.invalid(obj, action);
            }
            return test;
        },
        valid: function (obj, action) {
            action.valid.call(obj);
            obj.removeClass('invalid');
        },
        invalid: function (obj, action) {
            action.invalid.call(obj);
            obj.addClass('invalid');
            return false;
        }
    }
};
$.fn.validate = function (options) {
    var obj = $(this);
    $(this).each(function (i) {
        var obj = $(this);
        var action = $.extend({}, validatorUtilities.defaults, options);
		if (action.placeholder) {
            obj.attr('data-placeholder', action.placeholder);
            obj.val(action.placeholder).addClass('placeholded');
        }
        $(this).on('blur', function (e) {
            var v = obj.val();
            var p = obj.data('placeholder');
            validatorUtilities.placeholderAction(obj, e);
            if (typeof (action.test) === 'function') {
                validatorUtilities.status.check(obj, action.test.call(obj), action);
            } else {
                if (typeof (action.test) === 'object') {
                    var v = obj.val();
                    var p = obj.data('placeholder');
                    try {
                        var ev = action.test.val();
                        var ep = action.test.data('placeholder');
                        validatorUtilities.status.check(obj, (v && ev && (v == ev)) && (v !== ep), action);
                    } catch (e) {
                        try {
                            validatorUtilities.status.check(obj, action.test.test(v), action);
                        } catch (e) {
                            validatorUtilities.status.check(obj, validatorUtilities.tests.init(['blank'], v, p), action);
                            console.log(e.type, obj)
                        }
                    }
                } else {
                    var testing = action.test.split(' ');
                    for (t = 0; t < testing.length; t++) {
                        if (!validatorUtilities.status.check(obj, validatorUtilities.tests.init(testing[t].match(/([a-zA-Z]+|[\d-]+)/g), v, p), action)) {
							return false;
                        }
                    }
                }
            }
        }).on('focus', function (e) {
            validatorUtilities.placeholderAction(obj, e)
        });
    });
    return this;
};
$.fn.validation = function () {
    var obj = $(this);
    var args = [];
    for (arg in arguments) {
        args.push(arguments[arg].get().reverse())
    }
    $.data(obj, "validate", args);
    $(this).each(function () {
        $(this).click(function () {
            var fields = $.data(obj, "validate");
            var validator = true;
            for (f = 0; f < fields.length; f++) {
                for (c = 0; c < fields[f].length; c++) {
                    var current = $(fields[f][c]);
                    current.blur();
                    if (current.hasClass('invalid')) {
                        validator = false;
                    }
                }
            }
            return validator;
        });
    });
};
(function ($) {
	$(document).ready(function() {
		var forms = $('.validationControls');
		for(j=0; j<forms.length; j++){
			var form = $(forms[j]);		
			var submitBtn = form.find(form.data('submitSelector'));
			var inputs = form.find('[data-val-test]');
			inputs.each(function(i){
				var input = $(this);
				var current = {
					test: $(this).data('valTest'),
					msg: $(this).data('valMsg'),
					placeholder: $(this).data('valPlaceholder'),
					invalid:function(){
						if(current.msg){
							if($('.val'+j+i).length){
								$('.val'+j+i).html(current.msg);
							} else {
								form.prepend('<span class="invalid-msg val'+j+i+'">'+current.msg+'</span>');
							}

						}
					},
					valid: function(){
						$('.val'+j+i).remove();
					}
				};
				input.validate({test:current.test || 'none', placeholder:current.placeholder || '', invalid:current.invalid, valid:current.valid});
			});
			submitBtn.validation(inputs);
		}
	});
})(jQuery);