import axios from 'axios';

export class PhotoService {

    getImages() {
        return axios.get('assets/demo/data/photos.json').then(res => res.data.data);
    }

    getImages02() {
        return axios.get('assets/demo/data/dashboard-photos.json').then(res => res.data.data);
    }
}
