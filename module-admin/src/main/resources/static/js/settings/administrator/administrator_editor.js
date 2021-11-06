var onReady = function() {
    var $frm = $('form[name="frmRegister"]');
    console.log("dto", dto);
    loadRole();
    onlyNumberKeyEvent({className: "only-number"});
    isModify($frm, 'adminId') ? updateValidate($frm) : createValidate($frm);
}

var createValidate = function($frm) {

    $.validator.setDefaults({
        onkeyup:false,
        onclick:false,
        onfocusout:false,
        showErrors: function(errorMap, errorList) {
            if (errorList.length) {
                jQueryErrorField(errorList);
            }
        }
    });

    $.validator.addMethod("isEmailDuplicateCheck", function(value, element){
        var isDuplication = $('#emailCheckYn').val();

        console.log("duplication", isDuplication);
        return isDuplication;
    });

    $.validator.addMethod("isPwValidCheckYn", function(value, element){
        var isValid = $('#pwValidCheckYn').val();

        console.log("isValid", isValid);
        return isValid;
    });

    $.validator.addMethod("isPwEqualCheckYn", function(value, element){
        var isValid = $('#pwEqualCheckYn').val();

        console.log("isValid", isValid);
        return isValid;
    });

    $frm.validate({
        debug : true,
        ignore : '.valid-ignore, *:not([name])',
        rules: {
            username: {
                required: true,
                isEmailDuplicateCheck: false
            },
            name: {
                required: true
            },
            password: {
                required: true,
            },
            confirmPassword: {
                required: true,
            }
        },
        messages: {
            username: {
                required: '이메일을 입력해주세요.',
                isEmailDuplicateCheck: '이미 사용중인 이메일입니다.'
            },
            name: {
                required: '이름을 입력해주세요.'
            },
            password: {
                required: '비밀번호를 입력해주세요.',
            },
            confirmPassword: {
                required: '비밀번호를 입력해주세요.',
            }
        },
        submitHandler: function($frm) {
            save($frm);
        }
    });
}

var updateValidate = function($frm) {

    $.validator.setDefaults({
        onkeyup:false,
        onclick:false,
        onfocusout:false,
        showErrors: function(errorMap, errorList) {
            if (errorList.length) {
                jQueryErrorField(errorList);
            }
        }
    });

    $.validator.addMethod("isEmailDuplicateCheck", function(value, element){
        var isDuplication = $('#emailCheckYn').val();

        console.log("duplication", isDuplication);
        return isDuplication;
    });

    $frm.validate({
        debug : true,
        ignore : '.valid-ignore, *:not([name])',
        rules: {
            username: {
                required: true,
                isEmailDuplicateCheck: false
            },
            name: {
                required: true
            }
        },
        messages: {
            username: {
                required: '이메일을 입력해주세요.',
                isEmailDuplicateCheck: '이미 사용중인 이메일입니다.'
            },
            name: {
                required: '이름을 입력해주세요.'
            }
        },
        submitHandler: function($frm) {
            save($frm);
        }
    });
}

var loadRole = function($frm) {

    if (!checkNullOrEmptyValue(dto)) {
        return;
    }

    if (dto) {
        $.each(dto.roles, function (idx, role) {
            $('#exclude-role option[value="' + role + '"]').hide();
            $('#include-role option[value="' + role + '"]').show();
        });
    }
}

var addRole = function(e) {
    e.preventDefault();

    var role = $('#exclude-role option:checked').val();
    $('#exclude-role option[value="' + role + '"]').hide();
    $('#include-role option[value="' + role + '"]').show();
}

var removeRole = function(e) {
    e.preventDefault();

    var role = $('#include-role option:checked').val();
    $('#exclude-role option[value="' + role + '"]').show();
    $('#include-role option[value="' + role + '"]').hide();
};

var save = function($frm) {

    var formMethod = isModify($frm, 'adminId') ? 'put' : 'post';
    var param = serializeObject({form:$frm[0]}).json();
    param['roleNames'] = getRoleNames();
    console.log("params", param);

    $.ajax({
        url: "/settings/admin-manager",
        method: formMethod,
        type: "json",
        contentType: "application/json",
        data: JSON.stringify(param),
        success: function (result) {
            var message = isModify($frm, 'adminId') ? '정상적으로 수정되었습니다.' : '정상적으로 저장되었습니다.';
            twoBtnModal(message, function() {
                location.href = '/settings/admin-manager/' + result + '/edit';
            });
        },
        error:function(error){
            ajaxErrorFieldByText(error);
        }
    });
}

var checkDuplicateData = function(e) {
    e.preventDefault();

    if (dto.adminId != null) {
        return false;
    }

    var $field = $(this);
    var value = $field.val();

    if (!checkEmailValidCheck(value)) {
        return;
    }

    if (checkNullOrEmptyValue(value)) {
        $.ajax({
            url: "/settings/admin-manager/check-duplicate/username/" + value,
            method: "get",
            type: "json",
            contentType: "application/json",
            success: function(result) {

                var isDuplicate = result;

                console.log("isDuplicate", isDuplicate);

                $field.siblings('.error-message').remove();
                if (isDuplicate) {
                    drawErrorMessage($field, '이미 존재하는 이메일 입니다.');
                    $('#emailCheckYn').val(false);
                } else {
                    drawSuccessMessage($field, '사용가능한 이메일 입니다.');
                    $('#emailCheckYn').val(true);
                }
            },
            error: function(error){
                ajaxErrorFieldByText(error);
            }
        })
    }
};

const checkValidPassword = function() {

    const $frm = $('form[name=frmRegister]');
    var $password = $frm.find('input[name="password"]');
    var $field = $('input[name="confirmPassword"]');

    var password = $frm.find('input[name="password"]').val();
    var repeat = $frm.find('input[name="confirmPassword"]').val();

    if (!checkNullOrEmptyValue(password)) {
        return false;
    }

    if (password.length < 6) {
        $('#pwValidCheckYn').val(false);
        drawErrorMessage($password, '6자리 이상의 미빌번호를 입력해주세요.');
        return false;
    } else {
        $('#pwValidCheckYn').val(true);
    }

    if (password !== repeat) {
        $('#pwEqualCheckYn').val(false);
        drawErrorMessage($field, '동일한 비밀번호가 아닙니다.');
        return false;
    } else {
        $('#pwEqualCheckYn').val(true);
        drawSuccessMessage($field, '비밀번호가 동일합니다.');
        return false;
    }

};

var getRoleNames = function() {
    var roleNames = [];

    if ($('#include-role').length > 0) {
        var isEmptyRole = true;

        $('#include-role option').filter(function () {
            return $(this).css('display') === 'block';
        }).each(function (idx, role) {
            roleNames.push($(role).val());
            isEmptyRole = false;
        });

    }

    return roleNames;
}

$(document).ready(onReady)
    .on('blur', 'input[name=username]', checkDuplicateData)
    .on('click', '#btn-include-role', addRole)
    .on('click', '#btn-exclude-role', removeRole)
    .on('blur', 'input[name=confirmPassword]', checkValidPassword);
