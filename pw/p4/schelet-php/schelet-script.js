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
    //event.preventDefault();
    let section = $('#get-section');
    let table = $('#table');
    let tablePaginated = $('#table-paginated');
    let tableFilter = $('#table-filter');
    let list=$('#list');

    $.ajax( {
        type: "GET",
        url: "backend/get.php",
        data: {
            // pass here what you need to pass
        },
        success:  (response) => {
            response = JSON.parse(response);

            if(response.error){
                alert(response.error);
            } else {
                section.css("display", "block");
                insertDataTable(table, headersEntities, response);
                insertDataPaginated(tablePaginated, headersEntities, response);
                insertDataTable(tableFilter, headersEntities, response);
                insertDataList(list, headersEntities, response);
            }
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
    if(name === "" || date==""){
        alert("Wrong input!");
        return;
    }

    $.ajax( {
        type: "POST",
        url: "backend/add.php",
        data: {
            // pass here what you need to pass
            name: name,
            date: date
        },
        success:  (response) => {
            response = JSON.parse(response);
            alert(response.message);
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
        url: "backend/delete.php",
        data: {
            // pass here what you need to pass
            id: id,
        },
        success:  (response) => {
            response = JSON.parse(response);
            alert(response.message);
        }
    }).fail(console.error);
}

/**
 * Update entities
 */
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
        url: "backend/update.php",
        data: {
            // pass here what you need to pass
            id: id,
            name: name,
            date: date
        },
        success:  (response) => {
            alert(response.message);
        }
    }).fail(console.error);
}


/**
 * Filter entities
 */
function filter(){
    event.preventDefault();
    let filter = $('#filter-input').val();

    $.ajax( {
        type: "GET",
        url: "backend/filter.php",
        data: {
            // pass here what you need to pass
            name: filter,
        },
        success:  (response) => {
            response = JSON.parse(response);
            if(response.error){
                alert(response.error);
            } else {
                let tableFilter = $('#table-filter');
                insertDataTable(tableFilter, headersEntities, response);
            }
        }
    }).fail(console.error);
}

$( document ).ready(function() {
    usernameCurrent = readCookie("username");

    // -- populate tables
    get();
    // -- get for dropdown
    $.ajax( {
        type: "GET",
        url: "backend/get.php",
        data: {
            // pass here what you need to pass
        },
        success:  (response) => {
            response = JSON.parse(response);
            let dropdownDelete = $('#dropdown');
            let dropdownUpdate = $('#dropdown2');
            populateDropdown(dropdownDelete, response);
            populateDropdown(dropdownUpdate, response);
        }
    }).fail(console.error);
})