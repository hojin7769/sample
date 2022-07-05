import axios from 'axios';

const instance = axios.create({
	headers: { 'Content-Type': 'application/json' },
	baseURL: 'http://localhost:8090/menu/',
});

function callUrl(url, userData) {
	return instance.post(url, userData);
}

function getAxios(url, userData) {
	return instance.get(url, userData);
}

function deleteAxios(url, userData) {
	return instance.delete(url, userData);
}

function putAxios(url, userData) {
	return instance.delete(url, userData);
}
export { callUrl, getAxios, deleteAxios, putAxios };
