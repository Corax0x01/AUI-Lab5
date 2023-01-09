import {getParameterByName} from './dom_utils.js';
import {getBackendUrl} from './configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => postInfoAction(event));

});

function postInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/books', true);

    const request = {
        'title': document.getElementById('title').value,
        'author': document.getElementById('author').value,
        'price': document.getElementById('price').value,
        'publishingHouse': getParameterByName('publishing_house')
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    window.location.href = './../publishing_house_view.html?publishing_house=' + getParameterByName('publishing_house');
}
