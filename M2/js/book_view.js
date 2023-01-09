import {
    getParameterByName,
    setTextNode
} from './dom_utils.js';
import {getBackendUrl} from './configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayBook();
});

function fetchAndDisplayBook() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayBook(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/books/' + getParameterByName('book'), true);
    xhttp.send();
}

function displayBook(book) {
    setTextNode('title', book.title);
    setTextNode('book_title', book.title);
    setTextNode('author', book.author);
    setTextNode('price', book.price);
    }
