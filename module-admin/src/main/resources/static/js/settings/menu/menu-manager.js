var onReady = function() {
    $('.menu-disabled').attr('disabled', true);
    $('#include-role option').hide();
};

var includeRole = function() {
    var role = $('#exclude-role option:checked').val();
    $('#exclude-role option[value="' + role + '"]').hide();
    $('#include-role option[value="' + role + '"]').show();
};

var excludeRole = function() {
    var role = $('#include-role option:checked').val();
    $('#exclude-role option[value="' + role + '"]').show();
    $('#include-role option[value="' + role + '"]').hide();
};

var save = function(e) {
    e.preventDefault();

    var url = $('#parentUrl').text() + $('#url').val();
    var formMethod = "post";
    var $frm = $('form[name="frmRegister"]');

    var params = serializeObject({form:$frm[0]}).json();

    var id = $('#id').val();
    if (checkNullOrEmptyValue(id)) {
        formMethod = "put"
    }

    params.url = url;
    params.roleNames = getRoleNames();

    console.log("params", params);

    $.ajax({
        url: "/settings/menu-manager",
        method: formMethod,
        type: "json",
        contentType: "application/json",
        data: JSON.stringify(params),
        success: function (result) {
            location.reload();
        },
        error:function(error){
            ajaxErrorFieldByText(error);
        }
    });
};

var newMenu = function() {
    var $menuPanel = $(".menu-panel");

    if(confirm("하위메뉴 생성시 URI Pattern은 Single로 고정됩니다.")){
        $('.menu-disabled').attr('disabled', false);

        var id = $('#id').val();
        var parentId = $('#parentId').val();
        var parentUrl = $('#url').val();

        menuInit();

        if (checkNullOrEmptyValue(id)) {
            $('#parentId').val(id);
            $('#parentUrl').text('/' + parentUrl + '/');
            $('#id').val('');
        }
    }
};

var menuInit = function() {
    $('#name').val('');
    $('#url').val('');
    $('#icon').val('');
    $("#active").iCheck('check');
    $("#chkShow").iCheck('check');
    $("#pattern-single").iCheck('check');
    $('#parentUrl').text("/");
    $('#menuId').val('');
    $('#parentId').val('');
    $('#sortOrder').val('');
    //초기화
    $('#include-role option').hide();
    $('#exclude-role option').show();
};

var menuDataSet = function(e) {

    menuInit();

    var menuId = $(this).attr("menuId");

    $.get("/settings/menu-manager/menu/" + menuId, function (resp) {
        var body = resp;
        console.log("body", body);

        $('#id').val(body.id);
        $('#name').val(body.name);

        var url = body.url;
        if (body.parent != null) {
            $('#parentUrl').text(body.parent.url + (body.parent.url.length > 1 ? "/" : ""));
            url = url.replace(body.parent.url + (body.parent.url.length > 1 ? "/" : ""), "");
            $('#parentId').val(body.parent.id);
        } else {
            if (url.indexOf("/") === 0) {
                url = url.substr(1);
            }
        }

        $('#url').val(url);
        $('#icon').val(body.iconClass);
        $('#sortOrder').val(body.sortOrder);

        if (body.activeYn === 'Y') {
            $('#active').iCheck('check');
        } else {
            $('#inactive').iCheck('check');
        }

        if (body.displayYn === 'Y') {
            $('#chkShow').iCheck('check');
        } else {
            $('#chkHide').iCheck('check');
        }

        if (body.antMatcherType === 'Single') {
            $('#pattern-single').iCheck('check');
        } else {
            $('#pattern-all').iCheck('check');
        }

        $.each(body.roles, function (idx, role) {
            $('#exclude-role option[value="' + role.name + '"]').hide();
            $('#include-role option[value="' + role.name + '"]').show();
            //$('#selected-role').append('<option value="' + role.roleName + '">' + role.description + '</option>');
        });

        $('.menu-disabled').attr('disabled', false);
    });
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
    .on('click', '.btn-new-menu', newMenu)
    .on('click', '#btn-include-role', includeRole)
    .on('click', '#btn-exclude-role', excludeRole)
    .on('click', '.btn-save', save)
    .on('click', '.menu-tree-panel a', menuDataSet);
