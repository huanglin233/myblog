import { axiosIns } from '../utils/request'

/**
 * 查询笔记列表
 * @param {*} params 
 * @returns 
 */
export function queryList(params) {
    return axiosIns({
        url: '/notes/queryList',
        method: 'get',
        params: params
    })
}

export function queryById(id) {
    return axiosIns({
        url: '/notes/query/' + id,
        method: 'get'
    })
}

/**
 * 添加笔记
 * @param {*} data 
 * @returns 
 */
export function add(data) {
    return axiosIns({
        url: '/admin/notes/add',
        method: 'post',
        data: data
    })
}

/**
 * 更新笔记
 * @param {*} data 
 * @returns 
 */
export function update(data) {
    return axiosIns({
        url: '/admin/notes/update',
        method: 'put',
        data: data
    })
}

/**
 * 删除笔记
 * @param {笔记id} id 
 * @returns 
 */
export function del(id) {
    return axiosIns({
        url: '/admin/notes/del/' + id,
        method: 'delete'
    })
}