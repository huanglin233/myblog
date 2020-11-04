import {axiosIns} from '../utils/request'

export function queryTypeById(id) {
    return axiosIns({
        url    : '/types/' + id,
        method : 'get'
    })
}

export function queryTypeList(pageNum, pageSize) {
    return axiosIns({
        url    : '/types/' + pageNum + '/' + pageSize,
        method : 'get'
    })
}