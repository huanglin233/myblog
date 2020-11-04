import {axiosIns} from '../utils/request'

export function queryTagById(id) {
    return axiosIns({
        url    : '/tags/' + id,
        method : 'get'
    })
}

export function queryTagList(pageNum, pageSize) {
    return axiosIns({
        url    : '/tags/' + pageNum + '/' + pageSize,
        method : 'get'
    })
}