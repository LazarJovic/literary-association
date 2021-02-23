import React from 'react'
import { Button, Col, ListGroup, Row } from 'react-bootstrap'
import { Link, useHistory } from 'react-router-dom'
import ReactHtmlParser from 'react-html-parser'

const SearchBookListItem = ({ id, title, isFree, basicInfo, text }) => {

    const history = useHistory()

    const bookDetails = () => history.push(`/dashboard/books/${id}`)

    return (
        <ListGroup.Item>
            <Col>
                <Row className="justify-content-between">
                    <Col>
                        <Link style={{ color: '#1a0dab' }} to={`/dashboard/books/${id}`}>{title}</Link>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <p style={{ color: '#6f2aa3', fontSize: 13 }}>{basicInfo}</p>
                    </Col>
                </Row>
                <Row style={{ marginTop:'-1%' }}>
                    <Col>
                        {ReactHtmlParser(text)}
                    </Col>
                </Row>
            </Col>
            <Col>
                <Button onClick={bookDetails} style={{ marginTop:'1%' }}>{isFree ? 'Download' : 'Purchase'}</Button>
            </Col>
        </ListGroup.Item>
    )
}

export default SearchBookListItem