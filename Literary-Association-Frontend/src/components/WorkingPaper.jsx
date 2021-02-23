import React from 'react'
import { Card } from 'react-bootstrap'

const WorkingPaper = ({ content }) => {
    return (
        <>
            <Card
                bg='secondary'
                text='white'
                style={{ width: '24rem' }}
                className="mb-2"
            >
                <Card.Header>Working paper</Card.Header>
                <Card.Body>
                    <Card.Title> {content.title} </Card.Title>
                    <Card.Text>
                        <li><b>Synopsis:</b> <p>{content.synopsis}</p></li>
                        <li><b>Genre:</b> {content.genre}</li>
                        <li><b>Author:</b> {content.author}</li>
                    </Card.Text>
                </Card.Body>
            </Card>
        </>
    )
}

export default WorkingPaper