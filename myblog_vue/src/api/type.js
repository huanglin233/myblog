import {axiosIns} from '../utils/request'

export function queryTypeById(id) {
    return axiosIns({
        url    : '/types/' + id,
        method : 'get'
    })
}

export function queryTypeList(pageNum, pageSize) {
    return axiosIns({
        url    : '/types',
        method : 'get',
        params : {
            pageNum : pageNum,
            pageSize : pageSize
        }
    })
}

export function addType(type) {
    return axiosIns({
        url    : '/admin/type/add',
        method : 'post',
        data   : type
    })
}

export function updateType(type) {
    return axiosIns({
        url    : '/admin/type/update',
        method : 'put',
        data   : type
    })
}

export function deleteTypeById(id) {
    return axiosIns({
        url    : '/admin/type/delete/' + id,
        method : 'delete'
    })
}