import { axiosIns } from '../utils/request'

export function queryById(id) {
    return axiosIns({
        url: '/blog/' + id,
        method: 'get'
    })
}

export function queryByIdAndConvert(id) {
    return axiosIns({
        url: '/blog/convert/' + id,
        method: 'get'
    })
}

export function queryList(pageNum, pageSize) {
    return axiosIns({
        url: '/blogs/' + pageNum + '/' + pageSize,
        method: 'get'
    })
}

export function queryRecommentBlogTop(num) {
    return axiosIns({
        url: '/blogs/recommentBlogTop/' + num,
        method: 'get'
    })
}

export function queryByBlogQuery(pageNum, pageSize, blogQuery) {
    return axiosIns({
        url: '/blogs/search/' + pageNum + '/' + pageSize,
        method: 'post',
        data: blogQuery
    })
}

export function queryArchives() {
    return axiosIns({
        url: '/blogs/archives',
        method: 'get',
    })
}

export function addBlog(blog) {
    return axiosIns({
        url: '/admin/blog/add',
        method: 'post',
        data: blog
    })
}

export function updateBlog(blog) {
    return axiosIns({
        url: '/admin/blog/update',
        method: 'put',
        data: blog
    })
}

export function deleteBlogById(id) {
    return axiosIns({
        url: '/admin/blog/delete/' + id,
        method: 'delete'
    })
}