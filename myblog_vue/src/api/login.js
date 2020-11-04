import {axiosIns} from '../utils/request'

export function login(user) {
    return axiosIns({
        url    : '/admin/doLogin',
        method : 'post',
        data   : {
            userName : user.userName,
            password : user.password
        }
    })
}

export function loginOut() {
    return axiosIns({
        url    : '/admin/loginOut',
        method : 'delete'
    })
}