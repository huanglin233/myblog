import {axiosIns} from '../utils/request'

export function queryTagById(id) {
    return axiosIns({
        url    : '/tags/' + id,
        method : 'get'
    })
}

export function queryTagList(pageNum, pageSize) {
    return axiosIns({
        url    : '/tags',
        method : 'get',
        params : {
            pageNum: pageNum,
            pageSize: pageSize
        }
    })
}

export function addTag(tag) {
    return axiosIns({
        url    : '/admin/tag/add',
        method : 'post',
        data   : tag
    })
}

export function updateTag(tag) {
    return axiosIns({
        url    : '/admin/tag/update',
        method : 'put',
        data   : tag
    })
}

export function deleteTagById(id) {
    return axiosIns({
        url    : '/admin/tag/delete/' + id,
        method : 'delete'
    })
}