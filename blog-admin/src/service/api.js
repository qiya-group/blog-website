import axios from 'axios'
import qs from 'qs'

const contentType = {
    'json': 'application/json;charset=UTF-8',
    'form': 'application/x-www-form-urlencoded;charset=UTF-8',
};

class Api {

    constructor(url) {
        axios.create({
            baseURL: url,
            timeout: 5000,
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token"),
            }
        });
    }

    async get(url, param) {
      return await axios.get(url, qs.stringify(param));
    }

    async post() {

    }
}

const RequestAPI = new Api('http://127.0.0.1:8001');

export default RequestAPI;
