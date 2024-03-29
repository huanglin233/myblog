import axios from 'axios'
import router from './../router'
import {getToken, setToken, removeToken} from './token'

export var axiosIns = axios.create({
    baseURL: process.env.NODE_ENV !== 'production' ? 'http://localhost:8081' : '/blogApi',
    timeout: 100000,
    headers: {'Content-Type' : 'application/json;charset=utf-8', 'Access-Control-Allow-Origin' : '*'}
})

// request拦截器
axiosIns.interceptors.request.use(config => {
    console.log(process.env);
    let token = getToken();
    if(token != null && token != undefined) {
        config.headers['Authorization'] = token;
    }

    return config;
}), error => {
    console.log(error);
}

// 响应拦截器
axiosIns.interceptors.response.use(res => {
    // 获取状态码
    const code = res.data.code || 200;
    if(code === 401 && router.history.current.path !== '/login') {
        router.push('/login');
    } else if(code === 404 && router.history.current.path !== 'blog404') {
        router.push('/blog404');
    } else if(code === 500 && router.history.current.path !== 'blog500') {
        router.push('/blog500');
    } else if(code === 503 && router.history.current.path !== 'error') {
        router.push('/error?msg=' + res.data.msg);
    } else if(code === 200) {
        let token = res.headers['authorization'];
        if(token != null && token != undefined) {
            setToken(token);
        }
    }

    return res.data;
});