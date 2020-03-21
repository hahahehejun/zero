import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/commodity',
    method: 'get',
    params
  })
}
