import {axiosIns} from '../utils/request';

export function queryRecommentByBlogId(blogId) {
    return axiosIns({
        url    : '/comments/' + blogId,
        method : 'get'
    })
}

export function addRecomment(comment) {
    return axiosIns({
        url    : '/comments/add',
        method : 'post',
        data   : comment
    })
}