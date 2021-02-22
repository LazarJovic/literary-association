import React from 'react'
import { Pagination } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import { setPage } from '../reducers/paginationReducer'

const Paginator = () => {
    const totalPages = useSelector(state => state.pagination.totalPages)
    const page = useSelector(state => state.pagination.page)
    const dispatch = useDispatch()

    if (totalPages === 0) {
        return null
    }

    const changePage = async (page) => await dispatch(setPage(page))

    const change = async (number) => {
        let next = page + number < 0 ? 0 : page + number
        next = next >= totalPages ? totalPages - 1 : next
        await changePage(next)
    }

    const items = []
    for (let i = 1; i <= totalPages; i++) {
        items.push(
            <Pagination.Item onClick={() => changePage(i - 1)} key={i} active={page === i - 1}>
                {i}
            </Pagination.Item>
        )
    }

    return (
        <Pagination>
            <Pagination.Prev onClick={() => change(-1)}/>
            {items}
            <Pagination.Next onClick={() => change(1)}/>
        </Pagination>
    )
}

export default Paginator