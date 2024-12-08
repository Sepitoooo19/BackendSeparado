import httpClient from '../http-common';


const getAll = () => {
    return httpClient.get(`user`);
}

const create = data => {
    return httpClient.post(`user/new`, data);
}

const get = id => {
    return httpClient.get(`user/client_id/${id}`);
}

const updateById = (id, data) => {
    return httpClient.put(`user/${id}`, data);
}

const deleteById = id => {
    return httpClient.delete(`user/${clientId}`);
}

export default { getAll, create, get, updateById, deleteById };