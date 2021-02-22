import React from 'react'
import { Button, Col, ListGroup, Row } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import ReactHtmlParser from 'react-html-parser'

const SearchBookListItem = ({ id, title, isFree, basicInfo, text }) => {

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
                        <p style={{ color: '#006621', fontSize: 13 }}>{basicInfo}</p>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        {ReactHtmlParser(text)}
                    </Col>
                </Row>
            </Col>
            <Col>
                <Button>{isFree ? 'Download' : 'Purchase'}</Button>
            </Col>
        </ListGroup.Item>
    )
}

export default SearchBookListItem