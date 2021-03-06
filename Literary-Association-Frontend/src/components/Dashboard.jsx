import React, { useEffect } from 'react'
import { Redirect, useHistory } from 'react-router-dom'
import TaskList from './TaskList'
import { useDispatch, useSelector } from 'react-redux'
import Navbar from './Navbar'
import { Spinner } from 'react-bootstrap'
import { restore_login } from '../reducers/userReducer'
import Task from './Task'
import BookList from './BookList'
import { GuardProvider, GuardedRoute } from 'react-router-guards'
import Book from './Book'
import MyBooks from './MyBooks'
import Membership from './MembershipView'
import RetailerRegister from './RetailerRegister'

const Dashboard = () => {
    const history = useHistory()
    const dispatch = useDispatch()

    const username = useSelector(state => state.user.subject)
    const role = useSelector(state => state.user.role)

    useEffect(() => {
        if (!username || !role) {
            if (!window.localStorage.getItem('token')
                || !window.localStorage.getItem('subject')
                || !window.localStorage.getItem('role')) {
                history.push('/login')
            } else {
                dispatch(restore_login()).then()
            }
        }
    }, [])

    if (!username || !role) {
        return (
            <Spinner className='d-flex flex-column align-items-center' animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner>
        )
    }

    const requireRole = (to, from, next) => {
        if (to.meta.roles) {
            if (to.meta.roles.includes(role)) {
                next()
            }
            next.redirect(from)
        } else {
            next()
        }

    }

    return (
        <div>
            <Navbar role={role}/>
            <GuardProvider guards={[requireRole]}>
                <GuardedRoute path='/dashboard/tasks/:id'
                              meta={{ roles: ['WRITER', 'READER', 'BOARD_MEMBER', 'EDITOR', 'LECTOR'] }}>
                    <Task/>
                </GuardedRoute>
                <GuardedRoute exact path='/dashboard/tasks'
                              meta={{ roles: ['WRITER', 'READER', 'BOARD_MEMBER', 'EDITOR', 'LECTOR'] }}>
                    <TaskList username={username}/>
                </GuardedRoute>
                <GuardedRoute path='/dashboard/books/:id' meta={{ roles: ['READER'] }}>
                    <Book/>
                </GuardedRoute>
                <GuardedRoute exact path='/dashboard/books' meta={{ roles: ['READER'] }}>
                    <BookList search={true}/>
                </GuardedRoute>
                <GuardedRoute path='/dashboard/membership' meta={{ roles: ['WRITER'] }}>
                    <Membership/>
                </GuardedRoute>
                <GuardedRoute path='/dashboard/register-retailer' meta={{ roles: ['BOARD_MEMBER'] }}>
                    <RetailerRegister/>
                </GuardedRoute>
                <GuardedRoute path='/dashboard/my-books' meta={{ roles: ['WRITER', 'READER'] }}>
                    <MyBooks/>
                </GuardedRoute>
                <GuardedRoute exact path='/dashboard'
                              meta={{ roles: ['WRITER', 'READER', 'BOARD_MEMBER', 'EDITOR', 'LECTOR'] }}>
                    <Redirect to='/dashboard/tasks'/>
                </GuardedRoute>
            </GuardProvider>
        </div>
    )
}

export default Dashboard
