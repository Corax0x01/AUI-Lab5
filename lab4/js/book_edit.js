import {getParameterByName} from './dom_utils.js';
import {getBackendUrl} from './configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayBook();
});

function fetchAndDisplayBook() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/books/'
        + getParameterByName('book'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayBook();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/books/'
        + getParameterByName('book'), true);

    const request = {
        'title': document.getElementById('title').value,
        'author': document.getElementById('author').value,
        'price': document.getElementById('price').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    window.location.href = "./../publishing_house_view.html?publishing_house=" + getParameterByName('publishing_house');
}
