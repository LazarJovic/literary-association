import React from 'react'
import { useSelector } from 'react-redux'
import { ListGroup, Table } from 'react-bootstrap'
// import { getBooks } from '../reducers/bookReducer'
import BookListItem from './BookListItem'
import SearchForm from './SearchForm'
import SearchBookListItem from './SearchBookListItem'
import Paginator from './Paginator'
import { h2Style } from '../styles/generalStyles'

const BookList = ({ search }) => {

    const books = useSelector(state => state.books.list)

    if (!search) {
        return (
            <div>
                <h2 style={ h2Style }>Books</h2>
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Publisher</th>
                        <th>Year</th>
                        <th/>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        books.map(book =>
                            <BookListItem key={book.id} id={book.id} ISBN={book.isbn} publisher={book.publisher} title={book.title}
                                          year={book.year}/>)
                    }
                    </tbody>
                </Table>
            </div>
        )
    } else {
        return (
            <div>
                <h2 style={ h2Style }>Books</h2>
                <SearchForm/>
                <ListGroup style={{ marginBottom:'1%' }}>
                    {
                        books.map(b => <SearchBookListItem key={b.id} {...b}/>)
                    }
                </ListGroup>
                <Paginator/>
            </div>
        )
    }

}

export default BookList
