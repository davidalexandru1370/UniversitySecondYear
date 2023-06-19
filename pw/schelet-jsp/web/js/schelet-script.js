let usernameCurrent;
let headersEntities = ['id', 'name', 'date']; // same as the headers from the db
let pageSize = 5;
let currentPage = 0;
let maxPages;

/**
 * Helper function to read a cookie.
 * @type {string}
 */
function readCookie(name) {
    let allCookies = document.cookie;

    // Get all the cookies pairs in an array
    let cookieArray = allCookies.split(';');

    // Now take key value pair out of this array
    for (let i = 0; i < cookieArray.length; i++) {
        let cookieName = cookieArray[i].split('=')[0];
        let cookieValue = cookieArray[i].split('=')[1];
        if(cookieName === name){
            return cookieValue;
        }
    }

    return '';
}

function getTodayDate(){
    /**
     * Helper function to get today's date.
     * @type {Date}
     */
    const today =  new Date();
    const day = today && today.getDate() || -1;
    const dayWithZero = day.toString().length > 1 ? day : '0' + day;
    const month = today && today.getMonth() + 1 || -1;
    const monthWithZero = month.toString().length > 1 ? month : '0' + month;
    const year = today && today.getFullYear() || -1;

    return `${year}-${monthWithZero}-${dayWithZero}`;
}

// INSERT DATA IN THE TABLE
const insertDataTable = (table, headers, data) => {
    table.empty();
    let content = '';

    if(data.length == 0) {
        alert("You don't have any!")
    }

    // --- add headers
    content += '<tr>';
    for(let header of headers) {
        content += `<th>${header}</th>`;
    }
    //content += '<th>Select</th>'
    content += '</tr>';

    // --- add body
    for (let entity of data) {
        content += '<tr>';
        for (let index of headers) {
            content += `<td>${entity[index]}</td>`;
        }
        //content += `<td><button onclick=selectedRow(${entity.id})>Select me</button></td>`;
        content += '</tr>';
    }

    table.append(content);
}


// INSERT DATA IN THE LIST
const insertDataList = (list, headers, data) => {
    list.empty();
    let content = '';

    if(data.length == 0) {
        alert("You don't have any!")
    }

    for (let entity of data) {
        content += '<li>';
        for (let index of headers) {
            content += `${index}: ${entity[index]}   `;
        }
        //content += `<button onclick=selectedRow(${entity.id})>Select me</button>`;
        content += '</li>';
    }

    list.append(content);
}

// FROM HERE DOWN FOR PAGINATION
const insertDataPaginated = (table, headers, data) => {
    table.empty();
    let content = '';

    if(data.length == 0) {
        alert("You don't have any!")
    }

    // --- add headers
    content += '<tr>';
    for(let header of headers) {
        content += `<th>${header}</th>`;
    }
    //content += '<th>Select</th>'
    content += '</tr>';

    // --- add body
    for (let entity of data) {
        content += '<tr>';

        // start to fill page when condition is true
        if (data.indexOf(entity) >= pageSize * currentPage) {
            for (let index of headers) {
                content += `<td>${entity[index]}</td>`;
            }
            //content += `<td><button onclick=selectedRow(${entity.id})>Select me</button></td>`;
            content += '</tr>';
        }

        // stop filling when condition is true
        if (data.indexOf(entity) >= pageSize * currentPage + (pageSize-1)) {
            break;
        }

    }

    // get max pages
    maxPages = Math.floor(data.length / pageSize);
    if (data.length % pageSize != 0)
        maxPages = maxPages + 1;

    checkDisabledButtons();

    table.append(content);
}


function next() {
    currentPage++;
    get();
}

function prev() {
    if (currentPage > 0) {
        currentPage--;
    }
    get();
}

function checkDisabledButtons() {
    if (currentPage <= 0) {
        $('#previousButton').prop("disabled", true);
    } else {
        $('#previousButton').prop("disabled", false);
    }

    if (currentPage >= maxPages - 1) {
        $('#nextButton').prop("disabled", true);
    } else {
        $('#nextButton').prop("disabled", false);
    }
}


// --- CRUD OPERATIONS --
/**
 * Get entities
 */
