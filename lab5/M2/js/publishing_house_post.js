import {getBackendUrl} from './configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => postInfoAction(event));

});

function postInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/publishing_houses', true);

    const request = {
        'name': document.getElementById('name').value,
        'address': document.getElementById('address').value,
        'phoneNumber': document.getElementById('phone').value,
        'email': document.getElementById('email').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    window.location.href = "../publishing_house_list.html";
}
