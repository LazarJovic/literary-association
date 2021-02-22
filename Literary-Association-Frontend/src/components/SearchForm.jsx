import React, { useEffect, useState } from 'react'
import { Accordion, Button, Card, Form as BootstrapForm } from 'react-bootstrap'
import { boolOperationButtonStyle, mainDivStyle, searchFieldDivStyle, submitButtonStyle } from '../styles/searchFormStyle'
import genreService from '../services/genreService'
import { useDispatch, useSelector } from 'react-redux'
import BoolOperationButton from './BoolOperationButton'
import { searchBooks } from '../reducers/bookReducer'

const SearchForm = () => {

    const [allBooks, setAllBooks] = useState(true)
    const [genres, setGenres] = useState([])
    const [params, setParams] = useState({
        title: {
            value: '',
            booleanOperator: 'AND'
        },
        writers:  {
            value: '',
            booleanOperator: 'AND'
        },
        genre:  {
            value: '',
            booleanOperator: 'AND'
        },
        text:  {
            value: '',
            booleanOperator: 'AND'
        },
    })

    const pageNum = useSelector(state => state.pagination.page)

    const dispatch = useDispatch()

    useEffect(() => {
            search()
    }, [pageNum])

    useEffect(() => {
        genreService.getGenres().then(res => setGenres(res))
        dispatch(searchBooks(createSearchQuery()))
    }, [])

    const checkIfPhraze = (value) => {
        return value.slice(0, 1) === "\"" && value.slice(-1) === "\""
    }

    const createSearchQuery = () => {
        const searchParams = []
        let isAll = true
        for (const param in params) {
            if (params[param]) {
                if (params[param]['value'] !== '') {
                    isAll = false
                }
                let data = {
                    name: param,
                    value: params[param]['value'].replace('"', ''),
                    isPhraze: checkIfPhraze(params[param]['value']),
                    booleanParam: params[param]['booleanOperator']
                }
                searchParams.push(data)
            }
        }

        setAllBooks(isAll)

        return {
            searchParams,
            pageNum,
            allBooks
        }
    }

    const search = () => {
        dispatch(searchBooks(createSearchQuery()))
    }

    const onSubmit = (e) => {
        e.preventDefault()
        search()
    }

    const setFieldValue = (target) => {
        const newParams = {
            ...params
        }
        newParams[target.name]['value'] = target.value
        setParams(newParams)
    }

    const setFieldOperator = (name, target) => {
        const newParams = {
            ...params
        }
        newParams[name]['booleanOperator'] = target.value
        setParams(newParams)
        console.log(name)
    }

    return(
        <div style={ mainDivStyle }>
            <Accordion defaultActiveKey="0">
                <Card>
                    <Card.Header>
                        <Accordion.Toggle as={Button} variant="link" eventKey="0">
                            Search available books
                        </Accordion.Toggle>
                    </Card.Header>
                    <Accordion.Collapse eventKey="0">
                        <Card.Body>
                            <BootstrapForm onSubmit={(e) => onSubmit(e)}>

                                <div style={ searchFieldDivStyle }>
                                    <BootstrapForm.Label>Title: </BootstrapForm.Label>
                                    <BootstrapForm.Control
                                        name='title'
                                        type='text'
                                        value={params['title']['value']}
                                        pattern='^[\p{L}]+([\s]{1,1}[\p{L}]+)*$|^"[\p{L}]+([\s]{1,1}[\p{L}]+)*"$'
                                        placeholder='Search by title'
                                        onChange={({ target }) => setFieldValue(target)}
                                        style={ boolOperationButtonStyle }/>
                                        <BoolOperationButton
                                            name='title'
                                            value={params['title']['booleanOperator']}
                                            setValue={setFieldOperator}
                                            style={boolOperationButtonStyle}/>
                                </div>
                                <div style={ searchFieldDivStyle }>
                                    <BootstrapForm.Label>Writers: </BootstrapForm.Label>
                                    <BootstrapForm.Control
                                        name='writers'
                                        type='text'
                                        value={params['writers']['value']}
                                        placeholder='Search by writers'
                                        onChange={({ target }) => setFieldValue(target)}/>
                                    <BoolOperationButton
                                        name='writers'
                                        value={params['writers']['booleanOperator']}
                                        setValue={setFieldOperator}/>
                                </div>
                                <div style={ searchFieldDivStyle }>
                                    <BootstrapForm.Label>Genres: </BootstrapForm.Label>
                                    <BootstrapForm.Control
                                        name='genre'
                                        as="select"
                                        onChange={({ target }) => setFieldValue(target)}
                                        value={params['genre']['value']}>

                                        <option value="">-</option>
                                        {
                                            genres.map(genre => (
                                                <option value={genre} key={genre}>{genre}</option>
                                            ))
                                        }

                                    </BootstrapForm.Control>
                                    <BoolOperationButton
                                        name='genre'
                                        value={params['genre']['booleanOperator']}
                                        setValue={setFieldOperator}/>
                                </div>
                                <div style={ searchFieldDivStyle }>
                                    <BootstrapForm.Label>Content: </BootstrapForm.Label>
                                    <BootstrapForm.Control
                                        name='text'
                                        type='text'
                                        value={params['text']['value']}
                                        placeholder='Search by book content'
                                        onChange={({ target }) => setFieldValue(target)}/>
                                    <BoolOperationButton
                                        name='text'
                                        value={params['text']['booleanOperator']}
                                        setValue={setFieldOperator}/>
                                </div>

                                <Button variant='primary' type='submit' style={ submitButtonStyle }>Search</Button>
                            </BootstrapForm>
                        </Card.Body>
                    </Accordion.Collapse>
                </Card>
            </Accordion>
        </div>
    )
}

export default SearchForm