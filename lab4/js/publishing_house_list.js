import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from './dom_utils.js';
import {getBackendUrl} from './configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayPublishingHouses();
});

function fetchAndDisplayPublishingHouses() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPublishingHouses(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/publishing_houses', true);
    xhttp.send();
}

function displayPublishingHouses(publishing_houses) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    publishing_houses.publishingHouses.forEach(publishing_house => {
        tableBody.appendChild(createTableRow(publishing_house));
    })
}

function createTableRow(publishing_house) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(publishing_house));
    tr.appendChild(createLinkCell('view', '../publishing_house_view.html?publishing_house=' + publishing_house));
    tr.appendChild(createLinkCell('edit', '../publishing_house_edit.html?publishing_house=' + publishing_house));
    tr.appendChild(createButtonCell('delete', () => deletePublishingHouse(publishing_house)));
    return tr;
}

function deletePublishingHouse(publishing_house) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPublishingHouses();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/publishing_houses/' + publishing_house, true);
    xhttp.send();
}
