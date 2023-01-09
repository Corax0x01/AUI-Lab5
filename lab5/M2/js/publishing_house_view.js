import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from './dom_utils.js';
import {getBackendUrl} from './configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayPublishingHouse();
    fetchAndDisplayBooks();
    addBooks();
});

function fetchAndDisplayBooks() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayBooks(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/publishing_houses/' + getParameterByName('publishing_house') + '/books', true);
    xhttp.send();
}

function displayBooks(books) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    books.books.forEach(book => {
        tableBody.appendChild(createTableRow(book));
    })
}

function createTableRow(book) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(book.title));
    tr.appendChild(createLinkCell('edit', '../book_edit.html?publishing_house='
        + getParameterByName('publishing_house') + '&book=' + book.isbn));
    tr.appendChild(createLinkCell('view', '../book_view.html?book=' + book.isbn));
    tr.appendChild(createButtonCell('delete', () => deleteBook(book)));
    return tr;
}

function deleteBook(book) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayBooks();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/books/' + book.isbn, true);
    xhttp.send();
}

function fetchAndDisplayPublishingHouse() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPublishingHouse(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/publishing_houses/' + getParameterByName('publishing_house'), true);
    xhttp.send();
}

function addBooks() {
    let article = document.getElementById('add_book');
    article.appendChild(createLinkCell('Add book', '../book_post.html?publishing_house=' + getParameterByName('publishing_house')))
}

function displayPublishingHouse(publishing_house) {
    setTextNode('name', publishing_house.name);
    setTextNode('publishing_house_name', publishing_house.name);
    setTextNode('address', publishing_house.address);
    setTextNode('phone', publishing_house.phoneNumber);
    setTextNode('email', publishing_house.email);}
