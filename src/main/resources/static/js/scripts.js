//define caching mechanism for speed up static located DOM selectors
function selectorCache() {
    var collection = {};

    function getFromCache(selector) {
        if (undefined === collection[selector]) {
            collection[selector] = $(selector);
        }
        return collection[selector];
    }

    return {get: getFromCache};
}

const cache = new selectorCache();
const min_length = 1;
const max_length = 255;
const positions_selects = "#add_position_sel, #search_position_sel";

function initializeDialogs() {
    cache.get("#ok-message").dialog({
        autoOpen: false
    });
    cache.get("#fail-message").dialog({
        autoOpen: false
    });
}


function alertOk() {
    //noinspection JSUnusedGlobalSymbols
    cache.get("#ok-message").dialog({
        modal: true,
        buttons: {
            Ok: function () {
                cache.get(this).dialog("close");
            }
        },
        autoOpen: true
    });
}

function alertFail() {
    //noinspection JSUnusedGlobalSymbols
    cache.get("#fail-message").dialog({
        modal: true,
        buttons: {
            Ok: function () {
                cache.get(this).dialog("close");
            }
        },
        autoOpen: true
    });
}

function validation() {
    cache.get(this).val($.trim(cache.get(this).val()));
    return true;
}

function bindValidators() {
    cache.get("#tabs").tabs();
    cache.get("#form-1").validate({
        rules: {
            add_last_name: {
                required: {
                    depends: validation
                },
                minlength: min_length,
                maxlength: max_length
            },
            add_first_name: {
                required: {
                    depends: validation
                },
                minlength: min_length,
                maxlength: max_length
            },
            add_middle_name: {
                required: {
                    depends: validation
                },
                minlength: min_length,
                maxlength: max_length
            },
            add_birth_date: {
                required: {
                    depends: validation
                },
                minlength: min_length,
                maxlength: max_length
            },
            add_position_sel: {
                required: {
                    depends: validation
                },
                minlength: min_length,
                maxlength: max_length
            },
            messages: {
                add_last_name: "Required field"
            }
        }
    });
    cache.get("#form-2").validate({
        rules: {
            add_position_field: {
                required: {
                    depends: validation
                },
                minlength: min_length,
                maxlength: max_length
            },
            messages: {
                add_last_name: "Required field"
            }
        }
    });
}

function getYesterday() {
    var yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    var day = ("0" + yesterday.getDate()).slice(-2);
    var month = ("0" + (yesterday.getMonth() + 1)).slice(-2);
    return yesterday.getFullYear() + "-" + (month) + "-" + (day);
}

function fillDates() {
    var date = getYesterday();
    cache.get("#add_birth_date").val(date).attr("max", date);
}

function bindButtons() {
    cache.get("#add_employee_btn").button().click(function () {
        if (cache.get("#form-1").valid()) {
            addEmployee(
                cache.get("#add_first_name").val(),
                cache.get("#add_last_name").val(),
                cache.get("#add_middle_name").val(),
                cache.get("#add_birth_date").val(),
                cache.get("#add_position_sel").find(":selected").attr("id")
            );
        }
    });
    cache.get("#add_position_btn").button().click(function () {
        if (cache.get("#form-2").valid()) {
            addPosition(cache.get("#add_position_field").val());
        }
    });
    cache.get("#search_employee_btn").button().click(function () {
        searchEmployee(
            cache.get("#search_first_name").val(),
            cache.get("#search_last_name").val(),
            cache.get("#search_middle_name").val(),
            cache.get("#search_birth_date").val(),
            cache.get("#search_position_sel").find(":selected").attr("id")
        );
    });
}

function clearTable() {
    cache.get("#search_results").find("tbody").empty();
}

function addEmployeeRow(value) {
    var tbody = cache.get("#search_results").find("tbody");
    tbody.append(
        "<tr>" +
        "<td>" + value["lastName"] + "</td>" +
        "<td>" + value["firstName"] + "</td>" +
        "<td>" + value["middleName"] + "</td>" +
        "<td>" + value["birthDate"] + "</td>" +
        "<td>" + value["position"] + "</td>" +
        "</tr>"
    );
}

function fillTableWithResults(searchResult) {
    $.each(searchResult, function (key, value) {
        addEmployeeRow(value);
    });
}

function searchEmployee(firstName, lastName, middleName, birthDate, positionId) {
    var request = {
        firstName: firstName ? firstName : null,
        lastName: lastName ? lastName : null,
        middleName: middleName ? middleName : null,
        birthDate: birthDate ? birthDate : null,
        positionId: positionId ? positionId : null
    };
    $.ajax({
        url: "/search",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(request)
    }).done(function (searchList) {
        clearTable();
        fillTableWithResults(searchList);
    }).fail(function () {
        alertFail();
    });
}

function addPositionToSelects(value) {
    var selects = cache.get(positions_selects);
    selects.append($("<option></option>")
        .attr("value", value["name"])
        .attr("id", value["positionId"])
        .text(value["name"]));
}

function disableSelects() {
    var selects = cache.get(positions_selects);
    selects.attr("disabled", "disabled");
}

function enableSelects() {
    var selects = cache.get(positions_selects);
    selects.removeAttr("disabled");
}

function fillPositions() {
    disableSelects();
    $.ajax({
        url: "/getpos",
        type: "GET"
    }).done(function (data) {
        $.each(data, function (key, value) {
            addPositionToSelects(value);
        });
    }).fail(function () {
        alertFail();
    }).always(function () {
        enableSelects();
    });
}

function addPosition(positionName) {
    $.ajax({
        url: "/addpos",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify({name: positionName})
    }).done(function (positionId) {
        addPositionToSelects({
            name: positionName,
            positionId: positionId["id"]
        });
        alertOk();
    }).fail(function () {
        alertFail();
    });
}

function addEmployee(firstname, lastname, middlename, birthdate, positionId) {
    $.ajax({
        url: "/addemp",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            firstName: firstname,
            lastName: lastname,
            middleName: middlename,
            birthDate: birthdate,
            positionId: positionId
        })
    }).done(function () {
        alertOk();
    }).fail(function () {
        alertFail();
    });
}

cache.get(document).ready(function () {
    bindValidators();
    bindButtons();
    fillDates();
    initializeDialogs();
    fillPositions();
});