function get(){
    event.preventDefault();
    let section = $('#get-section');
    let table = $('#table');
    let tablePaginated = $('#table-paginated');
    let tableFilter = $('#table-filter');
    let list=$('#list');

    $.ajax( {
        type: "GET",
        url: "SkeletonController",
        data: {
            // pass here what you need to pass
            action: "get",
        },
        success:  (response) => {
            response = JSON.parse(response);

            section.css("display", "block");
            insertDataTable(table, headersEntities, response);
            insertDataPaginated(tablePaginated, headersEntities, response);
            insertDataTable(tableFilter, headersEntities, response);
            insertDataList(list, headersEntities, response);

        }
    }).fail(console.error);
}

/**
 * Add entities
 */
function add(){
    event.preventDefault();
    let name = $('#name').val();
    let date = $('#date').val();
    // let date = getTodayDate();

    if(name === "" || date===""){
        alert("Wrong input!");
        return;
    }

    $.ajax( {
        type: "POST",
        url: "SkeletonController",
        data: {
            // pass here what you need to pass
            action: "add",
            name: name,
            date: date
        },
        success:  (response) => {
            if (response) {
                alert("It was added!");
            } else {
                alert("Oops...something went wrong!");
            }
        }
    }).fail(console.error);
}

/**
 * Delete entities
 */
function populateDropdown(dropdown, data) {
    dropdown.empty();

    for (let i = 0; i < data.length; i++) {
        dropdown.append('<option value="' + data[i].id + '">' + data[i].name + '</option>');
    }
}

function myDelete(){
    let id = $('#dropdown').val();
    $.ajax( {
        type: "POST",
        url: "SkeletonController",
        data: {
            // pass here what you need to pass
            action: "delete",
            id: id,
        },
        success:  (response) => {
            if (response) {
                alert("It was deleted!");
            } else {
                alert("Oops...something went wrong!");
            }
        }
    }).fail(console.error);
}

function showDeleteSection(){
    let section = $('#delete-section')

    // -- get for dropdown
    $.ajax( {
        type: "GET",
        url: "SkeletonController",
        data: {
            // pass here what you need to pass
            action: "get",
        },
        success:  (response) => {
            response = JSON.parse(response);
            let dropdownDelete = $('#dropdown');
            populateDropdown(dropdownDelete, response);
            section.css("display", "block");
        }
    }).fail(console.error);
}

/**
 * Update entities
 */
function showUpdateSection(){
    let section = $('#update')

    // -- get for dropdown
    $.ajax( {
        type: "GET",
        url: "SkeletonController",
        data: {
            // pass here what you need to pass
            action: "get",
        },
        success:  (response) => {
            response = JSON.parse(response);
            let dropdownUpdate = $('#dropdown2');
            populateDropdown(dropdownUpdate, response);
            section.css("display", "block");
        }
    }).fail(console.error);
}

function showUpdateForm(){
    let section = $('#update-section');
    section.css("display", "block");
    $('#id').empty();
    $('#id').append($('#dropdown2').val())
}

function update(){
    event.preventDefault();
    let id = $('#id').text();
    let name = $('#name2').val();
    let date = $('#date2').val();
    if(name === "" || date==""){
        alert("Wrong input!");
        return;
    }

    $.ajax( {
        type: "POST",
        url: "SkeletonController",
        data: {
            // pass here what you need to pass
            action: "update",
            id: id,
            name: name,
            date: date
        },
        success:  (response) => {
            if(response){
                alert("It was updated!");
                $('#update-section').css("display", "none");
            } else {
                alert("Oops...something went wrong!");
            }
        }
    }).fail(console.error);
}


/**
 * Filter entities
 */
function showFilterSection(){
    let section = $('#filter-section')

    // -- get for dropdown
    $.ajax( {
        type: "GET",
        url: "SkeletonController",
        data: {
            // pass here what you need to pass
            action: "get",
        },
        success:  (response) => {
            response = JSON.parse(response);
            let tableFilter = $('#table-filter');
            insertDataTable(tableFilter, headersEntities, response);
            section.css("display", "block");
        }
    }).fail(console.error);
}

function filter(){
    event.preventDefault();
    let filter = $('#filter-input').val();

    $.ajax( {
        type: "GET",
        url: "SkeletonController",
        data: {
            // pass here what you need to pass
            action: "filter",
            name: filter,
        },
        success:  (response) => {
            response = JSON.parse(response);
            let tableFilter = $('#table-filter');
            insertDataTable(tableFilter, headersEntities, response);

        }
    }).fail(console.error);
}

$( document ).ready(function() {
    usernameCurrent = readCookie("username");


})