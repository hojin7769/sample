import axios from "axios";

const instance = axios.create({
  headers: { "Content-Type": "application/json" },
  baseURL: "http://localhost:8090/api/",
});

async function callUrl(url, userData) {
  return await instance.post(url, userData);
}

async function getAxios(url, userData) {
  return await instance.get(url, userData);
}

function deleteAxios(url, userData) {
  return instance.delete(url, userData);
}

function putAxios(url, userData) {
  return instance.delete(url, userData);
}
export { callUrl, getAxios, deleteAxios, putAxios };
