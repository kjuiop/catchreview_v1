var onReady = function() {
};

var search = function(e) {
    var $frm = $("form[name='frmSearch']");
    $frm.find("input[name='size']").val($("#limit :selected").val());
    $frm.find("input[name='page']").val(0);
    $frm.submit();
};

var reset = function(e) {

    var $frm = $("form[name='frmSearch']");
    $frm.find("input[name='page']").val(0);
    $frm.find("input[name='size']").val(10);
    $frm.find("input[name='username']").val('');
    $frm.find("input[name='name']").val('');
    $frm.find("input[name='userStatus']").val('');
    $frm.submit();
};

$(document).ready(onReady)
    .on('click', '#btnReset', reset)
    .on('click', '#btnSearch', search)
    .on('change', '#limit', search)
    .on('ifToggled', '.chkAll', selectedChkAll)
    .on('ifToggled', 'input[name=numbers]', selectedChkBox);
